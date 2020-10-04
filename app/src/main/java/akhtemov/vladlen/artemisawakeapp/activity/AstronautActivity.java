package akhtemov.vladlen.artemisawakeapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import akhtemov.vladlen.artemisawakeapp.Constant;
import akhtemov.vladlen.artemisawakeapp.R;

public class AstronautActivity extends AppCompatActivity {

    TextView positionTextView;

    TextView nameTextView;
    TextView maleTextView;
    TextView ageTextView;
    TextView heightTextView;
    TextView massTextView;
    TextView activityTextView;
    TextView dnaTextView;

    TextView jobRoleTextView;
    TextView levelOfImportanceTextView;

    TextView timeZoneTextView;
    TextView noteTextView;

    TextView bmrTextView;
    TextView bmrWithActivityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astronaut);

        init();
        setTexts();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void init() {
        positionTextView = findViewById(R.id.position_text_view);

        nameTextView = findViewById(R.id.name_text_view);
        maleTextView = findViewById(R.id.male_text_view);
        ageTextView = findViewById(R.id.age_text_view);
        heightTextView = findViewById(R.id.height_text_view);
        massTextView = findViewById(R.id.mass_text_view);
        activityTextView = findViewById(R.id.activity_text_view);
        dnaTextView = findViewById(R.id.dna_text_view);

        jobRoleTextView = findViewById(R.id.job_role_text_view);
        levelOfImportanceTextView = findViewById(R.id.level_of_importance_text_view);

        timeZoneTextView = findViewById(R.id.time_zone_text_view);
        noteTextView = findViewById(R.id.note_text_view);

        bmrTextView = findViewById(R.id.bmr_text_view);
        bmrWithActivityTextView = findViewById(R.id.bmr_with_activity_text_view);
    }

    private void setTexts() {
        double mass = Double.valueOf(getIntent().getStringExtra(Constant.MASS));
        double height = Double.valueOf(getIntent().getStringExtra(Constant.HEIGHT));
        double age = Double.valueOf(getIntent().getStringExtra(Constant.AGE));
        int male = Integer.valueOf(getIntent().getStringExtra(Constant.MALE));

        double bmr = 0;
        double bmrWithActivity = 0;
        int activity = Integer.valueOf(getIntent().getStringExtra(Constant.ACTIVITY));

        if (male == 1) {
            bmr = 66.47 + (13.75 * mass) + (5.003 * height) - (6.775 * age);

            if (activity == 1) {
                bmrWithActivity = bmr * 1.2;
            } else if (activity == 2) {
                bmrWithActivity = bmr * 1.55;
            } else if (activity == 3) {
                bmrWithActivity = bmr * 1.9;
            }

        } else if (male == 0) {
            bmr = 655.1 + (9.563 * mass) + (1.850 * height) - (4.676 * age);

            if (activity == 1) {
                bmrWithActivity = bmr * 1.2;
            } else if (activity == 2) {
                bmrWithActivity = bmr * 1.55;
            } else if (activity == 3) {
                bmrWithActivity = bmr * 1.9;
            }
        }


        positionTextView.setText("Astronaut #" + (getIntent().getIntExtra(Constant.POSITION, 0) + 1));

        nameTextView.setText("Name: " + getIntent().getStringExtra(Constant.NAME));
        maleTextView.setText("isMale: " + male);
        ageTextView.setText("Age: " + age);
        heightTextView.setText("Height: " + height);
        massTextView.setText("Mass: " + mass);
        activityTextView.setText("Activity: " + activity);
        dnaTextView.setText("DNA: " + getIntent().getStringExtra(Constant.DNA));

        jobRoleTextView.setText("Job Role: " + getIntent().getStringExtra(Constant.JOB_ROLE));
        levelOfImportanceTextView.setText("Level Of Importance (1-10): " + getIntent().getStringExtra(Constant.LEVEL_OF_IMPORTANCE));

        timeZoneTextView.setText("Time Zone (Number): " + getIntent().getStringExtra(Constant.TIME_ZONE));
        noteTextView.setText("Notes: " + getIntent().getStringExtra(Constant.NOTE));

        bmrTextView.setText("BMR: " + bmr + " kcal");
        bmrWithActivityTextView.setText("BMR with activity: " + bmrWithActivity + " kcal");
    }



    public void onClickAddCalendarEvent(View view) {
        startActivity(new Intent(this, AddCalendarEventActivity.class));
    }
}