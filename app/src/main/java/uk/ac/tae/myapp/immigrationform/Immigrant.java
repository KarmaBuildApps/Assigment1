package uk.ac.tae.myapp.immigrationform;

import java.util.ArrayList;

/**
 * Created by Karma on 14/12/15.
 */
public class Immigrant {
    int _id;
    String fname, lname, dob, gender, country, imagePath;

    public Immigrant() {
        fname = lname = dob = gender = country = imagePath = "";
    }

    public Immigrant(int id, String fname, String lname, String dob, String gender, String country, String imagePath) {
        this._id = id;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.gender = gender;
        this.country = country;
        this.imagePath = imagePath;
    }

    public Immigrant(String fname, String lname, String dob, String gender, String country) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.gender = gender;
        this.country = country;
    }

    public int get_id() {
        return _id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
