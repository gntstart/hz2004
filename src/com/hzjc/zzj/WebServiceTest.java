package com.hzjc.zzj;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hzjc.hz2004.base.encode.Hex;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.zzj.bean.*;
import org.apache.axis.client.Call;
import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class WebServiceTest {

    static String pcsbm = "340702";
    static String sbh = "001";
    static String md5 = "";
    //接口访问地址
    static String url = "http://127.0.0.1:8090/hz2004/services/rkxtWebService";

    /**
     * 对象---》字符串
     *
     * @param src
     * @return
     */
    static public String toJson(Object src) {
        GsonBuilder build = new GsonBuilder();
        Gson gson = build.create();
        //net.sf.cglib.proxy.CallbackFilter a;
        return gson.toJson(src);
    }

    /**
     * 对象转换：字符串---》对象
     *
     * @param classOfT
     * @param data
     * @return
     */
    static public <T> T getJsonData(Class<T> classOfT, String data) {
        GsonBuilder build = new GsonBuilder();

        Gson gson = build.create();
        return gson.fromJson(data, classOfT);
    }

    public static void main(String[] args) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        byte[] buff = m.digest("1234567".getBytes());
        md5 = new String(Hex.encode(buff));

        test_postCSDJRecrod();
//        test_postCSDJRecrods();
        //死亡注销
//        test_postSwzxRecrod();
        //辅项变更
//		test_postVbggzxxbRecrod();
        //获取字典详细
//        test_getDictValue();
        //获取字典list
//        test_getDict();

        //查询临时身份证
//        test_queryRkjbxxLssfBysfz();
        // 户口注销证明接口 也是死亡注销证明
        //test_queryRkjbxxZxxxBysfz();

        //同户关系证明接口
//        test_queryThzmBysfz();

        //全国人口基本信息
//        test_queryQgRkjbxxBysfz();

        //直系亲属关系证明接口
//        test_queryRkjbxxQsgxBysfz();

        //是户籍证明接口  也是地市常口信息查询接口
//        test_queryRkjbxxBysfz();

        //附件材料上传
//        test_postFjclRecrod();

        //户成员查询
//        test_queryHcyByhhnbid();

        //变更更正记录查询
//        test_queryBggzjlBysfz();
    }


    /**
     * 获取字典Dict
     *
     * @throws Exception
     */
    public static void test_getDictValue() throws Exception {
        Call call2 = new Call(url);
        call2.setOperationName("getDictValue");

        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5,"CS_SWZXLB", "0100"});
        System.out.println(re);
    }

    /**
     * 获取字典Dict list
     *
     * @throws Exception
     */
    public static void test_getDict() throws Exception {
        Call call2 = new Call(url);
        call2.setOperationName("getDict");
        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5, "CS_ZJXY"});
        System.out.println(re);
    }

    /**
     * 出生登记
     *
     * @throws Exception
     */
    public static void test_postCSDJRecrod() throws Exception {
        Call call2 = new Call(url);
        call2.setOperationName("postCSDJRecrod");

        //测试内宾登记（创建一个内宾对象）
        VcsdjBean nb = getTestVcsdjBean();
//		nb.setPostid("1234578912345998898");
        nb.setXm("测试数据2211");
        //nb.setTfsj("201307011201");

        //将内宾对象转换为JSON字符串表示
        GsonBuilder build = new GsonBuilder();
        Gson gson = build.create();
        String json = gson.toJson(nb);
        //json="{\"postid\":\"2fe084a718f843d6ba343e47ce778ea5\",\"lhsfz\":\"341202198702012030\",\"xm\":\"李龙\",\"xb\":\"1\",\"csrq\":\"20180804\",\"csdssxq\":\"341203\",\"cssj\":\"104142\",\"jgssxq\":\"341202\",\"cszmbh\":\"L340805996\",\"jhryxm\":\"王浩杰\",\"jhrygmsfhm\":\"341202198702012030\",\"jhrylxdh\":\"18523531830\",\"jhryjhgx\":\"51\",\"fqxm\":\"李飞\",\"mqxm\":\"张玲玲\",\"mqgmsfhm\":\"342101198202175844\"}";

        //提交内宾住宿信息，并且获取调用结果的json表示 String pcsbm,String sbh, String md5, String type, String json
        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5, "1", json});
        ReturnBean rb = WebServiceTest.getJsonData(ReturnBean.class, re);
        System.out.println(re);
    }

    /**
     * 夫妻投靠
     *
     * @throws Exception
     */
    public static void test_postFQTKRecrod() throws Exception {
        Call call2 = new Call(url);
        call2.setOperationName("postFQTKRecrod");

        //测试内宾登记（创建一个内宾对象）
        FqtkBean nb = getTestFqtkBean();
        nb.setPostid("123466623456");
        nb.setXm("测试数据");
        //nb.setTfsj("201307011201");

        //将内宾对象转换为JSON字符串表示
        GsonBuilder build = new GsonBuilder();
        Gson gson = build.create();
        String json = gson.toJson(nb);
        //json="{\"postid\":\"2fe084a718f843d6ba343e47ce778ea5\",\"lhsfz\":\"341202198702012030\",\"xm\":\"李龙\",\"xb\":\"1\",\"csrq\":\"20180804\",\"csdssxq\":\"341203\",\"cssj\":\"104142\",\"jgssxq\":\"341202\",\"cszmbh\":\"L340805996\",\"jhryxm\":\"王浩杰\",\"jhrygmsfhm\":\"341202198702012030\",\"jhrylxdh\":\"18523531830\",\"jhryjhgx\":\"51\",\"fqxm\":\"李飞\",\"mqxm\":\"张玲玲\",\"mqgmsfhm\":\"342101198202175844\"}";

        //提交内宾住宿信息，并且获取调用结果的json表示 String pcsbm,String sbh, String md5, String type, String json
        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5, "1", json});
        ReturnBean rb = WebServiceTest.getJsonData(ReturnBean.class, re);
        System.out.println(re);
    }


    /**
     * 批量出生登记
     *
     * @throws Exception
     */
    public static void test_postCSDJRecrods() throws Exception {
        org.apache.axis.client.Call call2 = new org.apache.axis.client.Call(url);
        call2.setOperationName("postCSDJRecrods");

        //准备10个演示用的10个内宾资料，准备批量提供
        List<VcsdjBean> list = new ArrayList<VcsdjBean>();
        for (int i = 0; i < 10; i++) {
            VcsdjBean nb = getTestVcsdjBean();
            nb.setPostid(i + "");
            list.add(nb);
        }

        //将10个内宾数组转换为JSON对象字符串表示
        GsonBuilder build = new GsonBuilder();
        Gson gson = build.create();
        String json_list = gson.toJson(list);

        //提交并返回结果
        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5, "1", json_list});
        System.out.println(re);
    }


    /**
     * 批量夫妻投靠
     *
     * @throws Exception
     */
    public static void test_postFQTKRecrods() throws Exception {
        Call call2 = new Call(url);
        call2.setOperationName("postFQTKRecrods");

        //准备10个演示用的10个内宾资料，准备批量提供
        List<FqtkBean> list = new ArrayList<FqtkBean>();
        for(int i=0;i<10;i++){
            FqtkBean nb = getTestFqtkBean();
            nb.setPostid(i+"");
            list.add(nb);
        }

        //将10个内宾数组转换为JSON对象字符串表示
        GsonBuilder build = new GsonBuilder();
        Gson gson = build.create();
        String json_list = gson.toJson(list);

        //提交内宾住宿信息，并且获取调用结果的json表示 String pcsbm,String sbh, String md5, String type, String json
        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5, "1", json_list});
        ReturnBean rb = WebServiceTest.getJsonData(ReturnBean.class, re);
        System.out.println(re);
    }

    /**
     * 查询临时身份证
     *
     * @throws Exception
     */
    public static void test_queryRkjbxxLssfBysfz() throws Exception {
        org.apache.axis.client.Call call2 = new org.apache.axis.client.Call(url);
        call2.setOperationName("queryRkjbxxLssfBysfz");
        //提交并返回结果
        String sfzh = "340702193403282026";
        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5, sfzh, "1",});
        System.out.println(re);
    }

    /**
     * 户口注销证明接口
     * 也是死亡注销证明
     *
     * @throws Exception
     */
    public static void test_queryRkjbxxZxxxBysfz() throws Exception {
        org.apache.axis.client.Call call2 = new org.apache.axis.client.Call(url);
        call2.setOperationName("queryRkjbxxZxxxBysfz");
        //提交并返回结果
        String sfzh = "150124199105042750";
        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5, sfzh, "1",});
        System.out.println(re);
    }


    /**
     * 同户关系证明接口
     *
     * @throws Exception
     */
    public static void test_queryThzmBysfz() throws Exception {
        org.apache.axis.client.Call call2 = new org.apache.axis.client.Call(url);
        call2.setOperationName("queryThzmBysfz");
        //提交并返回结果
        String sfzh1 = "340702193403282026";
        String sfzh2 = "340702197906280522";
        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5, sfzh1, sfzh2, "1",});
        System.out.println(re);
    }

    /**
     * 查询全国人口信息 by sfzh
     *
     * @throws Exception
     */
    public static void test_queryQgRkjbxxBysfz() throws Exception {
        org.apache.axis.client.Call call2 = new org.apache.axis.client.Call(url);
        call2.setOperationName("queryQgRkjbxxBysfz");
        //提交并返回结果
        String sfzh = "340702199010100030";
        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5, sfzh});
        System.out.println(re);
    }

    /**
     * 直系亲属关系证明接口
     *
     * @throws Exception
     */
    public static void test_queryRkjbxxQsgxBysfz() throws Exception {
        org.apache.axis.client.Call call2 = new org.apache.axis.client.Call(url);
        call2.setOperationName("queryRkjbxxQsgxBysfz");
        //提交并返回结果
        String sfzh1 = "340702193403282026";
        String sfzh2 = "340702197906280522";
        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5, sfzh1, sfzh2, "1",});
        System.out.println(re);
    }


    /**
     * 是户籍证明接口
     * 也是地市常口信息查询接口
     *
     * @throws Exception
     */
    public static void test_queryRkjbxxBysfz() throws Exception {
        org.apache.axis.client.Call call2 = new org.apache.axis.client.Call(url);
        call2.setOperationName("queryRkjbxxBysfz");
        //提交并返回结果
        String sfzh = "340702193403282026";
        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5, sfzh});
        System.out.println(re);
    }

    /**
     * 附件材料上传
     *
     * @throws Exception
     */
    public static void test_postFjclRecrod() throws Exception {
        org.apache.axis.client.Call call2 = new org.apache.axis.client.Call(url);
        call2.setOperationName("postFjclRecrod");
        //提交并返回结果
        //测试内宾登记（创建一个内宾对象）

        List<VfjclBean> list = new ArrayList<VfjclBean>();
        for(int i=0;i<2;i++){
            VfjclBean nb = getTestVfjclBean();
			nb.setPostid(i+"");
            list.add(nb);
        }
        //将内宾对象转换为JSON字符串表示
        GsonBuilder build = new GsonBuilder();
        Gson gson = build.create();
        String json = "[{\"postid\":\"0045\",\"ywlsid\":\"3401040135201912050002\",\"ywlx\":\"F1005\",\"fjclname\":\"户口本照片\",\"fjcllx\":\"11010104020\",\"fjclzp\":\"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\"},\n" +
                "{\"postid\":\"0034\",\"ywlsid\":\"3401040135201912050002\",\"ywlx\":\"F1005\",\"fjclname\":\"婚姻状况照片\",\"fjcllx\":\"11010101020\",\"fjclzp\":\"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\"},\n" +
                "{\"postid\":\"0012\",\"ywlsid\":\"3401040135201912050002\",\"ywlx\":\"F1005\",\"fjclname\":\"出生证照片\",\"fjcllx\":\"11010101010\",\"fjclzp\":\"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\"}]\n";

        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5,"1", json});
        System.out.println(re);
    }

    /**
     * 户成员查询
     * @throws Exception
     */
    public static void test_queryHcyByhhnbid() throws Exception{
        org.apache.axis.client.Call call2 = new org.apache.axis.client.Call(url);
        call2.setOperationName("queryHcyByhhnbid");
        String hhnbid = "3407000001000755213";
        //提交内宾住宿信息，并且获取调用结果的json表示 String pcsbm,String sbh, String md5, String type, String json
        String re = (String)call2.invoke(new Object[]{pcsbm,sbh,md5,hhnbid,"1"});
        ReturnBean rb = WebServiceTest.getJsonData(ReturnBean.class, re);
        System.out.println(re);
    }

    /**
     * 准迁证核验
     * @throws Exception
     */
    public static void test_queryZqzHy() throws Exception{
        org.apache.axis.client.Call call2 = new org.apache.axis.client.Call(url);
        call2.setOperationName("queryQzHy");
        String sfzh = "342422198408056750";
        String zqzbh = "11111111";
        String ssxq = "11111111";
        //提交内宾住宿信息，并且获取调用结果的json表示 String pcsbm,String sbh, String md5, String type, String json
        String re = (String)call2.invoke(new Object[]{pcsbm,sbh,md5,sfzh,zqzbh,ssxq,"1"});


        ReturnBean rb = WebServiceTest.getJsonData(ReturnBean.class, re);
        System.out.println(re+"-------------------");
    }


    /**
     * 死亡注销
     * @throws Exception
     */
    public static void test_postSwzxRecrod() throws Exception {
        org.apache.axis.client.Call call2 = new org.apache.axis.client.Call(url);
        call2.setOperationName("postSwzxRecrod");

        //测试内宾登记（创建一个内宾对象）
        VswzxywBean nb = getTestVswzxywBean();

        //将内宾对象转换为JSON字符串表示
        GsonBuilder build = new GsonBuilder();
        Gson gson = build.create();
        String json = gson.toJson(nb);

        //提交内宾住宿信息，并且获取调用结果的json表示 String pcsbm,String sbh, String md5, String type, String json
        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5, "1", json});

        ReturnBean rb = WebServiceTest.getJsonData(ReturnBean.class, re);

        System.out.println(re);
    }

    /**
     * 辅项变更
     * @throws Exception
     */
    public static void test_postVbggzxxbRecrod() throws Exception {
        org.apache.axis.client.Call call2 = new org.apache.axis.client.Call(url);
        call2.setOperationName("postBggzxxbRecrod");

        //测试内宾登记（创建一个内宾对象）
        VbggzxxbBean nb = getTestVbggzxxbBean();
        nb.setPostid("123456789987654");

        //将内宾对象转换为JSON字符串表示
        GsonBuilder build = new GsonBuilder();
        Gson gson = build.create();
        String json = gson.toJson(nb);

        //提交内宾住宿信息，并且获取调用结果的json表示 String pcsbm,String sbh, String md5, String type, String json
        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5, "1", json});


        ReturnBean rb = WebServiceTest.getJsonData(ReturnBean.class, re);

        System.out.println(re);
    }

    /**
     * 变更更正记录查询
     * @throws Exception
     */
    public static void test_queryBggzjlBysfz() throws Exception {
        org.apache.axis.client.Call call2 = new org.apache.axis.client.Call(url);
        call2.setOperationName("queryBggzjlBysfz");
        String sfz = "340702201312122518";
        //提交内宾住宿信息，并且获取调用结果的json表示 String pcsbm,String sbh, String md5, String type, String json
        String re = (String) call2.invoke(new Object[]{pcsbm,sbh,md5, sfz, "1"});
        ReturnBean rb = WebServiceTest.getJsonData(ReturnBean.class, re);

        System.out.println(re);
    }

    public static void print(String[] re) {
        if (re == null) {
            System.out.println("null");
        }

        for (int i = 0; i < re.length; i++) {
            String v = re[i];
            System.out.print("re[" + i + "]=" + v);
        }
    }

    public static void print(String[][] re) {
        if (re == null) {
            System.out.println("null");
        }

        for (int i = 0; i < re.length; i++) {
            String[] rows = re[i];
            System.out.print("{");
            for (int j = 0; j < rows.length; j++) {
                if (j > 0) {
                    System.out.print(";");
                }

                System.out.print(rows[j]);
            }
            System.out.println("}");
        }
    }


    /**
     * 封装出生登记数据
     *
     * @return
     */
    public static VcsdjBean getTestVcsdjBean() {
        VcsdjBean nb = new VcsdjBean();
        nb.setHhnbid("3407000001000755066");
        nb.setPostid("123");
        nb.setPcs("3401030135");
        nb.setLhsfz("370902197812290953");
        nb.setXm("张三222");
        nb.setXb("1");
        nb.setMz("01");
        nb.setYhzgx("20");
        //nb.setCjsj("20180624");
        nb.setCsdssxq("340902");
        nb.setCsdxz("出生地相知");
        nb.setCssj("121223");
        nb.setJgssxq("340902");
        nb.setHb("6");
        nb.setJthzl("2");
        nb.setCsdjlb("0101");
        nb.setCszmbh("cszm001");
        nb.setJhryxm("测试");
        nb.setJhrygmsfhm("110103201809195553");
        nb.setJhrylxdh("18508437056");
        nb.setJhryjhgx("02");
        nb.setJhrexm("测试");
        nb.setJhregmsfhm("110103201809195553");
        nb.setJhrelxdh("18508437056");
        nb.setJhrejhgx("02");
        nb.setFqxm("测试");
        nb.setFqgmsfhm("110103201809195553");
        nb.setMqxm("测试");
        nb.setMqgmsfhm("110103201809195553");
        nb.setSbrxm("测试");
        nb.setSbrsfz("110103201809195553");
        nb.setSbrlxdh("13918765444");
        nb.setCsrq("20090909");
        nb.setSbh("sb20090909");
        return nb;
    }

    /**
     * 封装死亡注销数据
     *
     * @return
     */
    public static VswzxywBean getTestVswzxywBean() {
        VswzxywBean nb = new VswzxywBean();
        nb.setPostid("3407000001051442743");
        nb.setBsbrid("3407000001051453616");
        nb.setBsbrname("张三");
        nb.setBsbridcard("340702200001012018");
        nb.setSbrname("李四清");
        nb.setSbridcard("340702196411173536");
        nb.setSwzxlb("0100");
        nb.setSwzmbh("123123123");
        nb.setSbrjtgx("02");
        nb.setSwsj("20190819");
        nb.setPcs("3401030135");
        nb.setSbh("sb20090909");
        return nb;
    }

    /**
     * 封装变更更正数据
     *
     * @return
     */
    public static VbggzxxbBean getTestVbggzxxbBean() {
        VbggzxxbBean nb = new VbggzxxbBean();
        nb.setPostid("123");
        nb.setRyid("3407000001051452985");
        nb.setXm("测试");
        nb.setGmsfhm("370902197812290953");
        nb.setYhzgx("");
        nb.setDhhm("");
        nb.setHyzk("");
        nb.setSg("");
        nb.setMlxz("上海市浦东新区南码头路1111号");
        nb.setFwcs("");
        nb.setWhcd("");
        nb.setByzk("");
        nb.setXx("");
        nb.setZy("");
        nb.setZylb("");
        nb.setPoxm("");
        nb.setPogmsfhm("");
        nb.setZjxy("");
        nb.setLxdh("");
        nb.setSbryxm("测试");
        nb.setSbrgmsfhm("370902197812290953");
        nb.setPcs("3401030135");
        nb.setSbh("sb20090909");
        return nb;
    }

    /**
     * 封装夫妻投靠数据
     *
     * @return
     */
    public static FqtkBean getTestFqtkBean() {
        FqtkBean nb = new FqtkBean();
        nb.setPostid("12345");
        nb.setLhsfz("370902197812290953");
        //  nb.setPcs("3401030135");
        // nb.setYwlsh("3709021978953888");
        nb.setGmsfhm("370902197812290953");//
        nb.setSbrsfz("370902197812290953");//
        nb.setSbrxm("李四");//
        nb.setSbrlxdh("13909098876");//
        //   nb.setFlag("0");//
        //  nb.setSbh("sbh0002");//

        return nb;
    }

    /**
     * 封装附件材料数据
     *
     * @return
     */
    public static VfjclBean getTestVfjclBean(){
        VfjclBean nb=new VfjclBean();
        nb.setPostid("213232232");
//        nb.setYwlsid("3401040135201912050002");
        nb.setYwlx("F1005");
        nb.setFjclname("户口本照片");
        nb.setFjcllx("11010104020");

        String imageString = null;
        try {
            String imagePath = "C:\\Users\\Administrator\\Desktop\\hz\\e29b7a718c4030002b61b3db240080c.jpg";
            String type = StringUtils.substring(imagePath, imagePath.lastIndexOf(".") + 1);
            BufferedImage image = ImageIO.read(new File(imagePath));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        nb.setFjclzp(imageString);
        nb.setFjclsize("10kb");
        nb.setFjclgs(".jpg");
        nb.setPcs("3401030135");
        nb.setSbh("sb20090909");
        return nb;
    }
}
