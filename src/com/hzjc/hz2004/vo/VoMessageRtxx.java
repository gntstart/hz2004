package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoMESSAGEXXB;
import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 消息存储对象
 * @version 1.0
 */
public class VoMessageRtxx
    extends DefaultVO {
  private PoMESSAGEXXB[] voMessagexx;

public PoMESSAGEXXB[] getVoMessagexx() {
	return voMessagexx;
}

public void setVoMessagexx(PoMESSAGEXXB[] voMessagexx) {
	this.voMessagexx = voMessagexx;
}

  
}
