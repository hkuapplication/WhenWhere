package cs.hku.hk.whenwhere.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.utils.InnerNavigationController;

public class AddMemberActivity extends AppCompatActivity {
    private Button button;
    private int aid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_member);
        button=findViewById(R.id.invite);
        EditText loginNameTxt = (EditText) findViewById(R.id.editText);
        loginNameTxt.setOnFocusChangeListener(this.onFocusAutoClearHintListener);
        aid = getIntent().getIntExtra("aid",-1);
    }

    public void onClick(View v)
    {
        if(v.getId()==R.id.invite)
        {
            Intent intent=new Intent(AddMemberActivity.this, InnerNavigationController.class);
            intent.putExtra("aid",aid);
            startActivity(intent);
        }
    }
    public static View.OnFocusChangeListener onFocusAutoClearHintListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText textView = (EditText) v;
            String hint;
            if (hasFocus) {

                //hint = textView.getHint().toString();
                //textView.setTag(hint);
                textView.setText("");

            }
        }
    };
}