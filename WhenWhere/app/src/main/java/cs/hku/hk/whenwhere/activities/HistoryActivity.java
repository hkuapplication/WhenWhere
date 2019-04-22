package cs.hku.hk.whenwhere.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.adapters.Activity_recycle;
import cs.hku.hk.whenwhere.model.Activities;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Activities> listActivities;
    private Activity_recycle activity_recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
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

        getDataFromMYSQL(); // get the history activities
    }

    private void getDataFromMYSQL() {

    }


    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewUsers);
    }
}
