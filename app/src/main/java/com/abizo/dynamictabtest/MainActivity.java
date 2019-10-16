package com.abizo.dynamictabtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CollectionReference tabsRef;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<String> names = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabsRef = FirebaseFirestore.getInstance().collection("Tabs");

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        setUpTabs();
    }

    public void setUpTabs() {
        Query query = tabsRef;
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot d : queryDocumentSnapshots) {
                    String name = d.get("name").toString();
                    names.add(name);
                    tabLayout.addTab(tabLayout.newTab().setText(name));
                }
                fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), names);
                viewPager.setAdapter(fragmentAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Avery", e.getMessage());
            }
        });
    }
}
