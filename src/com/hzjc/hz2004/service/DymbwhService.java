package com.hzjc.hz2004.service;

import java.util.Map;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.SimpleJson;


public interface DymbwhService
   {
	
  /**
   * 更新模板
 * @param param
 * @param sj
 * @return
 */
public void updateLodop(Map param,SimpleJson sj);
  
  /**
   * 删除模板
   * @param param
   * @param sj
   * @return
   */
  public void dealLodop(Map param);

/**
 * 新增模板
 * @param params
 * @param json
 */
public void insertLodop(Map params, SimpleJson json); 
}
