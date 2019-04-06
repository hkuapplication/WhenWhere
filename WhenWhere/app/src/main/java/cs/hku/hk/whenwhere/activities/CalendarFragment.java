package cs.hku.hk.whenwhere.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.adapters.Activity_recycle;
import cs.hku.hk.whenwhere.adapters.Events_recycle;
import cs.hku.hk.whenwhere.model.Activities;
import cs.hku.hk.whenwhere.model.Events;

public class CalendarFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Events> listEvents;
    private Events_recycle events_recycle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calender_activity, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewUsers);
        initObjects();
        return view;
    }
    private void initObjects() {
        listEvents = new ArrayList<>();
        events_recycle = new Events_recycle(listEvents);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(events_recycle);
        //databaseHelper = new DatabaseHelper(activity);

        //String emailFromIntent = getIntent().getStringExtra("EMAIL");
        // textViewName.setText(emailFromIntent);

        getDataFromMYSQL();
    }

    private void getDataFromMYSQL() {
        //fake data
        Events ac1=new Events();
        ac1.setId(2);
        ac1.setDiscription("dinner");
        listEvents.add(ac1);

    }
}
