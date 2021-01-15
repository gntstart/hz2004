package com.hzjc.hz2004.service.hzpt;

import com.hzjc.hz2004.po.PoHZ_ZJ_SB;

public interface ReceiveStateService {
    void updataReceiveState(String WX_CODE,String cktable);

	void insertEmsSentInfo(PoHZ_ZJ_SB sbx, String organid, String organname, String dwdm);
}
