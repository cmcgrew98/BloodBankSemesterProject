package com.example.bloodbanksemesterproject.DataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Donor {
    /*
    name: Clara McGrew
    date of presentation: 4/21/2020
    project: Android Blood Bank semester project
    This is a class that was created from a Json object, which was referred to in the blood bank tutorials.
    The following website was used:
    http://www.jsonschema2pojo.org/
     */



@SerializedName("name")
@Expose
private String name;
@SerializedName("email")
@Expose
private String email;
@SerializedName("city")
@Expose
private String city;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

}