package cs.hku.hk.whenwhere.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import cs.hku.hk.whenwhere.R;

public class AddEventActivity extends AppCompatActivity {
    /*1.define the spinners
    Spinner startTime,startMonth,startDay,endMonth,endDate,endTime;
    //2.set the spinners
    String months[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};
    String days[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    //adapter
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterD;
    //define variables to store what the user chooses
    String sMonth,sDay,eMonth,eDay; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        /* startTime = (Spinner) findViewById(R.id.startTime);
         startMonth = (Spinner) findViewById(R.id.startMonth);
         startDay = (Spinner) findViewById(R.id.startDay);
         endMonth = (Spinner) findViewById(R.id.spinner3);
         endDate = (Spinner) findViewById(R.id.endDate);
         endTime = (Spinner) findViewById(R.id.spinner4);

        adapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, months);
        adapterD = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, days);
        startMonth.setAdapter(adapter); startDay.setAdapter(adapterD);
        endMonth.setAdapter(adapter);   endDate.setAdapter(adapterD);

        //--------------months-------------------------------------------------------------
        endMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        eMonth = "Jan";
                        break;
                    case 1:
                        eMonth = "Feb";
                        break;
                    case 2:
                        eMonth = "Mar";
                        break;
                    case 3:
                        eMonth = "Apr";
                        break;
                    case 4:
                        eMonth = "May";
                        break;
                    case 5:
                        eMonth = "Jun";
                        break;
                    case 6:
                        eMonth = "Jul";
                        break;
                    case 7:
                        eMonth = "Aug";
                        break;
                    case 8:
                        eMonth = "Sept";
                        break;
                    case 9:
                        eMonth = "Oct";
                        break;
                    case 10:
                        eMonth = "Nov";
                        break;
                    case 11:
                        eMonth = "Dec";
                        break;
                }
            }
        });

        startMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        sMonth = "Jan";
                        break;
                    case 1:
                        sMonth = "Feb";
                        break;
                    case 2:
                        sMonth = "Mar";
                        break;
                    case 3:
                        sMonth = "Apr";
                        break;
                    case 4:
                        sMonth = "May";
                        break;
                    case 5:
                        sMonth = "Jun";
                        break;
                    case 6:
                        sMonth = "Jul";
                        break;
                    case 7:
                        sMonth = "Aug";
                        break;
                    case 8:
                        sMonth = "Sept";
                        break;
                    case 9:
                        sMonth = "Oct";
                        break;
                    case 10:
                        sMonth = "Nov";
                        break;
                    case 11:
                        sMonth = "Dec";
                        break;


                }
            }
        });

        //----------date--------------------------------------- */

    }
}
