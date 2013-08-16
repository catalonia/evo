package com.adaptor.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@SuppressWarnings("serial")
public class CustomHttpDispatcher extends HttpServletDispatcher 
{
	public static HashMap<String, String> versionInfo = new LinkedHashMap<String, String>();
	public static Properties properties = new Properties();

	public void init(javax.servlet.ServletConfig servletConfig)throws ServletException
	{
		super.init(servletConfig);
		InputStream inputStream1 = this.getClass().getClassLoader()
				.getResourceAsStream("versions.properties");
		try 
		{
			properties.load(inputStream1);
			
		} 
		catch (IOException e) 
		{

			e.printStackTrace();
		}
		try 
		{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("versionInfo.xml");
			// File fXmlFile = new File();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("version");
			for (int temp = 0; temp < nList.getLength(); temp++) 
			{

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{

					Element eElement = (Element) nNode;
					String versionId = eElement.getElementsByTagName("no")
							.item(0).getTextContent();
					String url = eElement.getElementsByTagName("url").item(0)
							.getTextContent();
					versionInfo.put(versionId, url);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
	
}
