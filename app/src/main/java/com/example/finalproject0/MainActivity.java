package com.example.finalproject0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    TextView result;
    EditText area, bathroom, bedroom;
    Button calculate;
    double price;
    double theta0 = 93.5730487, theta1 = 0.0707709889, theta2 = -4.36588035, theta3 = 20.285743, theta4 = 27.742016;
    int areaVal, bathroomVal, bedroomVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);
        area = (EditText) findViewById(R.id.area);
        bathroom = (EditText) findViewById(R.id.bathroom);
        bedroom = (EditText) findViewById(R.id.bedroom);
        calculate = (Button) findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areaVal = Integer.parseInt(area.getText().toString());
                bathroomVal = Integer.parseInt(bathroom.getText().toString());
                bedroomVal = Integer.parseInt(bedroom.getText().toString());
                price = theta0 + theta1 * areaVal + theta2 * Math.sqrt(areaVal) + theta3 * bedroomVal + theta4 * bathroomVal;
                int priceInt = (int) price;
                String priceStr = "$" + String.valueOf(priceInt) + ", 000";
                result.setText(priceStr);
            }
        });
    }


}
