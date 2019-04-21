package cs.hku.hk.whenwhere.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.model.Member;
import cs.hku.hk.whenwhere.utils.JSONHelper;
import cs.hku.hk.whenwhere.utils.OuterNavigationController;
import cs.hku.hk.whenwhere.utils.TimestampHelper;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private TimestampHelper timestampHelper=TimestampHelper.getInstance();
    private JSONHelper jsonHelper = JSONHelper.getInstance();
    private Member user;

    private int mYear,mDay,mMonth;
    private int eYear,eDay,eMonth;
    private int datenum,timenum;
//    private int dtime,atime;
    private Button confirm;
    TextView startDate, endDate, aTime, dTime;
    EditText activityName;

    //需要存入数据库中的数据
    private String activityNameSQL = "";
    private int startDateSQL = 0;
    private int endDateSQL = 0;
    private int activityTimeSQL = 0;
    private int discussTimeSQL = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity);

        user = (Member)getIntent().getSerializableExtra("user");
        System.out.println("创建活动获得用户："+user.getId());

        activityName = (EditText)findViewById(R.id.activityName);
        startDate=(TextView)findViewById(R.id.startDate);
        endDate=findViewById(R.id.endDate);
        aTime=findViewById(R.id.activitySession);
        dTime=findViewById(R.id.discussSession);
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
        aTime.setOnClickListener(this);
        dTime.setOnClickListener(this);
        confirm=(Button)findViewById(R.id.button);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //之后在button事件里加入数据库
        month+=1;
        String desc= String.format("%d-%d-%d",year,month,dayOfMonth);

//        System.out.println("日期的选取"+desc);
        if(datenum==1)
        {
            mYear=year;
            mMonth=month;
            mDay=dayOfMonth;
            startDate.setText(desc);
            startDateSQL = timestampHelper.getDateTimestamp(year,month,dayOfMonth);
            System.out.println("创造活动的时间戳Startdate:"+startDateSQL);
        }
        if(datenum==2)
        {
            eYear=year;
            eMonth=month;
            eDay=dayOfMonth;
            endDate.setText(desc);
            endDateSQL = timestampHelper.getDateTimestamp(year,month,dayOfMonth);
            System.out.println("创造活动的时间戳Enddate:"+endDateSQL);
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String desc=String.format("%dh %dm",hourOfDay,minute);
        if(timenum==1)
        {
//            dtime=hourOfDay*60+minute;  //discuss time (min)
            aTime.setText(desc);
            activityTimeSQL=timestampHelper.getMinuteTimestamp(hourOfDay,minute);
        }
        if(timenum==2)
        {
//            atime=hourOfDay*60+minute;
            dTime.setText(desc);
            discussTimeSQL=timestampHelper.getMinuteTimestamp(hourOfDay,minute);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.startDate)
        {
            datenum=1;
            Calendar ca= Calendar.getInstance();
            DatePickerDialog dialog=new DatePickerDialog(this,this,
            ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }
        if(v.getId()==R.id.endDate)
        {
            datenum=2;
            Calendar ca= Calendar.getInstance();
            DatePickerDialog dialog=new DatePickerDialog(this,this,
                    ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }
        if(v.getId()==R.id.activitySession)
        {
            timenum=1;
            Calendar calendar=Calendar.getInstance();
            TimePickerDialog dialog=new TimePickerDialog(this,this,
                calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
            dialog.show();
        }
        if(v.getId()==R.id.discussSession)
        {
            timenum=2;
            Calendar calendar=Calendar.getInstance();
            TimePickerDialog dialog=new TimePickerDialog(this,this,
                    calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
            dialog.show();
        }
        if(v.getId()==R.id.button)
        {
            activityNameSQL=activityName.getText().toString();

            if(activityNameSQL.equals("")||startDateSQL==0||endDateSQL==0
                ||activityTimeSQL==0||discussTimeSQL==0){
                //有数据没有填写
                alert("Warn","Please complete all information.");
            }
            else{
                if(endDateSQL<startDateSQL){
                    alert("Warn","Invalid activity date period.");
                }
                //信息完整，日期合理
                else{
                    System.out.println("用户名id:"+user.getId());
                    connect();
                    //将数据加入数据库中
                    //back to activity list
                }
            }

//            Intent intent=new Intent(CreateActivity.this, OuterNavigationController.class);
//            startActivity(intent);
        }

    }
    private void connect(){
        //用到uid,all SQL
//        user=(Member)getIntent().getSerializableExtra("user");
        final String url = "https://i.cs.hku.hk/~hdqi/WhenWhere/activity/createActivity.php" //TODO
                + "?action=createActivity&activityName="
                + android.net.Uri.encode(activityNameSQL, "UTF-8")
                + "&startDate=" + startDateSQL
                + "&endDate=" + endDateSQL
                + "&activityTime=" + activityTimeSQL
                + "&discussTime=" + discussTimeSQL
                + "&uid=" + user.getId();
        System.out.println("网址："+url);
        AsyncTask<String, Void, String> task = new AsyncTask<String,
                Void, String>() {
            boolean success;
            String jsonString;
            @Override
            protected String doInBackground(String... arg0) {
// TODO Auto-generated method stub
                success = true;
                jsonString = jsonHelper.getJsonPage(url);
                if ((!jsonString.isEmpty())&&(!jsonString.startsWith("success"))){
                    success = false;
                }
                return null;
            }
            @Override
            protected void onPostExecute(String result) {
                if (success) {
                    alert( "Success", "Successfully create an activity!" );
                } else {
                    alert( "Warn", "Fail to create an activity." );
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
