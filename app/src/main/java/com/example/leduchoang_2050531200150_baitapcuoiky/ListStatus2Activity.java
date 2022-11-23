package com.example.leduchoang_2050531200150_baitapcuoiky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListStatus2Activity extends AppCompatActivity {
    private ImageView imgSignup;
    private TextView ten,tv_chitiet,tvClose,tv_sua;
    private EditText edit_chitiet;

    private RelativeLayout layout;
    private AppCompatButton btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_status2);
        ten = findViewById(R.id.textviewTen);
        imgSignup =findViewById(R.id.imgSignup);
        tv_chitiet=findViewById(R.id.tv_chitiet);
        edit_chitiet=findViewById(R.id.edit_chitiet);
        layout=findViewById(R.id.layout);
        btnOk=findViewById(R.id.btnOk);
        tvClose=findViewById(R.id.tvClose);
        tv_sua=findViewById(R.id.tv_sua);

        tv_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tg = tv_chitiet.getText().toString();
                layout.setVisibility(View.VISIBLE);
                edit_chitiet.setText(tg);
                tv_sua.setVisibility(View.GONE);
            }
        });
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.GONE);
                tv_sua.setVisibility(View.VISIBLE);
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tg = edit_chitiet.getText().toString();
                if (tg.equals("")){
                    layout.setVisibility(View.GONE);
                    tv_sua.setVisibility(View.GONE);
                }else {
                    tv_chitiet.setText(tg);
                    layout.setVisibility(View.GONE);
                    tv_sua.setVisibility(View.VISIBLE);}
            }
        });
        imgSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}