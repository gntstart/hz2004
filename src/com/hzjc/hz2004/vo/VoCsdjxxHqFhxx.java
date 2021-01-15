package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoV_HJ_CSDJXXB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * 出生登记信息获取返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoCsdjxxHqFhxx
    extends PoV_HJ_CSDJXXB {

  public VoCsdjxxHqFhxx() {
  }

  public VoCsdjxxHqFhxx(Object poView) {
    try {
      BeanUtils.copyProperties(this, poView);
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

}