package cs.hku.hk.whenwhere.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.utils.InnerNavigationController;
import cs.hku.hk.whenwhere.utils.JSONHelper;

public class AddMemberActivity extends AppCompatActivity {
    private JSONHelper jsonHelper = JSONHelper.getInstance();

    private Button button;
    private EditText loginNameTxt;
    private int aid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_member);
        button=findViewById(R.id.invite);
        loginNameTxt = (EditText) findViewById(R.id.editText);
        loginNameTxt.setOnFocusChangeListener(this.onFocusAutoClearHintListener);
        aid = getIntent().getIntExtra("aid",-1);
    }

    public void onClick(View v)
    {
        if(v.getId()==R.id.invite)
        {
            //
            String emailSQL = loginNameTxt.getText().toString();
            //find user
            connect(emailSQL,aid);

            Intent intent=new Intent(AddMemberActivity.this, InnerNavigationController.class);
            intent.putExtra("aid",aid);
            startActivity(intent);
        }
    }

    public void connect(String email,int aid){
        final String url = "https://i.cs.hku.hk/~hdqi/WhenWhere/activity/inviteMember.php" //TODO
                + "?email="
                + android.net.Uri.encode(email,"UTF-8")
                + "&aid="+aid;
        AsyncTask<String, Void, String> task = new AsyncTask<String,
                Void, String>() {
            boolean success;
            String jsonString;
            @Override
            protected String doInBackground(String... arg0) {
// TODO Auto-generated method stub
                success = true;
                jsonString = jsonHelper.getJsonPage(url);
                if ((!jsonString.isEmpty())&&jsonString.startsWith("fail")){
                    success = false;
                }
                return null;
            }
            @Override
            protected void onPostExecute(String result) {
                if (success) {
                    alert( "Success", "Successful invite your friend!" );
                } else {
                    alert( "Warn", "User doesn't exist." );
                }
//                pdialog.hide();
            }
        }.execute("");

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


    protected void alert(String title, String mymessage){
        new AlertDialog.Builder(this)
                .setMessage(mymessage)
                .setTitle(title)
                .setCancelable(true)
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton){}
                        }
                )
                .show();
    }

}