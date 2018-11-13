package com.example.mgc.volley_array;

import android.app.ProgressDialog;
import android.nfc.Tag;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Details_Activity extends AppCompatActivity {
final String TAG=this.getClass().getSimpleName();
    private List<Movie> modelList=new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter madapter;
    String url="https://monikamishra.000webhostapp.com/jsonArray.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_);
        recyclerView=(RecyclerView) findViewById(R.id.recyler_view);
        madapter= new MoviesAdapter(Details_Activity.this,modelList);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(madapter);
        final ProgressDialog loading=ProgressDialog.show(this,"Uploading...","Please Wait...",false,false);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG,response.toString());
                loading.dismiss();
                int count=0;
                while(count<response.length()){
                    try{
                        JSONObject jsonObject=response.getJSONObject(count);
                        Movie model=new Movie(jsonObject.getString("Name"),jsonObject.getString("Email"),jsonObject.getString("Mobile"));

                        modelList.add(model);
                        madapter.notifyDataSetChanged();
                        count++;

                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(Details_Activity.this).addToRequestQueue(jsonArrayRequest);

    }
}
