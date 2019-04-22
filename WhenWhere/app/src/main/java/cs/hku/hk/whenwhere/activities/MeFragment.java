package cs.hku.hk.whenwhere.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.model.Member;

public class MeFragment extends Fragment {
    private Member user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.outer_fragment_me,null);
        Button history = (Button)view.findViewById(R.id.history);
        Button logout = (Button)view.findViewById(R.id.logout);
        TextView userName = (TextView)view.findViewById(R.id.Username);
        TextView email = (TextView)view.findViewById(R.id.emailAccount);

        //get the memeber object
/*
        user=(Member)getActivity().getIntent().getSerializableExtra("user");
        String username=user.getName();
        String emails=user.getEmail();

        userName.setText(username);
        email.setText(emails);
*/
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HistoryActivity.class);
                startActivity(intent);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });

        return view;
    }
}