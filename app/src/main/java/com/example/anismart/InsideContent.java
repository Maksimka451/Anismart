package com.example.anismart;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.anismart.Model.AllCategory;
import com.example.anismart.Model.BannerAnime;
import com.example.anismart.Model.CategoryItem;
import com.example.anismart.adapter.BannerAnimePagerAdapter;
import com.example.anismart.adapter.ContentRecyclerAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class InsideContent extends AppCompatActivity {

    BannerAnimePagerAdapter bannerAnimePagerAdapter;
    TabLayout indicatorTab, categoryTab;
    ViewPager bannerAnimeViewPager;
    List<BannerAnime> homeBannerList;
    List<BannerAnime> animeBannerList;
    List<BannerAnime> mangaBannerList;
    List<AllCategory> allCategoryList1;
    List<AllCategory> allCategoryList3;
    Timer sliderTimer;
    private FirebaseAuth mAuth;

    ContentRecyclerAdapter contentRecyclerAdapter;
    RecyclerView contentRecycler;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_content);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottm_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext()
                        , Search.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
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


        indicatorTab = findViewById(R.id.tab_indicator);
        categoryTab = findViewById(R.id.tabLayout);

        Button sign_out;
        sign_out = (Button)findViewById(R.id.sign_out);

        Button google_sign_out;
        google_sign_out = (Button)findViewById(R.id.google_sign_out);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null) {

        }

        google_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOutGoogle();
                Intent intent = new Intent(InsideContent.this, MainActivity.class);
                startActivity(intent);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerAnime(1, "Tokyo Ghoul", "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/bannerView%2Ftokyo_ghoul_1.jpg?alt=media&token=fd6c45f6-0d78-45cb-a2b9-f5552325027e", ""));
        homeBannerList.add(new BannerAnime(2, "Boruto", "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/bannerView%2Fboruto_1.jpg?alt=media&token=55bf0cf2-c1bf-485d-9efc-b9fe5db39ff0", ""));
        homeBannerList.add(new BannerAnime(3, "Black Clover", "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/bannerView%2Fblack_clover_1.jpg?alt=media&token=fc54af7e-3b67-4333-b7c1-2fcb8b716572", ""));
        homeBannerList.add(new BannerAnime(4, "Pink Time", "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/bannerView%2Fpink_time_1.png?alt=media&token=ca4f748e-edc5-4df0-bc85-8722b2cd8812", ""));


        animeBannerList = new ArrayList<>();
        animeBannerList.add(new BannerAnime(1, "Konosuba", "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/animeBanner%2Fkonosuba.jpg?alt=media&token=df8ad4ea-9139-4f5d-ac28-1756e82741ab", ""));
        animeBannerList.add(new BannerAnime(2, "Onepunchman", "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/animeBanner%2Fonepunchman.png?alt=media&token=59f94871-1c11-4bc3-b3f5-e4bbe12495f4", ""));
        animeBannerList.add(new BannerAnime(3, "SAO", "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/animeBanner%2Fsao.png?alt=media&token=8a268a11-1996-4357-9848-055077d2a8f2", ""));
        animeBannerList.add(new BannerAnime(4, "Stray Dogs", "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/animeBanner%2Fstray_dogs.jpg?alt=media&token=e60f6fea-c56a-4094-90f1-a7a7426ad3ca", ""));


        mangaBannerList = new ArrayList<>();
        mangaBannerList.add(new BannerAnime(1, "Horimiya", "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/mangaBanner%2Fhorimiya.jpg?alt=media&token=ac8fd17e-6dcb-4f2c-b66f-523fcf8cc058", ""));
        mangaBannerList.add(new BannerAnime(2, "SAO", "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/mangaBanner%2FSword-Art-Online-2.png?alt=media&token=4264c84e-ed03-4343-a959-bc8c4beb070d", ""));
        mangaBannerList.add(new BannerAnime(3, "Tokyo Ghoul", "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/mangaBanner%2Ftokyo_ghoul.jpg?alt=media&token=d519e801-a5d8-44a7-9281-562ec80b3d87", ""));
        mangaBannerList.add(new BannerAnime(4, "Reincarnation Of The Unemployed", "https://firebasestorage.googleapis.com/v0/b/anismart-f2ec2.appspot.com/o/mangaBanner%2Funemployed.png?alt=media&token=66cf252a-9ff1-42f5-83af-a3706ab16318", ""));



        setBannerAnimePagerAdapter(homeBannerList);
        sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(), 4000, 6000);
        indicatorTab.setupWithViewPager(bannerAnimeViewPager, true);



        List<CategoryItem> homeCatListItem1 = new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1, "Ranking of Kings", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx113717-WGzAyw27y9sI.jpg", ""));
        homeCatListItem1.add(new CategoryItem(2, "Platinum End", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx127401-fVKVbuIE5W5Q.jpg", ""));
        homeCatListItem1.add(new CategoryItem(3, "One Piece", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx21-tXMN3Y20PIL9.jpg", ""));
        homeCatListItem1.add(new CategoryItem(4, "Attack of Titan", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx131681-ODIRpBIbR5Eu.jpg", ""));

        List<CategoryItem> homeCatListItem2 = new ArrayList<>();
        homeCatListItem2.add(new CategoryItem(1, "Demon Slayer", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx142329-Oa6NT07c5KOn.jpg", ""));
        homeCatListItem2.add(new CategoryItem(2, "My Dress-Up Darling", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx132405-Iy6Lze5SOme8.jpg", ""));
        homeCatListItem2.add(new CategoryItem(3, "Arifureta", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx112323-C6nlP84x8jH8.png", ""));
        homeCatListItem2.add(new CategoryItem(4, "World's End Harem", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx118465-EGB0OajA5rei.jpg", ""));

        List<CategoryItem> homeCatListItem3 = new ArrayList<>();
        homeCatListItem3.add(new CategoryItem(1, "The Rising of the Shield", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx111321-joYie2DidTSs.jpg", ""));
        homeCatListItem3.add(new CategoryItem(2, "Kaguya-sama: Love is War", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx125367-ihXoySwIRfIM.jpg", ""));
        homeCatListItem3.add(new CategoryItem(3, "SPY x FAMILY", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx140960-Yl5M3AiLZAMq.png", ""));
        homeCatListItem3.add(new CategoryItem(4, "5-Toubun no Hanayome Movie", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx131520-kCOY9XBAbMcX.png", ""));

        List<CategoryItem> homeCatListItem4 = new ArrayList<>();
        homeCatListItem4.add(new CategoryItem(1, "Attack on Titan", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx16498-m5ZMNtFioc7j.png", ""));
        homeCatListItem4.add(new CategoryItem(2, "Death Note", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx1535-lawCwhzhi96X.jpg", ""));
        homeCatListItem4.add(new CategoryItem(3, "Demon Slayer: Kimetsu no Yaiba", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx101922-PEn1CTc93blC.jpg", ""));
        homeCatListItem4.add(new CategoryItem(4, "My Hero Academia", "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx21459-oZMZ7JwS5Sxq.jpg", ""));


        allCategoryList1 = new ArrayList<>();
        allCategoryList1.add(new AllCategory(1, "Trending now", homeCatListItem1));
        allCategoryList1.add(new AllCategory(2, "Popular this season", homeCatListItem2));
        allCategoryList1.add(new AllCategory(3, "Upcoming next season", homeCatListItem3));
        allCategoryList1.add(new AllCategory(4, "All time popular", homeCatListItem4));

        List<CategoryItem> mangaCatListItem1 = new ArrayList<>();
        mangaCatListItem1.add(new CategoryItem(5,"One Piece", "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx30013-oT7YguhEK1TE.jpg", ""));
        mangaCatListItem1.add(new CategoryItem(6,"My Hero Academia", "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx85486-INqnYx8gL3eX.jpg", ""));
        mangaCatListItem1.add(new CategoryItem(7,"Oshi no Ko", "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx117195-r3kf8eF0xkDJ.png", ""));
        mangaCatListItem1.add(new CategoryItem(8,"Black Clover", "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/nx86123-yRZuDFrUEDGu.png", ""));

        List<CategoryItem> mangaCatListItem2 = new ArrayList<>();
        mangaCatListItem2.add(new CategoryItem(5,"Attack on Titan", "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx53390-1RsuABC34P9D.jpg", ""));
        mangaCatListItem2.add(new CategoryItem(6,"Chainsaw Man", "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx105778-82gwrvQV6OBc.png", ""));
        mangaCatListItem2.add(new CategoryItem(7,"Demon Slayer: Kimetsu no Yaiba", "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx87216-c9bSNVD10UuD.png", ""));
        mangaCatListItem2.add(new CategoryItem(8,"Tokyo Ghoul", "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx63327-zvK2l9DjCqK4.jpg", ""));

        List<CategoryItem> mangaCatListItem3 = new ArrayList<>();
        mangaCatListItem3.add(new CategoryItem(5,"Solo Leveling", "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx105398-b673Vt5ZSuz3.jpg", ""));
        mangaCatListItem3.add(new CategoryItem(6,"Tower of God", "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx85143-lW27BYY5zMqC.jpg", ""));
        mangaCatListItem3.add(new CategoryItem(7,"Bastard", "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/nx86964-r7S3IbJNr4SD.jpg", ""));
        mangaCatListItem3.add(new CategoryItem(8,"Omniscient Reader", "https://s4.anilist.co/file/anilistcdn/media/manga/cover/large/bx119257-KzlNZtgbRzxF.jpg", ""));


        allCategoryList3 = new ArrayList<>();
        allCategoryList3.add(new AllCategory(1, "Trending now", mangaCatListItem1));
        allCategoryList3.add(new AllCategory(2, "All time popular", mangaCatListItem2));
        allCategoryList3.add(new AllCategory(3, "Popular manhwa", mangaCatListItem3));


        setContentRecycler(allCategoryList1);

        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        setBannerAnimePagerAdapter(animeBannerList);
                        setContentRecycler(allCategoryList1);
                        return;
                    case 2:
                        setBannerAnimePagerAdapter(mangaBannerList);
                        setContentRecycler(allCategoryList3);
                        return;
                    default:
                        setBannerAnimePagerAdapter(homeBannerList);
                        setContentRecycler(allCategoryList1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mAuth = FirebaseAuth.getInstance();

    }

    private void signOutGoogle() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }


    private void signOut() {
        mAuth.signOut();
        Intent intent = new Intent(InsideContent.this, MainActivity.class);
        startActivity(intent);
    }

    private void setBannerAnimePagerAdapter(List<BannerAnime> bannerAnimeList) {

        bannerAnimeViewPager = findViewById(R.id.banner_viewPager);
        bannerAnimePagerAdapter = new BannerAnimePagerAdapter(this, bannerAnimeList);
        bannerAnimeViewPager.setAdapter(bannerAnimePagerAdapter);

    }

    class AutoSlider extends TimerTask{

        @Override
        public void run() {
            InsideContent.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (bannerAnimeViewPager.getCurrentItem() < homeBannerList.size() - 1) {
                        bannerAnimeViewPager.setCurrentItem(bannerAnimeViewPager.getCurrentItem() + 1);
                    } else if (bannerAnimeViewPager.getCurrentItem() % 3 == 0){
                        bannerAnimeViewPager.setCurrentItem(0);
                    }
                }
            });
        }

    }

    public void setContentRecycler(List<AllCategory> allCategoryList){

        contentRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        contentRecycler.setLayoutManager(layoutManager);
        contentRecyclerAdapter = new ContentRecyclerAdapter(this, allCategoryList);
        contentRecycler.setAdapter(contentRecyclerAdapter);

    }

}