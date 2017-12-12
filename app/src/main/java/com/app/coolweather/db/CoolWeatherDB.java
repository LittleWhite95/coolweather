package com.app.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.coolweather.model.City;
import com.app.coolweather.model.County;
import com.app.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kzj54 on 2017/12/12.
 */

public class CoolWeatherDB {
    public static final String DB_NAME = "cool_weather";
    private static final int VERSION = 1;
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;

    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB == null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvince_name());
            values.put("province_code", province.getProvince_code());
            db.insert("Province", null, values);

        }
    }

    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                province.setProvince_code(cursor.getString(cursor.getColumnIndex("province_code")));
                province.setProvince_name(cursor.getString(cursor.getColumnIndex("province_name")));
                list.add(province);

            } while (cursor.moveToNext());
        }
        return list;
    }

    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCity_name());
            values.put("city_code", city.getCity_code());
            values.put("province_id", city.getProvince_id());
            db.insert("City", null, values);
        }
    }

    public List<City> loadCity() {
        List<City> list = new ArrayList<>();
        Cursor cursor = db.query("City", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setCity_code(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setCity_name(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setProvince_id(cursor.getString(cursor.getColumnIndex("province_id")));
                list.add(city);
            } while (cursor.moveToNext());

        }
        return list;
    }

    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCounty_name());
            values.put("County_code", county.getCounty_code());
            values.put("province_id", county.getCity_id());
            db.insert("County", null, values);
        }
    }

    public List<County> loadCounty() {
        List<County> list = new ArrayList<>();
        Cursor cursor = db.query("County", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County County = new County();
                County.setCounty_code(cursor.getString(cursor.getColumnIndex("County_code")));
                County.setCounty_name(cursor.getString(cursor.getColumnIndex("County_name")));
                County.setCity_id(cursor.getInt(cursor.getColumnIndex("city_id")));
                list.add(County);
            } while (cursor.moveToNext());

        }
        return list;
    }


}
