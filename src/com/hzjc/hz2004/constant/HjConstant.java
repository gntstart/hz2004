package com.hzjc.hz2004.constant;

/**
 * 户籍常量类
 * <p>Title: hz2004</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class HjConstant {

  //入立户标志
  public final static String RLHBZ_RH = "1"; //立户
  public final static String RLHBZ_LH = "2"; //入户

  //准迁证件类型
  public final static String ZQZJLX_ZQZ = "1"; //准迁准
  public final static String ZQZJLX_ZYZ = "2"; //准移证

  //文化程度
  public final static String WHCD_CWSGX = "00"; //从未上过学

  //婚姻状况
  public final static String HYZK_WH = "10"; //未婚
  public final static String HYZK_WSMDHYZK = "90"; //未说明的婚姻状况

  //人员性别
  public final static String RYXB_NA = "1"; //男性
  public final static String RYXB_NV = "2"; //女性

  //人员类别
  public final static String RYLB_YB = "1"; //一般
  public final static String RYLB_GZDX = "2"; //工作对象
  public final static String RYLB_RHFL = "3"; //人户分离
  public final static String RYLB_WCRK = "4"; //袋儿户

  //人员状态
  public final static String RYZT_ZC = "0"; //正常
  public final static String RYZT_SW = "1"; //死亡
  public final static String RYZT_QC = "2"; //迁出
  public final static String RYZT_FBY = "3"; //服兵役
  public final static String RYZT_CGJDJ = "4"; //出国境定居
  public final static String RYZT_SZ = "6"; //失踪   add by zjm 20190702
  public final static String RYZT_SC = "7"; //删除
  public final static String RYZT_ZX = "8"; //注销(用于冲销)
  public final static String RYZT_QT = "9"; //其它

  //人员锁定状态
  public final static String RYSDZT_ZC = "0"; //正常
  public final static String RYSDZT_DJ1 = "1"; //冻结(脱机离线数据)
  public final static String RYSDZT_DJ2 = "2"; //冻结(成批采集)
  public final static String RYSDZT_HBBGSP = "3"; //户别变更审批
  public final static String RYSDZT_HJSCSP = "4"; //户籍删除审批
  public final static String RYSDZT_BGGZSP = "5"; //变更更正审批

  //公民身份号码分配尾数_操作类别
  public final static String GMSFHMFPWS_CZLB_FP = "1"; //分配
  public final static String GMSFHMFPWS_CZLB_LR = "2"; //录入
  public final static String GMSFHMFPWS_CZLB_QT = "9"; //其它

  //户关联状态
  public final static String HGLZT_YWSC = "1"; //业务生成
  public final static String HGLZT_SGSR = "2"; //手工输入

  //建户类别 : select 'public final static String JHLB_' || zwpy || ' = "' || dm || '"; //' ||  mc as 建户类别 from xt_xtcsb where cslb='1017'
  public final static String JHLB_SJRK = "0"; //数据入库
  public final static String JHLB_YWSC = "1"; //业务生成
  public final static String JHLB_QHTZSC = "2"; //区划调整生成
  public final static String JHLB_HXGSC = "3"; //户修改生成

  //撤户类别 : select 'public final static String CHLB_' || zwpy || ' = "' || dm || '"; //' ||  mc as 撤户类别 from xt_xtcsb where cslb='1018'
  public final static String CHLB_YWZX = "1"; //业务注销
  public final static String CHLB_QHTZZX = "2"; //区划调整注销
  public final static String CHLB_HZXGZX = "3"; //户修改注销

  //建地类别 : select 'public final static String JDLB_' || zwpy || ' = "' || dm || '"; //' ||  mc as 建地类别 from xt_xtcsb where cslb='1015'
  public final static String JDLB_SJRK = "0"; //数据入库
  public final static String JDLB_YWJL = "1"; //业务建立
  public final static String JDLB_QHTZJL = "2"; //区划调整建立
  public final static String JDLB_DZXGSC = "3"; //地址修改生成

  //撤地类别 : select 'public final static String CDLB_' || zwpy || ' = "' || dm || '"; //' ||  mc as 撤地类别 from xt_xtcsb where cslb='1016'
  public final static String CDLB_YWZX = "1"; //业务注销
  public final static String CDLB_QHTZZX = "2"; //区划调整注销
  public final static String CDLB_DZXGZX = "3"; //地址修改注销

  //业务内容 : select 'public final static String YWNR_' || zwpy || ' = "' || dm || '"; //' ||  mc as 业务内容 from xt_xtcsb where cslb='1053'
  public final static String YWNR_RKSJRK = "100"; //人口数据入库
  public final static String YWNR_CSDJ = "101"; //出生登记
  public final static String YWNR_SWZX = "102"; //死亡注销
  public final static String YWNR_ZZBDQR = "103"; //住址变动(迁入)
  public final static String YWNR_ZZBDQC = "104"; //住址变动(迁出)
  public final static String YWNR_QRDJ = "105"; //迁入登记
  public final static String YWNR_QCZX = "106"; //迁出注销
  public final static String YWNR_BGGZ = "107"; //变更更正
  public final static String YWNR_HJBL = "108"; //户籍补录
  public final static String YWNR_HJSC = "109"; //户籍删除
  public final static String YWNR_HBBG = "110"; //户别变更
  public final static String YWNR_ZXHK = "111"; //注销户口
  public final static String YWNR_HFHK = "112"; //恢复户口
  public final static String YWNR_HLJ = "201"; //户建立
  public final static String YWNR_HSXBG = "202"; //户属性变更
  public final static String YWNR_HCX = "203"; //户撤消
  public final static String YWNR_DDJ = "301"; //地登记
  public final static String YWNR_DXMBG = "302"; //地项目变更
  public final static String YWNR_DCX = "303"; //地撤消
  public final static String YWNR_QHTZ = "400"; //区划调整

  //变动范围 : select 'public final static String BDFW_' || zwpy || ' = "' || dm || '"; //' ||  mc as 变动范围 from xt_xtcsb where cslb='9005'
  public final static String BDFW_HKLBZH = "11"; //户口类别转换
  public final static String BDFW_FH = "12"; //分户
  public final static String BDFW_BH = "13"; //并户
  public final static String BDFW_BJMWYH = "14"; //本居(村)民委员会内
  public final static String BDFW_BSNJWH = "15"; //本所内居委会之间
  public final static String BDFW_BQNTS = "21"; //本区(县)内他所(乡)
  public final static String BDFW_DSN = "30"; //地市内
  public final static String BDFW_BSSQ = "31"; //本市市区
  public final static String BDFW_BSJQ = "32"; //本市郊区
  public final static String BDFW_BSSXXC = "33"; //本市属县(旗)乡村
  public final static String BDFW_BSSXCZ = "34"; //本市属县(旗)城镇
  public final static String BDFW_SN = "40"; //省内
  public final static String BDFW_BSNXC = "41"; //本省(市、自治区)内乡村
  public final static String BDFW_BSNCZ = "42"; //本省(市、自治区)内城镇
  public final static String BDFW_BSNCS = "43"; //本省(市、自治区)内城市
  public final static String BDFW_GN = "50"; //国内
  public final static String BDFW_BSWXC = "51"; //本省(市、自治区)外乡村
  public final static String BDFW_BSWCZ = "52"; //本省(市、自治区)外城镇
  public final static String BDFW_BSWCS = "53"; //本省(市、自治区)外城市
  public final static String BDFW_GADQ = "61"; //港、澳地区
  public final static String BDFW_TWDQ = "62"; //台湾地区
  public final static String BDFW_GW = "71"; //国外

  //住址变动类型 : select 'public final static String ZZBDLB_' || zwpy || ' = "' || dm || '"; //' ||  mc as 住址变动类别 from xt_xtcsb where cslb='1026'
  public final static String ZZBDLB_DZBD = "0500"; //地址变动
  public final static String ZZBDLB_GBDD = "0501"; //干部调动
  public final static String ZZBDLB_GRDD = "0502"; //工人调动
  public final static String ZZBDLB_ZG = "0503"; //招干
  public final static String ZZBDLB_ZG1 = "0504"; //招工（特招）
  public final static String ZZBDLB_ZDDBSJ = "0505"; //驻当地办事机构干部、职工调?
  public final static String ZZBDLB_JZQYGB = "0506"; //建制迁移干部,职工调动
  public final static String ZZBDLB_ZNDTGZ = "0507"; //子女顶替工作
  public final static String ZZBDLB_JH = "0508"; //结婚
  public final static String ZZBDLB_DZZG = "0509"; //顶职招工
  public final static String ZZBDLB_ZG2 = "0510"; //招工（补员）
  public final static String ZZBDLB_GBDDJS = "0511"; //干部调动家属随迁
  public final static String ZZBDLB_GRDDJS = "0512"; //工人调动家属随迁
  public final static String ZZBDLB_ZGJSSQ = "0513"; //招干家属随迁
  public final static String ZZBDLB_ZGJSSQ1 = "0514"; //招工家属随迁
  public final static String ZZBDLB_ZDDBSJ1 = "0515"; //驻当地办事机构干部、职工家?
  public final static String ZZBDLB_JZQYGB1 = "0516"; //建制迁移干部、职工家属随迁
  public final static String ZZBDLB_JSDD = "0517"; //教师调动子女随迁
  public final static String ZZBDLB_BSYJS = "0520"; //博士、研究生毕业分配
  public final static String ZZBDLB_LTX = "0521"; //离、退休
  public final static String ZZBDLB_TZ = "0522"; //退[辞]职
  public final static String ZZBDLB_TX = "0523"; //退学、转学
  public final static String ZZBDLB_DZZZS = "0524"; //大学以上学生招生
  public final static String ZZBDLB_DZZXSB = "0525"; //大中专学生毕业
  public final static String ZZBDLB_DZXSZS = "0526"; //大专学生招生
  public final static String ZZBDLB_ZZJX = "0527"; //中专、技校学生招生
  public final static String ZZBDLB_ZFSBY = "0528"; //自费生毕业分配
  public final static String ZZBDLB_WPSBY = "0529"; //委陪生毕业分配
  public final static String ZZBDLB_JXSBY = "0530"; //技校生毕业分配
  public final static String ZZBDLB_TKQS = "0531"; //投靠亲属
  public final static String ZZBDLB_FMYZNH = "0532"; //父母与子女互相投靠
  public final static String ZZBDLB_FQTK = "0533"; //夫妻投靠
  public final static String ZZBDLB_FQTKZN = "0534"; //夫妻投靠子女随迁
  public final static String ZZBDLB_KJGBJS = "0535"; //科技干部家属迁移
  public final static String ZZBDLB_MKJXZG = "0536"; //煤矿井下职工家属迁移
  public final static String ZZBDLB_NMSQ = "0537"; //农民随迁
  public final static String ZZBDLB_ZQZNFC = "0538"; //知青子女返城
  public final static String ZZBDLB_JSSJ = "0541"; //家属随军
  public final static String ZZBDLB_BDDDJS = "0542"; //部队调动家属随迁
  public final static String ZZBDLB_JDZYGB = "0543"; //军队转业干部家属随迁
  public final static String ZZBDLB_JDGBZY = "0544"; //军队干部转业
  public final static String ZZBDLB_ZYBFY = "0545"; //志愿兵复员
  public final static String ZZBDLB_YWBTW = "0546"; //义务兵退伍
  public final static String ZZBDLB_JDGB = "0547"; //军队干部离、退休
  public final static String ZZBDLB_LSZC = "0551"; //落实政策
  public final static String ZZBDLB_SY = "0552"; //收[寄]养
  public final static String ZZBDLB_ZLKL = "0553"; //自理口粮
  public final static String ZZBDLB_ZD = "0561"; //征地
  public final static String ZZBDLB_GFRH = "0562"; //购房入户
  public final static String ZZBDLB_TZRH = "0563"; //投资入户
  public final static String ZZBDLB_LYRH = "0564"; //蓝印入户
  public final static String ZZBDLB_RHXCZ = "0569"; //入户小城镇
  public final static String ZZBDLB_TWZY = "0571"; //退伍、转业
  public final static String ZZBDLB_GZRYHG = "0572"; //公职人员回国
  public final static String ZZBDLB_LXRYHG = "0573"; //留学人员回国
  public final static String ZZBDLB_HGDJ = "0574"; //回国定居
  public final static String ZZBDLB_CGAHLD = "0575"; //从港澳回来定居
  public final static String ZZBDLB_CTWHLD = "0576"; //从台湾回来定居
  public final static String ZZBDLB_SZXH = "0577"; //失踪寻回
  public final static String ZZBDLB_WTFJ = "0578"; //外逃反解
  public final static String ZZBDLB_XMSFJC = "0579"; //刑满释放、解除劳教、少管
  public final static String ZZBDLB_CJFBY = "0581"; //参军、服兵役
  public final static String ZZBDLB_GZRYCG = "0582"; //公职人员出国
  public final static String ZZBDLB_LXRYCG = "0583"; //留学人员出国
  public final static String ZZBDLB_CGDJ = "0584"; //出国定居
  public final static String ZZBDLB_QWGA = "0585"; //迁往港澳
  public final static String ZZBDLB_QWTW = "0586"; //迁往台湾
  public final static String ZZBDLB_SZ = "0587"; //失踪
  public final static String ZZBDLB_TWJW = "0588"; //逃往境外
  public final static String ZZBDLB_DBSLJS = "0589"; //逮捕、送劳教、少管
  public final static String ZZBDLB_TQHG = "0590"; //探亲回国
  public final static String ZZBDLB_LH = "0593"; //离婚
  public final static String ZZBDLB_HQ = "0594"; //婚迁
  public final static String ZZBDLB_SNQJ = "0595"; //市内迁居
  public final static String ZZBDLB_FH = "0596"; //分户
  public final static String ZZBDLB_LH1 = "0597"; //立户
  public final static String ZZBDLB_BH = "0598"; //并户
  public final static String ZZBDLB_QT = "0599"; //其他原因

  //迁出注销类型 : select 'public final static String QCZXLB_' || zwpy || ' = "' || dm || '"; //' ||  mc as 迁出注销类别 from xt_xtcsb where cslb='1019'
  public final static String QCZXLB_WYY = "0400"; //迁出注销
  public final static String QCZXLB_GBDD = "0401"; //干部调动
  public final static String QCZXLB_GRDD = "0402"; //工人调动
  public final static String QCZXLB_ZG = "0403"; //招干
  public final static String QCZXLB_ZG1 = "0404"; //招工（特招）
  public final static String QCZXLB_ZDDBSJ = "0405"; //驻当地办事机构干部、职工调?
  public final static String QCZXLB_JZQYGB = "0406"; //建制迁移干部,职工调动
  public final static String QCZXLB_ZNDTGZ = "0407"; //子女顶替工作
  public final static String QCZXLB_JH = "0408"; //结婚
  public final static String QCZXLB_DZZG = "0409"; //顶职招工
  public final static String QCZXLB_ZG2 = "0410"; //招工（补员）
  public final static String QCZXLB_GBDDJS = "0411"; //干部调动家属随迁
  public final static String QCZXLB_GRDDJS = "0412"; //工人调动家属随迁
  public final static String QCZXLB_ZGJSSQ = "0413"; //招干家属随迁
  public final static String QCZXLB_ZGJSSQ1 = "0414"; //招工家属随迁
  public final static String QCZXLB_ZDDBSJ1 = "0415"; //驻当地办事机构干部、职工家?
  public final static String QCZXLB_JZQYGB1 = "0416"; //建制迁移干部、职工家属随迁
  public final static String QCZXLB_JSDD = "0417"; //教师调动子女随迁
  public final static String QCZXLB_BSYJS = "0420"; //博士、研究生毕业分配
  public final static String QCZXLB_LTX = "0421"; //离、退休
  public final static String QCZXLB_TCZ = "0422"; //退[辞]职
  public final static String QCZXLB_TX = "0423"; //退学、转学
  public final static String QCZXLB_DZZZS = "0424"; //大学以上学生招生
  public final static String QCZXLB_DZZXSB = "0425"; //统招生学生毕业
  public final static String QCZXLB_DZXSZS = "0426"; //大专学生招生
  public final static String QCZXLB_ZZJXZS = "0427"; //中专技校招生
  public final static String QCZXLB_ZFSBY = "0428"; //自费生毕业分配
  public final static String QCZXLB_WPSBY = "0429"; //委陪生毕业分配
  public final static String QCZXLB_JXSBY = "0430"; //技校生毕业分配
  public final static String QCZXLB_TKQS = "0431"; //投靠亲属
  public final static String QCZXLB_FMYZNH = "0432"; //父母与子女互相投靠
  public final static String QCZXLB_FQTK = "0433"; //夫妻投靠
  public final static String QCZXLB_FQTKZN = "0434"; //夫妻投靠子女随迁
  public final static String QCZXLB_KJGBJS = "0435"; //科技干部家属迁移
  public final static String QCZXLB_MKJXZG = "0436"; //煤矿井下职工家属迁移
  public final static String QCZXLB_NMSQ = "0437"; //农民随迁
  public final static String QCZXLB_ZQZNFC = "0438"; //知青子女返城
  public final static String QCZXLB_JSSJ = "0441"; //家属随军
  public final static String QCZXLB_BDDDJS = "0442"; //部队调动家属随迁
  public final static String QCZXLB_JDZYGB = "0443"; //军队转业干部家属随迁
  public final static String QCZXLB_JDGBZY = "0444"; //军队干部转业
  public final static String QCZXLB_ZYBFY = "0445"; //志愿兵复员
  public final static String QCZXLB_YWBTW = "0446"; //义务兵退伍
  public final static String QCZXLB_JDGB = "0447"; //军队干部离、退休
  public final static String QCZXLB_LSZC = "0451"; //落实政策
  public final static String QCZXLB_SJY = "0452"; //收（寄）养
  public final static String QCZXLB_ZLKL = "0453"; //自理口粮
  public final static String QCZXLB_ZD = "0461"; //征地
  public final static String QCZXLB_GFRH = "0462"; //购房入户
  public final static String QCZXLB_TZRH = "0463"; //投资入户
  public final static String QCZXLB_LYRH = "0464"; //蓝印入户
  public final static String QCZXLB_TWZY = "0471"; //退伍、转业
  public final static String QCZXLB_GZRYHG = "0472"; //公职人员回国
  public final static String QCZXLB_LXRYHG = "0473"; //留学人员回国
  public final static String QCZXLB_HGDJ = "0474"; //回国定居
  public final static String QCZXLB_CGAHLD = "0475"; //从港澳回来定居
  public final static String QCZXLB_CTWHLD = "0476"; //从台湾回来定居
  public final static String QCZXLB_SZXH = "0477"; //失踪寻回
  public final static String QCZXLB_WTFJ = "0478"; //外逃反解
  public final static String QCZXLB_XMSFJC = "0479"; //刑满释放、解除劳教、少管
  public final static String QCZXLB_CJFBY = "0481"; //参军、服兵役
  public final static String QCZXLB_GZRYCG = "0482"; //公职人员出国
  public final static String QCZXLB_LXRYCG = "0483"; //留学人员出国
  public final static String QCZXLB_CGDJ = "0484"; //出国定居
  public final static String QCZXLB_QWGA = "0485"; //迁往港澳
  public final static String QCZXLB_QWTW = "0486"; //迁往台湾
  public final static String QCZXLB_SZ = "0487"; //失踪
  public final static String QCZXLB_TWJW = "0488"; //逃往境外
  public final static String QCZXLB_DBSLJS = "0489"; //逮捕、送劳教、少管
  public final static String QCZXLB_SC = "0490"; //删除
  public final static String QCZXLB_DGXX = "0491"; //调干学习
  public final static String QCZXLB_JPKC = "0492"; //解聘开除
  public final static String QCZXLB_LH = "0493"; //离婚
  public final static String QCZXLB_HQ = "0494"; //婚迁
  public final static String QCZXLB_BQ = "0495"; //搬迁
  public final static String QCZXLB_RYEY = "0496"; //入(退)幼儿园
  public final static String QCZXLB_FXZX = "0497"; //复学转学
  public final static String QCZXLB_DFCZHK = "0498"; //地方城镇户口迁出
  public final static String QCZXLB_QT = "0499"; //其他

  //户号状态
  public final static String HHZT_ZC = "0"; //正常
  public final static String HHZT_DJBYXXG = "1"; //锁定（不允许修改）
  public final static String HHZT_ZX = "2"; //注销

  //迁出处理_操作类型
  public final static String QCCL_CZXL_RYQC = "1"; //人员迁出
  public final static String QCCL_CZXL_RYBG = "2"; //人员变更
  public final static String QCCL_CZXL_RYHF = "3"; //人员恢复

  //迁出处理_处理标志
  public final static String QCCL_CLBZ_WCL = "0"; //未处理
  public final static String QCCL_CLBZ_YCL = "1"; //已处理
  public final static String QCCL_CLBZ_YCLBDY = "2"; //已处理并打印

  //1001,打印标志
  //add kgb 2004-04-22
  public final static String DKDY_WDY = "0"; //'0','未打印','WDY'
  public final static String DKDY_YDY = "1"; //'1','已打印','YDY'

  //户成员变动类型
  public final static String HCYBDLX_CSH = "0"; //初始化
  public final static String HCYBDLX_RH = "1"; //入户
  public final static String HCYBDLX_LH = "2"; //离户
  public final static String HCYBDLX_JTGXBD = "3"; //家庭关系变动

  //重号处理方式
  public final static String CHCLFS_WCL = "0"; //未处理
  public final static String CHCLFS_BRZHXG = "1"; //本人证号修改
  public final static String CHCLFS_DFZHXG = "2"; //对方证号修改
  public final static String CHCLFS_BRQC = "3"; //本人迁出
  public final static String CHCLFS_DFQC = "4"; //对方迁出
  public final static String CHCLFS_BRSW = "5"; //本人死亡
  public final static String CHCLFS_DFSW = "6"; //对方死亡
  public final static String CHCLFS_BRZX = "7"; //本人注销
  public final static String CHCLFS_DFZX = "8"; //对方注销
  public final static String CHCLFS_QT = "9"; //其他

  //人户分离状态
  public final static String RHFLZT_SYZ = "1"; //使用中
  public final static String RHFLZT_ZX = "2"; //注销
  public final static String RHFLZT_HJQC = "3";//户籍迁出
  public final static String RHFLZT_HJSW = "4";//户籍死亡
  public final static String RHFLZT_HJSC = "5";//户籍删除

  public final static String RHFLZT_BG = "2"; //变更
  public final static String RHFLZT_JJ = "3"; //解决
  public final static String RHFLZT_CX = "4"; //撤消

  //审批标志
  public final static String SPBZ_BXSP = "0"; //不需审批
  public final static String SPBZ_XSP = "1"; //需审批

  //落实标志
  public final static String LSBZ_WLS = "0"; //未落实
  public final static String LSBZ_YLS = "1"; //已落实

  //审批结果
  public final static String SPJG_BTY = "0"; //不同意
  public final static String SPJG_TY = "1"; //同意
  public final static String SPJG_WX = "2"; //无效

  //打印标志
  public final static String DYBZ_WDY = "0"; //打印日期
  public final static String DYBZ_YDY = "1"; //已打印

  //变更更正_控制标志
  public final static String BGGZ_KZBZ_BYX = "0"; //不允许
  public final static String BGGZ_KZBZ_YX = "1"; //允许

  //变更更正类别
  public final static String BGGZLB_GMSFHM = "10"; //变更更正公民身份号码
  public final static String BGGZLB_GMSFHM_JZCH = "11"; //纠正重号
  public final static String BGGZLB_GMSFHM_JZZH = "12"; //纠正错号
  public final static String BGGZLB_GMSFHM_QT = "19"; //其他
  public final static String BGGZLB_XM = "20"; //变更更正姓名
  public final static String BGGZLB_XM_BG = "21"; //变更姓名
  public final static String BGGZLB_XM_GZ = "22"; //更正姓名
  public final static String BGGZLB_XM_BGCYM = "23"; //变更曾用名
  public final static String BGGZLB_XB = "30"; //变更更正性别
  public final static String BGGZLB_XB_BG = "31"; //变更性别
  public final static String BGGZLB_XB_GZ = "32"; //更正性别
  public final static String BGGZLB_CSRQ = "40"; //变更更正出生日期
  public final static String BGGZLB_CSRQ_BG = "41"; //更正出生日期
  public final static String BGGZLB_CSRQ_QT = "49"; //其他
  public final static String BGGZLB_MZ = "50"; //变更更正民族
  public final static String BGGZLB_MZ_BG = "51"; //变更民族
  public final static String BGGZLB_MZ_GZ = "52"; //更正民族
  public final static String BGGZLB_CSD = "60"; //变更更正出生地
  public final static String BGGZLB_CSD_BG = "61"; //更正出生地
  public final static String BGGZLB_CSD_QT = "69"; //其他
  public final static String BGGZLB_JG = "70"; //变更更正籍贯
  public final static String BGGZLB_JG_BG = "71"; //变更籍贯
  public final static String BGGZLB_JG_GZ = "72"; //更正籍贯
  public final static String BGGZLB_XXJB = "80"; //变更更正信息级别
  public final static String BGGZLB_XXJB_BG = "81"; //变更信息级别
  public final static String BGGZLB_QT = "90"; //变更更正其他项目
  public final static String BGGZLB_QT_BG = "91"; //变更其他项目
  public final static String BGGZLB_QT_GZ = "92"; //更正其他项目

  //动作标志(模板流程)
  public final static String DZBZ_QSB = "0"; //起始步
  public final static String DZBZ_DYB = "1"; //单一步
  public final static String DZBZ_ZJB = "2"; //中间步
  public final static String DZBZ_JSB = "9"; //结束步

  //审批类型
  public final static String SPLX_RCLH = "1"; //人才落户
  public final static String SPLX_SWQR = "2"; //市外迁入
  public final static String SPLX_BGGZ = "3"; //变更更正
  public final static String SPLX_HBBG = "4"; //户别变更
  public final static String SPLX_HJBL = "5"; //户籍补录
  public final static String SPLX_HJSC = "6"; //户籍删除

  //操作结果
  public final static String CZJG_BTY = "0"; //不同意
  public final static String CZJG_TY = "1"; //同意
  
  //信息读
  public final static String READ_WD = "0"; //未读
  public final static String READ_YD = "1"; //已读
  
  //信息删除
  public final static String REMOVE_WSC = "0"; //未删除
  public final static String REMOVE_YSC = "1"; //已删除

}
