package com.hzjc.hz2004.service.impl;

import org.dom4j.*;
import org.dom4j.io.*;
import java.util.*;
import java.io.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import javax.xml.rpc.ParameterMode;
import javax.xml.namespace.QName;
import com.hzjc.hz2004.vo.VoCsdjxx;

/**
 * <p>Title: 回国定居人员需要去访问外事系统进行审核</p>
 * 20100521 目前外事在推动这个工作，但常口户籍部门不准备做，暂停工作。
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class WsServiceImpl {
  private final String strResult = "result";
  private final String strData = "data";
  private final String strRow = "row";
  private final String strField = "field";
  private final String strName = "name";
  private final String strValue = "value";

  protected Logger _log = LogManager.getLogger(this.getClass());

  public WsServiceImpl() {
  }

  /**
   * 解析获取的XML
   * @param strXml String
   * @return List
   */
  private List parseXml(String strXml){
    List wsxxlist = new ArrayList();
    if (strXml.equals("") || strXml == null) {
      return wsxxlist;
    }

    try{
      SAXReader saxReader = new SAXReader();
      Document document = saxReader.read(new ByteArrayInputStream(strXml.getBytes("gbk")));

      Element root = document.getRootElement();
      Element result = root.element(strResult);
      Element data = root.element(strData);

      for (Iterator a = data.elementIterator(strRow); a.hasNext(); ) {
        Element row = (Element) a.next();
        Map sjdata = new HashMap();

        sjdata.put(strResult,result.getData());

        for (Iterator b = row.elementIterator(strField); b.hasNext(); ) {
          Element field = (Element) b.next();
          String name = "";
          String value = "";

          for (int i = 0; i < field.nodeCount(); i++) {
            Node node = field.node(i);
            if (node.hasContent()) {
              if (node.getName().equals(strName)) {
                name = node.getText();
              }
              else if (node.getName().equals(strValue)) {
                value = node.getText();
              }
            }
          }

          sjdata.put(name, value);
        }
        wsxxlist.add(sjdata);
      }

    }
    catch (Exception ex) {
      _log.error(ex);
    }
    return wsxxlist;
  }

  /**
   * 回国定居人员在办理出生申报时需要去外事审核一下
   * @param voCsdjxx VoCsdjxx[]
   */
  public void cssbToWs(VoCsdjxx voCsdjxx[]) {
    String strXml = "";
    List xmlList = new ArrayList();

    for (int i = 0; i < voCsdjxx.length; i++) {
      //测试 出生登记类别还未定下来，还需要业务部门来确定。
      if (voCsdjxx[i].getCsdjlb() == "001") {

        //获取姓名，性别，出生日期
        String xm = voCsdjxx[i].getXm();
        String xb = voCsdjxx[i].getXb();
        String csrq = voCsdjxx[i].getCsrq();
        String dwdm = "";
        String session = "";

        System.out.println("xm=" + xm + "; xb=" + xb + "; csrq=" + csrq + ";");

        //去外事审核数据
        _log.debug("发送待审核数据到外事。");
        try {
          Service service = new Service();
          Call call = (Call) service.createCall();
          call.setTargetEndpointAddress(new java.net.URL("http://10.118.3.12:7001/elsSupportPlatSVC/services/SupportComponent"));
          call.setOperationName(new QName("checkHqdjInfo"));
          call.addParameter("chnname", XMLType.XSD_STRING, ParameterMode.IN);//姓名
          call.addParameter("sex", XMLType.XSD_STRING, ParameterMode.IN);//性别
          call.addParameter("birthday", XMLType.XSD_STRING, ParameterMode.IN);//出生日期
          call.addParameter("unitno", XMLType.XSD_STRING, ParameterMode.IN);//单位代码
          call.addParameter("session", XMLType.XSD_STRING, ParameterMode.IN);//暂时未空
          call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
          strXml = (String) call.invoke(new Object[] {xm,xb,csrq,"",""}); //结果集
          //_log.debug(strXml);

          System.out.println(strXml);
        }
        catch (Exception ex) {
          _log.error("无法获取外事核查反馈信息!");
        }

        //解析外事返回信息
        xmlList = parseXml(strXml);
        //判断外事返回结果
        for (int j = 0; j < xmlList.size(); j++) {
          Map wsxxmap = (Map) xmlList.get(j);
          String result = wsxxmap.get("result").toString();
          String jlbz = wsxxmap.get("checkflag").toString();
          String msg = wsxxmap.get("info").toString();
          String lno = wsxxmap.get("tblno").toString();

        }
      }
    }
  }

  /**
   * 反馈外事已经入户常口的人信息
   * @param pcs String
   * @param gmsfhm String
   * @param slsj String
   * @param tblno String
   */
  public void hqhgdjxxfk(String pcs, String gmsfhm, String slsj, String tblno){
    String strXml = "";
    //华侨回国定居人员信息反馈
    _log.debug("反馈华侨回国定居人员信息到外事。");
    try {
      Service service = new Service();
      Call call = (Call) service.createCall();
      call.setTargetEndpointAddress(new java.net.URL(
          "http://10.118.3.12:7001/elsSupportPlatSVC/services/SupportComponent"));
      call.setOperationName(new QName("HMSettleInfo"));
      call.addParameter("unit", XMLType.XSD_STRING, ParameterMode.IN);//单位机构编码（12位） 目前传9位派出所代码
      call.addParameter("sessionId", XMLType.XSD_STRING, ParameterMode.IN);//交易号，预留，现可为空
      call.addParameter("tblno", XMLType.XSD_STRING, ParameterMode.IN);//业务编号  由获取外事信息时，外事传入的参数
      call.addParameter("idno", XMLType.XSD_STRING, ParameterMode.IN);//身份证号码
      call.addParameter("policestation", XMLType.XSD_STRING, ParameterMode.IN);//派出所代码(9位)
      call.addParameter("SettleDate", XMLType.XSD_STRING, ParameterMode.IN);//定居时间
      call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
      strXml = (String) call.invoke(new Object[] {pcs, "", tblno, gmsfhm, pcs, slsj}); //结果集

      System.out.println(strXml);
    }
    catch (Exception ex) {
      _log.error("无法获取外事核查反馈信息!");
    }

  }


  public static void main(String[] agrs) {
    WsServiceImpl pp = new WsServiceImpl();
    pp.testwsdy();
//    pp.testjx();
//    pp.testwsqr();
  }

  public void testwsdy(){
    VoCsdjxx vocsdjxx[] = new VoCsdjxx[5];
    vocsdjxx[0] = new VoCsdjxx();
    vocsdjxx[0].setXm("张三");
    vocsdjxx[0].setXb("1");
    vocsdjxx[0].setCsrq("19800101");
    vocsdjxx[0].setCsdjlb("001");


    cssbToWs(vocsdjxx);

    //测试
//    vocsdjxx[0] = new VoCsdjxx();
//    vocsdjxx[0].setXm("张三");
//    vocsdjxx[0].setXb("1");
//    vocsdjxx[0].setCsrq("19800101");
//    vocsdjxx[0].setCsdjlb("001");

//    vocsdjxx[1] = new VoCsdjxx();
//    vocsdjxx[1].setXm("王金贝");
//    vocsdjxx[1].setXb("2");
//    vocsdjxx[1].setCsrq("19870303");
//    vocsdjxx[1].setCsdjlb("001");

//    vocsdjxx[2] = new VoCsdjxx();
//    vocsdjxx[2].setXm("好好");
//    vocsdjxx[2].setXb("1");
//    vocsdjxx[2].setCsrq("19830716");
//    vocsdjxx[2].setCsdjlb("001");

//end

  }

  public void testjx(){
    String test = "<?xml version=\"1.0\" encoding=\"GB2312\"?><response>"
        + "<result>success</result>"
        + "<data><row type='hmsettle'>"
        + "<field><name>info</name><value><![CDATA[查询华侨回国定居证业务申请信息成功]]></value></field>"
        + "<field><name>checkflag</name><value><![CDATA[1]]></value></field>"
        + "<field><name>tblno</name><value><![CDATA[330000T00000308]]></value></field>"
        + "</row></data></response>";

//    test = "<?xml version=\"1.0\" encoding=\"GB2312\"?><response>"
//        + "<result>failure</result>"
//        + "<data><row type='hmsettle'>"
//        + "<field><name>info</name><value><![CDATA[查询不到华侨回国定居证业务申请信息]]></value></field>"
//        + "<field><name>checkflag</name><value><![CDATA[0]]></value></field>"
//        + "</row></data></response>";

//    test = "<?xml version=\"1.0\" encoding=\"GB2312\"?><response>"
//        + "<result>success</result>"
//        + "<data><row type='hmsettle'>"
//        + "<field><name>info</name><value><![CDATA[华侨回国定居落户信息接收成功]]></value></field>"
//        + "<field><name>checkflag</name><value><![CDATA[1]]></value></field>"
//        + "</row></data></response>";


    List xmlList = parseXml(test);

    System.out.println(xmlList.size());

    for (int j = 0; j < xmlList.size(); j++) {
      Map wsxxmap = (Map) xmlList.get(j);
      String jlbz = wsxxmap.get("checkflag").toString();
      String msg = wsxxmap.get("info").toString();
      String lno = wsxxmap.get("tblno").toString();
    }
  }

  public void testwsqr(){
    String pcs = "330203025";
    String gmsfhm = "330225200701261555";
    String slsj = "20100429143032";
    String tblno = "330000T00000308";

    hqhgdjxxfk(pcs,gmsfhm,slsj,tblno);
  }


  /**
   * 生成外市确认信息
   * @param list List
   * @return String
   */
  public String buildWsqrxx(List list) {
    String fhxx = null;
    try {
      Document doc = DocumentHelper.createDocument();

      Element newroot = doc.addElement("request");
      Element newheader = newroot.addElement("header");
      newheader.addElement("sessionid").addText("");
      newroot.addElement("type").addText("operate");
      newroot.addElement("function").addText("ZJ-S5-03-01.01.047");
      Element newdata = newroot.addElement("data");

      for (int i = 0; i < list.size(); i++) {
        Map wsxxmap = (Map) list.get(i);

        Element newrow = newdata.addElement("row").addAttribute("type",
            "ws_alarmpopulace_tbl");
        Element newfield = newrow.addElement("field");
        newfield.addElement("name").addText("pcheckalarmtblno");
        newfield.addElement("value").addText(wsxxmap.get("pcheckalarmtblno").
                                             toString());
      }

      OutputFormat format = OutputFormat.createPrettyPrint();
      OutputStream sjl = new ByteArrayOutputStream();
      format.setEncoding("gb2312");
      XMLWriter writer = new XMLWriter(sjl, format);
      writer.write(doc);
      writer.close();

//      System.out.println(sjl.toString());
      fhxx = sjl.toString();
    }
    catch (Exception e) {
      _log.error(e);
    }
    return fhxx;
  }
}
