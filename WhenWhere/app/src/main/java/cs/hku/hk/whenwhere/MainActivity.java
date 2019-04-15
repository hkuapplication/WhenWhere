package cs.hku.hk.whenwhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cs.hku.hk.whenwhere.activities.CalendarFragment;
import cs.hku.hk.whenwhere.activities.CreateActivity;
import cs.hku.hk.whenwhere.activities.ListActivity;
import cs.hku.hk.whenwhere.activities.Register;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
}
