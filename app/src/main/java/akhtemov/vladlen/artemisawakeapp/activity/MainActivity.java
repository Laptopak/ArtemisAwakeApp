package akhtemov.vladlen.artemisawakeapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import akhtemov.vladlen.artemisawakeapp.Astronaut;
import akhtemov.vladlen.artemisawakeapp.Constant;
import akhtemov.vladlen.artemisawakeapp.R;

public class MainActivity extends AppCompatActivity {

    List<String> astronautList;
    List<Astronaut> astronautObjectList;
    ArrayAdapter<String> adapter;

    SwipeRefreshLayout swipeRefreshLayout;
    ListView astronautListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addSwipeRefreshLayoutListener();
        addOnItemClickListener();

        updateAstronautListView();
    }

    private void init() {
        astronautList = new ArrayList<>();
        astronautObjectList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, astronautList);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        astronautListView = findViewById(R.id.astornaut_list_view);
        astronautListView.setAdapter(adapter);
    }

    public void onClickAddAstronaut(View view) {
        startActivity(new Intent(this, AddAstronautActivity.class));
    }

    private void addSwipeRefreshLayoutListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateAstronautListView();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void updateAstronautListView() {
        Constant.ASTRONAUT_DATABASE_REFERENCE.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(astronautList.size() > 0) astronautList.clear();
                if(astronautObjectList.size() > 0) astronautObjectList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Astronaut astronaut = dataSnapshot.getValue(Astronaut.class);
                    assert astronaut != null;
                    astronautList.add(astronaut.name + " - " + astronaut.jobRole);
                    astronautObjectList.add(astronaut);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Exception on updateAstronautListView", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.notifyDataSetChanged();
        updateAgain();
    }

    private void updateAgain() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                updateAstronautListView();
            }
        };

        handler.postDelayed(runnable, 1000);
    }

    private void addOnItemClickListener() {
        astronautListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Astronaut astronaut = astronautObjectList.get(position);
                Intent intent = new Intent(MainActivity.this, AstronautActivity.class);

                intent.putExtra(Constant.POSITION, position);
                intent.putExtra(Constant.NAME, astronaut.name);
                intent.putExtra(Constant.MALE, astronaut.isMale);
                intent.putExtra(Constant.AGE, astronaut.age);
                intent.putExtra(Constant.HEIGHT, astronaut.height);
                intent.putExtra(Constant.MASS, astronaut.mass);
                intent.putExtra(Constant.ACTIVITY, astronaut.activity);
                intent.putExtra(Constant.DNA, astronaut.dna);
                intent.putExtra(Constant.JOB_ROLE, astronaut.jobRole);
                intent.putExtra(Constant.LEVEL_OF_IMPORTANCE, astronaut.levelOfImportance);
                intent.putExtra(Constant.TIME_ZONE, astronaut.timeZone);
                intent.putExtra(Constant.NOTE, astronaut.note);

                startActivity(intent);
            }
        });
    }
}