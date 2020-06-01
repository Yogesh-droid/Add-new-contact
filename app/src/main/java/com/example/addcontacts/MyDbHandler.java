package com.example.addcontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDbHandler extends SQLiteOpenHelper {
    private static String TABLE_NAME="contacts";
    private static String KEY_NAME="name";
    private static String KEY_ADDRESS="address";
    private static String KEY_IMAGE="image";
    private static String KEY_PHONE="phone";
    ArrayList<MyData>arrayList;

    public MyDbHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="create table "+TABLE_NAME+"("+KEY_NAME+" text,"+KEY_ADDRESS+" text,"+KEY_IMAGE+" blob,"+KEY_PHONE+" TEXT)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int insert(MyData myData) {
        int i=0;
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,myData.getName());
        values.put(KEY_ADDRESS,myData.getAddress());
        values.put(KEY_IMAGE,myData.getImage());
        values.put(KEY_PHONE,myData.getMobile());
        database.insert(TABLE_NAME,null,values);
        i=1;
        return i;
    }

    public ArrayList<MyData> showAll() {
        arrayList=new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("select *from "+TABLE_NAME,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                String name=cursor.getString(0);
                String addr=cursor.getString(1);
                byte[] image=cursor.getBlob(2);
                String mo=cursor.getString(3);
                MyData myData=new MyData();
                myData.setName(name);
                myData.setAddress(addr);
                myData.setImage(image);
                myData.setMobile(mo);

                arrayList.add(myData);
            }
        }
        return arrayList;
    }

    public void delete(String name) {
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete(TABLE_NAME,"name=?",new String[]{name});
    }
}
