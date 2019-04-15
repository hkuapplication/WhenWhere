package cs.hku.hk.whenwhere.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import cs.hku.hk.whenwhere.R;


public class Login extends AppCompatActivity {

    AppCompatButton button1;
    TextInputEditText email;
    TextInputEditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        button1 = (AppCompatButton)findViewById(R.id.loginButton);
        email=(TextInputEditText)findViewById(R.id.textInputEditTextEmail);
        password=(TextInputEditText)findViewById(R.id.textInputEditTextPassword);
        String mEmail = email.getText().toString();
        String myPassword = password.getText().toString();
        
        //determine whether login is successful.....
        //if succeed jump to homepage immediately
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Login.this, CalendarFragment.class);
                startActivity(intent);
            }
        });
    }


}
