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
import cs.hku.hk.whenwhere.adapters.Times_recycle;
import cs.hku.hk.whenwhere.model.Activities;
import cs.hku.hk.whenwhere.model.Times;

public class TimeFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Times> listTimes;
    private Times_recycle times_recycle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inner_fragment_time, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewUsers);
        initObjects();
        return view;
    }
    private void initObjects() {
        listTimes = new ArrayList<>();
        times_recycle = new Times_recycle(listTimes);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(times_recycle);
        //databaseHelper = new DatabaseHelper(activity);

        //String emailFromIntent = getIntent().getStringExtra("EMAIL");
        // textViewName.setText(emailFromIntent);

        getDataFromMYSQL();
    }

    private void getDataFromMYSQL() {
        //fake data
        Times ac1=new Times();
        ac1.setId(2);
        ac1.setDate("2019-01-02");
        ac1.setTime("2:00");
        ac1.setMember("henry, lucy");
        listTimes.add(ac1);
        Times ac2=new Times();
        ac2.setId(3);
        ac1.setDate("2019-01-09");
        ac1.setTime("6:00");
        ac1.setMember("frank, kiki");
        listTimes.add(ac2);
    }
}
