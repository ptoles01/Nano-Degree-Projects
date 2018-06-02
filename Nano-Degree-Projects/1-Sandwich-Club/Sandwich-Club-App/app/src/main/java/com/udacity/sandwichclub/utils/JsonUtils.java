// JSON can generally express the same data using fewer characters than XML,
// thus saving the phone from having to transfer more data every time
// there is a query to a website. This makes JSON a natural choice
// for quick and efficient website querying (for websites which offer it).

package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    /* 1 */
    private static final String TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String jsonSandwichString)throws JSONException {

        // Initialize Sandwich object from json string in 3 steps:
        //      receive the json text as a string
        //      assign the string to a json array
        //      iterate over the jsonArray and extract values into
        //          ArrayLists

        //http://www.appsdeveloperblog.com/java-into-json-json-into-java-all-possible-examples/
       // Sandwich sandwich                 = new Sandwich();
        JSONObject jsonSandwich           = new JSONObject(jsonSandwichString);

        String mainNameString             = jsonSandwich.getJSONObject("name").getString("mainName");
        JSONArray alsoKnownAsArray        = jsonSandwich.getJSONObject("name").getJSONArray("alsoKnownAs");
        String placeOfOriginString        = jsonSandwich.getString("placeOfOrigin");
        String descriptionString          = jsonSandwich.getString("description");
        String imageString                = jsonSandwich.getString("image");
        JSONArray ingredientsArray        = jsonSandwich.getJSONArray("ingredients");

        List<String> alsoKnownAsArrayList = new ArrayList<>();
        List<String> ingredientsArrayList = new ArrayList<>();

     /* 2 */

        try {

                Log.d(jsonSandwichString, "Building sandwich. Line 49.");

    /* 3 */
                for(int i=0;i<alsoKnownAsArray.length();i++) {
                    alsoKnownAsArrayList.add(alsoKnownAsArray.getString(i));
                }


                for (int j = 0; j < ingredientsArray.length(); j++) {
                    ingredientsArrayList.add(ingredientsArray.getString(j));
                }


    /* 4 */
               // sandwich.setImage(imageString);
               // sandwich.setMainName(mainNameString);
               // sandwich.setDescription(descriptionString);
               // sandwich.setPlaceOfOrigin(placeOfOriginString);
               // sandwich.setAlsoKnownAs(alsoKnownAsArrayList);
               // sandwich.setIngredients(ingredientsArrayList);

               // Log.d(TAG, "Building sandwich. Line 70.");

              // if (sandwich.getMainName().isEmpty()) {
              //     sandwich.setMainName ("Unknown Sandwich");
               //}

               //Log.d(TAG, "Finished building " + sandwich.getMainName());

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    /* 5 */
        return new Sandwich(mainNameString, alsoKnownAsArrayList, placeOfOriginString,
                          descriptionString, imageString, ingredientsArrayList);
        //return (sandwich);

    }


}
