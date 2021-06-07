package com.example.imageclassification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    private RecyclerView recyclerView;
    List<HistoryDataModel> historyDataModelList;
    RecyclerView.LayoutManager layoutManager;
    private HistoryDataAdapter historyDataAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        drawerLayout = findViewById(R.id.my_drawer_layout_history);
        actionBarDrawerToggle = new ActionBarDrawerToggle(  this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view_history);
        navigationView.setNavigationItemSelectedListener(this);

        progressDialog = new ProgressDialog(this);

        loadData();

    }

    private void loadData(){
        historyDataModelList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_menu_history_data);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        progressDialog.setMessage("Please wait...");
        progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    "http://htichhicker.000webhostapp.com/showme.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray histData = jsonObject.getJSONArray("data");
                                for (int i=0;i<histData.length();i++){
                                    JSONObject object = histData.getJSONObject(i);
                                    HistoryDataModel model = new HistoryDataModel(
                                            object.getInt("id"),
                                            object.getString("prediction"),
                                            object.getString("flag"),
                                            object.getString("correct_value"),
                                            object.getString("timestamp")
                                    );
                                    historyDataModelList.add(model);
                                }
                                historyDataAdapter = new HistoryDataAdapter(getApplicationContext(),historyDataModelList);
                                recyclerView.setAdapter(historyDataAdapter);
                                progressDialog.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            error.printStackTrace();
                            Toast.makeText(getApplicationContext(),"Please try again later",Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return super.getParams();
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
                break;
            case R.id.nav_history:
                Toast.makeText(getApplicationContext(),"you're already in a history page.",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_mail_me:
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, "hitchhicker8401@hotmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "mail from image classification app");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
        }

        return false;
    }
}