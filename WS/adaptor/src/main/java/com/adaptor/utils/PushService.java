package com.adaptor.utils;

import java.io.IOException;
import java.io.InputStream;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

public class PushService {

	public void pushMSG(String msg, String deviceToken) throws IOException	 
	{
		System.out.println("Notification is sended");
		try{
			InputStream inputStream = this.getClass().getClassLoader()
			.getResourceAsStream("Certificates_push.p12");
		ApnsService service;
		service = APNS.newService()
			.withCert(inputStream, "123")
			.withSandboxDestination()
			.build();
		String payload = APNS.newPayload().alertBody(msg).build();
		//String token = "64a383f0 84ee1d6d 5c2f8119 1bd4f8f4 d36f26f4 4a214821 7987c3d6 26907f8e";
		service.push(deviceToken, payload);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
