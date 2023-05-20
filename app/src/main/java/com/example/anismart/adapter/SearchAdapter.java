package com.example.anismart.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.anismart.Model.AnimeOrMangaDetails;
import com.example.anismart.Model.SearchModel;
import com.example.anismart.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {


    Context context;
    ArrayList<SearchModel> searchModelArrayList;
    ArrayList<SearchModel> searchModelArrayListFull;

    public SearchAdapter(Context context, ArrayList<SearchModel> searchModelArrayList) {
        this.context = context;
        this.searchModelArrayListFull = searchModelArrayList;
        this.searchModelArrayList = new ArrayList<>(searchModelArrayListFull);
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_item_search, parent, false);

        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, @SuppressLint("RecyclerView") int position) {

        SearchModel searchModel = searchModelArrayList.get(position);
        holder.tvHeading.setText(searchModel.getHeading());
        Glide.with(context).load(searchModelArrayList.get(position).getImageUrl()).into(holder.titleImage);

        holder.titleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AnimeOrMangaDetails.class);
                i.putExtra("animeId", 1);
                i.putExtra("animeName", searchModelArrayList.get(position).getHeading());
                i.putExtra("animeImageUrl", searchModelArrayList.get(position).getImageUrl());
                i.putExtra("animeFile", searchModelArrayList.get(position).getFileUrl());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchModelArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    private final Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<SearchModel> filteredSearchList = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0) {
                filteredSearchList.addAll(searchModelArrayListFull);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (SearchModel searchModel: searchModelArrayListFull){
                    if(searchModel.getHeading().toLowerCase().contains(filterPattern))
                        filteredSearchList.add(searchModel);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredSearchList;
            results.count = filteredSearchList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            searchModelArrayList.clear();
            searchModelArrayList.addAll((ArrayList)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class SearchViewHolder extends RecyclerView.ViewHolder{

        TextView tvHeading;
        ShapeableImageView titleImage;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeading = itemView.findViewById(R.id.tvHeadingSearch);
            titleImage = itemView.findViewById(R.id.title_image_search);
        }
    }



}
