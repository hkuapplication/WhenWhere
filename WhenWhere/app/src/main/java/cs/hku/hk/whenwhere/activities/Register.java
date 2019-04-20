package cs.hku.hk.whenwhere.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.model.Member;
import cs.hku.hk.whenwhere.utils.JSONHelper;
import cs.hku.hk.whenwhere.utils.OuterNavigationController;

public class Register extends AppCompatActivity {

    JSONHelper jsonHelper = JSONHelper.getInstance();


    TextInputEditText username;
    TextInputEditText email;
    TextInputEditText password;
    TextInputEditText repassword;
    AppCompatButton registerButton;
    AppCompatTextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = (TextInputEditText)findViewById(R.id.textInputEditTextName);
        email = (TextInputEditText)findViewById(R.id.textInputEditTextEmail);
        password= (TextInputEditText)findViewById(R.id.textInputEditTextPassword);
        repassword = (TextInputEditText)findViewById(R.id.textInputEditTextConfirmPassword);
        registerButton = (AppCompatButton)findViewById(R.id.appCompatButtonRegister);
        loginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String textName = username.getText().toString();
                String textEmail = email.getText().toString();
                String textPassword = password.getText().toString();
                String textRePassword = repassword.getText().toString();

                //初步判断是否完整
                if(textName.isEmpty()||textEmail.isEmpty()||textPassword.isEmpty()||textRePassword.isEmpty()){
                    alert("Warn","Please fill all the blank.");
                }else{
                    //完整但是两次密码不一致
                    if(!textPassword.equals(textRePassword)){
                        alert("Warn","The two passwords you typed do not match.");
                    }else{
                        //可以去数据库进一步判断了
                        connect(textName,textEmail,textPassword);
                    }
                }

                //连接判断是否可加

            }
        });

        loginLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //不注册了直接去到登录界面
                Intent intent=new Intent();
                intent.setClass(Register.this, Login.class);
                startActivity(intent);
            }
        });

    }


    public void connect(String username,String email,String password){
        final String url = "https://i.cs.hku.hk/~hdqi/WhenWhere/user/register.php" //TODO
                + "?action=register"
                + "&username="
                + android.net.Uri.encode(username,"UTF-8")
                + "&email="
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
                jsonString = jsonHelper.getJsonPage(url);
                if ((!jsonString.isEmpty())&&jsonString.startsWith("fail")){
                    success = false;
                }
                return null;
            }
            @Override
            protected void onPostExecute(String result) {
                if (success) {
                    alert( "Success", "Successful registration.Login now!" );
                } else {
                    alert( "Warn", "Your email has been registered." );
                }
//                pdialog.hide();
            }
        }.execute("");
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
