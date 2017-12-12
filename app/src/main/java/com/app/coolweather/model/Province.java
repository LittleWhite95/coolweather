package com.app.coolweather.model;

/**
 * Created by kzj54 on 2017/12/12.
 */

public class Province {
    private int id;
    private String province_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    private String province_code;

}
