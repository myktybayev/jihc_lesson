package kz.jihc.registration.university;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import kz.jihc.registration.R;

public class UniversityListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<University> universityList;
    RecyclerView.LayoutManager linearLayoutManager, gridLayoutManager;
    UniversityListAdapter universityListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_list);

        recyclerView = findViewById(R.id.recyclerView);

        universityList = new ArrayList<>();
        universityList.add(new University("https://studykazakhstan.com/uploads/images/universities/logo-153673757918096hUH.png", "SDU", "Almaty", "87471112233"));
        universityList.add(new University("https://pbs.twimg.com/profile_images/955414941/IT.jpg", "MUIT", "Almaty", "87471112233"));
        universityList.add(new University("https://admin.vipusknik.kz/storage/1487/img-0300jpg.jpeg","TarGu", "Taraz", "87471112233"));
        universityList.add(new University("https://admin.vipusknik.kz/storage/1487/img-0300jpg.jpeg","TIGu", "Taraz", "87775555555"));
        universityList.add(new University("https://admin.vipusknik.kz/storage/1487/img-0300jpg.jpeg","TIGu", "Taraz", "87775555555"));
        
        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        universityListAdapter = new UniversityListAdapter(this, universityList);
        recyclerView.setAdapter(universityListAdapter);
    }
}