package com.example.bloodbanksemesterproject.DataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestDataModel {
    /*
       name: Clara McGrew
       project: Android Blood Bank Semester Project
       date: 4/21/2020 Presentation
       date: 4/23/2020 Final code upload
       relevant github libraries/code/repositories used in this application include
       VolleySingleton.java
       https://gist.github.com/RISHABH3821/6b1e58de77a4e4909b097c2ca51acf6e
       StringRequest.java
       https://gist.github.com/RISHABH3821/bc48fe91119c2efa14cfab1accc71376
       ExampleAdapter.java
       https://gist.github.com/RISHABH3821/f54ed1d96d95d827ac74bb86aee39521
       Android Networking library
       https://github.com/amitshekhariitbhu/Fast-Android-Networking

       This class was created by http://www.jsonschema2pojo.org/

        */
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("email")
    @Expose
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}




