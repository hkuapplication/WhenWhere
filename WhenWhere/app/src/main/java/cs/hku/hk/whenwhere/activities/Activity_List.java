package cs.hku.hk.whenwhere.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.adapters.Activity_recycle;
import cs.hku.hk.whenwhere.model.Activities;

public class Activity_List extends AppCompatActivity {
    private AppCompatActivity activity=Activity_List.this;
    private RecyclerView recyclerView;
    private List<Activities> listActivities;
    private Activity_recycle activity_recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();

    }

    private void initObjects() {
        listActivities = new ArrayList<>();
        activity_recycle = new Activity_recycle(listActivities);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(activity_recycle);
        //databaseHelper = new DatabaseHelper(activity);

        //String emailFromIntent = getIntent().getStringExtra("EMAIL");
       // textViewName.setText(emailFromIntent);

        getDataFromMYSQL();
    }

    private void getDataFromMYSQL() {

    }



    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewUsers);
    }
}
