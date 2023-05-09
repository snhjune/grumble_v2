package com.example.foodapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    //initialize a floating action button called fab
    private FloatingActionButton fab;

    //initialize a chipgroup to store the chips retrieved from the filterActivity class
    private ChipGroup msgTV;

    private Button spin;

    //creating a ChipGroup object named chipgroup
    ChipGroup chipGroup;

    //initializing an array adapter for strings
//    private ArrayAdapter<String> arrayAdapter;
    //initializing a string arraylist

    //creating a SwipeFlingAdapterView object named flingAdapterView
    //used for swiping feature
    SwipeFlingAdapterView flingAdapterView;

    ArrayList<String> likeArr = new ArrayList<>();
    ArrayList<Object> dislikeArr = new ArrayList<>();

    private Handler mHandler;

   // List<String> restaurantNames = new ArrayList<>();

    int count = 0;

    ArrayList<Restaurant> myArrayList;
    ArrayList<String> rNames;
    int rCount = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rNames = getIntent().getStringArrayListExtra("myList");

        ArrayAdapter<String> a = new ArrayAdapter<String>(HomeActivity.this, R.layout.item, R.id.data, rNames) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
                imageView.setImageResource(R.drawable.img_1);

                return view;
            }
        };

        flingAdapterView = findViewById(R.id.swipe);
        flingAdapterView.setAdapter(a);



        flingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            //removes the card once swiped
            public void removeFirstObjectInAdapter() {
                if (a.getCount() > 1) {
                    a.remove(a.getItem(0));
                    a.notifyDataSetChanged();
                }
                rCount++;
            }

            @Override
            //if the card is swiped Left the toast prints dislike
            public void onLeftCardExit(Object o) {

                dislikeArr.add(o);
            }
            @Override
            //if the card is swiped Left the toast prints like
            public void onRightCardExit(Object o) {

                likeArr.add(String.valueOf(o));
                count++;

                if (count == 5) {
                    onBackPressed();
                }
                System.out.println("count = " + count);
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

            }

            @Override
            public void onScroll(float v) {

            }

        });



        //connecting the msgTV to the chipgroup from xml.
        msgTV = findViewById(R.id.idTVMsg);


        //populating the data from the data arraylist to the arrayadapter


        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //button for the filter option
        fab = findViewById(R.id.fab);

        //onclickListener takes the button to the next acivity using the sendUserToNextActivity function
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToNextActivity();
            }
        });

        FloatingActionButton like1 = findViewById(R.id.like);
        FloatingActionButton dislike1 = findViewById(R.id.dislike);

        like1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flingAdapterView.getTopCardListener().selectRight();
            }
        });

        dislike1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flingAdapterView.getTopCardListener().selectLeft();
            }
        });

        msgTV = findViewById(R.id.idTVMsg);
        ArrayList<String> arr = getIntent().getStringArrayListExtra("message");

        //it shows null on the frontend so if it the arraylist is null define it as new arraylist
        if (arr == null || arr.size() == 0) {
            arr = new ArrayList<>();
        }

        //populating the chipgroup with chips from the string data in the arralist named arr
        chipGroup = findViewById(R.id.idTVMsg);
        for (String s : arr) {
            setChips(s);
        }

        FloatingActionButton details = findViewById(R.id.detail);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToNextActivity3();
            }
        });


    }
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putStringArrayList("myList", data);
//    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendUserToNextActivity() {
        Intent intent= new Intent(HomeActivity.this, filterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void sendUserToNextActivity1() {
        Intent intent= new Intent(HomeActivity.this, spinActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("message", likeArr);
        startActivity(intent);
    }

    private void sendUserToNextActivity3() {
        Intent intent= new Intent(HomeActivity.this, RestaurantDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name", rNames.get(rCount));
        startActivity(intent);
    }

    public void setChips(String e){
        final Chip chip = (Chip) this.getLayoutInflater().inflate(R.layout.single_input_chip_layout, null, false);
        chip.setText(e);
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chipGroup.removeView(chip);
            }
        });
        chipGroup.addView(chip);
    }

    public ArrayList<String> getLikeArr(){
        return likeArr;
    }

    public void onBackPressed() {
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("spin the wheel !!");

        // Set Alert Title
        builder.setTitle("Confused ?");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            sendUserToNextActivity1();
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
            count = 0;
            likeArr.clear();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }


}





