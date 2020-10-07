package com.yohanome.starwarsapplication.utils;

import com.yohanome.starwarsapplication.models.CharacterModel;
import com.yohanome.starwarsapplication.models.FilmModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StarWarsAPI {

    @GET(NetworkUtils.FILMS_ENDPOINT)
    Call<FilmModel> getFilms();

    @GET(NetworkUtils.PEOPLE_ENDPOINT + "{character_id}")
    Observable<CharacterModel> getCharacter(@Path("character_id") String character_id);

}
