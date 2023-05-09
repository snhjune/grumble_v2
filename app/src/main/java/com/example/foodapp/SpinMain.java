package com.example.foodapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpinMain extends AppCompatActivity {

    LuckyWheel luckyWheel;
    List<WheelItem> wheelItems;
    String points;

    TextView tv;

    MainActivity m = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin);
        generateWheelItems();

        luckyWheel = findViewById(R.id.wheel);
        luckyWheel.addWheelItems(wheelItems);
        luckyWheel.setTarget(1);

        luckyWheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                String itemSelected = wheelItems.get(Integer.parseInt(points) - 1).text;
                Toast.makeText(SpinMain.this, "Item selected: " + itemSelected, Toast.LENGTH_SHORT).show();
                tv = findViewById(R.id.tv);
                tv.setText("Selected Item : " + itemSelected);

            }
        });
        Button start = findViewById(R.id.spinButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRandomSpinValue(view);
            }

        });


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Display the back button
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Call the default back button action
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void generateWheelItems() {
        ArrayList<String> colors = new ArrayList<String>();
        colors.add("#023e8a");
        colors.add("#0077b6");
        colors.add("#0096c7");
        colors.add("#00b4d8");
        colors.add("#03045e");

        wheelItems = new ArrayList<>();
        int i = 0;

        ArrayList<String> arr = getIntent().getStringArrayListExtra("list");
        for(String s : arr) {
            System.out.println("restaurant : " + s);
            WheelItem wheelItem = new WheelItem(Color.parseColor(colors.get(i++)), BitmapFactory.decodeResource(getResources(), com.bluehomestudio.luckywheel.R.drawable.ic_action_name), s);
            wheelItems.add(wheelItem);
        }
    }
    public void getRandomSpinValue(View view){
        Random random = new Random();
        points = String.valueOf(random.nextInt(4) + 1);
        System.out.println("points: " + points);
        luckyWheel.rotateWheelTo(Integer.parseInt(points));
    }

}