package cs.hku.hk.whenwhere.activities;

import android.content.Intent;
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
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.adapters.Member_recycle;
import cs.hku.hk.whenwhere.model.Member;

public class MemberFragment extends Fragment {
  //  private AppCompatActivity activity=MemberFragment.this;
    private RecyclerView recyclerView;
    private List<Member> listMembers;
    private Member_recycle member_recycle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.member_list,null);
        //getSupportActionBar().setTitle("");
        recyclerView = view.findViewById(R.id.recyclerViewUsers);
        initObjects();
        return view;
    }
    private void initObjects() {
        listMembers = new ArrayList<>();
        member_recycle = new Member_recycle(listMembers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(member_recycle);
        //databaseHelper = new DatabaseHelper(activity);

        //String emailFromIntent = getIntent().getStringExtra("EMAIL");
        // textViewName.setText(emailFromIntent);

        getDataFromMYSQL();
    }

    private void getDataFromMYSQL() {
      //fake data
      Member ac1=new Member();
      ac1.setId(2);
      ac1.setName("dinner");
      ac1.setEmail("abc@gmail.com");
      listMembers.add(ac1);
    }

}
