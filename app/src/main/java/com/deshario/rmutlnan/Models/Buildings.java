package com.deshario.rmutlnan.Models;

/**
 * Created by Deshario on 4/4/2017.
 */

public class Buildings {

    private int id;
    private String building_no;
    private String building_name;
    private String building_lat;
    private String building_long;
    private String building_logo;

    public Buildings(String building_no, String building_name, String building_lat, String building_long, String building_logo) {
        this.building_no = building_no;
        this.building_name = building_name;
        this.building_lat = building_lat;
        this.building_long = building_long;
        this.building_logo = building_logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuilding_no() {
        return building_no;
    }

    public void setBuilding_no(String building_no) {
        this.building_no = building_no;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getBuilding_lat() {
        return building_lat;
    }

    public void setBuilding_lat(String building_lat) {
        this.building_lat = building_lat;
    }

    public String getBuilding_long() {
        return building_long;
    }

    public void setBuilding_long(String building_long) {
        this.building_long = building_long;
    }

    public String getBuilding_logo() {
        return building_logo;
    }

    public void setBuilding_logo(String building_logo) {
        this.building_logo = building_logo;
    }

}
