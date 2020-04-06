package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    //TODO JSON data is parsed correctly to a Sandwich object in JsonUtils
    //TODO JSON is parsed without using 3rd party libraries

    public static Sandwich parseSandwichJson(String json) {

        try {
            //Serializing
            JSONObject sandwichDetails = new JSONObject(json);

            // Convert Object Keys
            String placeOfOrigin = sandwichDetails.getString("placeOfOrigin");
            String description = sandwichDetails.getString("description");
            String image = sandwichDetails.getString("image");
            JSONObject name = sandwichDetails.getJSONObject("name");

            // Converting JSON to String
            String mainName = name.getString("mainName");
            JSONArray ingredients = sandwichDetails.getJSONArray("ingredients");
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");

            // JSON Array to String
            List<String> secondName = new ArrayList<>();
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                secondName.add(alsoKnownAs.getString(i));
            }

            List<String> ingredientsNeeded = new ArrayList<>();
            for (int i = 0; i < ingredients.length(); i++) {
                ingredientsNeeded.add(ingredients.getString(i));
            }

            return new Sandwich(mainName, secondName, placeOfOrigin, description, image, ingredientsNeeded);


        } catch (JSONException exc) {
            exc.printStackTrace();
        }
        return null;
    }
}