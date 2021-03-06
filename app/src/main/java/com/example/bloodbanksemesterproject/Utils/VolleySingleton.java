package com.example.bloodbanksemesterproject.Utils;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
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

//These are the variables declared.
  private static VolleySingleton mInstance;
  private RequestQueue mRequestQueue;
  private static Context mContext;

  //A private constrctor.
  private VolleySingleton(Context context) {
    // Specify the application context
    mContext = context;
    // Get the request queue
    mRequestQueue = getRequestQueue();
  }

  //This is a synchronized instance, that enables you to run the VolleySingleton.  This helps to make sure that it's thread safe.
  public static synchronized VolleySingleton getInstance(Context context) {
    // If Instance is null then initialize new Instance
    if (mInstance == null) {
      mInstance = new VolleySingleton(context);
    }
    // Return MySingleton new Instance
    return mInstance;
  }

  //The requestQueue, a getter.
  public RequestQueue getRequestQueue() {
    // If RequestQueue is null the initialize new RequestQueue
    if (mRequestQueue == null) {
      mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
    }

    // Return RequestQueue
    return mRequestQueue;
  }

  //Adds to the RequestQueue
  public <T> void addToRequestQueue(Request<T> request) {
    // Add the specified request to the request queue
    getRequestQueue().add(request);
  }
}