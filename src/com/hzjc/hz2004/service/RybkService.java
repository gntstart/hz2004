package com.hzjc.hz2004.service;

import java.util.List;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.po.PoHJYW_RKBKXXB;

public interface RybkService {
	public Page queryRybk(ExtMap<String,Object> params);
	public Page queryRybkgjxx(ExtMap<String,Object> params);
	
	public void delRybk(ExtMap<String,Object> params);
	public PoHJYW_RKBKXXB saveRybk(PoHJYW_RKBKXXB bkry);
	public List<PoHJYW_RKBKXXB> saveRybkgj(String hjywlx, String hjywlxmc, String gmsfhm);
	public String saveRybkgjToString(String hjywlx, String hjywlxmc, String gmsfhm);
}
