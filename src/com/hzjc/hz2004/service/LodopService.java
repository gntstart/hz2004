package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.WSErrCode;

import java.io.UnsupportedEncodingException;
import java.util.*;

import com.hzjc.hz2004.base.SimpleJson;
import com.hzjc.hz2004.po.*;
import org.apache.commons.logging.*;
import com.hzjc.wsstruts.service.WSService;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.vo.*;
/**
 * 户籍业务类
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public interface LodopService
    extends WSService {
/**
 * @param flag
 * @param lodopId
 * @param jsonStr 
 * @param index 
 * @param baseUrl 
 * @return
 */
public String processLodop(String lodopId, String flag, String jsonStr, String index, String baseUrl);

/**
 * @param spywid
 * @param zqzbh
 * @return
 */
public String processValidZqzbh(String spywid, String zqzbh);

/**
 * @param lodopId
 * @param id
 * @return
 */
public String queryLodopById(String lodopId, String id);

/**
 * @param spywid
 * @param zqzbh
 * @return
 */
public String processValidQyzbh(String qyzbh, String yznf);

}
