package cs.hku.hk.whenwhere.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs.hku.hk.whenwhere.R;

public class AddEventActivity extends AppCompatActivity {

    private Spinner s1,s2,s3,s4,s5,s6;
    private List<String> months;  private ArrayAdapter<String> madapter;
    private List<String> dates;
    private List<String> times;
    private String sMonth,sDate,sTime,eMonth,eDate,eTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        s1=(Spinner) findViewById(R.id.monthSpinner);s2=(Spinner) findViewById(R.id.datespinner); s3=(Spinner) findViewById(R.id.spinner2);
        s4=(Spinner) findViewById(R.id.monthSpinner2);s5=(Spinner) findViewById(R.id.dateSpinner2);s6=(Spinner) findViewById(R.id.spinner4);

        //months=new ArrayList<String>();
       // months.add("Jan");months.add("Feb");months.add("Mar");months.add("Apr");months.add("May");months.add("Jun");months.add("Jul");months.add("Aug");months.add("Sept");months.add("Oct");months.add("Nov");months.add("Dec");
        months= Arrays.asList("Jan","Feb","Mar");
        madapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,months);
        madapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s1.setAdapter(madapter); s4.setAdapter(madapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sMonth= madapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eMonth= madapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });








    }


}
