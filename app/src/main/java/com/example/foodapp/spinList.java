package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class spinList extends AppCompatActivity {

    ArrayList<String> stringList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_list);

        Button spin = findViewById(R.id.add);
        spin.setOnClickListener(view -> {
            EditText editText1 = findViewById(R.id.editText1);
            String string1 = editText1.getText().toString();

            EditText editText2 = findViewById(R.id.editText2);
            String string2 = editText2.getText().toString();

            EditText editText3 = findViewById(R.id.editText3);
            String string3 = editText3.getText().toString();

            EditText editText4 = findViewById(R.id.editText4);
            String string4 = editText4.getText().toString();

            EditText editText5 = findViewById(R.id.editText5);
            String string5 = editText5.getText().toString();

            stringList.add(string1);
            stringList.add(string2);
            stringList.add(string3);
            stringList.add(string4);
            stringList.add(string5);

            for(String s : stringList){
                System.out.println("s = " + s);
            }

            Intent i = new Intent(spinList.this, SpinMain.class);
            i.putExtra("list", stringList);
            startActivity(i);
        });
    }
}
