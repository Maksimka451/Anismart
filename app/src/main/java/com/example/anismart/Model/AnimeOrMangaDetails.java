package com.example.anismart.Model;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.anismart.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AnimeOrMangaDetails extends AppCompatActivity {

    int aId;
    String aName;
    String aImage;
    String aFile;

    ImageView animeImage;

    AnimeData animeData;

    TextView description;
    TextView status;
    TextView format;
    TextView released;
    TextView source;
    TextView genre;

    String AnimeOrMangaData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_or_manga_details);


        animeImage = findViewById(R.id.animeImage);
        description = findViewById(R.id.description);
        status = findViewById(R.id.status_text);
        format = findViewById(R.id.format_text);
        released = findViewById(R.id.released_text);
        source = findViewById(R.id.source_text);
        genre = findViewById(R.id.genre_text);


        aId = getIntent().getIntExtra("animeId", 0);
        aName = getIntent().getStringExtra("animeName");
        aImage = getIntent().getStringExtra("animeImageUrl");
        aFile = getIntent().getStringExtra("animeFile");


        Glide.with(this).load(aImage).into(animeImage);

        if(aId > 4){
            AnimeOrMangaData = "MangaData";
        } else {
            AnimeOrMangaData = "AnimeData";
        }

        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();



        Rootref.child(AnimeOrMangaData).child(aName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                animeData = snapshot.getValue(AnimeData.class);
                if ( animeData.getDescription() == null || animeData.getFormat() == null || animeData.getReleased() == null || animeData.getSource() == null || animeData.getStatus() == null || animeData.getGenre() == null) {
                    Toast.makeText(AnimeOrMangaDetails.this, "Error" + aName, Toast.LENGTH_SHORT).show();
                } else {
                    description.setText(animeData.getDescription());
                    status.setText("STATUS\n" + animeData.getStatus());
                    source.setText("SOURCE\n" + animeData.getSource());
                    format.setText("FORMAT\n" + animeData.getFormat());
                    released.setText("RELEASED\n" + animeData.getReleased());
                    genre.setText("GENRE\n" + animeData.getGenre());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}