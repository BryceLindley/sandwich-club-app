package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    /*Only declare the fields like the following:*/
    private TextView alsoKnownAs, ingredients, origin, description;
    private ImageView ingredientsIv;

    /*An Activity is not fully initialized and ready to look up views until after setContentView(...) is called in onCreate()*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        findViewById();

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        setText(sandwich);
        /*Picasso supports both download and error placeholders as optional features.
        Placeholder drawable image will be shown until the actual image is loaded.
        So if the user has a slow network then this placeholder image will be shown
        instead of blank ImageView.
        if there is any error then error placeholder image will be shown*/
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);
    }

    public void findViewById() {
        alsoKnownAs = findViewById(R.id.also_known_tv);
        ingredients = findViewById(R.id.ingredients_tv);
        origin = findViewById(R.id.origin_tv);
        description = findViewById(R.id.description_tv);
        ingredientsIv = findViewById(R.id.image_iv);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    public void setText(Sandwich sandwich) {

        origin.setText(sandwich.getPlaceOfOrigin());
        description.setText(sandwich.getDescription());
        setTitle(sandwich.getMainName());
        // DELISTED List<String> ingedientList = sandwich.getIngredients();
        // Use TextUtils.join() method instead of showing the list in square brackets [].
        ingredients.setText(TextUtils.join("\n",sandwich.getIngredients()));

        //List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();
        alsoKnownAs.setText(TextUtils.join("\n",sandwich.getAlsoKnownAs()));
    }
}
