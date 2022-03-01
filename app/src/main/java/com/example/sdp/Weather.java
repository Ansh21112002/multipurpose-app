package com.example.sdp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather extends AppCompatActivity {

    EditText et;
    TextView tv;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        et=findViewById(R.id.et);
        tv=findViewById(R.id.tv);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);

    }

    public void get(View v){
        String apiKey="dcc14a371f2ad3f2bdb75dfe639d882e";
        String city=et.getText().toString();
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=dcc14a371f2ad3f2bdb75dfe639d882e";
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object=response.getJSONObject("main");
                    String temperature= object.getString("temp");
                    Double temp=Double.parseDouble(temperature)-273.15;
                    tv.setText("Temperature \n" + temp.toString().substring(0,5) + " Celcius");

                    String min_temp= object.getString("temp_min");
                    Double temp_min=Double.parseDouble(min_temp)-273.15;
                    tv1.setText("Minimum Temperature \n " + temp.toString().substring(0,5) + " Celcius");

                    String max_temp= object.getString("temp_max");
                    Double temp_max=Double.parseDouble(min_temp)-273.15;
                    tv2.setText("Minimum Temperature \n" + temp.toString().substring(0,5) + " Celcius");

                    String Humidity= object.getString("humidity");
                    Double humidity=Double.parseDouble(min_temp)-273.15;
                    tv3.setText("Humidity \n" + humidity.toString().substring(0,5) + "%");

                    String Pressure= object.getString("pressure");
                    Double pressure=Double.parseDouble(min_temp)-273.15;
                    tv3.setText("Pressure \n" + pressure.toString().substring(0,5) + "hPa");







                }
                catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Weather.this, "Enter Appropriate City Name and Try Again", Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(request);


    }
}