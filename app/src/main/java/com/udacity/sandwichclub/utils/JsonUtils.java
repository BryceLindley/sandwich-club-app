package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static String PLACEOFORIGIN = "placeOfOrigin";
    private static String DESCRIPTION = "description";
    private static String IMAGE = "image";

    public static Sandwich parseSandwichJson(String json) {

        try {
            //Serializing
            JSONObject sandwichDetails = new JSONObject(json);

            // Convert Object Keys
            JSONObject name = sandwichDetails.getJSONObject("name");
            PLACEOFORIGIN = sandwichDetails.getString("placeOfOrigin");
            DESCRIPTION = sandwichDetails.getString("description");
            IMAGE = sandwichDetails.getString("image");

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

            return new Sandwich(mainName, secondName, PLACEOFORIGIN, DESCRIPTION, IMAGE, ingredientsNeeded);

        } catch (JSONException exc) {
            exc.printStackTrace();
        }
        return null;
    }
}