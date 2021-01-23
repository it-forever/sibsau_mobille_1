package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        //TextView details = findViewById(R.id.details);



        ImageView ingredientsIv = findViewById(R.id.image_iv);

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
        Sandwich sandwich = JSONConverter(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.get()
                .load(sandwich.getImage())
                .into(ingredientsIv);

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        setTitle(sandwich.getMainName());
        TextView origin_tv = findViewById(R.id.origin_tv);
        origin_tv.setText(sandwich.getPlaceOfOrigin());
        TextView description_tv = findViewById(R.id.description_tv);
        description_tv.setText(sandwich.getDescription());
        TextView also_known_tv = findViewById(R.id.also_known_tv);
        String str="";
        for(int i=0;i<sandwich.getAlsoKnownAs().size();i++){
            str+= sandwich.getAlsoKnownAs().get(i)+", ";
        }
        also_known_tv.setText(str);
        TextView ingredients_tv = findViewById(R.id.ingredients_tv);
        str="";
        for(int i=0;i<sandwich.getIngredients().size();i++){
            str+= sandwich.getIngredients().get(i)+", ";
        }
        ingredients_tv.setText(str);

    }

    private Sandwich JSONConverter(String json) {
        String mName, mPlase, mDescription, mImage;
        List<String> alsoList = new ArrayList<>(), ingredientsList = new ArrayList<>();
        JSONObject mJson = null;
        try {
            mJson = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject name = null;
        try {
            name = mJson.getJSONObject("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            mName = name.getString("mainName");
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            if (alsoKnownAsArray != null) {
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    alsoList.add(alsoKnownAsArray.getString(i));
                }
            }
            JSONArray ingredients = mJson.getJSONArray("ingredients");
            if (ingredients != null) {
                for (int i = 0; i < ingredients.length(); i++) {
                    ingredientsList.add(ingredients.getString(i));
                }
            }
            mPlase = mJson.getString("placeOfOrigin");
            mDescription = mJson.getString("description");
            mImage = mJson.getString("image");
            return new Sandwich(mName, alsoList, mPlase, mDescription, mImage, ingredientsList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        /*Log.i("GSON", sandwich.name.mainName);
        List<String> list = sandwich.name.alsoKnownAs;
        StringBuffer fullString = new StringBuffer(" ");
        if (list != null) {
            for (String string : list) fullString.append(string + ", ");
        }*/
    }
}
