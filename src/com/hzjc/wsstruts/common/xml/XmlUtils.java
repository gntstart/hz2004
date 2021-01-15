package com.hzjc.wsstruts.common.xml;

import org.dom4j.*;
import org.dom4j.io.*;

//import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.*;
import org.apache.commons.logging.*;
import java.net.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

/**
 * XML公用处理方法
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class XmlUtils {
  //日志处理
  private static Log _log = LogFactory.getLog(XmlUtils.class);

  /**
   *
   */
  private XmlUtils() {
  }

  /**
   * 将XML文档以某种编码方式保存
   * @param aDoc             - DOCUMENT XML 文档
   * @param strEncodingName - 编码方式
   * @return
   */
  public static String toXML(Document aDoc, String strEncodingName) throws
      Exception {
    String strXml = "";
    if (aDoc != null) {
      try {
        //1、方法一：
        //return _doc.asXML();
        //2、方法二：
        /*
         ISO-8859-1 8859_1 ISO8859_1 US-ASCII ASCII UTF-8 UTF8
         ISO-10646-UCS-2 UTF-16 UTF-16BE UTF-16LE ISO-10646-UCS-4 UTF-16BE
         UTF-16LE UTF-16 ISO-10646-UCS-2
         */
        //返回的格式很标准优美，创建输出格式
        OutputFormat outFormat = new OutputFormat().createPrettyPrint();
        //返回的格式比较紧凑
        //OutputFormat outFormat = new OutputFormat().createCompactFormat();
        //设置生成XML文件编码
        if (!"".equals(strEncodingName)) {
          outFormat.setEncoding(strEncodingName);
        }
        StringWriter writer = new StringWriter();
        XMLWriter out = new XMLWriter(writer, outFormat);
        //写数据流
        out.write(aDoc);
        strXml = writer.toString();
        /*
                 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 XMLWriter out = new XMLWriter(baos, outFormat);
                 out.write(aDoc);
                 strXml = baos.toString(strEncodingName);
         */
        out.close();
      }
      catch (IOException ex) {
        throw ex;
      }
    }
    return strXml;
  }

  /**
   * 将XML数据加载到DCUMENT中
   * @param aXmlData  - XML数据
   * @return
   */
  public static Document fromXML(String aXmlData) throws Exception {
    Document doc = null;
    try {
      SAXReader reader = new SAXReader();
      /**
       * 说明：1）解析xml文件时，xml中含有中文字符，如果xml的encoding="gb2312"时，1和3没有问题；
       *   如果encoding="utf-8"时，1和3有问题，报错有异常。
       * 2）
       * 解决方法：1）客户端（delphi）传过来用“gb2312”或“GBK”
       * 2）修改dom4j的原代码
       */
      //1、该方法没问题
      //doc = reader.read(new ByteArrayInputStream(aXmlData.getBytes()));
      //2、该方法对字符串的读入有问题（好象对任何编码读有问题）
      //doc = reader.read(new StringBufferInputStream(aXmlData));
      //3、该方法没有问题
      doc = reader.read(new StringReader(aXmlData));
      //4、直接用sax的inputsource(有问题)
      //doc = reader.read(new InputSource(aXmlData));
      //5、有问题
      //doc = reader.read(new InputStreamReader(new StringBufferInputStream(aXmlData),"UTF-8"));
      if (doc == null) {
        _log.info("解析加载XML异常:LoadFromXml Document is null!");

      }
    }
    catch (DocumentException ex) {
      throw ex;
    }
    return doc;
  }

  /**
   * 将XML数据以某种编码方式加载入
   * @param aXmlData         - XML数据
   * @param strEncodingName  - 编码方式
   * @return
   * @throws java.lang.Exception
   */
  public static Document fromXML(String aXmlData, String strEncodingName) throws
      Exception {
    Document doc = null;
    try {
      SAXReader reader = new SAXReader();
      //doc = reader.read(new ByteArrayInputStream(aXmlData.
      //                                         getBytes(strEncodingName)));
      doc = reader.read(new InputStreamReader(new ByteArrayInputStream(aXmlData.getBytes()), strEncodingName));
      if (doc == null) {
        _log.info("解析加载XML异常:LoadFromXml Document is null!");
      }
    }
    catch (DocumentException ex) {
      throw ex;
    }
    return doc;
  }

  /**
   * 将XML资源文件加载到读入流中
   * @param strXmlResource - XML资源文件路径
   * @return
   * @throws java.lang.Exception
   */
  public static InputStream getResourceAsStream(String strXmlResource) throws
      Exception {
    InputStream is = null;
    if (strXmlResource != null) {
      ///////////////////////////////////////////////////////////////
      //判断开始字符，然后加载
      if (strXmlResource.startsWith("/")) { //"/"作为开始字符
        is = XmlUtils.class.getResourceAsStream(strXmlResource);
      }
      else { //其它字符为开始字符
        //Thread.currentThread().getContextClassLoader();
        is = XmlUtils.class.getClassLoader().getResourceAsStream(
            strXmlResource);
      }
    }
    return is;
  }

  /**
   * 得到XML资源文件的实际物理路径
   * @param strXmlResource  - XML资源名称
   * @return
   */
  public static String getResourcePhysicPath(String strXmlResource) {
    String strPhysicPath = null;
    if (strXmlResource != null) {
      if (strXmlResource.startsWith("/")) {
        strPhysicPath = XmlUtils.class.getResource(strXmlResource).getPath();
      }
      else {
        strPhysicPath = XmlUtils.class.getClassLoader().getResource(
            strXmlResource).getPath();
      }
    }
    return strPhysicPath;
  }

  public static void main(String[] args) {
    try {
      SAXReader reader = new SAXReader();
      InputStreamReader isr = new InputStreamReader(new FileInputStream(
          "c:/Request.xml"), "UTF-16");
      Document doc = reader.read(isr);
      _log.info(toXML(doc, XmlEncode.ENCODING_NAME_ISO_10646_UCS_4));
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * 通过样式表单转换XML
   * @param document    - XML文档
   * @param stylesheet  - 样式表单dtd
   * @return
   * @throws java.lang.Exception
   */
  public static Document styleDocument(
      Document document,
      String stylesheet
      ) throws Exception {

    Document transformedDoc = null;
    try {
      // load the transformer using JAXP
      TransformerFactory factory = TransformerFactory.newInstance();
      Transformer transformer = factory.newTransformer(new StreamSource(
          stylesheet)
          );

      // now lets style the given document
      DocumentSource source = new DocumentSource(document);
      DocumentResult result = new DocumentResult();
      transformer.transform(source, result);

      // return the transformed document
      transformedDoc = result.getDocument();
    }
    catch (TransformerException ex) {
      throw ex;
    }
    catch (TransformerFactoryConfigurationError ex) {
      throw ex;
    }
    return transformedDoc;
  }

}