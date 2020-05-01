package com.example.finalproject0;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.VolleyError;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;

import android.os.AsyncTask;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


//    ProgressDialog pd;
    public static TextView result;
    EditText area, bathroom, bedroom;
    Button calculate, moreHouse;
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

        moreHouse = (Button) findViewById(R.id.moreHouse);
        moreHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                result.setText("000");
//                new JsonTask().execute("https://www.homesearchil.com/results-gallery/?");
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


//    private class JsonTask extends AsyncTask<String, String, String> {
//
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            pd = new ProgressDialog(MainActivity.this);
//            pd.setMessage("Please wait");
//            pd.setCancelable(false);
//            pd.show();
//        }
//
//        protected String doInBackground(String... params) {
//
//
//            HttpURLConnection connection = null;
//            BufferedReader reader = null;
//
//            try {
//                URL url = new URL(params[0]);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//
//
//                InputStream stream = connection.getInputStream();
//
//                reader = new BufferedReader(new InputStreamReader(stream));
//
//                StringBuffer buffer = new StringBuffer();
//                String line = "";
//
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line+"\n");
//                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
//
//                }
//
//                return buffer.toString();
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (connection != null) {
//                    connection.disconnect();
//                }
//                try {
//                    if (reader != null) {
//                        reader.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result0) {
//            super.onPostExecute(result0);
//            if (pd.isShowing()){
//                pd.dismiss();
//            }
//            result.setText(result0);
//        }
//    }

}
