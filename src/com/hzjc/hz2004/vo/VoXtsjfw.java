package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 数据范围操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */


public class VoXtsjfw
extends DefaultVO {
  //数据范围
  private String xqlx; //辖区类型
  private String sjfwbz; //数据范围标志，区划，派出所，居委会 0,1,2,3,9
  private String sjfw; //数据范围

  public VoXtsjfw() {
  }

  public void setXqlx(String xqlx){
    this.xqlx = xqlx;
  }
  public String getXqlx(){
    return this.xqlx;
  }
  public void setSjfwbz(String sjfwbz){
    this.sjfwbz = sjfwbz;
  }
  public String getSjfwbz(){
    return this.sjfwbz;
  }
  public void setSjfw(String sjfw){
    this.sjfw = sjfw;
  }
  public String getSjfw(){
    return this.sjfw;
  }


}