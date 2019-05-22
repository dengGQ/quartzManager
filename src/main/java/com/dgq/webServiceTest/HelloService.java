package com.dgq.webServiceTest;
/*
* @Description: public class HelloService{ }
* @author dgq 
* @date 2018年12月24日
*/

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.http.HTTPBinding;

@WebService
public class HelloService {
	
	@WebMethod(operationName="foticService",action="web")
	public String say(String name) {
		System.out.println("-----------"+name);
		return "hello "+name;
	}
	
	public static void main(String[] args) {
		Object impl = new HelloService();
		
		Endpoint.publish("http://localhost:8080/HS", impl);
	}
}
