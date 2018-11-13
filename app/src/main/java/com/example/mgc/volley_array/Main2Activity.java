package com.example.mgc.volley_array;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
Spinner s1,s2;

    ConnectDetector cd;
    Boolean isinternetpresent;
    String url;
    String sharname,strGender,sharphone,sharUserphoto,strAgeCrit,strMinAge,strMaxAge,strAge,strEdu,strDob,strKnowLang,strCountry,strIntrest,strState,strSmoking,strCity,strDrink,strRelation,strEyeColor,strLookfor,strSkinColor,strLookRel,strWorkas,strAbout,strParent;


    JSONArray resultCountry,resultState,resultCity;


    private ArrayList<String> countrys,states,citys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        s1=(Spinner) findViewById(R.id.state);
        s2=(Spinner) findViewById(R.id.city);
        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);
        citys=new ArrayList<String>();
        states=new ArrayList<String>();

        // changes done hhhhhhhhhhhhhhhhhh



        String sstate= s1.getSelectedItem().toString();
        String scity= s2.getSelectedItem().toString();


        GetStateData(sstate);














    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()){
            case R.id.state:
                Log.d("form","state id:");

                String items = parent.getItemAtPosition(position).toString();
                Log.d("form","regionid: state" + items);

                GetCityData(items);

                break;
        }

    }

    private void GetCityData(String stateitem) {
        citys.clear();
        cd=new ConnectDetector(getApplicationContext());
        isinternetpresent=cd.isConnectToInternet();

        if(isinternetpresent) {

            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // progressBar.setVisibility(View.GONE);
                            Log.i("","Getstatedata================"+response.toString());

                            JSONObject JSObj = null;

                            try {

                                if(response.equals("0"))
                                {
                                    Toast.makeText(getApplicationContext(),"In-Valid email or password",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {

                                    JSObj = new JSONObject(response);
                                    resultState = JSObj.getJSONArray("state");


                                    Log.i("","sign us response get country data response"+response);

                                    Log.i("","sign us response get country dataresult"+resultState);

                                    //Calling method getStudents to get the students from the JSON Array
                                    getState(resultState);

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                            Log.i("","profile error========="+error.toString());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("GetState", "yes");
                 //   params.put("sendCountryName", countryitem);

                 //   Log.i("","asdsadfcountryitem====="+countryitem);

                    return params;
                }
            };

            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    // GetCityData
    private void getCity(JSONArray arr) {

        for(int i=0;i<arr.length();i++){
            try {
                JSONObject json = null;
                json = arr.getJSONObject(i);
                //Getting json object
                String  jname=json.getString("name");
                Log.i("","json response adfsdfsadfsd"+jname);
                //Adding the name of the student to array list

                citys.add(json.getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        s2.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, citys));
        if (strCity != null) {
            int alreadySelectcity =citys.indexOf(strCity);



            Log.i("","json response adfsdfsadfsd"+alreadySelectcity);

            s2.setSelection(alreadySelectcity);
        }

    }
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }
    private void getState(JSONArray arr) {

        for(int i=0;i<arr.length();i++){
            try {
                JSONObject json = null;
                json = arr.getJSONObject(i);
                //Getting json object
                String  jname=json.getString("name");
                Log.i("","json response adfsdfsadfsd"+jname);
                //Adding the name of the student to array list

                states.add(json.getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        //Setting adapter to show the items in the spinner
        s1.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, states));

        if (strState != null) {
            int alreadySelectState =states.indexOf(strState);

            s1.setSelection(alreadySelectState);
        }
    }




    // GetCityData

    private void GetStateData(final String countryitem) {

        states.clear();
        cd=new ConnectDetector(getApplicationContext());
        isinternetpresent=cd.isConnectToInternet();

        if(isinternetpresent)
        {
            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // progressBar.setVisibility(View.GONE);
                            Log.i("","Getstatedata================"+response.toString());

                            JSONObject JSObj = null;

                            try {

                                if(response.equals("0"))
                                {
                                    Toast.makeText(getApplicationContext(),"In-Valid email or password",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {

                                    JSObj = new JSONObject(response);
                                    resultState = JSObj.getJSONArray("state");


                                    Log.i("","sign us response get country data response"+response);

                                    Log.i("","sign us response get country dataresult"+resultState);

                                    //Calling method getStudents to get the students from the JSON Array
                                    getState(resultState);

                                }








                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                            Log.i("","profile error========="+error.toString());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("GetState", "yes");
                    params.put("sendCountryName", countryitem);

                    Log.i("","asdsadfcountryitem====="+countryitem);

                    return params;
                }
            };

            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }
        else {
            Toast.makeText(getApplicationContext(),"Connect To Internet",Toast.LENGTH_SHORT).show();

        }



    }







}
