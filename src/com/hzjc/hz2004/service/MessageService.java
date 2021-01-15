package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.WSErrCode;
import java.util.*;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.SimpleJson;
import com.hzjc.hz2004.po.*;
import org.apache.commons.logging.*;
import com.hzjc.wsstruts.service.WSService;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.vo.*;
/**
 * 消息业务类
 */
public interface MessageService
    extends WSService {

  /**
 * @param voMessagexx
 * @return
 * @throws ServiceException
 * @throws DAOException
 */
public VoMessageRtxx processXxhf(VoMessagexx voMessagexx) throws
      ServiceException,
      DAOException;

  /**
 * @param voMessagexx
 * @return
 * @throws ServiceException
 * @throws DAOException
 */
public VoMessageRtxx processXxfs(VoMessagexx voMessagexx) throws
      ServiceException,
      DAOException;

/**
 * @param messageid
 * @return
 */
public VoMessagexx updateMessage(Long messageid);

/**
 * @param messageid
 * @return
 */
public VoMessagexx deleteMessage(Long messageid);

/**
 * @param params
 * @return
 */
public VoMessageRtxx processFsxx(ExtMap<String, Object> params);

/**
 * @param jsryhid
 * @param dwdm 
 * @return
 */
public List<?> checkUnReadMessage(Long jsryhid, String dwdm); 


 

}
