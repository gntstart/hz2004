package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import java.util.List;

/**
 * 变更更正信息增强Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoBggzxxEx
    extends DefaultVO {

  private int flag = 1; //是否需要修改常住人口信息表中的对应记录(-1-不修改但处理户成员关系调整)/0-不修改/1-修改)
  private Long rynbid; //人员内部ID
  private Long sbhjywid; //上笔户籍业务ID
  private List bggzxxList; //变更更正list(VoBggzxx List)

  public List getBggzxxList() {
    return bggzxxList;
  }

  public void setBggzxxList(List bggzxxList) {
    this.bggzxxList = bggzxxList;
  }

  public int getFlag() {
    return flag;
  }

  public void setFlag(int flag) {
    this.flag = flag;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getSbhjywid() {
    return sbhjywid;
  }

  public void setSbhjywid(Long sbhjywid) {
    this.sbhjywid = sbhjywid;
  }

}