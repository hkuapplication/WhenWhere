package cs.hku.hk.whenwhere.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.adapters.Activity_recycle;
import cs.hku.hk.whenwhere.model.Activities;


public class ActivityFragment extends Fragment {
   // private AppCompatActivity activity=Activity_List.this;
    private RecyclerView recyclerView;
    private List<Activities> listActivities;
    private Activity_recycle activity_recycle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return inflater.inflate(R.layout.activity_list,null);
        View view = inflater.inflate(R.layout.activity_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewUsers);
        initObjects();

        return view;
    }
    private void initObjects() {
        listActivities = new ArrayList<>();
        activity_recycle = new Activity_recycle(listActivities);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
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
        //fake data
        Activities ac1=new Activities();
        ac1.setId(2);
        ac1.setName("dinner");
        ac1.setTime("2:00");
        ac1.setPlace("su");
        listActivities.add(ac1);
        Activities ac2=new Activities();
        ac2.setId(3);
        ac2.setName("supper");
        ac2.setTime("2:00");
        ac2.setPlace("cym");
        listActivities.add(ac2);
    }

}
