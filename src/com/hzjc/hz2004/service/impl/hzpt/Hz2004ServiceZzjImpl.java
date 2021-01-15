package com.hzjc.hz2004.service.impl.hzpt;

import com.hzjc.hz2004.base.ServiceImpl;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.hz2004.po.entity.*;
import com.hzjc.hz2004.service.hzpt.Hz2004ServiceZzj;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "hz2004ServiceZzj")
public class Hz2004ServiceZzjImpl extends ServiceImpl implements Hz2004ServiceZzj {


    @Override
    public List queryByHQL(String hql) {
        return super.getObjectListByHql(hql);
    }

    @Override
    public void saveLog(CallLog log) {
        super.create(log);
    }

    @Override
    public void saveLog2(LgApiLog log) {
        super.create(log);
    }

    @Override
    public ApiYs getApiYsByPostid(String lgbm, String type, String postid) {
        return (ApiYs) super.getObject(" from ApiYs a where a.postid=? and a.nwbbz=? and a.lg=?", new Object[]{postid, type, lgbm});
    }

    @Override
    public void savePostLog(PostLog log) {
        super.create(log);
    }


    @Override
    public List queryHSQL(String hsql, Object[] values) {
        return super.getObjectListByHql(hsql, values);
    }

    @Override
    public XkzApi getXkzApi(String pcsbm, String sbh) {
        return (XkzApi) super.getObject("from XkzApi where lgbm='" + pcsbm + "' ", null);//;get(XkzApi.class, pcsbm);//.getObjectListByHql(hsql, values);
    }

    @Override
    public PoXT_YHXXB getYhxxb(String dwdm,String yhdlm) {
        PoXT_YHXXB po = (PoXT_YHXXB) super.getObject("from PoXT_YHXXB where dwdm= ? or yhdlm = ?",
                new String[] { dwdm,yhdlm});
        return po;
    }

    @Override
    public String nextSjbbh() {
        PostSjbbh obj = (PostSjbbh) super.getObject("from PostSjbbh", new Object[]{});
        if (obj == null) {
            obj = new PostSjbbh();
            obj.setBh(1);
            obj.setBhsj(DateHelper.formateDate("yyyyMMdd"));
        } else {
            if (obj.getBhsj().equals(DateHelper.formateDate("yyyyMMdd"))) {
                obj.setBh(obj.getBh() + 1);
            } else {
                obj.setBh(1);
                obj.setBhsj(DateHelper.formateDate("yyyyMMdd"));
            }
        }
        super.saveOrUpdate(obj);

        String bh = String.valueOf(obj.getBh());
        int len = bh.length();
        String str = "00000".substring(0, 5 - len) + bh;

        return str;
    }

    /**
     * 获取业务流水号编码
     */
    @Override
    public String updateLkbm(String lgbm) {
        String rq = DateHelper.formateDate("yyyyMMdd");
        String lgbm2 = lgbm;
        Lkbmgr gr = (Lkbmgr) super.getObject("from Lkbmgr t where t.lgbm='" + lgbm + "' and rq='" + rq + "'", null);//.getObjectListByHql("from Lkbmgr t where t.lgbm='"+lgbm+"' and rq='"+rq+"'");//.get("com.gnt.rest.entity.Lkbmgr",lgbm2, org.hibernate.LockMode.UPGRADE_NOWAIT);
        if (gr == null) {
            gr = new Lkbmgr();
            gr.setLgbm(lgbm2);
            gr.setRq(rq);
            gr.setXh(new Long(1));
            super.create(gr);
        } else {
            if (!rq.equals(gr.getRq())) {
                gr.setRq(rq);
                gr.setXh(new Long(0));
            }
            gr.setXh(gr.getXh() + 1);
            super.update(gr);
        }

        return (add0(gr.getXh().intValue(), 4).insert(0, lgbm2 + rq)).toString();

    }

    /**
     * 根据业务流水号号获取出生登记信息
     *
     * @param lkbm
     * @return
     */
    @Override
    public Vcsdj queryNbByLkbm(String lkbm) {
        Vcsdj nb = null;
        nb = super.get(Vcsdj.class, lkbm);
        return nb;
    }

    @Override
    public Fqtk queryFqtkByLkbm(String lkbm) {
        Fqtk nb = null;
        nb = super.get(Fqtk.class, lkbm);
        return nb;
    }

    @Override
    public void saveNb(Vcsdj nb, String postid, String hhnbid) {
        //saveNb(nb, null, request);
        super.create(nb);

        // 记录POSTID和入住流水号的关系
        ApiYs ys = new ApiYs();
        ys.setCjsj(new Date());
        ys.setLg(nb.getPcs());
        ys.setNwbbz("1");
        ys.setPostid(postid);
        ys.setLkbm(nb.getYwlsh());

        super.create(ys);
    }




    /**
     * 保存死亡注销
     *
     * @param vs
     * @param postid
     */
    @Override
    public void saveVswzxyw(Vswzxyw vs, String postid) {
        super.create(vs);
        // 记录POSTID和入住流水号的关系
        ApiYs ys = new ApiYs();
        ys.setCjsj(new Date());
        ys.setLg(vs.getPcs());
        ys.setNwbbz("1");
        ys.setPostid(postid);
        ys.setLkbm(vs.getYwlsh());
        super.create(ys);
    }

