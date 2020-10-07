package com.yohanome.starwarsapplication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yohanome.starwarsapplication.models.CharacterModel;
import com.yohanome.starwarsapplication.models.FilmResult;
import com.yohanome.starwarsapplication.repositories.StarWarsRepository;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class StarWarsViewModel extends ViewModel {
    private LiveData<List<FilmResult>> filmResultListLiveData;
    private LiveData<List<CharacterModel>> charactersLiveData;
    private CompositeDisposable compositeDisposable;
    private StarWarsRepository starWarsRepository;
    private int oldIndex = -1;

    public StarWarsViewModel(){
        starWarsRepository = new StarWarsRepository();
    }

    public LiveData<List<FilmResult>> getAllFilms(){
        if(filmResultListLiveData == null){
            filmResultListLiveData = starWarsRepository.getAllFilms();
        }
        return filmResultListLiveData;
    }

    public LiveData<List<CharacterModel>> getCharacters(int currentIndex, List<String> charactersUrl){
        if(oldIndex != currentIndex){
            compositeDisposable = starWarsRepository.getCharacters(charactersUrl);
            charactersLiveData = starWarsRepository.getCharactersLiveData();
            oldIndex = currentIndex;
        }
        return charactersLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(compositeDisposable != null){
            compositeDisposable.dispose();
        }
    }
}
