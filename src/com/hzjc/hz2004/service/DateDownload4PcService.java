package com.hzjc.hz2004.service;

import com.hzjc.wsstruts.service.*;
import com.hzjc.hz2004.vo.VoDateDownloadxx;
import com.hzjc.wsstruts.exception.ServiceException;

/**
 * <p>Title: </p>
 *
 * <p>Description: 常住人口二代证Hz2004版</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public interface DateDownload4PcService extends WSService {

    /**
     * 数据导出
     * @param vo VoDateDownloadxx
     * @return String[]
     */
    public String[] dateDownload(VoDateDownloadxx vo) throws ServiceException;

}
