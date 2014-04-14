package com.examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		
		WebResource resource = client.resource("http://1-dot-filewebservice.appspot.com/rest/file/filedata");
		String ans = resource.accept("application/json").get(String.class); //Receive Result in JSON Format
		
		out.println("<html><body>");
		out.println("<h1>First 10 Lines of File in JSON Format</h1></br>");//Displaying Result in HTML Format
		out.println(ans);
		out.println("</body></html>");
	}

}
