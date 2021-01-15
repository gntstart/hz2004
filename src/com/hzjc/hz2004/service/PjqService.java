package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.service.WSService;
import java.util.*;
import java.sql.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.hz2004.vo.*;
import com.hzjc.wsstruts.vo.*;

public interface PjqService extends WSService {

  /**
   * 评价信息保存
   * @param pjjg String
   * @param pjrlxfs String
   * @return String
   * @throws ServiceException
   * @throws DAOException
   */
  Long processPjxxbc(String sPjjg,String sPjrlxfs,String sPjkssj) throws ServiceException,
      DAOException ;

}
