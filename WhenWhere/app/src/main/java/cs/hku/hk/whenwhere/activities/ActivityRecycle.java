package cs.hku.hk.whenwhere.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import cs.hku.hk.whenwhere.R;

    public class ActivityRecycle extends AppCompatActivity {
        private ImageButton iButton;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_recycle);
            iButton =(ImageButton)findViewById(R.id.imageButton);
            iButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2=new Intent();
                    intent2.setClass(ActivityRecycle.this, MemberFragment.class);
                    startActivity(intent2);
                }
            });

        }
    }
