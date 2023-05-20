package com.example.anismart;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anismart.Model.SearchModel;
import com.example.anismart.adapter.SearchAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<SearchModel> searchModelArrayList;
    SearchAdapter searchAdapter;
    String[] headings;
    String[] imageTitleUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recyclerViewSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        searchModelArrayList = new ArrayList<SearchModel>();



        headings = new String[]{
          "Tokyo Ghoul", "Boruto", "Black Clover", "Pink Time", "Konosuba", "Onepunchman", "SAO", "Stray Dogs", "Horimiya",
                "Reincarnation Of The Unemployed", "Ranking of Kings", "Platinum End", "One Piece", "Attack of Titan", "Demon Slayer",
                "My Dress-Up Darling", "Arifureta", "World's End Harem", "The Rising of the Shield", "Kaguya-sama: Love is War",
                "SPY x FAMILY", "5-Toubun no Hanayome Movie", "Death Note", "My Hero Academia"
        };

        imageTitleUrls = new String[]{
                "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/bannerView%2Ftokyo_ghoul_1.jpg?alt=media&token=fd6c45f6-0d78-45cb-a2b9-f5552325027e",
                "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/bannerView%2Fboruto_1.jpg?alt=media&token=55bf0cf2-c1bf-485d-9efc-b9fe5db39ff0",
                "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/bannerView%2Fblack_clover_1.jpg?alt=media&token=fc54af7e-3b67-4333-b7c1-2fcb8b716572",
                "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/bannerView%2Fpink_time_1.png?alt=media&token=ca4f748e-edc5-4df0-bc85-8722b2cd8812",
                "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/animeBanner%2Fkonosuba.jpg?alt=media&token=df8ad4ea-9139-4f5d-ac28-1756e82741ab",
                "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/animeBanner%2Fonepunchman.png?alt=media&token=59f94871-1c11-4bc3-b3f5-e4bbe12495f4",
                "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/animeBanner%2Fsao.png?alt=media&token=8a268a11-1996-4357-9848-055077d2a8f2",
                "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/animeBanner%2Fstray_dogs.jpg?alt=media&token=e60f6fea-c56a-4094-90f1-a7a7426ad3ca",
                "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/mangaBanner%2Fhorimiya.jpg?alt=media&token=ac8fd17e-6dcb-4f2c-b66f-523fcf8cc058",
                "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/mangaBanner%2Funemployed.png?alt=media&token=66cf252a-9ff1-42f5-83af-a3706ab16318",
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx113717-WGzAyw27y9sI.jpg",
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx127401-fVKVbuIE5W5Q.jpg",
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx21-tXMN3Y20PIL9.jpg",
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx131681-ODIRpBIbR5Eu.jpg",
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx142329-Oa6NT07c5KOn.jpg",
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx132405-Iy6Lze5SOme8.jpg",
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx112323-C6nlP84x8jH8.png",
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx118465-EGB0OajA5rei.jpg",
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx111321-joYie2DidTSs.jpg",
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx125367-ihXoySwIRfIM.jpg",
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx140960-Yl5M3AiLZAMq.png",
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx131520-kCOY9XBAbMcX.png",
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx1535-lawCwhzhi96X.jpg",
                "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx21459-oZMZ7JwS5Sxq.jpg"
        };

        getData();




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottm_navigation);
        bottomNavigationView.setSelectedItemId(R.id.search);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , InsideContent.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.bookshelf:
                        startActivity(new Intent(getApplicationContext()
                                , Bookshelf.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.me:
                        startActivity(new Intent(getApplicationContext()
                                , Me.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

    private void getData() {
        for(int i = 0; i < headings.length; i++) {
            SearchModel searchModel = new SearchModel(headings[i], imageTitleUrls[i]);
            searchModelArrayList.add(searchModel);
        }

        searchAdapter = new SearchAdapter(this, searchModelArrayList);
        recyclerView.setAdapter(searchAdapter);

        searchAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search your anime here!");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                searchAdapter.getFilter().filter(s);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}