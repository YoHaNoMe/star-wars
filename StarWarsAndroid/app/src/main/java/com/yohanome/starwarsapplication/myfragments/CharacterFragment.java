package com.yohanome.starwarsapplication.myfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.yohanome.starwarsapplication.MainActivity;
import com.yohanome.starwarsapplication.R;
import com.yohanome.starwarsapplication.adapters.CharacterAdapter;
import com.yohanome.starwarsapplication.models.CharacterModel;
import com.yohanome.starwarsapplication.models.FilmResult;
import com.yohanome.starwarsapplication.utils.MainItemClickListener;
import com.yohanome.starwarsapplication.viewmodels.StarWarsViewModel;

import java.util.List;
import java.util.Objects;


public class CharacterFragment extends Fragment implements MainItemClickListener {

    private NavController navController;
    private List<CharacterModel> characterModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Define views
        RecyclerView recyclerView = view.findViewById(R.id.character_fragment_recycler_view);
        ProgressBar progressBar = view.findViewById(R.id.fragment_character_progress_bar);
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        // Get Arguments
        int position = CharacterFragmentArgs.fromBundle(getArguments()).getPosition();
        String filmTitle = CharacterFragmentArgs.fromBundle(getArguments()).getFilmTitle();

        // Set actionBar title to filmTitle
        ((MainActivity) getActivity()).setActionBarTitle(filmTitle);

        // init viewModel
        StarWarsViewModel starWarsViewModel = new ViewModelProvider(requireActivity()).get(StarWarsViewModel.class);

        // init navController
        navController = Navigation.findNavController(view);

        // init recyclerView
        CharacterAdapter adapter = new CharacterAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        // init listeners
        starWarsViewModel.getAllFilms().observe(getViewLifecycleOwner(), filmResults -> {
            if(filmResults == null)
                return;

            FilmResult filmResult = filmResults.get(position);

            starWarsViewModel.getCharacters(position, filmResult.getCharacters()).observe(getViewLifecycleOwner(), characterModels -> {
                if(characterModels == null)
                    return;

                this.characterModels = characterModels;
                progressBar.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.setCharacters(characterModels);
            });
        });
    }

    @Override
    public void onItemClicked(int position) {
        navController.navigate(CharacterFragmentDirections
                .actionCharacterFragmentToCharacterDetailsFragment(characterModels.get(position))
        );
    }
}