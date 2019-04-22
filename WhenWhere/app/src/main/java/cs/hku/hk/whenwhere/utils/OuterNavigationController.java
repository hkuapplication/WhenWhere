package cs.hku.hk.whenwhere.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.activities.ActivityFragment;
import cs.hku.hk.whenwhere.activities.CalendarFragment;
import cs.hku.hk.whenwhere.activities.CreateActivity;
import cs.hku.hk.whenwhere.activities.MapFragment;
import cs.hku.hk.whenwhere.activities.MeFragment;
import cs.hku.hk.whenwhere.activities.MemberFragment;
import cs.hku.hk.whenwhere.activities.Register;
import cs.hku.hk.whenwhere.activities.TimeFragment;
import cs.hku.hk.whenwhere.model.Member;

public class OuterNavigationController extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Member user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outer_navigation);

        BottomNavigationView navigation=findViewById(R.id.outer_navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        loadFragment(new CalendarFragment());

        //获取当前用户
        user = (Member)getIntent().getSerializableExtra("user");
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment != null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.outer_fragment_container,fragment)
                    .commit();

            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId()){

            case R.id.navigation_calendar:
                fragment = new CalendarFragment();
                break;

            case R.id.navigation_activity:
                fragment = new ActivityFragment();
                break;

            case R.id.navigation_me:
                fragment = new MeFragment();
//                return loadActivity();
                break;
        }

        return loadFragment(fragment);
    }

    public boolean loadActivity(){
        Intent intent=new Intent();
        intent.setClass(OuterNavigationController.this, Register.class);
        startActivity(intent);
        return true;
    }

}
