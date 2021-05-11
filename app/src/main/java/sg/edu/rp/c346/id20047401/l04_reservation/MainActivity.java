package sg.edu.rp.c346.id20047401.l04_reservation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    Button confirm, reset;
    CheckBox smoke;
    EditText name, phone, size;
    DatePicker date;
    TimePicker time;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        confirm = findViewById(R.id.btnConfirm);
        reset = findViewById(R.id.btnReset);
        smoke = findViewById(R.id.checkBoxSmoking);
        name = findViewById(R.id.editTextName);
        phone = findViewById(R.id.editTextPhone);
        size = findViewById(R.id.editTextSize);
        date = findViewById(R.id.datePicker);
        time = findViewById(R.id.timePicker);

        date.updateDate(2021,5, 1);
        time.setCurrentHour(19);
        time.setCurrentMinute(30);


        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() { // Set Time avaiable between 8AM to
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(time.getCurrentHour() < 8) {
                    Toast.makeText(MainActivity.this, "Open after 8AM", Toast.LENGTH_LONG).show();
                    time.setCurrentHour(8);
                    time.setCurrentMinute(0);
                }
                if(time.getCurrentHour() > 20) {
                    Toast.makeText(MainActivity.this, "Closes at 9PM", Toast.LENGTH_LONG).show();
                    time.setCurrentHour(20);
                    time.setCurrentMinute(59);
                }
            }
        });
        date.setMinDate(System.currentTimeMillis());// Set Minimum Date to today

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isSmoke;
                if(smoke.isChecked()){
                    isSmoke = "Smoking area";
                }else {
                    isSmoke = "No smoking area";
                }

                if(name.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Name is empty", Toast.LENGTH_LONG).show();
                } else if (phone.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Phone is empty", Toast.LENGTH_LONG).show();
                } else if (size.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Size is empty", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Name: " + name.getText().toString()
                            + "\nPhone: " + phone.getText().toString()
                            + "\nSize: " + size.getText().toString()
                            + "\n" + isSmoke
                            + "\nDate: " + date.getDayOfMonth() + "/" + (date.getMonth()+1) + "/" + date.getYear()
                            + "\nTime: " + time.getCurrentHour() + ":" + String.format("%02d",time.getCurrentMinute()), Toast.LENGTH_LONG).show();
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                phone.setText("");
                size.setText("");
                date.updateDate(2021,5, 1);
                time.setCurrentHour(19);
                time.setCurrentMinute(30);
                smoke.setChecked(false);
            }
        });


    }
}