package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class filterActivity extends AppCompatActivity {
    //creating chip object for all the filters for multiple select
    private Chip chipTrending, chipNewArrival, chipBestSelling;
    //creating chip object for all the filters for single select
    private Chip chipLowToHigh, chipHighToLow;
    //creating an apply button
    private Button btnApply;
    //arraylist named selectedChipData that will store the selected chips
    private ArrayList<String> selectedChipData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        //mapping the xml of all chips to the object created
        chipTrending = findViewById(R.id.chipTrending);
        chipNewArrival = findViewById(R.id.chipNewArrival);
        chipBestSelling = findViewById(R.id.chipBestSelling);
        chipLowToHigh = findViewById(R.id.chipLowToHigh);
        chipHighToLow = findViewById(R.id.chipHighToLow);

        //initializing the list
        selectedChipData = new ArrayList<>();

        //CompoundButton has an onchecked method which if true adds the chips after converting it to string to the arraylist
        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    selectedChipData.add(compoundButton.getText().toString());
                }
                else {
                    selectedChipData.remove(compoundButton.getText().toString());
                }
            }
        };
        //the onclick changes the activity
        //when the apply button is pressed it will switch the activity to home activity and display the selcted chips
        btnApply = findViewById(R.id.btnApply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(filterActivity.this, HomeActivity.class);
                i.putExtra("message", selectedChipData);
                startActivity(i);
            }
        });
        //setting each chip to setOnCheckedChangeListener
        chipTrending.setOnCheckedChangeListener(checkedChangeListener);
        chipNewArrival.setOnCheckedChangeListener(checkedChangeListener);
        chipBestSelling.setOnCheckedChangeListener(checkedChangeListener);
        chipLowToHigh.setOnCheckedChangeListener(checkedChangeListener);
        chipHighToLow.setOnCheckedChangeListener(checkedChangeListener);
    }
}