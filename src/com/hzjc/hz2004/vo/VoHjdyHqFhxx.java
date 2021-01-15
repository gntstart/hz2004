package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoHJXX_DYXXB;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * 户籍打印返回信息Vo
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT Corp. 2004</p>
 * <p>Company: </p>
 * @author hb
 * @version 1.0
 */

public class VoHjdyHqFhxx
    extends PoHJXX_DYXXB {

  public VoHjdyHqFhxx() {
  }

  public VoHjdyHqFhxx(Object poView) {
    try {
      BeanUtils.copyProperties(this, poView);
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }
  }


}
