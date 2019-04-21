package cs.hku.hk.whenwhere.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import cs.hku.hk.whenwhere.R;


public class ListActivity extends AppCompatActivity {

    FloatingActionButton addActivityButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        addActivityButton=(FloatingActionButton)findViewById(R.id.addActivityButton);
        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ListActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });


    }
}
