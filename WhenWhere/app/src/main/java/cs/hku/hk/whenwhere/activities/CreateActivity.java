package cs.hku.hk.whenwhere.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import cs.hku.hk.whenwhere.R;
import cs.hku.hk.whenwhere.utils.OuterNavigationController;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private int mYear,mDay,mMonth;
    private int eYear,eDay,eMonth;
    private int datenum,timenum;
    private int dtime,atime;
    private Button confirm;
    TextView startDate, endDate, aTime, dTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity);
        startDate=(TextView)findViewById(R.id.startDate);
        endDate=findViewById(R.id.endDate);
        aTime=findViewById(R.id.activitysession);
        dTime=findViewById(R.id.discuss_session);
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
        String desc= String.format("%d-%d-%d",year,month+1,dayOfMonth);
        if(datenum==1)
        {
            mYear=year;
            mMonth=month;
            mDay=dayOfMonth;
            startDate.setText(desc);
        }
        if(datenum==2)
        {
            eYear=year;
            eMonth=month;
            eDay=dayOfMonth;
            endDate.setText(desc);
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String desc=String.format("%dh %dm",hourOfDay,minute);
        if(timenum==1)
        {
            dtime=hourOfDay*60+minute;
            aTime.setText(desc);
        }
        if(timenum==2)
        {
            atime=hourOfDay*60+minute;
            dTime.setText(desc);
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
        if(v.getId()==R.id.activitysession)
        {
            timenum=1;
            Calendar calendar=Calendar.getInstance();
            TimePickerDialog dialog=new TimePickerDialog(this,this,
                calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
            dialog.show();
        }
        if(v.getId()==R.id.discuss_session)
        {
            timenum=2;
            Calendar calendar=Calendar.getInstance();
            TimePickerDialog dialog=new TimePickerDialog(this,this,
                    calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
            dialog.show();
        }
        if(v.getId()==R.id.button)
        {
            //将数据加入数据库中
            //back to activity list
            Intent intent=new Intent(CreateActivity.this, OuterNavigationController.class);
            startActivity(intent);
        }
    }
}
