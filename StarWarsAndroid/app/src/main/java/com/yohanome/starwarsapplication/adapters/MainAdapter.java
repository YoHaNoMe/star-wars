package com.yohanome.starwarsapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yohanome.starwarsapplication.R;
import com.yohanome.starwarsapplication.models.FilmResult;
import com.yohanome.starwarsapplication.utils.MainItemClickListener;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<FilmResult> filmsDataList;
    private MainItemClickListener itemClickListener;

    public MainAdapter(MainItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item_list_recycler_view, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        if(filmsDataList == null)
            return;
        FilmResult filmResult = filmsDataList.get(position);
        holder.filmTitleTextView.setText(filmResult.getTitle());
        holder.itemView.setOnClickListener(v-> itemClickListener.onItemClicked(position));
    }

    @Override
    public int getItemCount() {
        if(filmsDataList == null)
            return 0;
        return filmsDataList.size();
    }

    public void setFilmsDataList(List<FilmResult> filmsDataList){
        this.filmsDataList = filmsDataList;
        notifyDataSetChanged();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder{
        TextView filmTitleTextView;

        MainViewHolder(View itemView){
            super(itemView);
            filmTitleTextView = itemView.findViewById(R.id.default_item_list_text_view);
        }
    }
}
