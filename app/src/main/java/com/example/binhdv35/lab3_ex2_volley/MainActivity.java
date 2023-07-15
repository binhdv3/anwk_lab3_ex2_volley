package com.example.binhdv35.lab3_ex2_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.binhdv35.lab3_ex2_volley.API.urlJson;
import com.example.binhdv35.lab3_ex2_volley.Adrapter.UserAdrapter;
import com.example.binhdv35.lab3_ex2_volley.app.AppContronler;
import com.example.binhdv35.lab3_ex2_volley.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private Button btnGetJsonArray, btnGetJsonObject;
    private TextView tvResult;
    private ProgressDialog progressDialog;
    private String jsonReponse;
    private ListView lv_;

    private List<User> userList;

    private UserAdrapter adrapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetJsonArray = findViewById(R.id.btn_get_json_arr);
        btnGetJsonObject = findViewById(R.id.btn_get_json_obj);
        tvResult = findViewById(R.id.tv_result);
        lv_ = findViewById(R.id.lv_);

        userList = new ArrayList<>();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        btnGetJsonObject.setOnClickListener(v -> {
            makeJsonObjectRequest();
        });

        btnGetJsonArray.setOnClickListener(v -> {
            makeJsonArrayRequest();
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        tvResult.setVisibility(View.INVISIBLE);
        lv_.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adrapter = new UserAdrapter(this,userList);
        lv_.setAdapter(adrapter);
        adrapter.notifyDataSetChanged();
    }

    private void makeJsonArrayRequest() {
        showPDialog();//hiện dialog
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlJson.urlJsonArray,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString()); // dữ liệu trả về

                try {
                    jsonReponse = "";
                    for (int i = 0; i < response.length() ; i++) {
                        JSONObject person = (JSONObject) response.get(i);

                        String name = person.getString("name");
                        String email = person.getString("email");

                        JSONObject phone = person.getJSONObject("phone");
                        String home = phone.getString("home");
                        String mobile = phone.getString("mobile");
//                        jsonReponse += "Name: "+name+ "\n"
//                                + "Email: "+email +"\n"
//                                + "Home: "+home +"\n"
//                                + "Mobile: "+mobile +"\n";
                        userList.add(new User(name,email,home,mobile));
                    }

                    lv_.setVisibility(View.VISIBLE);
                    tvResult.setVisibility(View.INVISIBLE);

                    adrapter = new UserAdrapter(getApplicationContext(),userList);
                    lv_.setAdapter(adrapter);
                    adrapter.notifyDataSetChanged();
//                    tvResult.setText(jsonReponse);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error" + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }

                hidePDialog(); // ẩn dialog
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error" + error.getMessage(),
                        Toast.LENGTH_SHORT).show();
                hidePDialog();
            }
        });
        AppContronler.getInstance().addToRequestQueue(jsonArrayRequest);
    }

    private void makeJsonObjectRequest() {
        showPDialog();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,urlJson.urlJsonObject,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        try {
                            String name = response.getString("name");
                            String email = response.getString("email");

                            JSONObject phone = response.getJSONObject("phone");
                            String home = phone.getString("home");
                            String mobile = phone.getString("mobile");

                            jsonReponse = "";
                            jsonReponse += "Name: "+name+ "\n"
                                    + "Email: "+email +"\n"
                                    + "Home: "+home +"\n"
                                    + "Mobile: "+mobile +"\n";

                            lv_.setVisibility(View.INVISIBLE);
                            tvResult.setVisibility(View.VISIBLE);

                            tvResult.setText(jsonReponse);
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        hidePDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error: "+ error.getMessage()
                        , Toast.LENGTH_SHORT).show();
                hidePDialog();
            }
        });

        AppContronler.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private void showPDialog() {
        if (!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    private void hidePDialog(){
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}