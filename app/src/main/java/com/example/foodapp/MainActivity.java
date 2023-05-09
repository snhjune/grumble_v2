package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    //GRUMBLE API key for google maps api (probably should store this in secret file)
    private static String MAPS_API_KEY = "AIzaSyCnGX2N1Ww991C17tB0Mkeb2WeH3ym8q0I";
    double latitude = 33.782531;
    double longitude = -118.110435;
    int radius = 5000; // 5 km

    private Button ratingSortButton;
    private Button distanceSortButton;
    private Button priceSortButton;

    private ArrayList<Restaurant> restaurantList = new ArrayList<>();
    private ArrayList<String> restaurantNames = new ArrayList<>();

    private ArrayList<String> spinList = new ArrayList<>();

    Button spin;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ...
        try {
            run();
            System.out.println(restaurantList.toString());
            priceSortButton = findViewById(R.id.price_sort);
            priceSortButton.setOnClickListener(view -> {
                System.out.println("size = " + restaurantList.size());
                System.out.println(restaurantList.toString());
                System.out.println("list size = " + restaurantNames.size());

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("myList", restaurantNames);
                startActivity(intent);
            });
        }catch (IOException e) {
            e.printStackTrace();
        }
        spin = findViewById(R.id.spin);
        spin.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, spinList.class);
            startActivity(i);
        });
    }
    void run() throws IOException {

        //create an okHTTP client to call the google maps nearby search api
        OkHttpClient httpClient = new OkHttpClient().newBuilder()
                .build();
        //calls local URL method and builds request to call the API
        Request request = new Request.Builder()
                .url(placesURL(latitude, longitude, radius))
                .build();
        //calls the API
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String httpResponse = response.body().string();

                try {
                    //parse through the Nearby Search API call result as a JSON object and store as a Restaurant
                    JSONObject jsonResponse = new JSONObject(httpResponse);
                    JSONArray jsonArray = (JSONArray) jsonResponse.get("results");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonOBJ = jsonArray.getJSONObject(i);
                        Restaurant restaurant = new Restaurant();

                        restaurant.setPlaceId(jsonOBJ.get("place_id").toString());

                        if(jsonOBJ.has("name"))
                        { restaurant.setName(jsonOBJ.get("name").toString()); }

                        if(jsonOBJ.has("rating"))
                        { restaurant.setRating(Float.parseFloat(jsonOBJ.get("rating").toString())); }

                        else
                        { restaurant.setRating(Float.parseFloat("0")); }

                        if(jsonOBJ.has("price_level"))
                        { restaurant.setPriceLevel(Integer.parseInt((jsonOBJ.get("price_level").toString()))); }

                        if(jsonOBJ.has("vicinity"))
                        { restaurant.setAddress(jsonOBJ.get("vicinity").toString()); }

                        if(jsonOBJ.has("geometry"))
                        {
                            restaurant.setLatitude(Double.parseDouble(jsonOBJ.getJSONObject("geometry").getJSONObject("location").get("lat").toString()));
                            restaurant.setLongitude(Double.parseDouble(jsonOBJ.getJSONObject("geometry").getJSONObject("location").get("lng").toString()));
                        }

                        if(jsonOBJ.has("photos"))
                        {
                            restaurant.setPhotoReference(jsonOBJ.getJSONArray("photos").getJSONObject(0).getString("photo_reference").toString());
                            // TODO: download the place photos using nearby place photo api and display
                        }

                        if(jsonOBJ.has("opening_hours"))
                        {
                            //restaurant.setOpen_now(jsonOBJ.getJSONObject("opening_hours").getBoolean("open_now"));
                        }

                        //add the restaurant to our list
                        restaurantList.add(restaurant);
                        restaurantNames.add(restaurant.getName());
                        for (String s : restaurantNames){
                            System.out.println("name = "+s);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                //cancel the API call if fails
                call.cancel();
                e.printStackTrace();
            }
        });
    }
    //method to open google maps to route to the restaurant location once tapped on the front end
    public void addressClick(double latitude, double longitude, String name) {
        Uri gmmIntentUri = Uri.parse(googleMapsURI(latitude, longitude, name));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        //ensures that the android app for google maps resolves the intent
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public static String googleMapsURI(double latitude, double longitude, String name) {

        //Example URI: "geo:37.7749,-122.4194?q=restaurants" - open google maps to search for restaurants in SF
        return new StringBuilder().append("geo:")
                .append(latitude)
                .append(",")
                .append(longitude)
                .append("?=")
                .append(name).toString();
    }
    public static String pictureURL(String photoReference) {
        //URL to call the place photo API to return the image
        return new StringBuilder().append("https://maps.googleapis.com/maps/api/place/photo?")
                .append("maxwidth=")
                .append(400)
                .append("&photo_reference=")
                .append(photoReference)
                .append("&key=")
                .append(MAPS_API_KEY).toString();
    }
    public static String placesURL(double latitude, double longitude, int radius)
    {
        return new StringBuilder().append("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=")
                .append(latitude)
                .append(",")
                .append(longitude)
                .append("&radius=")
                .append(radius)
                .append("&type=restaurant&key=")
                .append(MAPS_API_KEY).toString();
    }
}