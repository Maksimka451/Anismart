package com.example.anismart.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.anismart.Model.AnimeOrMangaDetails;
import com.example.anismart.Model.BannerAnime;
import com.example.anismart.R;

import java.util.List;

public class BannerAnimePagerAdapter extends PagerAdapter {

    Context context;
    List<BannerAnime> bannerAnimeList;

    public BannerAnimePagerAdapter(Context context, List<BannerAnime> bannerAnimeList) {
        this.context = context;
        this.bannerAnimeList = bannerAnimeList;
    }

    @Override
    public int getCount() {
        return bannerAnimeList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_anime_layout, null);

        ImageView bannerImage = view.findViewById(R.id.banner_image);

        Glide.with(context).load(bannerAnimeList.get(position).getImageUrl()).into(bannerImage);
        container.addView(view);

        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AnimeOrMangaDetails.class);
                i.putExtra("animeId", bannerAnimeList.get(position).getId());
                i.putExtra("animeName", bannerAnimeList.get(position).getAnimeName());
                i.putExtra("animeImageUrl", bannerAnimeList.get(position).getImageUrl());
                i.putExtra("animeFile", bannerAnimeList.get(position).getFileUrl());
                context.startActivity(i);
            }
        });

        return view;
    }
}
