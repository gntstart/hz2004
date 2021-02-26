package com.hzjc.hz2004.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipOutputStream;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.bean.CheckQrspBean;
import com.hzjc.hz2004.po.PoSFXXB;
//import com.hzjc.hz2004.po.PoUPLOAD_TEMP;
import com.hzjc.hz2004.po.PoHJXX_CZRKJBXXB;
import com.hzjc.hz2004.po.PoHJXX_JWHZPLSB;
import com.hzjc.hz2004.po.PoHJXX_RHFLXXB;
import com.hzjc.hz2004.po.PoPERSON_DY_SET;
import com.hzjc.hz2004.po.PoXT_XTRZB;
import com.hzjc.hz2004.po.PoXT_YHHHXXB;
import com.hzjc.hz2004.vo.VoBb;
import com.hzjc.hz2004.vo.VoHxxHqFhxx;
import com.hzjc.hz2004.vo.VoMessageRtxx;
import com.hzjc.hz2004.vo.VoMessagexx;
import com.hzjc.hz2004.vo.VoQyzdyxxHqFhxx;
import com.hzjc.hz2004.vo.VoRhflywfhxx;
import com.hzjc.hz2004.vo.VoSfxxb;
import com.hzjc.wsstruts.common.ID;
import com.hzjc.wsstruts.exception.DAOException;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 通用Service
 * @author Administrator
 *
 */
public interface CommonService {
	/**
	 * 获取数据库时间
	 * @return
	 */
	public Date getSjksj();

	/**
	 * 依据CLASS获取下一个ID
	 * @param entityClass
	 * @return
	 * @throws DAOException
	 */
	public  Serializable getId(Class<?> entityClass) throws DAOException;
	public  Serializable getId(ID id)  throws DAOException;
	
	public List<?> queryAll(String hql);
	
	public void insertObject(Object obj);
	public void deleteObject(Object obj);
	public void updateObject(Object obj);
	public <T> T getByID(final Class<T> entityClass, final Serializable id);
	public void refreshObject(Object obj, LockOptions lockMode) throws DAOException;
	public List<?> queryLock(String hsql);
	public Session getSession();
	public Connection getConnection();
	
	/**
	 * 查询人信息
	 * @param params
	 * @return
	 */
	public Page queryPoHJXX_CZRKJBXXB(ExtMap<String,Object> params);
	
	/**
	 * 查询户信息
	 * @param params
	 * @return
	 */
	public Page queryPoHJXX_HXXB(ExtMap<String,Object> params);
	
	/**
	 * 查询现住地信息
	 * @param params
	 * @return
	 */
	public Page queryPoHJXX_RHFLXXB(ExtMap<String,Object> params);

	/**
	 * 查询信息
	 * 页面中传入config_key参数获取common.xml文件中的查询语句
	 * @param params
	 * @return
	 */
	public Page queryPageByConf(ExtMap<String,Object> params);
	
	/**
	 * 查询户籍审批信息
	 * @param params
	 * @return
	 */
	public Page queryPoHJSP_HJSPSQB(ExtMap<String,Object> params);
	
	/**
	 * 查询户籍审批子信息
	 * @param params
	 * @return
	 */
	public List<?> queryPoHJSP_HJSPZB(ExtMap<String,Object> params);
	
	/**
	 * 查询户籍户政业务处理
	 * @param params
	 * @return
	 */
	public List<?> queryPoHZ_ZJ_SB(ExtMap<String,Object> params);

	/**
	 * 套打功能
	 * @param params
	 * @return
	 */
	public Page queryPoHJXX_LODOP(ExtMap<String, Object> params);
	
	/**
	 * 详细地址
	 * @param params
	 * @return
	 */
	public Object getDzxz(ExtMap<String, Object> params);
	/**
	 * 迁入审批等级业务检查：身份证重复，审批登记重复等
	 * @param params
	 * @return
	 */
	public CheckQrspBean checkQrspdjyw(ExtMap<String, Object> params);

	public void saveHjxxDyxxb(PoHJXX_CZRKJBXXB vohjxx_czrkjbxxb, String lodopId, String zjbh, String yznf);

	public List queryZqzList(ExtMap<String, Object> params);

	public VoQyzdyxxHqFhxx queryQyzList(ExtMap<String, Object> params);
	
