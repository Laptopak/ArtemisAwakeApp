package akhtemov.vladlen.artemisawakeapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import akhtemov.vladlen.artemisawakeapp.R;

public class AddCalendarEventActivity extends AppCompatActivity {

    private EditText taskNameEditText;
    private EditText taskNoteEditText;
    private EditText startTimeEditText;
    private EditText startDateEditText;
    private EditText endTimeEditText;
    private EditText endDateEditText;

    private int startYear;
    private int startMonth;
    private int startDay;

    private int startHourOfDay;
    private int startMinute;

    private int endYear;
    private int endMonth;
    private int endDay;

    private int endHourOfDay;
    private int endMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calendar_event);

        init();
        addEditTextOnClickListeners();
    }

    private void init() {
        taskNameEditText = findViewById(R.id.task_name_edit_text);
        taskNoteEditText = findViewById(R.id.task_note_edit_text);
        startTimeEditText = findViewById(R.id.start_time_edit_text);
        startDateEditText = findViewById(R.id.start_date_edit_text);
        endTimeEditText = findViewById(R.id.end_time_edit_text);
        endDateEditText = findViewById(R.id.end_date_edit_text);
    }

    private void addEditTextOnClickListeners() {
        startTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AddCalendarEventActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                startHourOfDay = hourOfDay;
                                startMinute = minute;

                                startTimeEditText.setText(hourOfDay + ":" + minute);
                            }
                        },
                        Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                        Calendar.getInstance().get(Calendar.MINUTE),
                        true
                );

                timePickerDialog.show();
            }
        });

        startDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddCalendarEventActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                startYear = year;
                                startMonth = month;
                                startDay = dayOfMonth;

                                startDateEditText.setText(year + "/" + month + "/" + dayOfMonth);
                            }
                        },
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            }
        });

        endTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AddCalendarEventActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                endHourOfDay = hourOfDay;
                                endMinute = minute;

                                endTimeEditText.setText(hourOfDay + ":" + minute);
                            }
                        },
                        Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                        Calendar.getInstance().get(Calendar.MINUTE),
                        true
                );

                timePickerDialog.show();
            }
        });

        endDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddCalendarEventActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                endYear = year;
                                endMonth = month;
                                endDay = dayOfMonth;

                                endDateEditText.setText(year + "/" + month + "/" + dayOfMonth);
                            }
                        },
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            }
        });
    }

    public void onClickSaveTask(View view) {
        String taskName = taskNameEditText.getText().toString();
        String taskNote = taskNoteEditText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, getBeginTime())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, getEndTime())
                .putExtra(CalendarContract.Events.TITLE, taskName)
                .putExtra(CalendarContract.Events.DESCRIPTION, taskNote)
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);

        startActivity(intent);
    }

    private long getBeginTime() {
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(startYear, startMonth, startDay, startHourOfDay, startMinute);

        return beginTime.getTimeInMillis();
    }

    private long getEndTime() {
        Calendar endTimeCalendar = Calendar.getInstance();
        endTimeCalendar.set(endYear, endMonth, endDay, endHourOfDay, endMinute);

        return endTimeCalendar.getTimeInMillis();
    }
}