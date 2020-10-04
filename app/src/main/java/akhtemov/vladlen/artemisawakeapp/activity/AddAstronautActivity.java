package akhtemov.vladlen.artemisawakeapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import akhtemov.vladlen.artemisawakeapp.Astronaut;
import akhtemov.vladlen.artemisawakeapp.Constant;
import akhtemov.vladlen.artemisawakeapp.R;

public class AddAstronautActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText maleEditText;
    private EditText ageEditText;
    private EditText heightEditText;
    private EditText massEditText;
    private EditText activityEditText;
    private EditText dnaEditText;
    private EditText jobRoleEditText;
    private EditText levelOfImportanceEditText;
    private EditText timezoneEditText;
    private EditText noteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_astronaut);

        nameEditText = findViewById(R.id.name_edit_text);
        maleEditText = findViewById(R.id.male_edit_text);
        ageEditText = findViewById(R.id.age_edit_text);
        heightEditText = findViewById(R.id.height_edit_text);
        massEditText = findViewById(R.id.mass_edit_text);
        activityEditText = findViewById(R.id.activity_edit_text);
        dnaEditText = findViewById(R.id.dna_edit_text);
        jobRoleEditText = findViewById(R.id.job_role_edit_text);
        levelOfImportanceEditText = findViewById(R.id.level_of_importance_edit_text);
        timezoneEditText = findViewById(R.id.timezone_edit_text);
        noteEditText = findViewById(R.id.note_edit_text);
    }

    public void onClickSaveAstronaut(View view) {
        String id = Constant.ASTRONAUT_DATABASE_REFERENCE.getKey();
        String name = nameEditText.getText().toString();
        String isMale = maleEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String height = heightEditText.getText().toString();
        String mass = massEditText.getText().toString();
        String activity = activityEditText.getText().toString();
        String dna = dnaEditText.getText().toString();
        String jobRole = jobRoleEditText.getText().toString();
        String levelOfImportance = levelOfImportanceEditText.getText().toString();
        String timezone = timezoneEditText.getText().toString();
        String note = noteEditText.getText().toString();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(dna) && !TextUtils.isEmpty(timezone)) {
            Astronaut astronaut = new Astronaut(id, name, isMale, age, height, mass, activity, dna, jobRole, levelOfImportance, timezone, note);
            Constant.ASTRONAUT_DATABASE_REFERENCE.push().setValue(astronaut);
            Toast.makeText(this, "Astronaut Added", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}