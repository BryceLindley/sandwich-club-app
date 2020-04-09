package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        final String KEY_NAME = "name";
        final String KEY_MAIN_NAME = "mainName";
        final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
        final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
        final String KEY_DESCRIPTION = "description";
        final String KEY_INGREDIENTS = "ingredients";
        final String KEY_IMAGE = "image";

        Sandwich sandwich = null;

        try {

            //Serializing
            JSONObject parsingText = new JSONObject(json);

            // Convert Object Keys
            // Header
            JSONObject name = parsingText.getJSONObject(KEY_NAME);

            String mainName = name.getString(KEY_MAIN_NAME);
            String placeOfOrigin = parsingText.getString(KEY_PLACE_OF_ORIGIN);
            String description = parsingText.getString(KEY_DESCRIPTION);
            String image = parsingText.getString(KEY_IMAGE);


            // JSONArray alsoKnownAs to String
            JSONArray alsoKnownAs = name.getJSONArray(KEY_ALSO_KNOWN_AS);
            List<String> otherNames = new ArrayList<>();
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                otherNames.add(alsoKnownAs.getString(i));
            }

            // Converting JSONArray ingredients List to List[]
            JSONArray ingredients = parsingText.getJSONArray(KEY_INGREDIENTS);
            List<String> ingredientsNeeded = new ArrayList<>();
            for (int i = 0; i < ingredients.length(); i++) {
                ingredientsNeeded.add(ingredients.getString(i));
            }

            return new Sandwich(mainName, otherNames, placeOfOrigin, description, image, ingredientsNeeded);

        } catch (JSONException exc) {
            exc.printStackTrace();
        }

        return null;
    }
}