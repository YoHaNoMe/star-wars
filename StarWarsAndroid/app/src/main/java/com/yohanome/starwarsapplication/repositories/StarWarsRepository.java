package com.yohanome.starwarsapplication.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yohanome.starwarsapplication.models.CharacterModel;
import com.yohanome.starwarsapplication.models.FilmModel;
import com.yohanome.starwarsapplication.models.FilmResult;
import com.yohanome.starwarsapplication.utils.NetworkUtils;
import com.yohanome.starwarsapplication.utils.StarWarsAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class StarWarsRepository {
    private StarWarsAPI starWarsAPI;
    private MutableLiveData<List<CharacterModel>> charactersMutableLiveData = new MutableLiveData<>();
    public LiveData<List<CharacterModel>> getCharactersLiveData(){ return charactersMutableLiveData; }

    public StarWarsRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        starWarsAPI = retrofit.create(StarWarsAPI.class);
    }


    public LiveData<List<FilmResult>> getAllFilms() {
        final MutableLiveData<List<FilmResult>> filmResultMutableLiveData = new MutableLiveData<>();

        starWarsAPI.getFilms().enqueue(new Callback<FilmModel>() {
            @Override
            public void onResponse(Call<FilmModel> call, Response<FilmModel> response) {
                if (response.body() == null)
                    return;

                filmResultMutableLiveData.postValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<FilmModel> call, Throwable t) {
                Log.d("StarWarsRepository", t.getMessage());
                filmResultMutableLiveData.postValue(null);
            }
        });

        return filmResultMutableLiveData;
    }

    public CompositeDisposable getCharacters(List<String> charactersUrl) {
        List<Observable<CharacterModel>> requests = new ArrayList<>();
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        String index;
        for (String characterUrl : charactersUrl) {
            String[] splittedArray = characterUrl.split("/");
            index = splittedArray[splittedArray.length-1];
            requests.add(starWarsAPI.getCharacter(index).subscribeOn(Schedulers.io()));
        }

        Observable<List<CharacterModel>> observable =
                Observable.zip(
                        requests,
                        objects -> {
                            List<CharacterModel> characterModels = new ArrayList<>();
                            for (Object object : objects) {
                                CharacterModel characterModel = (CharacterModel) object;
                                characterModels.add(characterModel);
                            }
                            return characterModels;
                        });
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<List<CharacterModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<CharacterModel> characterModels) {
                        charactersMutableLiveData.postValue(characterModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("StarWarsRepository", "Error: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return compositeDisposable;
    }

}
