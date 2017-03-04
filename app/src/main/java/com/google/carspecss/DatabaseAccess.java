package com.google.carspecss;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if(instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open () {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if(database!=null){
            this.database.close();
        }
    }

    public boolean check(String car) {
        String name;
        String[] args = new String[] {car};
        Cursor cursor = database.rawQuery("Select * from CarsInfo where Name = ?", args);
        if (cursor.moveToFirst()){
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }

    }

    public String getManu(String car) {
        String manuDet;
        String[] args = new String[] {car};
        Cursor cursor = database.rawQuery("Select * from CarsInfo where Name = ? ",args);
        cursor.moveToFirst();

        manuDet = cursor.getString(1);
        cursor.close();
        return manuDet;
    }

    public String getBstyle(String car) {
        String bstyleDet;
        String[] args = new String[] {car};
        Cursor cursor = database.rawQuery("Select * from CarsInfo where Name = ? ",args);
        cursor.moveToFirst();

        bstyleDet = cursor.getString(2);
        cursor.close();
        return bstyleDet;
    }

    public String getLayout(String car) {
        String layoutDet;
        String[] args = new String[] {car};
        Cursor cursor = database.rawQuery("Select * from CarsInfo where Name = ? ",args);
        cursor.moveToFirst();

        layoutDet = cursor.getString(3);
        cursor.close();
        return layoutDet;
    }

    public String getEngine(String car) {
        String engineDet;
        String[] args = new String[] {car};
        Cursor cursor = database.rawQuery("Select * from CarsInfo where Name = ? ",args);
        cursor.moveToFirst();

        engineDet = cursor.getString(4);
        cursor.close();
        return engineDet;
    }

    public String getDisp(String car) {
        String dispDet;
        String[] args = new String[] {car};
        Cursor cursor = database.rawQuery("Select * from CarsInfo where Name = ? ",args);
        cursor.moveToFirst();

        dispDet = cursor.getString(5);
        cursor.close();
        return dispDet;
    }

    public String getPower(String car) {
        String powerDet;
        String[] args = new String[] {car};
        Cursor cursor = database.rawQuery("Select * from CarsInfo where Name = ? ",args);
        cursor.moveToFirst();

        powerDet = cursor.getString(6);
        cursor.close();
        return powerDet;
    }

    public String getTorq(String car) {
        String torqDet;
        String[] args = new String[] {car};
        Cursor cursor = database.rawQuery("Select * from CarsInfo where Name = ? ",args);
        cursor.moveToFirst();

        torqDet = cursor.getString(7);
        cursor.close();
        return torqDet;
    }

    public String getCO2(String car) {
        String CO2Det;
        String[] args = new String[] {car};
        Cursor cursor = database.rawQuery("Select * from CarsInfo where Name = ? order by Name ",args);
        cursor.moveToFirst();

        CO2Det = cursor.getString(8);
        cursor.close();
        return CO2Det;
    }

    public List<String> getAllManu() {
        List<String> manufacturers = new ArrayList<>();
        Cursor cursor = database.rawQuery("Select * from Brands order by Brands " , null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            manufacturers.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return manufacturers;
    }

    public List<String> getAllCars(String brand) {
        List<String> cars = new ArrayList<>();
        String[] args = new String[] {brand};
        Cursor cursor = database.rawQuery("Select Name from CarsInfo where Manufacturer = ? order by Name ",args );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            cars.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return cars;
    }

}
