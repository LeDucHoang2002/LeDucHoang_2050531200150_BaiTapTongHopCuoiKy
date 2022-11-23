package com.example.leduchoang_2050531200150_baitapcuoiky;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    StatusAdapter2 musicAdapter;
    RelativeLayout relativeLayout;
    FloatingActionButton floatingActionButton;
    BottomNavigationView bottomNavigationView;
    TextView menu1,menu2,menu3;
    RelativeLayout page,page1,page2;
    BadgeDrawable badgeDrawable;
    TextView name1,nameSingel1,time;
    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        menu1=findViewById(R.id.menu1);
        menu2=findViewById(R.id.menu2);
        menu3=findViewById(R.id.menu3);
        page=findViewById(R.id.page);
        page2=findViewById(R.id.page2);
        page1=findViewById(R.id.page1);
        bottomNavigationView=findViewById(R.id.bottom_navigtor);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        name1=findViewById(R.id.name1);
        nameSingel1=findViewById(R.id.nameSingel1);
        time=findViewById(R.id.time);
        img1=findViewById(R.id.img1);
        relativeLayout=findViewById(R.id.item);
        badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.nav_notification);
        badgeDrawable.setVisible(true);
        badgeDrawable.setBadgeTextColor(Color.WHITE);
        badgeDrawable.setNumber(4);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_list:
                        startActivity(new Intent(getApplicationContext(), ListStatusActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_notification:
                        startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_home:
                        return true;
                }
                return false;
            }
        });
        menu1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                menu1.setBackground(getDrawable(R.drawable.switch_trcks2));
                menu2.setBackground(getDrawable(R.drawable.switch_tumbs2));
                menu3.setBackground(getDrawable(R.drawable.switch_tumbs2));
                page.setVisibility(View.VISIBLE);
                page1.setVisibility(View.GONE);
                page2.setVisibility(View.GONE);
            }
        });
        menu2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                menu2.setBackground(getDrawable(R.drawable.switch_trcks2));
                menu1.setBackground(getDrawable(R.drawable.switch_tumbs2));
                menu3.setBackground(getDrawable(R.drawable.switch_tumbs2));
                page1.setVisibility(View.VISIBLE);
                page.setVisibility(View.GONE);
                page2.setVisibility(View.GONE);
            }
        });
        menu3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                menu3.setBackground(getDrawable(R.drawable.switch_trcks2));
                menu2.setBackground(getDrawable(R.drawable.switch_tumbs2));
                menu1.setBackground(getDrawable(R.drawable.switch_tumbs2));
                page2.setVisibility(View.VISIBLE);
                page1.setVisibility(View.GONE);
                page.setVisibility(View.GONE);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(HomeActivity.this,2));

        FirebaseRecyclerOptions<Status> options =
                new FirebaseRecyclerOptions.Builder<Status>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Status"), Status.class)
                        .build();

        musicAdapter = new StatusAdapter2(options);
        recyclerView.setAdapter(musicAdapter);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this, ListStatus2Activity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        musicAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        musicAdapter.stopListening();
    }

    private void txtSearch(String str) {
        FirebaseRecyclerOptions<Status> options =
                new FirebaseRecyclerOptions.Builder<Status>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Status").orderByChild("name").startAt(str).endAt(str+"~"), Status.class)
                        .build();

        musicAdapter = new StatusAdapter2(options);
        musicAdapter.startListening();
        recyclerView.setAdapter(musicAdapter);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}