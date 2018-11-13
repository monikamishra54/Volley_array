package com.example.mgc.volley_array;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class Main4Activity extends AppCompatActivity {
    Spinner spinner_edit_country;
    ConnectDetector cd;
    Boolean isinternetpresent;
    JSONArray resultCountry,resultState,resultCity;
    private ArrayList<String> countrys,states,citys;
    String sharname,strGender,sharphone,sharUserphoto,strAgeCrit,strMinAge,strMaxAge,strAge,strEdu,strDob,strKnowLang,strCountry,strIntrest,strState,strSmoking,strCity,strDrink,strRelation,strEyeColor,strLookfor,strSkinColor,strLookRel,strWorkas,strAbout,strParent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

         spinner_edit_country=(Spinner) findViewById(R.id.spinner_next);
      //  ArrayList<String> items=getCountries();


        countrys=new ArrayList<String>();
        GetCountryData();


    }




    private void getCountry(JSONArray arr) {
        //Traversing through all the items in the json array
        for(int i=0;i<arr.length();i++){
            try {
                JSONObject json = null;
                json = arr.getJSONObject(i);
                //Getting json object
                String  jname=json.getString("name");
                Log.i("","json response adfsdfsadfsd"+jname);
                //Adding the name of the student to array list

                countrys.add(json.getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner_edit_country.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, countrys));


        if (strCountry != null) {
            int alreadySelectCountry =countrys.indexOf(strCountry);

            spinner_edit_country.setSelection(alreadySelectCountry);
        }





    }


    private void GetCountryData() {
        cd=new ConnectDetector(getApplicationContext());
        isinternetpresent=cd.isConnectToInternet();

        if(isinternetpresent)
        {

            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://brilltechno.com/bdating/android/GetDataForUpdateProfile.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // progressBar.setVisibility(View.GONE);
                            Log.i("","GetCountryData================"+response.toString());

                            JSONObject JSObj = null;

                            try {

                                if(response.equals("0"))
                                {
                                    Toast.makeText(getApplicationContext(),"In-Valid email or password",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {

                                    JSObj = new JSONObject(response);

                                    resultCountry = JSObj.getJSONArray("country");
                                    countrys.clear();

                                    Log.i("","sign us response get country data response"+response);

                                    Log.i("","sign us response get country dataresult"+resultCountry);

                                    //Calling method getStudents to get the students from the JSON Array
                                    getCountry(resultCountry);

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
                    params.put("Getcountry", "yes");

                    return params;
                }
            };

            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }
        else {
            Toast.makeText(getApplicationContext(),"Connect To Internet", Toast.LENGTH_SHORT).show();

        }



    }

}
