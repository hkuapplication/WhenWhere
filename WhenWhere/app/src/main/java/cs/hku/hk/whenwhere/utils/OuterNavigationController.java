package cs.hku.hk.whenwhere.utils;

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
import cs.hku.hk.whenwhere.activities.MapFragment;
import cs.hku.hk.whenwhere.activities.MeFragment;
import cs.hku.hk.whenwhere.activities.MemberFragment;
import cs.hku.hk.whenwhere.activities.TimeFragment;

public class OuterNavigationController extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outer_navigation);

        BottomNavigationView navigation=findViewById(R.id.outer_navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new CalendarFragment());
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
                break;
        }

        return loadFragment(fragment);
    }
}
