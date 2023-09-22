package com.rgt.engine;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public class demoAPI 
{
	
	 public static void main(String args[]) throws MalformedURLException
	 {
		 //APIDriver apidriver = new APIDriver();
		List<String> response =  APIDriver.getRequest("https://jsonplaceholder.typicode.com/posts");
		System.out.println(response.get(0));
		System.out.println(response.get(1));
		List<String> postresponse = APIDriver.postRequest();
		System.out.println(postresponse.get(0));
		System.out.println(postresponse.get(1));
	 }

}
