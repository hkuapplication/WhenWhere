package cs.hku.hk.whenwhere.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import cs.hku.hk.whenwhere.R;

public class WeeklyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
// set a back arrow in the weekly calendar page
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Weekly Schedule");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
