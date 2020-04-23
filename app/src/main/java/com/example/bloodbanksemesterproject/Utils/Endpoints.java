package com.example.bloodbanksemesterproject.Utils;

public class Endpoints {
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

       */
    private static final String base_url = "http://semester-project-android.000webhostapp.com/";
    public static final String register_url = base_url + "register.php";
    public static final String login_url = base_url + "login.php";
    public static final String upload_request = base_url + "upload_request.php";
    public static final String get_requests = base_url + "get_requests.php";
    public static final String search_donors = base_url + "search_donors.php";

}
