package com.example.leduchoang_2050531200150_baitapcuoiky;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView lich;
    EditText ngaysinh;
    ImageView avata;
    int day,month,year;
    int REQUEST_CODE_IMAGE =1;
    BadgeDrawable badgeDrawable;
    ImageButton Logout;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        lich=findViewById(R.id.lich);
        Logout=findViewById(R.id.logout);
        ngaysinh=findViewById(R.id.tvNgaySinh);
        avata=findViewById(R.id.circleImageView);
        bottomNavigationView=findViewById(R.id.bottom_navigtor);
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);
        badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.nav_notification);
        badgeDrawable.setVisible(true);
        badgeDrawable.setBadgeTextColor(Color.WHITE);
        badgeDrawable.setNumber(4);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_list:
                        startActivity(new Intent(getApplicationContext(), ListStatusActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_notification:
                        startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_profile:
                        return true;
                }
                return false;
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProfileActivity.this, SingInSignUp.class);
                startActivity(i);
            }
        });
        lich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                showDialog(113);
            }
        });
        avata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if(id==113)
        {
            return new DatePickerDialog(this, dateChange, year, month, day);
        }
        return null;
    }
    /**
     * xử lý DatePickerDialog
     */
    private DatePickerDialog.OnDateSetListener dateChange= new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year1, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            year=year1;
            month=monthOfYear;
            day=dayOfMonth;
            EditText eDate=(EditText) findViewById(R.id.tvNgaySinh);
            eDate.setText(day+"-"+(month+1)+"-"+year);
        }
    };
    /**
     * thiết lập ngày tháng năm hiện tại
     */
    public void setCurrentDateOnView()
    {
        EditText eDate=(EditText) findViewById(R.id.tvNgaySinh);
        Calendar cal=Calendar.getInstance();
        day=cal.get(Calendar.DAY_OF_MONTH);
        month=cal.get(Calendar.MONTH);
        year=cal.get(Calendar.YEAR);
        eDate.setText(day+"-"+(month+1)+"-"+year);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==REQUEST_CODE_IMAGE && resultCode==RESULT_OK && data !=null){
            Bitmap bitmap=(Bitmap) data.getExtras().get("data");
            avata.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}