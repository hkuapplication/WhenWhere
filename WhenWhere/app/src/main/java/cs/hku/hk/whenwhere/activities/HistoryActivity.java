package cs.hku.hk.whenwhere.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import cs.hku.hk.whenwhere.R;
public class HistoryActivity extends AppCompatActivity {
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        button1 = (Button)findViewById(R.id.more);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(HistoryActivity.this, DetailedHistory.class);
                //pass location address to detailed page
                Bundle bundle = new Bundle();
                bundle.putString("location","address");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
