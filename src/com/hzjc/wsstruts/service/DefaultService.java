package com.hzjc.wsstruts.service;

import java.util.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.wsstruts.vo.*;

/**
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public interface DefaultService
    extends WSService {

  List findAllEntities(String strHQL) throws ServiceException, DAOException;
  VoQueryResult query(String strHQL, String strCountHQL,VoPage voPage) throws
      ServiceException,
      DAOException;
}