    @Override
    public Vswzxyw queryVswzxywByYwlsh(String ywlsh) {
        Vswzxyw nb = null;
        nb = super.get(Vswzxyw.class, ywlsh);
        return nb;
    }

    @Override
    public Vbggzxxb queryVbggzxxbByYwlsh(String ywlsh) {
        Vbggzxxb nb = null;
        nb = super.get(Vbggzxxb.class, ywlsh);
        return nb;
    }


    @Override
    public void updateVswzxyw(Vswzxyw vs) {
        super.saveOrUpdate(vs);
    }

    @Override
    public void saveVbggzxxb(Vbggzxxb vs, String postid) {
        super.create(vs);
        // 记录POSTID和入住流水号的关系
        ApiYs ys = new ApiYs();
        ys.setCjsj(new Date());
        ys.setLg(vs.getPcs());
        ys.setNwbbz("1");
        ys.setPostid(postid);
        ys.setLkbm(vs.getYwlsh());
        super.create(ys);
    }

    @Override
    public void updateVbggzxxb(Vbggzxxb vs) {
        super.saveOrUpdate(vs);
    }

    @Override
    public void saveVfjcl(Vfjcl vf, String postid) {
        //saveNb(nb, null, request);
        super.create(vf);
        // 记录POSTID和入住流水号的关系
        ApiYs ys = new ApiYs();
        ys.setCjsj(new Date());
        ys.setLg(vf.getPcs());
        ys.setNwbbz("1");
        ys.setPostid(postid);
        ys.setLkbm(vf.getYwlsh());

        super.create(ys);
    }

    @Override
    public void updateVfjcl(Vfjcl vf) {
        super.saveOrUpdate(vf);
    }

    @Override
    public void updateNb(Vcsdj nb) {
        //saveNb(nb, null, request);
        super.saveOrUpdate(nb);//.create(nb);

    }

    @Override
    public void saveFqtk(Fqtk nb, String postid) {
        //saveNb(nb, null, request);
        super.create(nb);
        // 记录POSTID和入住流水号的关系
        ApiYs ys = new ApiYs();
        ys.setCjsj(new Date());
        ys.setLg(nb.getPcs());
        ys.setNwbbz("1");
        ys.setPostid(postid);
        ys.setLkbm(nb.getYwlsh());

        super.create(ys);
    }

    @Override
    public void updateFqtk(Fqtk nb) {
        //saveNb(nb, null, request);
        super.saveOrUpdate(nb);//.create(nb);

    }

    @Override
    public void saveOrupdateFqtk(Fqtk nb) {
        //saveNb(nb, null, request);
        super.saveOrUpdate(nb);//.create(nb);

    }

    @Override
    public void saveZjywZqzxx(ZjywZqzxx zqzxx, String pcsbm) {
        super.create(zqzxx);
        // 记录POSTID和入住流水号的关系
        ApiYs ys = new ApiYs();
        ys.setCjsj(new Date());
        ys.setLg(pcsbm);
        ys.setNwbbz("1");
        ys.setPostid(zqzxx.getYwlsh());
        ys.setLkbm(zqzxx.getPostid());
        super.create(ys);
    }

    @Override
    public ZjywZqzxx queryZjywZqzxxByPostid(String postid) {
        ZjywZqzxx nb = null;
        nb = super.get(ZjywZqzxx.class, postid);
        return nb;
    }

    @Override
    public void updateZjywZqzxx(ZjywZqzxx zqzxx) {
        super.saveOrUpdate(zqzxx);
    }


    @Override
    public void saveZjywZqzQyrxx(ZjywZqzQyrxx zqzQyrxx) {
        super.saveOrUpdate(zqzQyrxx);
    }

    @Override
    public void saveZjywQyzxx(ZjywQyzxx qyzxx, String pcsbm) {
        super.create(qyzxx);
        // 记录POSTID和入住流水号的关系
        ApiYs ys = new ApiYs();
        ys.setCjsj(new Date());
        ys.setLg(pcsbm);
        ys.setNwbbz("1");
        ys.setPostid(qyzxx.getQyzbh());
        ys.setLkbm(qyzxx.getYwlsh());
        super.create(ys);
    }

    @Override
    public void updateZjywQyzxx(ZjywQyzxx qyzxx) {
        super.saveOrUpdate(qyzxx);
    }

    @Override
    public void saveZjywQyzQyrxx(ZjywQyzxxQyrxx zqzQyrxx) {
        super.saveOrUpdate(zqzQyrxx);
    }

    @Override
    public ZjywQyzxx queryZjywQyzxxByPostid(String qyzbh) {
        ZjywQyzxx nb = null;
        nb = super.get(ZjywQyzxx.class, qyzbh);
        return nb;
    }

    private StringBuffer add0(int n, int size) {
        StringBuffer sb = new StringBuffer();
        sb.append(n);
        int m = size - sb.length();
        for (int i = 0; i < m; i++) {
            sb.insert(0, '0');
        }
        return sb;
    }


}
