package com.example.finalproject0;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Jama.Matrix;

public class MainActivity extends AppCompatActivity {


    public static TextView result;
    EditText area, bathroom, bedroom;
    Button calculate, moreHouse;
    double price;
    double[] theta = new double[5];
    int a = getParameter(theta);
    double theta0 = theta[0], theta1 = theta[1], theta2 = theta[2], theta3 = theta[3], theta4 = theta[4];
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

        moreHouse = (Button) findViewById(R.id.moreHouse);
        moreHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                result.setText(Arrays.toString(theta));
                String website = "https://www.homesearchil.com/results-gallery/?";
                clicked_button(website);
            }
        });
    }

    public void clicked_button(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public int getParameter(double[] theta) {
        double[] area = new double[]{1359.0, 1790.0, 1433.0, 1418.0, 1723.0, 3158.0, 2713.0, 989.0, 2070.0, 2064.0, 1721.0, 3524.0, 1222.0, 1440.0, 4291.0, 1480.0, 1193.0, 2321.0, 1496.0, 1260.0};
        double[] bds = new double[]{2, 4, 3, 3, 3, 6, 5, 3, 4, 4, 3, 5, 3, 4, 6, 3, 3, 4, 3, 4};
        double[] bas = new double[]{2, 3, 2, 3, 4, 5, 4, 2, 4, 4, 2, 4, 2, 2, 4, 3, 1, 3, 2, 2};
        double[] y = new double[]{175, 189, 155, 135, 259.9, 379.9, 239.9, 104.9, 220, 274.9, 185, 200, 135, 169.9, 389.9, 120, 139.9, 195, 125.5, 183.5};
        int m = area.length;
        int n = 4;
        double[][] X = new double[m][n + 1];
        for (int i = 0; i < m; i++) {
            X[i][0] = 1;
        }
        for (int i = 0; i < m; i++) {
            X[i][1] = area[i];
        }
        for (int i = 0; i < m; i++) {
            X[i][2] = Math.sqrt(area[i]);
        }
        for (int i = 0; i < m; i++) {
            X[i][3] = bds[i];
        }
        for (int i = 0; i < m; i++) {
            X[i][4] = bas[i];
        }

        double[][] b = new double[m][1];
        for (int i = 0; i < m; i++) {
            b[i][0] = y[i];
        }
        Matrix bM = new Matrix(b);
        Matrix XM = new Matrix(X);
        Matrix XMT = XM.transpose();
        Matrix lhs = XMT.times(XM);
        Matrix rhs = XMT.times(bM);
        Matrix x = lhs.solve(rhs);

        for (int i = 0; i < 5; i++) {
            theta[i] = x.get(i, 0);
        }
        return 0;
    }
}