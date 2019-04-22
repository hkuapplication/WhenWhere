package cs.hku.hk.whenwhere.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.adapters.Activity_recycle;
import cs.hku.hk.whenwhere.model.Activities;
import cs.hku.hk.whenwhere.model.Member;
import cs.hku.hk.whenwhere.utils.JSONHelper;


public class ActivityFragment extends Fragment {
   // private AppCompatActivity activity=Activity_List.this;
    private JSONHelper jsonHelper=JSONHelper.getInstance();
    private Member user;
    private RecyclerView recyclerView;
    //TODO
    private List<Activities> listActivities = new ArrayList<>();
    private Activity_recycle activity_recycle = new Activity_recycle(listActivities);

    private FloatingActionButton addActivityButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return inflater.inflate(R.layout.activity_list,null);
        View view = inflater.inflate(R.layout.activity_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewUsers);
        addActivityButton=view.findViewById(R.id.addActivityButton);
        initObjects();

        return view;
    }
    private void initObjects() {
        user = (Member)getActivity().getIntent().getSerializableExtra("user");

//        listActivities = new ArrayList<>();
//        activity_recycle = new Activity_recycle(listActivities);

//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(activity_recycle);

        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), CreateActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        //databaseHelper = new DatabaseHelper(activity);

        //String emailFromIntent = getIntent().getStringExtra("EMAIL");
        // textViewName.setText(emailFromIntent);

        getDataFromMYSQL();
    }

    private void getDataFromMYSQL() {
        //fake data
        Activities ac1=new Activities();
        ac1.setId(2);
        ac1.setName("dinner");
        ac1.setTime("2:00");
        ac1.setPlace("su");
        listActivities.add(ac1);
//        Activities ac2=new Activities();
//        ac2.setId(3);
//        ac2.setName("supper");
//        ac2.setTime("2:00");
//        ac2.setPlace("cym");
//        listActivities.add(ac2);
        System.out.println("假的活动列表长度:"+listActivities.size());

        final String url = "https://i.cs.hku.hk/~hdqi/WhenWhere/activity/getActivity.php" //TODO
                + "?action=getAllActivity&uid=" + user.getId();
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
                    parse_JSON_Array_and_Assign_Activity(jsonString);

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(activity_recycle);

                    alert( "Success", "Sucess load all activities." );
                } else {
                    alert( "Error", "Invalid email or password." );
                }
//                pdialog.hide();
            }

        }.execute("");


        System.out.println("真假活动列表长度:"+listActivities.size());
    }

    public void parse_JSON_Array_and_Assign_Activity(String JSONString){
        try{
            JSONArray jsonArray = new JSONArray(JSONString);
            System.out.println("JSON数组"+jsonArray.length());
            for(int i=0;i<jsonArray.length();i++){
                System.out.println("JSON对象:"+jsonArray.getJSONObject(i).getString("activity_name"));
                JSONObject jo = jsonArray.getJSONObject(i);
                Activities ac = new Activities();
                ac.setName(jo.getString("activity_name"));
                ac.setId(jo.getInt("aid"));
                int status = jo.getInt("status");

                if(status == 1){//TODO
                    ac.setTime("DECIDED");
                    ac.setPlace("DECIDED");
                }else{
                    ac.setTime("DISCUSSING");
                    ac.setPlace("DISCUSSING");
                }
                listActivities.add(ac);
                System.out.println("FOR循环里的活动列表长度:"+listActivities.size());
            }
            System.out.println("活动列表长度:"+listActivities.size());
        }catch(JSONException e){
            e.printStackTrace();
        }

    }

    protected void alert(String title, String mymessage){
        new AlertDialog.Builder(getActivity())
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
