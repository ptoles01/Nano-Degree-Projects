package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;


public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getCanonicalName();

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageIv = findViewById(R.id.image_iv);

        Intent intent = getIntent(); Log.d(TAG, "getIntent() line 30.");

        if (intent == null) {
            closeOnError();
            //return; Why not return here also?
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }   Log.d(TAG, "position != DEFAULT_POSITION) line 42.");

        try {

            String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
                // create an array of sandwich details to display in a listview

            String json = sandwiches[position];
                // pull the json details for the particular sandwich chosen

            Sandwich sandwich = JsonUtils.parseSandwichJson(json);
                // create a Sandwich object based on the given json details

            if (sandwich == null) {
                // Sandwich details unknown or unavailable
                closeOnError();
                return;
            }  Log.d(TAG, "position != null) line 59.");

            populateUI(sandwich);   Log.d(TAG, "sandwich populated) line 61.");

            Picasso.with(this)
                    .load(sandwich.getImage())
                    .into(imageIv);

            setTitle(sandwich.getMainName().toUpperCase());


        }catch (Exception e){

            Log.d(TAG,"Exception: " + e.getMessage());
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(@NonNull  Sandwich sandwich) {
        Log.d(TAG, "Displaying " + sandwich.getMainName() + " details.\n");

        setTextView((TextView) findViewById(R.id.description_tv), "description",  sandwich.getDescription());
        setTextView((TextView) findViewById(R.id.also_known_detail_tv),   "alsoKnownAs", removeBrackets(sandwich.getAlsoKnownAs().toString()) );
        setTextView((TextView) findViewById(R.id.ingredients_detail_tv),  "ingredients", removeBrackets(sandwich.getIngredients().toString()) );
        setTextView((TextView) findViewById(R.id.origin_tv), "placeOfOrigin",sandwich.getPlaceOfOrigin());

        Log.d(TAG, "Sandwich details ended.");

    }
    private void setTextView (TextView textView, String viewName, String textString){
        textView.setText(textString);
            // initialize the text view
        Log.d(TAG, viewName+ " loaded.");
            // write the log
    }

    private String removeBrackets (String bracketedInput){
        //https://stackoverflow.com/questions/4389480/print-array-without-brackets-and-commas
        return bracketedInput
                //.replace(",", "")  //remove the commas
                .replace("[", "")  //remove the leftmost bracket
                .replace("]", "")  //remove the rightmost bracket
                .trim();                           //remove trailing spaces


    }
}
