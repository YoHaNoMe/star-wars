package com.yohanome.starwarsapplication.myfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yohanome.starwarsapplication.MainActivity;
import com.yohanome.starwarsapplication.R;
import com.yohanome.starwarsapplication.models.CharacterModel;
import com.yohanome.starwarsapplication.viewmodels.StarWarsViewModel;

public class CharacterDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Define Views
        TextView characterNameTextView = view.findViewById(R.id.fragment_character_details_name_text_view);
        TextView characterHeightTextView = view.findViewById(R.id.fragment_character_details_height_text_view);
        TextView characterMassTextView = view.findViewById(R.id.fragment_character_details_mass_text_view);
        TextView characterHairColorTextView = view.findViewById(R.id.fragment_character_details_hair_color_text_view);
        TextView characterSkinColorTextView = view.findViewById(R.id.fragment_character_details_skin_color_text_view);
        TextView characterEyeColorTextView = view.findViewById(R.id.fragment_character_details_eye_color_text_view);
        TextView characterBirthYearTextView = view.findViewById(R.id.fragment_character_details_birth_year_text_view);
        TextView characterGenderTextView = view.findViewById(R.id.fragment_character_details_gender_text_view);
        TextView characterFilmsTextView = view.findViewById(R.id.fragment_character_details_films_text_view);

        // get Character
        CharacterModel characterModel = CharacterDetailsFragmentArgs.fromBundle(getArguments()).getCharacter();

        // init viewModel
        StarWarsViewModel starWarsViewModel = new ViewModelProvider(requireActivity()).get(StarWarsViewModel.class);

        // set ActionBar title to character name
        ((MainActivity) getActivity()).setActionBarTitle(characterModel.getName());

        characterNameTextView.setText(getString(R.string.character_name, characterModel.getName()));
        characterHeightTextView.setText(getString(R.string.character_height, characterModel.getHeight()));
        characterMassTextView.setText(getString(R.string.character_mass, characterModel.getMass()));
        characterHairColorTextView.setText(getString(R.string.character_hair_color, characterModel.getHairColor()));
        characterEyeColorTextView.setText(getString(R.string.character_eye_color, characterModel.getEyeColor()));
        characterSkinColorTextView.setText(getString(R.string.character_skin_color, characterModel.getSkinColor()));
        characterBirthYearTextView.setText(getString(R.string.character_birth_year, characterModel.getBirthYear()));
        characterGenderTextView.setText(getString(R.string.character_gender, characterModel.getGender()));

        starWarsViewModel.getAllFilms().observe(getViewLifecycleOwner(), filmResults -> {
            StringBuilder stringBuilder = new StringBuilder();
            for(String filmUrl: characterModel.getFilms()){
                String splittedArray[] = filmUrl.split("/");
                int index = Integer.parseInt(splittedArray[splittedArray.length-1])-1;
                stringBuilder.append(filmResults.get(index).getTitle() + ", ");
            }
            characterFilmsTextView.setText(getString(R.string.character_films, stringBuilder));
        });
    }
}