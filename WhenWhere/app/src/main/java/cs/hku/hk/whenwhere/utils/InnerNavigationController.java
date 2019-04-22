package cs.hku.hk.whenwhere.utils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.activities.MapFragment;
import cs.hku.hk.whenwhere.activities.MemberFragment;
import cs.hku.hk.whenwhere.activities.TimeFragment;
import cs.hku.hk.whenwhere.model.Member;

public class InnerNavigationController extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Member user;
    private int aid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inner_navigation);

        BottomNavigationView navigation=findViewById(R.id.inner_navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new MemberFragment());

        //获取当前用户
        user = (Member)getIntent().getSerializableExtra("user");
        aid = (int) getIntent().getIntExtra("aid",-1);
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment != null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.inner_fragment_container,fragment)
                    .commit();

            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId()){

            case R.id.navigation_member:
                fragment = new MemberFragment();
                break;

            case R.id.navigation_map:
                fragment = new MapFragment();
                break;

            case R.id.navigation_time:
                fragment = new TimeFragment();
                break;
        }

        return loadFragment(fragment);
    }
}
