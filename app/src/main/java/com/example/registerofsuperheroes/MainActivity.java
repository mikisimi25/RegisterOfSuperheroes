package com.example.registerofsuperheroes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.registerofsuperheroes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String SUPERHERO_KEY = "superhero";
    public static final String BITMAP_KEY = "bitmap";
    public static final int PHOTO_REQUEST_CODE = 1000;
    private ActivityMainBinding binding;
    private Bitmap takenPhotoBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.saveButton.setOnClickListener( view -> {
            String superheroName = binding.heroNameEdit.getText().toString();
            String alterEgo = binding.alterEgoEdit.getText().toString();
            String bio = binding.editBio.getText().toString();
            float rating = binding.rating.getRating();

            Superhero superhero = new Superhero(superheroName, alterEgo, bio, rating);

            openDetailActivity(superhero);
        });

        binding.heroImage.setOnClickListener(v -> {
            openCamera();
        });

    }
    private void openCamera() {
        //Le decimos a android abrir la aplicación de la cámara
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //startActivityForResult -> para que nos devuelva un resultado de la activity
        startActivityForResult(cameraIntent, PHOTO_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( resultCode == Activity.RESULT_OK && requestCode == PHOTO_REQUEST_CODE) {
            if ( data != null ) {
                takenPhotoBitmap = data.getExtras().getParcelable("data");
                binding.heroImage.setImageBitmap(takenPhotoBitmap);
            } else {
                Toast.makeText( this, "Error taking photo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openDetailActivity(Superhero superhero) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(SUPERHERO_KEY, superhero);
        intent.putExtra(BITMAP_KEY, takenPhotoBitmap);

        startActivity(intent);
    }
}