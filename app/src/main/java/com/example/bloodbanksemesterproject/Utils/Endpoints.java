package com.example.bloodbanksemesterproject.Utils;

public class Endpoints {
    /*
    name: Clara McGrew
    date of presentation: 4/21/2020
    project: Android blood bank semester project
    This class contains the urls that are used for registering, uploading, searching and all of that
    to the seperate server for my project, where I have php to handle all of thse kinds of requests.
     */
    private static final String base_url = "http://semester-project-android.000webhostapp.com/";
    public static final String register_url = base_url + "register.php";
    public static final String login_url = base_url + "login.php";
    public static final String upload_request = base_url + "upload_request.php";
    public static final String get_requests = base_url + "get_requests.php";
    public static final String search_donors = base_url + "search_donors.php";

}
