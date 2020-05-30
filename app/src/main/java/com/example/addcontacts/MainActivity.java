package com.example.addcontacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    MyDbHandler dbHandler;
    ArrayList<MyData>arrayList;
    ImageView imageView;
    byte[] image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHandler=new MyDbHandler(this,"contac1",null,1);
        Temp.setDbHandler(dbHandler);
        arrayList=dbHandler.showAll();
        ShowActivity.arrayList=arrayList;

        recyclerView=findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new MyCustomAdapter(arrayList,MainActivity.this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.add)
        {
            LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
            View view=inflater.inflate(R.layout.custom_alert_dialog,null);
            final EditText name=view.findViewById(R.id.editText);
            final EditText address=view.findViewById(R.id.editText2);
            imageView=view.findViewById(R.id.imageView_dialog);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera,0);
                }
            });

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setView(view);
            builder.setCancelable(false);
            builder.setPositiveButton("save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(image!=null && image.length!=0 && name.getText()!=null && !name.getText().toString().isEmpty() && address.getText()!=null && !address.getText().toString().isEmpty()){
                        MyData myData=new MyData();
                        myData.setName(name.getText().toString());
                        myData.setAddress(address.getText().toString());
                        myData.setImage(image);
                        int i=dbHandler.insert(myData);
                        if(i>0){
                            arrayList.add(myData);
                            Toast.makeText(MainActivity.this,"data inserted",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(MainActivity.this,"fill all",Toast.LENGTH_SHORT).show();
                    }
                }
            })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .create().show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            switch (requestCode) {
                case 0: {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(bitmap);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
                    image = stream.toByteArray();
                    break;
                }
            }
        }
        else if(resultCode==RESULT_CANCELED){
            Toast.makeText(MainActivity.this,"No Image Selected",Toast.LENGTH_SHORT).show();
        }
    }
}
