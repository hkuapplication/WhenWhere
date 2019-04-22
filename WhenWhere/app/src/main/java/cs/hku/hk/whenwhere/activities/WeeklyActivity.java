package cs.hku.hk.whenwhere.activities;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.adapters.Events_recycle;
import cs.hku.hk.whenwhere.model.Events;


public class WeeklyActivity extends AppCompatActivity {
    private TextView sun,mon,tue,wed,thu,fri,sat;
    private RecyclerView recyclerView;
    private List<Events> listEvents;
    private Events_recycle events_recycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        recyclerView = (RecyclerView) findViewById(R.id.rV);
        sun=(TextView)findViewById(R.id.sun);
        mon=(TextView)findViewById(R.id.mon);
        tue=(TextView)findViewById(R.id.tue);
        wed=(TextView)findViewById(R.id.wed);
        thu=(TextView)findViewById(R.id.thu);
        fri=(TextView)findViewById(R.id.fri);
        sat=(TextView)findViewById(R.id.sat);
        setClick(sun);setClick(mon);setClick(tue);setClick(wed);setClick(thu);setClick(fri);setClick(sat);

      /*  TextView[] tvs = new TextView[3];
        for (int i = 15; i < 18; i++) {
                String tvID = "mon" + (i);
                int resID = getResources().getIdentifier(tvID, "id", getPackageName());
                tvs[i-15] = ((TextView) findViewById(resID));
                tvs[i-15].setBackgroundColor(Color.parseColor("#2C0044"));
            } */


      //fake
        coloring("mon",15,18);
        coloring("tue",6,10);
        coloring("wed",11,13);
        coloring("sat",17,20)
       // initObjects("mon");

        }

 public void initObjects(String date){
     listEvents = new ArrayList<>();
     events_recycle = new Events_recycle(listEvents);
     RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
     recyclerView.setLayoutManager(mLayoutManager);
     recyclerView.setItemAnimator(new DefaultItemAnimator());
     recyclerView.setHasFixedSize(true);
     recyclerView.setAdapter(events_recycle);
     getDataFromMYSQL(date); //database

 }


    private void getDataFromMYSQL(String date) {
        //fake data
        Events e1=new Events();
        e1.setId(1);
        e1.setDiscription("dinner");
        listEvents.add(e1);
    }

//  color a whole week schedule

    public void coloring(String day, int start, int end){
        int slots=end-start;
        TextView[] tvs = new TextView[slots];
        for (int i = start; i < end; i++) {
            String tvID = day + (i);
            int resID = getResources().getIdentifier(tvID, "id", getPackageName());
            tvs[i-start] = ((TextView) findViewById(resID));
            tvs[i-start].setBackgroundColor(Color.parseColor("#2C0044"));
        }
    }


    public void setClick (final TextView t){
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = t.getText().toString();
                initObjects(date);
            }
        });
    }

 }
