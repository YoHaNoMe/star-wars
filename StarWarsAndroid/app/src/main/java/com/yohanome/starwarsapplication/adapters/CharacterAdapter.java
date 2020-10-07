package com.yohanome.starwarsapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yohanome.starwarsapplication.R;
import com.yohanome.starwarsapplication.models.CharacterModel;
import com.yohanome.starwarsapplication.utils.MainItemClickListener;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<CharacterModel> characters;
    private MainItemClickListener itemClickListener;

    public CharacterAdapter(MainItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item_list_recycler_view, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        if(characters == null)
            return;

        CharacterModel character = characters.get(position);
        holder.characterNameTextView.setText(character.getName());
        holder.itemView.setOnClickListener(v->itemClickListener.onItemClicked(position));
    }

    @Override
    public int getItemCount() {
        if(characters == null)
            return 0;

        return characters.size();
    }

    public void setCharacters(List<CharacterModel> characters){
        this.characters = characters;
        notifyDataSetChanged();
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder{
        TextView characterNameTextView;

        CharacterViewHolder(View itemView){
            super(itemView);
            characterNameTextView = itemView.findViewById(R.id.default_item_list_text_view);
        }
    }
}
