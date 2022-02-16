package com.uob.xml.parse.ocoemapper.client;

import java.io.*;
import java.net.*;
import java.util.*;

import com.uob.xml.parse.ocoemapper.model.ExObject;

class Test {
  public static void main(String[] args) throws Exception {
    URL url = new URL("http://localhost:8080/v1/parse/postObjectvalue");
    Map<String,Object> params = new LinkedHashMap<>();
     ExObject exObject=new ExObject("first",1,2,"r","g");
    params.put("sampleobject", exObject);
    StringBuilder postData = new StringBuilder();
    for (Map.Entry<String,Object> param : params.entrySet()) {
      if (postData.length() != 0) postData.append('&');
      postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
      postData.append('=');
      postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
    }
    byte[] postDataBytes = postData.toString().getBytes("UTF-8");
    
   

    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
    conn.setDoOutput(true);
    conn.getOutputStream().write(postDataBytes);
    


    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

    for (int c; (c = in.read()) >= 0;)
      System.out.print((char)c);
  }
}
