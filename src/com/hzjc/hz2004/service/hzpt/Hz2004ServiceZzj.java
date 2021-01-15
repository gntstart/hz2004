package com.hzjc.hz2004.service.hzpt;

import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.po.entity.*;

import java.util.List;


public interface Hz2004ServiceZzj {

    public XkzApi getXkzApi(String pcsbm, String sbh);



    public PoXT_YHXXB getYhxxb(String dwdm,String yhdlm);

    /**
     * 通用查询
     *
     * @param hql
     * @return
     */
    public List queryByHQL(String hql);

    public ApiYs getApiYsByPostid(String lgbm, String type, String postid);

    public void saveLog(CallLog log);

    public void saveLog2(LgApiLog log);

    public void savePostLog(PostLog log);

    public List queryHSQL(String hsql, Object[] values);

    //数据包编号
    public String nextSjbbh();

    public String updateLkbm(String lgbm);

    public Vcsdj queryNbByLkbm(String lkbm);

    public Fqtk queryFqtkByLkbm(String lkbm);

    public void saveNb(Vcsdj nb, String postid, String hhnbid);

    public void saveVswzxyw(Vswzxyw vs, String postid);

    public Vswzxyw queryVswzxywByYwlsh(String ywlsh);

    public Vbggzxxb queryVbggzxxbByYwlsh(String ywlsh);

    public void updateVswzxyw(Vswzxyw vs);

    public void saveVbggzxxb(Vbggzxxb vs, String postid);

    public void updateVbggzxxb(Vbggzxxb vs);

    public void saveVfjcl(Vfjcl vs, String postid);

    public void updateVfjcl(Vfjcl vs);

    public void updateNb(Vcsdj nb);

    public void saveFqtk(Fqtk nb, String postid);

    public void updateFqtk(Fqtk nb);

    public void saveOrupdateFqtk(Fqtk nb);


    public void saveZjywZqzxx(ZjywZqzxx zqzxx, String pcsbm);

    public ZjywZqzxx queryZjywZqzxxByPostid(String postid);

    public void updateZjywZqzxx(ZjywZqzxx zqzxx);

    public void saveZjywZqzQyrxx(ZjywZqzQyrxx zqzQyrxx);

    public void saveZjywQyzxx(ZjywQyzxx qyzxx, String pcsbm);

    public void updateZjywQyzxx(ZjywQyzxx qyzxx);

    public void saveZjywQyzQyrxx(ZjywQyzxxQyrxx zqzQyrxx);

    public ZjywQyzxx queryZjywQyzxxByPostid(String qyzbh);
}