	public List queryXt_bssqb(ExtMap<String, Object> params);
	
	public List getZp(ExtMap<String, Object> params);
	
	/**
	 * 查询本地待处理的异地迁出业务
	 * @param params
	 * @return
	 */
	public Page queryKDQHjspyw(ExtMap<String,Object> params);
	
	/**
	 * 查询户籍户信息（户和门楼牌）
	 * @param params
	 * @return
	 */
	public VoHxxHqFhxx queryHxx(ExtMap<String, Object> params);

	public Page queryPoHJXX_CZRKJBXXB1(ExtMap<String, Object> params);

	public Page queryPoHJXX_CZRKJBXXB6(ExtMap<String, Object> params);

	public List getYdZp(ExtMap<String, Object> params);

	/**
	 * 查询消息
	 * 20190404
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryMessage(ExtMap<String, Object> params);

	/**
	 * 回复消息
	 * 20190408
	 * zjm
	 * @param params
	 * @return
	 */
	public VoMessageRtxx xxhf(ExtMap<String, Object> params);

	/**
	 * 删除指定消息记录
	 * 20190408
	 * zjm
	 * @param params
	 * @return
	 */
	public VoMessagexx deleteMessage(ExtMap<String, Object> params);

	/**
	 * 更新指定消息记录为已读
	 * 20190409
	 * zjm
	 * @param params
	 * @return
	 */
	public VoMessagexx updateMessage(ExtMap<String, Object> params);

	/**
	 * 查询是否有未读的消息
	 * 20190409
	 * zjm
	 * @param params
	 * @return
	 */
	public List<?> checkUnReadMessage(ExtMap<String, Object> params);

	/**
	 * 查询人户分离信息
	 * 20190805
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryRhfl(ExtMap<String, Object> params);

	/**
	 * 新增人户分离信息
	 * 20190806
	 * zjm
	 * @param params
	 * @return
	 */
	public VoRhflywfhxx addRhfl(ExtMap<String, Object> params); 
	/**
	 * 删除人户分离信息
	 * 20190806
	 * zjm
	 * @param params
	 * @return
	 */
	public PoHJXX_RHFLXXB deleteRhfl(ExtMap<String, Object> params); 
	/**
	 * 保存日志信息
	 */
	public void saveRzxx(AuthToken user, String logcode, String logstr, long startTime, String startDate);

	/**
	 * add by zjm 
	 * 20191024 不收费原因保存
	 * @param params
	 * @return
	 */
	public PoSFXXB bjfyySave(ExtMap<String, Object> params);

	/**
	 * add by zjm 
	 * 20191025
	 * 收费信息表插入记录
	 * @param vosfxxb
	 * @return
	 */
	public PoSFXXB insertSfxxb(VoSfxxb vosfxxb);

	public int queryDycsByhh(ExtMap<String, Object> params);

	public PoPERSON_DY_SET getPersonDyset(ExtMap<String, Object> params);

	public Page validHhid(ExtMap<String, Object> params);

	public PoHJXX_JWHZPLSB uploadZp(MultipartHttpServletRequest params) throws FileNotFoundException, IOException;

	public VoBb checkDjJth(ExtMap<String, Object> params);

	/**
	 * 照片上传到临时照片表，并返回照片id
	 * add by zjm  20200821
	 * @param logoFile
	 * @return
	 */
//	public PoUPLOAD_TEMP commonUploadZp(MultipartHttpServletRequest params) throws FileNotFoundException, IOException;

	public void downZp(HttpServletRequest req, HttpServletResponse rep, ExtMap<String, Object> params) throws IOException, ServletException;

	public void downExcelZip(HttpServletRequest req, HttpServletResponse rep, ExtMap<String, Object> params);

	public void uploadQdZp(MultipartHttpServletRequest logoFile);

	/**
	 * 收费信息表更新
	 * @param posfxxb
	 */
	public PoSFXXB updateSfxxb(PoSFXXB posfxxb);

	public void downBkMb(HttpServletRequest req, HttpServletResponse rep, ExtMap<String, Object> params);

	/**
	 * add by zjm  
	 * 20210202
	 * 上传布控模板文件
	 * @param bkMbFile
	 * @throws IOException 
	 */
	public void uploadBkMb(MultipartHttpServletRequest bkMbFile) throws IOException;
}
