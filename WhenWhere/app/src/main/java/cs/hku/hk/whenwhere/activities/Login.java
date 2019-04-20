package cs.hku.hk.whenwhere.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.utils.JSONHelper;
import cs.hku.hk.whenwhere.utils.OuterNavigationController;



public class Login extends AppCompatActivity {

    JSONHelper jsonHelper = JSONHelper.getInstance();

    AppCompatButton button1;
    TextInputEditText email;
    TextInputEditText password;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        button1 = (AppCompatButton)findViewById(R.id.appCompatButtonLogin);
        email=(TextInputEditText)findViewById(R.id.textInputEditTextEmail);
        tv=(TextView)findViewById(R.id.textViewLinkRegister) ;
        password=(TextInputEditText)findViewById(R.id.textInputEditTextPassword);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //determine whether login is successful.....
                //if succeed jump to homepage immediately else Toast maketext "Sorry, your username or password may be incorrect"

                String mEmail = email.getText().toString();
                String myPassword = password.getText().toString();
                System.out.println("邮箱是："+mEmail);
                System.out.println("密码是："+myPassword);
                connect(mEmail,myPassword);

//                Intent intent=new Intent();
//                intent.setClass(Login.this, OuterNavigationController.class);
//                startActivity(intent);
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    public void connect(String email,String password){

        if(email.isEmpty()||password.isEmpty()){
            System.out.println("信息不完整");
            alert("Warn","You have missed important login information.");
        }
        else{
            final String url = "https://i.cs.hku.hk/~hdqi/WhenWhere/user/login.php" //TODO
                    + "?action=login&email="
                    + android.net.Uri.encode(email, "UTF-8")
                    + "&password="
                    + android.net.Uri.encode(password, "UTF-8");
            AsyncTask<String, Void, String> task = new AsyncTask<String,
                    Void, String>() {
                boolean success;
                String jsonString;
                @Override
                protected String doInBackground(String... arg0) {
// TODO Auto-generated method stub
                    success = true;
//                pdialog.setMessage("Before ...");
//                pdialog.show();
                    jsonString = jsonHelper.getJsonPage(url);
                    System.out.println("网站页面JSON信息："+jsonString);
                    if (jsonString.startsWith("fail")){
                        success = false;
                    }
                    return null;
                }
                @Override
                protected void onPostExecute(String result) {
                    if (success) {
                        parse_JSON_String_and_Switch_Activity(jsonString);
                    } else {
                        alert( "Error", "Invalid email or password." );
                    }
//                pdialog.hide();
                }
            }.execute("");

        }
    }

    public void parse_JSON_String_and_Switch_Activity(String JSONString) {

        System.out.println("JSON长度: "+JSONString.length());

        //成功登录跳转主界面
        Intent intent=new Intent();
        intent.setClass(Login.this, OuterNavigationController.class);
        startActivity(intent);
    }

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
