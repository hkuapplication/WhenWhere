package cs.hku.hk.whenwhere.activities;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.adapters.Activity_recycle;
import cs.hku.hk.whenwhere.adapters.Member_recycle;
import cs.hku.hk.whenwhere.model.Activities;
import cs.hku.hk.whenwhere.model.Member;
import cs.hku.hk.whenwhere.utils.InnerNavigationController;
import cs.hku.hk.whenwhere.utils.JSONHelper;

public class MemberFragment extends Fragment {
    //  private AppCompatActivity activity=MemberFragment.this;

    private int aid;

    private JSONHelper jsonHelper=JSONHelper.getInstance();

    private RecyclerView recyclerView;
    private List<Member> listMembers;
    private Member_recycle member_recycle;
    private TextView add;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.member_list,null);
        //getSupportActionBar().setTitle("");
        recyclerView = view.findViewById(R.id.recyclerViewUsers);
        initObjects();
        add=(TextView) view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), AddMemberActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
    private void initObjects() {
        listMembers = new ArrayList<>();
        member_recycle = new Member_recycle(listMembers);
        //databaseHelper = new DatabaseHelper(activity);

        //String emailFromIntent = getIntent().getStringExtra("EMAIL");
        // textViewName.setText(emailFromIntent);
        aid = getActivity().getIntent().getIntExtra("aid",-1);

        getDataFromMYSQL();
    }

    private void getDataFromMYSQL() {
        //fake data
        Member ac1=new Member();
        ac1.setId(2);
        ac1.setName("dinner");
        ac1.setEmail("abc@gmail.com");
        listMembers.add(ac1);
        System.out.println("假的成员列表长度:"+listMembers.size());

        final String url = "https://i.cs.hku.hk/~hdqi/WhenWhere/activity/getActivityMember.php" //TODO
                + "?action=getActivityMember&aid=" + aid;
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
                    parse_JSON_Array_and_Assign_Member(jsonString);



                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(member_recycle);

                    alert( "Success", "Sucess load all members." );
                } else {
                    alert( "Error", "Fail to load all members." );
                }
            }

        }.execute("");
        System.out.println("真假成员列表长度:"+listMembers.size());

    }

    public void parse_JSON_Array_and_Assign_Member(String JSONString){
        try{
            JSONArray jsonArray = new JSONArray(JSONString);
            System.out.println("JSON数组"+jsonArray.length());
            for(int i=0;i<jsonArray.length();i++){
               // System.out.println("JSON对象:"+jsonArray.getJSONObject(i).getString("username"));
                JSONObject jo = jsonArray.getJSONObject(i);

                Member member = new Member();
                member.setEmail(jo.getString("email"));
                member.setName(jo.getString("username"));
                member.setId(jo.getInt("uid"));

                listMembers.add(member);
                System.out.println("FOR循环里的成员列表长度:"+listMembers.size());
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        System.out.println("成员列表长度:"+listMembers.size());

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