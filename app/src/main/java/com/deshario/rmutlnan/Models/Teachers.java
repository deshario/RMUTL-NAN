package com.deshario.rmutlnan.Models;

/**
 * Created by Deshario on 3/25/2017.
 */

public class Teachers {

    private int id;
    private String prefix_name;
    private String name;
    private String surname;
    private String sex;
    private String faculty;
    private String img;

    public Teachers(String prefix_name, String name, String surname, String faculty, String img) {
        this.prefix_name = prefix_name;
        this.name = name;
        this.surname = surname;
        this.faculty = faculty;
        this.img = img;
    }

    public Teachers(int id, String prefix_name, String name, String surname, String faculty, String img) {
        this.id = id;
        this.prefix_name = prefix_name;
        this.name = name;
        this.surname = surname;
        this.img = img;
        this.faculty = faculty;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrefix_name() {
        return prefix_name;
    }

    public void setPrefix_name(String prefix_name) {
        this.prefix_name = prefix_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }




}
