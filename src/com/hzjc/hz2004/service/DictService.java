package com.hzjc.hz2004.service;

import java.util.List;
import java.util.Map;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.bean.ExtField;
import com.hzjc.hz2004.base.bean.SjpzBean;
import com.hzjc.hz2004.base.bean.SysCode;
import com.hzjc.hz2004.base.bean.TreeNode;
import com.hzjc.hz2004.po.PoXT_SJPZB;
import com.hzjc.hz2004.vo.VoXT_SJPZB;

public interface DictService {
	public SysCode getRemoteDictItem(String visiontype,String code);
	public SjpzBean querySjpzb(ExtMap<String,Object> params);
	public Page searchJlx(ExtMap<String,Object> params);
	public Page searchXzqh(ExtMap<String,Object> params);
	public Page searchXxb(ExtMap<String,Object> params);
	public List<TreeNode> getTreeXzqh(String parent_dm);
	public List<ExtField> queryYsjpzbJS(ExtMap<String, Object> params);
	public Page searchJwh(ExtMap<String, Object> params);
	public Page searchJwhPcsXzjd(ExtMap<String, Object> params);
	public Page searchPcs(ExtMap<String, Object> params);
	public List querySjpzbUpdate();
	public Page searchXzlx(ExtMap<String, Object> params);
	public Page searchSpdz(ExtMap<String, Object> params);
	public Page searchYhxx(ExtMap<String,Object> params);
	public Page searchXzqhNew(ExtMap<String,Object> params);
	public List<VoXT_SJPZB> querySjpzbGz(ExtMap<String, Object> params);
}
