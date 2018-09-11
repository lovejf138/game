package com.api.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlUtil
{
  public static String convertToXml(Object obj)
  {
    StringWriter sw = new StringWriter();
    try
    {
      JAXBContext context = JAXBContext.newInstance(new Class[] { obj.getClass() });
      
      Marshaller marshaller = context.createMarshaller();
      
      marshaller.setProperty("jaxb.formatted.output", 
        Boolean.TRUE);
      
      marshaller.marshal(obj, sw);
    }
    catch (JAXBException e)
    {
      e.printStackTrace();
    }
    return sw.toString();
  }
  
  public static void convertToXml(Object obj, String path)
  {
    try
    {
      JAXBContext context = JAXBContext.newInstance(new Class[] { obj.getClass() });
      
      Marshaller marshaller = context.createMarshaller();
      
      marshaller.setProperty("jaxb.formatted.output", 
        Boolean.TRUE);
      
      FileWriter fw = null;
      try
      {
        fw = new FileWriter(path);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      marshaller.marshal(obj, fw);
    }
    catch (JAXBException e)
    {
      e.printStackTrace();
    }
  }
  
  public static <T> T convertXmlStrToObject(Class<T> clazz, String xmlStr)
  {
    T xmlObject = null;
    try
    {
      JAXBContext context = JAXBContext.newInstance(new Class[] { clazz });
      
      Unmarshaller unmarshaller = context.createUnmarshaller();
      StringReader sr = new StringReader(xmlStr);
      xmlObject = clazz.cast(unmarshaller.unmarshal(sr));
    }
    catch (JAXBException e)
    {
      e.printStackTrace();
    }
    catch (ClassCastException e)
    {
      return null;
    }
    return xmlObject;
  }
  
  public static <T> T convertXmlFileToObject(Class<T> clazz, String xmlPath)
  {
    T xmlObject = null;
    try
    {
      JAXBContext context = JAXBContext.newInstance(new Class[] { clazz });
      Unmarshaller unmarshaller = context.createUnmarshaller();
      FileReader fr = null;
      try
      {
        fr = new FileReader(xmlPath);
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
      }
      xmlObject = clazz.cast(unmarshaller.unmarshal(fr));
    }
    catch (JAXBException e)
    {
      e.printStackTrace();
    }
    catch (ClassCastException e)
    {
      return null;
    }
    return xmlObject;
  }
}
