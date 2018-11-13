package com.example.mgc.volley_array;

import android.app.ProgressDialog;
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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main3Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
Spinner sp;
    private ArrayList<String> state_arrayList;

    //JSON Array
    private JSONArray result;
    JSONArray resultCountry;

    //JSON array name
    public static final String JSON_ARRAY = "result";
    private ArrayList<String> countrys,states,citys;


    String sharname,strGender,sharphone,sharUserphoto,strAgeCrit,strMinAge,strMaxAge,strAge,strEdu,strDob,strKnowLang,strCountry,strIntrest,strState,strSmoking,strCity,strDrink,strRelation,strEyeColor,strLookfor,strSkinColor,strLookRel,strWorkas,strAbout,strParent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        sp=(Spinner) findViewById(R.id.sp);
        //Initializing the ArrayList
        state_arrayList = new ArrayList<String>();

        sp.setOnItemSelectedListener(this);


        //This method will fetch the data from the URL
       // getData();

        String scountry= sp.getSelectedItem().toString();

        GetCountryData();

    }



    private void GetCountryData() {



            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://www.brilltechno.com/tutor/state.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // progressBar.setVisibility(View.GONE);
                            Log.i("","GetCountryData================"+response.toString());

                            JSONObject JSObj = null;

                            try {



                                    JSObj = new JSONObject(response);

                                    resultCountry = JSObj.getJSONArray("country");
                                    countrys.clear();

                                    Log.i("","sign us response get country data response"+response);

                                    Log.i("","sign us response get country dataresult"+resultCountry);

                                    //Calling method getStudents to get the students from the JSON Array
                                    getCountry(resultCountry);



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

            };

            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);





    }





    /*

    private void getData() {



        //Creating a string request
        StringRequest stringRequest = new StringRequest("http://www.brilltechno.com/tutor/state.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Main3Activity.this,response,Toast.LENGTH_LONG).show();

                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getStudents(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);





    }

    private void getStudents(JSONArray j){

        //Setting adapter to show the items in the spinner
        sp.setAdapter(new ArrayAdapter<String>(Main3Activity.this, android.R.layout.simple_spinner_dropdown_item, state_arrayList));
    }




    */




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
        sp.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, countrys));


        if (strCountry != null) {
            int alreadySelectCountry =countrys.indexOf(strCountry);

            sp.setSelection(alreadySelectCountry);
        }





    }











    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       // Toast.makeText(Main3Activity.this,parent.getSelectedItem().toString())

Toast.makeText(Main3Activity.this,"item selected"+position,Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
