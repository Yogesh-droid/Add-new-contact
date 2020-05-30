package com.example.addcontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    public static ArrayList<MyData>arrayList;
    TextView tv1,tv2;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        tv1=findViewById(R.id.adapter_tv1);
        tv2=findViewById(R.id.adapter_tv2);
        imageView=findViewById(R.id.image_show);

        int position=getIntent().getExtras().getInt("position");
        tv1.setText(arrayList.get(position).getName());
        tv2.setText(arrayList.get(position).getAddress());
        byte[] bytes=arrayList.get(position).getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        imageView.setImageBitmap(bitmap);


    }
}
