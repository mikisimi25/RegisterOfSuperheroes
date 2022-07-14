package com.example.registerofsuperheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.registerofsuperheroes.databinding.ActivityDetailBinding;
import com.example.registerofsuperheroes.databinding.ActivityMainBinding;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Bundle -> puede tener valores de diferentes tipos
        Bundle extras = getIntent().getExtras();
        Superhero superhero = extras.getParcelable(MainActivity.SUPERHERO_KEY);
        Bitmap bitmap = extras.getParcelable(MainActivity.BITMAP_KEY);

        if(bitmap != null) {
            binding.imageView.setImageBitmap(bitmap);
        }

        binding.setSuperhero(superhero);
    }
}