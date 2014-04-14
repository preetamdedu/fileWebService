package com.examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


@Path("/file")
public class FileService {
	
	private static int count = 0;
	
	@Context
	ServletContext context;
	
	@GET
	@Produces("application/json")
	@Path("/filedata")
	public String getFile() throws JSONException{
		
		
		File filepath = new File(context.getRealPath("file.txt"));
		
		int num = 0;
		int i = 0;
		FileReader fr = null;
		BufferedReader br = null;
		JSONObject jsonObject = new JSONObject();
		try{
			fr = new FileReader(filepath);
			br = new BufferedReader(fr);
			String line = null;
			while((line = br.readLine())!=null){
				if(num < 10){
					i++;
					jsonObject.put("line" + i, line); //Adding File Line to JSON Object
				}else{
					break;
				}
				num++;
			}
			count++;
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return jsonObject.toString(); //Sending Result in JSON Format
	}
	
	@GET
	@Produces("application/json")
	@Path("/count")
	public String count() throws JSONException{
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("count", count); //Adding Count in JSON Object
		return jsonObject.toString(); //Sending Result in JSON Format
	}
}
