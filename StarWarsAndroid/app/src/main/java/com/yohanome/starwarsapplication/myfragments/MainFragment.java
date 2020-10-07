package com.yohanome.starwarsapplication.myfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.yohanome.starwarsapplication.MainActivity;
import com.yohanome.starwarsapplication.R;
import com.yohanome.starwarsapplication.adapters.MainAdapter;
import com.yohanome.starwarsapplication.models.FilmResult;
import com.yohanome.starwarsapplication.utils.MainItemClickListener;
import com.yohanome.starwarsapplication.viewmodels.StarWarsViewModel;

import java.util.List;


public class MainFragment extends Fragment implements MainItemClickListener {

    private NavController navController;
    private List<FilmResult> filmResults;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Define views
        RecyclerView recyclerView = view.findViewById(R.id.fragment_main_recycler_view);
        ProgressBar progressBar = view.findViewById(R.id.fragment_main_progress_bar);
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        // Set actionBar title to filmTitle
        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.app_name));

        // init ViewModel
        StarWarsViewModel starWarsViewModel = new ViewModelProvider(requireActivity()).get(StarWarsViewModel.class);

        // init navController
        navController = Navigation.findNavController(view);

        // init recyclerView
        MainAdapter adapter = new MainAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        // init listeners
        starWarsViewModel.getAllFilms().observe(getViewLifecycleOwner(), filmResults -> {
            if (filmResults == null)
                return;

            this.filmResults = filmResults;
            progressBar.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.setFilmsDataList(filmResults);
        });
    }

    @Override
    public void onItemClicked(int position) {
        navController.navigate(MainFragmentDirections
                .actionMainFragmentToCharacterFragment(position)
                .setFilmTitle(filmResults.get(position).getTitle())
        );
    }
}