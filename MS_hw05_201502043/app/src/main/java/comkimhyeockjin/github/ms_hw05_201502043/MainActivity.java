package comkimhyeockjin.github.ms_hw05_201502043;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText phoneNum;
    Button day;
    Button time;
    int year;
    int month;
    int dayofMonth;
    int hour;
    int minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        phoneNum = (EditText) findViewById(R.id.phoneNum);
        day = (Button) findViewById(R.id.day);
        time = (Button) findViewById(R.id.time);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        day.setText(year +"년 " + (month+1) + "월 " + dayofMonth + "일");
        time.setText(hour + "시 " + minute +"분");

    }

    public void dateBtn(View v) {
        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                day.setText(y + "년 " + (m+1) + "월 "+ d + "일");
                year = y;
                month = m;
                dayofMonth = d;
            }
        }, year, month, dayofMonth).show();
    }

    public void timeBtn(View v) {
        new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int m) {
                time.setText(hourOfDay + "시 " + minute + "분");
                hour = hourOfDay;
                minute = m;
            }
        }, hour, minute, false).show();
    }

    public void saveBtn(View v) {
        Toast.makeText(getApplicationContext(),
                "이름 : " + name.getText()  +", 번호 : "+phoneNum.getText()
                +",\n날짜: "+ day.getText() + ", 시간:" + time.getText() , Toast.LENGTH_LONG).show();
    }
}
