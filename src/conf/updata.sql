-- Create table
create table HZPYDZB
(
  HZ   VARCHAR2(10) not null,
  PY   VARCHAR2(20) not null,
  PYID VARCHAR2(32) not null
)
tablespace HZ2004_CZRK
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  
-- Create table
create table ZJYW_ZZHKXXB
(
  ZZHKID   NUMBER(19) not null,
  RYNBID   NUMBER(19) not null,
  NBSLID   NUMBER(19) not null,
  KTGLH    VARCHAR2(20),
  ZZDWFFRQ CHAR(8),
  JDZP     LONG RAW,
  ZJYWID   NUMBER(19) not null,
  CXBZ     CHAR(1) default '0' not null,
  CXSJ     CHAR(14),
  CXRID    NUMBER(19),
  CXZJYWID NUMBER(19),
  ZZHKSJ   CHAR(14)
)
tablespace HZ2004_PRM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 872
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table ZJYW_ZZHKXXB
  is '制证回馈信息表';
-- Add comments to the columns 
comment on column ZJYW_ZZHKXXB.ZZHKID
  is '制证回馈ID';
comment on column ZJYW_ZZHKXXB.RYNBID
  is '人员内部ID';
comment on column ZJYW_ZZHKXXB.NBSLID
  is '内部受理ID';
comment on column ZJYW_ZZHKXXB.KTGLH
  is '卡体管理号';
comment on column ZJYW_ZZHKXXB.ZZDWFFRQ
  is '制证单位分发日期';
comment on column ZJYW_ZZHKXXB.JDZP
  is '冲销证件业务ID';
comment on column ZJYW_ZZHKXXB.ZJYWID
  is '证件业务ID';
comment on column ZJYW_ZZHKXXB.CXBZ
  is '冲销标志';
comment on column ZJYW_ZZHKXXB.CXSJ
  is '冲销时间';
comment on column ZJYW_ZZHKXXB.CXRID
  is '冲销人ID';
comment on column ZJYW_ZZHKXXB.CXZJYWID
  is '机读照片';
comment on column ZJYW_ZZHKXXB.ZZHKSJ
  is '制证回馈时间';
CREATE TABLE ZJYW_FFJSXXB
(
  FFJSID    NUMBER(19)                          NOT NULL,
  SLH       CHAR(22 BYTE)                       NOT NULL,
  SLZT      CHAR(2 BYTE)                        NOT NULL,
  XM        NVARCHAR2(40)                       NOT NULL,
  GMSFHM    CHAR(18 BYTE)                       NOT NULL,
  YXQXQSRQ  CHAR(8 BYTE),
  YXQXJZRQ  CHAR(8 BYTE),
  CZSJ      CHAR(14 BYTE),
  CZRID     NUMBER(19)                          NOT NULL,
  CZRIP     NVARCHAR2(40),
  CZRDW     CHAR(9 BYTE),
  BZ        NVARCHAR2(200),
  FJPCH     CHAR(16 BYTE)                       NOT NULL,
  RYID      NUMBER(19)                          NOT NULL,
  RYNBID    NUMBER(19)                          NOT NULL,
  MLPNBID   NUMBER(19)                          NOT NULL,
  SSXQ      CHAR(6 BYTE),
  JLX       CHAR(12 BYTE),
  MLPH      NVARCHAR2(40),
  MLXZ      NVARCHAR2(400),
  PCS       CHAR(9 BYTE)                        NOT NULL,
  ZRQ       VARCHAR2(20 BYTE),
  XZJD      VARCHAR2(20 BYTE),
  JCWH      VARCHAR2(20 BYTE)                   NOT NULL,
  PXH       NVARCHAR2(400),
  CXBZ      CHAR(1 BYTE)                        NOT NULL
)
TABLESPACE HZ2004_PRM
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
MONITORING;

COMMENT ON TABLE ZJYW_FFJSXXB IS '证件分发接收信息表';

COMMENT ON COLUMN ZJYW_FFJSXXB.FFJSID IS '分检结果ID';

COMMENT ON COLUMN ZJYW_FFJSXXB.SLH IS '受理号';

COMMENT ON COLUMN ZJYW_FFJSXXB.SLZT IS '受理状态';

COMMENT ON COLUMN ZJYW_FFJSXXB.XM IS '姓名';

COMMENT ON COLUMN ZJYW_FFJSXXB.GMSFHM IS '公民身份号码';

COMMENT ON COLUMN ZJYW_FFJSXXB.YXQXQSRQ IS '有效期限起始日期';

COMMENT ON COLUMN ZJYW_FFJSXXB.YXQXJZRQ IS '有效期限截止日期';

COMMENT ON COLUMN ZJYW_FFJSXXB.CZSJ IS '操作时间';

COMMENT ON COLUMN ZJYW_FFJSXXB.CZRID IS '操作人ID';

COMMENT ON COLUMN ZJYW_FFJSXXB.CZRIP IS '操作人IP';

COMMENT ON COLUMN ZJYW_FFJSXXB.CZRDW IS '操作人单位';

COMMENT ON COLUMN ZJYW_FFJSXXB.BZ IS '备注';

COMMENT ON COLUMN ZJYW_FFJSXXB.FJPCH IS '分检批次号';

COMMENT ON COLUMN ZJYW_FFJSXXB.RYID IS '人员ID';

COMMENT ON COLUMN ZJYW_FFJSXXB.RYNBID IS '人员内部ID';

COMMENT ON COLUMN ZJYW_FFJSXXB.MLPNBID IS '门楼牌内部ID';

COMMENT ON COLUMN ZJYW_FFJSXXB.SSXQ IS '省市县（区）';

COMMENT ON COLUMN ZJYW_FFJSXXB.JLX IS '街路巷';

COMMENT ON COLUMN ZJYW_FFJSXXB.MLPH IS '门楼牌号';

COMMENT ON COLUMN ZJYW_FFJSXXB.MLXZ IS '门楼详址';

COMMENT ON COLUMN ZJYW_FFJSXXB.PCS IS '派出所';

COMMENT ON COLUMN ZJYW_FFJSXXB.ZRQ IS '责任区';

COMMENT ON COLUMN ZJYW_FFJSXXB.XZJD IS '乡镇街道';

COMMENT ON COLUMN ZJYW_FFJSXXB.JCWH IS '居（村）委会';

COMMENT ON COLUMN ZJYW_FFJSXXB.PXH IS '排序号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ZJYW_ZZHKXXB
  add constraint PK_ZJYW_ZZHKXXB primary key (ZZHKID)
  using index 
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 17M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate indexes 
create index I_ZZHK_CXBZ on ZJYW_ZZHKXXB (CXBZ)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 10M
    minextents 1
    maxextents unlimited
  );
create index I_ZZHK_NBSLID on ZJYW_ZZHKXXB (NBSLID)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 17M
    minextents 1
    maxextents unlimited
  );
create index I_ZZHK_RYNBID on ZJYW_ZZHKXXB (RYNBID)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 17M
    minextents 1
    maxextents unlimited
  );
  
-- Create/Recreate primary, unique and foreign key constraints 
alter table HZPYDZB
  add constraint HZPYDZB_PK primary key (PYID)
  using index 
  tablespace HZ2004_CZRK
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
 -- Create table
create table WW_FJJGRZB
(
  FJJGID   NUMBER(19) not null,
  SLH      CHAR(22) not null,
  SLZT     CHAR(2) not null,
  XM       NVARCHAR2(20) not null,
  GMSFHM   CHAR(18) not null,
  YXQXQSRQ CHAR(8),
  YXQXJZRQ CHAR(8),
  CZSJ     CHAR(14),
  CZRID    NUMBER(19) not null,
  CZRIP    NVARCHAR2(20),
  CZRDW    CHAR(9),
  BZ       NVARCHAR2(100),
  FJPCH    CHAR(16),
  CWH      NUMBER,
  CWHDYPCS CHAR(9),
  RYID     NUMBER(19),
  RYNBID   NUMBER(19),
  MLPNBID  NUMBER(19),
  SSXQ     CHAR(6),
  JLX      CHAR(12),
  MLPH     NVARCHAR2(20),
  MLXZ     NVARCHAR2(200),
  PCS      CHAR(9),
  ZRQ      VARCHAR2(20),
  XZJD     VARCHAR2(20),
  JCWH     VARCHAR2(20),
  PXH      NVARCHAR2(200),
  JLBZ     CHAR(1) default '1'
)
tablespace HZ2004_CZRK
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table WW_FJJGRZB
  is '分检结果日志表';
-- Add comments to the columns 
comment on column WW_FJJGRZB.FJJGID
  is '分检结果ID';
comment on column WW_FJJGRZB.SLH
  is '受理号';
comment on column WW_FJJGRZB.SLZT
  is '分检前受理状态';
comment on column WW_FJJGRZB.XM
  is '姓名';
comment on column WW_FJJGRZB.GMSFHM
  is '公民身份号码';
comment on column WW_FJJGRZB.YXQXQSRQ
  is '有效期限起始日期';
comment on column WW_FJJGRZB.YXQXJZRQ
  is '有效期限截止日期';
comment on column WW_FJJGRZB.CZSJ
  is '操作时间';
comment on column WW_FJJGRZB.CZRID
  is '操作人ID';
comment on column WW_FJJGRZB.CZRIP
  is '操作人IP';
comment on column WW_FJJGRZB.CZRDW
  is '操作人单位';
comment on column WW_FJJGRZB.BZ
  is '备注';
comment on column WW_FJJGRZB.FJPCH
  is '分检批次号';
comment on column WW_FJJGRZB.CWH
  is '槽位号';
comment on column WW_FJJGRZB.CWHDYPCS
  is '槽位号对应派出所';
comment on column WW_FJJGRZB.RYID
  is '人员ID';
comment on column WW_FJJGRZB.RYNBID
  is '人员内部ID';
comment on column WW_FJJGRZB.MLPNBID
  is '门楼牌内部ID';
comment on column WW_FJJGRZB.SSXQ
  is '省市县（区）';
comment on column WW_FJJGRZB.JLX
  is '街路巷';
comment on column WW_FJJGRZB.MLPH
  is '门楼牌号';
comment on column WW_FJJGRZB.MLXZ
  is '门楼详址';
comment on column WW_FJJGRZB.PCS
  is '派出所';
comment on column WW_FJJGRZB.ZRQ
  is '责任区';
comment on column WW_FJJGRZB.XZJD
  is '乡镇街道';
comment on column WW_FJJGRZB.JCWH
  is '居（村）委会';
comment on column WW_FJJGRZB.PXH
  is '排序号';
comment on column WW_FJJGRZB.JLBZ
  is '记录标志';
-- Create/Recreate primary, unique and foreign key constraints 
alter table WW_FJJGRZB
  add constraint PK_WW_FJJGRZB primary key (FJJGID)
  using index 
  tablespace HZ2004_CZRK
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate indexes 
create index I_FJJG_CZRDW on WW_FJJGRZB (CZRDW)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_CZRID on WW_FJJGRZB (CZRID)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_CZSJ on WW_FJJGRZB (CZSJ)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_FJPCH on WW_FJJGRZB (FJPCH)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_GMSFHM on WW_FJJGRZB (GMSFHM)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_JCWH on WW_FJJGRZB (JCWH)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_JLX on WW_FJJGRZB (JLX)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_MLPH on WW_FJJGRZB (MLPH)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_MLPNBID on WW_FJJGRZB (MLPNBID)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_MLXZ on WW_FJJGRZB (MLXZ)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_PCS on WW_FJJGRZB (PCS)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_PXH on WW_FJJGRZB (PXH)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_RYID on WW_FJJGRZB (RYID)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_RYNBID on WW_FJJGRZB (RYNBID)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_SLH on WW_FJJGRZB (SLH)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_SLZT on WW_FJJGRZB (SLZT)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_XM on WW_FJJGRZB (XM)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_XZJD on WW_FJJGRZB (XZJD)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_YXQXJZRQ on WW_FJJGRZB (YXQXJZRQ)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create index I_FJJG_YXQXQSRQ on WW_FJJGRZB (YXQXQSRQ)
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  ); 
-- Create/Recreate primary, unique and foreign key constraints 
alter table SB_YWBWDZ
  add constraint SB_YWBWDZ_PK primary key (YWLX);
  
  -- Add/modify columns 
alter table HZPYDZB add PYID varchar2(32);
update hzpydzb set pyid = hz || '.' || py;

-- Create/Recreate primary, unique and foreign key constraints 
alter table HZPYDZB
  drop constraint HZPYDZB_PK cascade;
alter table HZPYDZB
  add constraint HZPYDZB_PK primary key (PYID);
  
  -- Create/Recreate primary, unique and foreign key constraints 
alter table VIEW_ZW
  add constraint VIEW_ZW_PK primary key (JMSFZSLH);
  
  -- Create/Recreate primary, unique and foreign key constraints 
alter table VIEW_ZZST
  add constraint VIEW_ZZST_PK primary key (JMSFZSLH);
  
-- Add/modify columns 
alter table XT_XTGNCDB add URL varchar2(200);
-- Add comments to the columns 
comment on column XT_XTGNCDB.URL
  is '动作URL, B/S模式需要';

-- Add/modify columns 
alter table HZ_ZJ_SB add qcdbm varchar2(50);
create or replace view V_QRSPXXB as
select     pohjsp_hjs0_.SPYWID         ,
       pohjsp_hjs1_.SPLSID         ,
       pohjsp_hjs1_.DZID           ,
       pohjsp_hjs0_.SQRGMSFHM      ,
       pohjsp_hjs0_.SPMBID         ,
       pohjsp_hjs0_.SQRXM          ,
       pohjsp_hjs0_.SQRZZSSXQ      ,
       pohjsp_hjs0_.SPLX           ,
       pohjsp_hjs0_.SLRQ           ,
       pohjsp_hjs0_.XM             ,
       pohjsp_hjs0_.RYID           ,
       pohjsp_hjs0_.XB             ,
       pohjsp_hjs0_.GMSFHM         ,
       pohjsp_hjs0_.CSRQ           ,
       pohjsp_hjs0_.MZ             ,
       pohjsp_hjs0_.JHNY           ,
       pohjsp_hjs0_.YJRCLX         ,
       pohjsp_hjs0_.YRDWJCYRYSJ    ,
       pohjsp_hjs0_.LXDH           ,
       pohjsp_hjs0_.BYXX           ,
       pohjsp_hjs0_.WHCD           ,
       pohjsp_hjs0_.BYSJ           ,
       pohjsp_hjs0_.ZYZL           ,
       pohjsp_hjs0_.BYZSH         ,
       pohjsp_hjs0_.ZYJSZC         ,
       pohjsp_hjs0_.JSZSH          ,
       pohjsp_hjs0_.JSFZJG         ,
       pohjsp_hjs0_.FMZLMC         ,
       pohjsp_hjs0_.FMZLH          ,
       pohjsp_hjs0_.ZLFZJG         ,
       pohjsp_hjs0_.HB             ,
       pohjsp_hjs0_.QRHHB          ,
       pohjsp_hjs0_.ZZSSXQ         ,
       pohjsp_hjs0_.ZZXZ           ,
       pohjsp_hjs0_.HKSZDDJJG      ,
       pohjsp_hjs0_.QRDQX          ,
       pohjsp_hjs0_.QRDPCS         ,
       pohjsp_hjs0_.QRDJWZRQ       ,
       pohjsp_hjs0_.QRDXZJD        ,
       pohjsp_hjs0_.QRDJWH         ,
       pohjsp_hjs0_.QRDJLX         ,
       pohjsp_hjs0_.QRDMLPH        ,
       pohjsp_hjs0_.QRDZ           ,
       pohjsp_hjs0_.QRDHKDJJG      ,
       pohjsp_hjs0_.QRDHHID        ,
       pohjsp_hjs0_.QRDHLX         ,
       pohjsp_hjs0_.RLHBZ          ,
       pohjsp_hjs0_.SQQRLY         ,
       pohjsp_hjs0_.SQRZZXZ        ,
       pohjsp_hjs0_.BZ            ,
       pohjsp_hjs0_.DJRID         ,
       pohjsp_hjs0_.DJSJ           ,
       pohjsp_hjs0_.XYDZID         ,
       pohjsp_hjs0_.SPJG           ,
       pohjsp_hjs0_.SQRHKDJJG      ,
       pohjsp_hjs0_.LSBZ           ,
       pohjsp_hjs0_.RYNBID         ,
       pohjsp_hjs0_.HJYWID         ,
       pohjsp_hjs0_.YSQRGX         ,
       pohjsp_hjs0_.SPSM           ,
       pohjsp_hjs0_.BZDZ           ,
       pohjsp_hjs0_.BZDZID         ,
       pohjsp_hjs0_.BZDZX          ,
       pohjsp_hjs0_.BZDZY          ,
       pohjsp_hjs0_.BZDZST         ,
       pohjsp_hjs0_.cxfldm         ,
       pohjsp_hjs0_.nyzyrklhczyydm ,
       pohjsp_hjs0_.kdqqy_qchjywid ,
       pohjsp_hjs0_.kdqqy_qcdqbm   ,
       pohjsp_hjs0_.kdqqy_fksj     ,
       pohjsp_hjs0_.kdqqy_fkzt     ,
       pohjsp_hjs0_.kdqqy_fknr     ,
       pohjsp_hjs0_.kdqqy_czyxm    ,
       pohjsp_hjs0_.kdqqy_czydw    ,
       pohjsp_hjs0_.kdqqy_qyzbh    ,
       pohjsp_hjs0_.kdqqy_lscxfldm ,
       pohjsp_hjs1_.CZJG           ,
       pohjsp_hjs1_.CZYJ           ,
       pohjsp_hjs1_.CZRID        ,
       pohjsp_hjs1_.CZSJ          ,
       poxt_spdzb2_.DZMC       as xydz_dzmc,
       poxt_spdzb3_.DZMC           
    from HJSP_HJSPSQB pohjsp_hjs0_,
       HJSP_HJSPLSB pohjsp_hjs1_,
       XT_SPDZB     poxt_spdzb2_,
       XT_SPDZB     poxt_spdzb3_
   where (pohjsp_hjs0_.SPYWID = pohjsp_hjs1_.SPYWID)
     and (pohjsp_hjs0_.SPLX = pohjsp_hjs1_.SPLX)
     and (pohjsp_hjs0_.XYDZID = poxt_spdzb2_.DZID(+))
     and (pohjsp_hjs1_.DZID = poxt_spdzb3_.DZID(+))
   order by pohjsp_hjs0_.xm, pohjsp_hjs0_.SPYWID;

comment on table V_QRSPXXB is '迁入审批信息视图';
CREATE OR REPLACE VIEW V_BGSPXXB AS
select cz.zpid,
               cz.xm,
               cz.xb,
               cz.mz,
               cz.csrq,
               cz.gmsfhm,
               bg.spjg,
               bg.lsbz,
               cz.ssxq,
               cz.jlx,
               cz.mlph,
               cz.mlxz,
               cz.pcs,
               cz.zrq,
               cz.xzjd,
               cz.jcwh,
               bg.bgryid,
               bg.spmbid,
               bg.xydzid,
               xt2.dzmc,
               bg.bghrynbid,
               bg.hjywid,
               bg.spywid,
               cz.pxh,
               hj.splsid,
               hj.splx,
               hj.czjg,
               hj.czyj,
               hj.czrid,
               hj.czsj,
               xt2.dzmc as xydz_dzmc,
               bg.spsm,
               hj.dzid
          from HJSP_BGSPXXB bg, HJXX_CZRKJBXXB cz, HJSP_HJSPLSB hj,XT_SPDZB xt1,XT_SPDZB xt2 
         where bg.BGRYID = cz.RYID
           and hj.SPYWID = bg.SPYWID
           and hj.SPLX = '3'
           and xt2.DZID(+)=bg.XYDZID
           and xt1.DZID(+)=hj.DZID
           and cz.JLBZ = '1'
           and cz.CXBZ = '0'
;
comment on table V_BGSPXXB is '变更审批信息视图';
create or replace view v_hbbgspxxb as
select pohjsp_hbb0_.SPYWID         ,
       pohjxx_czr1_.RYNBID         ,
       pohjsp_hjs2_.SPLSID         ,
       pohjsp_hbb0_.SPMBID         ,
       pohjsp_hbb0_.BGRYID         ,
       pohjsp_hbb0_.BGYY           ,
       pohjsp_hbb0_.BGQHB          ,
       pohjsp_hbb0_.BGHHB          ,
       pohjsp_hbb0_.XYDZID         ,
       pohjsp_hbb0_.SPJG           ,
       pohjsp_hbb0_.LSBZ           ,
       pohjsp_hbb0_.BGHRYNBID      ,
       pohjsp_hbb0_.HJYWID         ,
       pohjsp_hbb0_.SBSJ           ,
       pohjsp_hbb0_.SBRYXM         ,
       pohjsp_hbb0_.SBRGMSFHM      ,
       pohjsp_hbb0_.DJRID          ,
       pohjsp_hbb0_.SPSM           ,
       pohjxx_czr1_.NBSFZID        ,
       pohjxx_czr1_.QFJG           ,
       pohjxx_czr1_.YXQXQSRQ       ,
       pohjxx_czr1_.YXQXJZRQ       ,
       pohjxx_czr1_.SWZXRQ         ,
       pohjxx_czr1_.FQXM           ,
       pohjxx_czr1_.FQGMSFHM       ,
       pohjxx_czr1_.MQXM           ,
       pohjxx_czr1_.MQGMSFHM       ,
       pohjxx_czr1_.POXM           ,
       pohjxx_czr1_.POGMSFHM       ,
       pohjxx_czr1_.JGGJDQ         ,
       pohjxx_czr1_.JGSSXQ         ,
       pohjxx_czr1_.ZJXY           ,
       pohjxx_czr1_.WHCD           ,
       pohjxx_czr1_.HYZK           ,
       pohjxx_czr1_.BYZK           ,
       pohjxx_czr1_.SG             ,
       pohjxx_czr1_.XX             ,
       pohjxx_czr1_.ZY             ,
       pohjxx_czr1_.ZYLB           ,
       pohjxx_czr1_.FWCS           ,
       pohjxx_czr1_.XXJB           ,
       pohjxx_czr1_.HSQL           ,
       pohjxx_czr1_.HYQL           ,
       pohjxx_czr1_.HGJDQQL        ,
       pohjxx_czr1_.HSSXQQL        ,
       pohjxx_czr1_.HXZQL          ,
       pohjxx_czr1_.HSLBZ          ,
       pohjxx_czr1_.HYLBZ          ,
       pohjxx_czr1_.HGJDQLBZ       ,
       pohjxx_czr1_.HSSSQLBZ       ,
       pohjxx_czr1_.HXZLBZ         ,
       pohjxx_czr1_.SWRQ           ,
       pohjxx_czr1_.SWZXLB         ,
       pohjxx_czr1_.QCRQ           ,
       pohjxx_czr1_.QCZXLB         ,
       pohjxx_czr1_.QWDGJDQ        ,
       pohjxx_czr1_.QWDSSXQ        ,
       pohjxx_czr1_.QWDXZ          ,
       pohjxx_czr1_.CSZMBH         ,
       pohjxx_czr1_.CSZQFRQ        ,
       pohjxx_czr1_.HYLB           ,
       pohjxx_czr1_.QTSSXQ         ,
       pohjxx_czr1_.QTZZ           ,
       pohjxx_czr1_.RYLB           ,
       pohjxx_czr1_.HB             ,
       pohjxx_czr1_.YHZGX          ,
       pohjxx_czr1_.RYZT           ,
       pohjxx_czr1_.RYSDZT         ,
       pohjxx_czr1_.LXDBID         ,
       pohjxx_czr1_.BZ             ,
       pohjxx_czr1_.JLBZ           ,
       pohjxx_czr1_.YWNR           ,
       pohjxx_czr1_.CJHJYWID       ,
       pohjxx_czr1_.CCHJYWID       ,
       pohjxx_czr1_.QYSJ           ,
       pohjxx_czr1_.JSSJ           ,
       pohjxx_czr1_.CXBZ           ,
       pohjxx_czr1_.RYID           ,
       pohjxx_czr1_.HHNBID         ,
       pohjxx_czr1_.MLPNBID        ,
       pohjxx_czr1_.ZPID           ,
       pohjxx_czr1_.GMSFHM         ,
       pohjxx_czr1_.XM             ,
       pohjxx_czr1_.CYM            ,
       pohjxx_czr1_.XMPY           ,
       pohjxx_czr1_.CYMPY          ,
       pohjxx_czr1_.XB             ,
       pohjxx_czr1_.MZ             ,
       pohjxx_czr1_.CSRQ           ,
       pohjxx_czr1_.CSSJ           ,
       pohjxx_czr1_.CSDGJDQ        ,
       pohjxx_czr1_.CSDSSXQ        ,
       pohjxx_czr1_.CSDXZ          ,
       pohjxx_czr1_.DHHM           ,
       pohjxx_czr1_.JHRYXM         ,
       pohjxx_czr1_.JHRYGMSFHM     ,
       pohjxx_czr1_.JHRYJHGX       ,
       pohjxx_czr1_.JHREXM         ,
       pohjxx_czr1_.JHREGMSFHM     ,
       pohjxx_czr1_.JHREJHGX       ,
       pohjxx_czr1_.ZJLB           ,
       pohjxx_czr1_.SSXQ           ,
       pohjxx_czr1_.JLX            ,
       pohjxx_czr1_.MLPH           ,
       pohjxx_czr1_.MLXZ           ,
       pohjxx_czr1_.PCS            ,
       pohjxx_czr1_.ZRQ            ,
       pohjxx_czr1_.XZJD           ,
       pohjxx_czr1_.JCWH           ,
       pohjxx_czr1_.PXH            ,
       pohjxx_czr1_.HH             ,
       pohjxx_czr1_.HLX            ,
       pohjxx_czr1_.BDFW           ,
       pohjxx_czr1_.HHID           ,
       pohjxx_czr1_.MLPID          ,
       pohjxx_czr1_.XXQYSJ         ,
       pohjxx_czr1_.DHHM2          ,
       pohjxx_czr1_.XMX            ,
       pohjxx_czr1_.XMM            ,
       pohjxx_czr1_.JGXZ           ,
       pohjxx_czr1_.JHRYZJZL       ,
       pohjxx_czr1_.JHRYZJHM       ,
       pohjxx_czr1_.JHRYWWX        ,
       pohjxx_czr1_.JHRYWWM        ,
       pohjxx_czr1_.JHRYLXDH       ,
       pohjxx_czr1_.JHREZJZL       ,
       pohjxx_czr1_.JHREZJHM       ,
       pohjxx_czr1_.JHREWWX        ,
       pohjxx_czr1_.JHREWWM        ,
       pohjxx_czr1_.JHRELXDH       ,
       pohjxx_czr1_.FQZJZL         ,
       pohjxx_czr1_.FQZJHM         ,
       pohjxx_czr1_.FQWWX          ,
       pohjxx_czr1_.FQWWM          ,
       pohjxx_czr1_.MQZJZL         ,
       pohjxx_czr1_.MQZJHM         ,
       pohjxx_czr1_.MQWWX          ,
       pohjxx_czr1_.MQWWM          ,
       pohjxx_czr1_.POZJZL         ,
       pohjxx_czr1_.POZJHM         ,
       pohjxx_czr1_.POWWX          ,
       pohjxx_czr1_.POWWM          ,
       pohjxx_czr1_.QYLDYY         ,
       pohjxx_czr1_.YHKXZMC        ,
       pohjxx_czr1_.YHKXZSJ        ,
       pohjxx_czr1_.BZDZ           ,
       pohjxx_czr1_.BZDZID         ,
       pohjxx_czr1_.BZDZX          ,
       pohjxx_czr1_.BZDZY          ,
       pohjxx_czr1_.BZDZST         ,
       pohjxx_czr1_.CXFLDM         ,
       pohjxx_czr1_.nyzyrklhczyydm ,
       pohjxx_czr1_.lzd_cxfldm     ,
       pohjxx_czr1_.kdqqy_qrdqbm   ,
       pohjxx_czr1_.kdqqy_qrywid   ,
       pohjxx_czr1_.kdqqy_qrywlx   ,
       pohjxx_czr1_.kdqqy_qrfkzt   ,
       pohjxx_czr1_.kdqqy_qrfksj   ,
       pohjxx_czr1_.JTHZL          ,
       pohjsp_hjs2_.SPLX           ,
       pohjsp_hjs2_.DZID           ,
       pohjsp_hjs2_.CZJG           ,
       pohjsp_hjs2_.CZYJ           ,
       pohjsp_hjs2_.CZRID          ,
       pohjsp_hjs2_.CZSJ           ,
       poxt_spdzb3_.DZMC           as xydz_dzmc,
       poxt_spdzb4_.DZMC           
  from HJSP_HBBGSPSQB pohjsp_hbb0_,
       HJXX_CZRKJBXXB pohjxx_czr1_,
       HJSP_HJSPLSB   pohjsp_hjs2_,
       XT_SPDZB       poxt_spdzb3_,
       XT_SPDZB       poxt_spdzb4_
 where (pohjsp_hbb0_.SPYWID = pohjsp_hjs2_.SPYWID)
   and (pohjsp_hjs2_.SPLX = '4')
   and (pohjsp_hbb0_.XYDZID = poxt_spdzb3_.DZID(+))
   and (pohjsp_hjs2_.DZID = poxt_spdzb4_.DZID(+))
   and (pohjsp_hbb0_.BGRYID = pohjxx_czr1_.RYID)
   and (pohjxx_czr1_.JLBZ = '1')
   and (pohjxx_czr1_.CXBZ = '0');
comment on table V_HBBGSPXXB is '户别变更审批信息视图';
create or replace view V_HJBLSPXXB as
  select pohjsp_hjb0_.SPYWID     ,
         pohjsp_hjs1_.SPLSID     ,
         pohjsp_hjb0_.SPMBID     ,
         pohjsp_hjb0_.JHRYXM     ,
         pohjsp_hjb0_.JHRYGMSFHM ,
         pohjsp_hjb0_.JHRYJHGX   ,
         pohjsp_hjb0_.JHREXM     ,
         pohjsp_hjb0_.JHREGMSFHM ,
         pohjsp_hjb0_.JHREJHGX   ,
         pohjsp_hjb0_.GMSFHM     ,
         pohjsp_hjb0_.XM         ,
         pohjsp_hjb0_.CYM        ,
         pohjsp_hjb0_.XB         ,
         pohjsp_hjb0_.MZ         ,
         pohjsp_hjb0_.CSRQ       ,
         pohjsp_hjb0_.CSSJ       ,
         pohjsp_hjb0_.CSDGJDQ    ,
         pohjsp_hjb0_.CSDSSXQ    ,
         pohjsp_hjb0_.CSDXZ      ,
         pohjsp_hjb0_.JGGJDQ     ,
         pohjsp_hjb0_.JGSSXQ     ,
         pohjsp_hjb0_.FQGMSFHM   ,
         pohjsp_hjb0_.FQXM       ,
         pohjsp_hjb0_.MQGMSFHM   ,
         pohjsp_hjb0_.MQXM       ,
         pohjsp_hjb0_.POGMSFHM   ,
         pohjsp_hjb0_.POXM       ,
         pohjsp_hjb0_.ZJXY       ,
         pohjsp_hjb0_.WHCD       ,
         pohjsp_hjb0_.HYZK       ,
         pohjsp_hjb0_.BYZK       ,
         pohjsp_hjb0_.SG         ,
         pohjsp_hjb0_.XX         ,
         pohjsp_hjb0_.ZY         ,
         pohjsp_hjb0_.ZYLB       ,
         pohjsp_hjb0_.FWCS       ,
         pohjsp_hjb0_.XXJB       ,
         pohjsp_hjb0_.HB         ,
         pohjsp_hjb0_.CSZMBH     ,
         pohjsp_hjb0_.SSSSXQ     ,
         pohjsp_hjb0_.SSPCS      ,
         pohjsp_hjb0_.SSJWH      ,
         pohjsp_hjb0_.SSJLX      ,
         pohjsp_hjb0_.SSXZJD     ,
         pohjsp_hjb0_.SSJWZRQ    ,
         pohjsp_hjb0_.SSMLPH     ,
         pohjsp_hjb0_.SSXZ       ,
         pohjsp_hjb0_.SSHHID     ,
         pohjsp_hjb0_.SSHH       ,
         pohjsp_hjb0_.SSHLX      ,
         pohjsp_hjb0_.YHZGX      ,
         pohjsp_hjb0_.RLHBZ      ,
         pohjsp_hjb0_.BLYY       ,
         pohjsp_hjb0_.DJRID      ,
         pohjsp_hjb0_.DJSJ       ,
         pohjsp_hjb0_.XYDZID     ,
         pohjsp_hjb0_.SPJG       ,
         pohjsp_hjb0_.LSBZ       ,
         pohjsp_hjb0_.RYNBID     ,
         pohjsp_hjb0_.HJYWID     ,
         pohjsp_hjb0_.SPSM       ,
         pohjsp_hjb0_.YWXL       ,
         pohjsp_hjb0_.XMX        ,
         pohjsp_hjb0_.XMM        ,
         pohjsp_hjb0_.JGXZ       ,
         pohjsp_hjb0_.JHRYZJZL   ,
         pohjsp_hjb0_.JHRYZJHM   ,
         pohjsp_hjb0_.JHRYWWX    ,
         pohjsp_hjb0_.JHRYWWM    ,
         pohjsp_hjb0_.JHRYLXDH   ,
         pohjsp_hjb0_.JHREZJZL   ,
         pohjsp_hjb0_.JHREZJHM   ,
         pohjsp_hjb0_.JHREWWX    ,
         pohjsp_hjb0_.JHREWWM    ,
         pohjsp_hjb0_.JHRELXDH   ,
         pohjsp_hjb0_.FQZJZL     ,
         pohjsp_hjb0_.FQZJHM     ,
         pohjsp_hjb0_.FQWWX      ,
         pohjsp_hjb0_.FQWWM      ,
         pohjsp_hjb0_.MQZJZL     ,
         pohjsp_hjb0_.MQZJHM     ,
         pohjsp_hjb0_.MQWWX      ,
         pohjsp_hjb0_.MQWWM      ,
         pohjsp_hjb0_.POZJZL     ,
         pohjsp_hjb0_.POZJHM     ,
         pohjsp_hjb0_.POWWX      ,
         pohjsp_hjb0_.POWWM      ,
         pohjsp_hjb0_.QYLDYY     ,
         pohjsp_hjb0_.SBRJTGX    ,
         pohjsp_hjb0_.BZDZ       ,
         pohjsp_hjb0_.BZDZID     ,
         pohjsp_hjb0_.BZDZX      ,
         pohjsp_hjb0_.BZDZY      ,
         pohjsp_hjb0_.BZDZST     ,
         pohjsp_hjb0_.CXFLDM     ,
         pohjsp_hjb0_.JTHZL      ,
         pohjsp_hjs1_.SPLX       ,
         pohjsp_hjs1_.DZID       ,
         pohjsp_hjs1_.CZJG       ,
         pohjsp_hjs1_.CZYJ       ,
         pohjsp_hjs1_.CZRID      ,
         pohjsp_hjs1_.CZSJ       ,
         poxt_spdzb2_.DZMC       as xydz_dzmc,
         poxt_spdzb3_.DZMC       
    from HJSP_HJBLSPSQB pohjsp_hjb0_,
       HJSP_HJSPLSB   pohjsp_hjs1_,
       XT_SPDZB       poxt_spdzb2_,
       XT_SPDZB       poxt_spdzb3_
     where (pohjsp_hjb0_.SPYWID = pohjsp_hjs1_.SPYWID)
     and (pohjsp_hjs1_.SPLX = '5')
     and (pohjsp_hjb0_.XYDZID = poxt_spdzb2_.DZID(+))
     and (pohjsp_hjs1_.DZID = poxt_spdzb3_.DZID(+))
     order by fqgmsfhm, pohjsp_hjb0_.SPYWID;
comment on table V_HJBLSPXXB is '户籍补录审批信息视图';
create or replace view V_HJSCSPXXB as
    select pohjsp_hjs0_.SPYWID         ,
               pohjxx_czr1_.RYNBID         ,
               pohjsp_hjs2_.SPLSID         ,
               pohjsp_hjs0_.SPMBID         ,
               pohjsp_hjs0_.BSCRYID        ,
               pohjsp_hjs0_.CSYY           ,
               pohjsp_hjs0_.XYDZID         ,
               pohjsp_hjs0_.SPJG           ,
               pohjsp_hjs0_.LSBZ           ,
               pohjsp_hjs0_.HJYWID         ,
               pohjsp_hjs0_.SQSJ           ,
               pohjsp_hjs0_.SQYHID         ,
               pohjsp_hjs0_.SPSM           ,
               pohjsp_hjs0_.HJSCSM         ,
               pohjxx_czr1_.NBSFZID        ,
               pohjxx_czr1_.QFJG           ,
               pohjxx_czr1_.YXQXQSRQ       ,
               pohjxx_czr1_.YXQXJZRQ       ,
               pohjxx_czr1_.SWZXRQ         ,
               pohjxx_czr1_.FQXM           ,
               pohjxx_czr1_.FQGMSFHM       ,
               pohjxx_czr1_.MQXM           ,
               pohjxx_czr1_.MQGMSFHM       ,
               pohjxx_czr1_.POXM           ,
               pohjxx_czr1_.POGMSFHM       ,
               pohjxx_czr1_.JGGJDQ         ,
               pohjxx_czr1_.JGSSXQ         ,
               pohjxx_czr1_.ZJXY           ,
               pohjxx_czr1_.WHCD           ,
               pohjxx_czr1_.HYZK           ,
               pohjxx_czr1_.BYZK           ,
               pohjxx_czr1_.SG             ,
               pohjxx_czr1_.XX             ,
               pohjxx_czr1_.ZY             ,
               pohjxx_czr1_.ZYLB           ,
               pohjxx_czr1_.FWCS           ,
               pohjxx_czr1_.XXJB           ,
               pohjxx_czr1_.HSQL           ,
               pohjxx_czr1_.HYQL           ,
               pohjxx_czr1_.HGJDQQL        ,
               pohjxx_czr1_.HSSXQQL        ,
               pohjxx_czr1_.HXZQL          ,
               pohjxx_czr1_.HSLBZ          ,
               pohjxx_czr1_.HYLBZ          ,
               pohjxx_czr1_.HGJDQLBZ       ,
               pohjxx_czr1_.HSSSQLBZ       ,
               pohjxx_czr1_.HXZLBZ         ,
               pohjxx_czr1_.SWRQ           ,
               pohjxx_czr1_.SWZXLB         ,
               pohjxx_czr1_.QCRQ           ,
               pohjxx_czr1_.QCZXLB         ,
               pohjxx_czr1_.QWDGJDQ        ,
               pohjxx_czr1_.QWDSSXQ        ,
               pohjxx_czr1_.QWDXZ          ,
               pohjxx_czr1_.CSZMBH         ,
               pohjxx_czr1_.CSZQFRQ        ,
               pohjxx_czr1_.HYLB           ,
               pohjxx_czr1_.QTSSXQ         ,
               pohjxx_czr1_.QTZZ           ,
               pohjxx_czr1_.RYLB           ,
               pohjxx_czr1_.HB             ,
               pohjxx_czr1_.YHZGX          ,
               pohjxx_czr1_.RYZT           ,
               pohjxx_czr1_.RYSDZT         ,
               pohjxx_czr1_.LXDBID         ,
               pohjxx_czr1_.BZ             ,
               pohjxx_czr1_.JLBZ           ,
               pohjxx_czr1_.YWNR           ,
               pohjxx_czr1_.CJHJYWID       ,
               pohjxx_czr1_.CCHJYWID       ,
               pohjxx_czr1_.QYSJ           ,
               pohjxx_czr1_.JSSJ           ,
               pohjxx_czr1_.CXBZ           ,
               pohjxx_czr1_.RYID           ,
               pohjxx_czr1_.HHNBID         ,
               pohjxx_czr1_.MLPNBID        ,
               pohjxx_czr1_.ZPID           ,
               pohjxx_czr1_.GMSFHM         ,
               pohjxx_czr1_.XM             ,
               pohjxx_czr1_.CYM            ,
               pohjxx_czr1_.XMPY           ,
               pohjxx_czr1_.CYMPY          ,
               pohjxx_czr1_.XB             ,
               pohjxx_czr1_.MZ             ,
               pohjxx_czr1_.CSRQ           ,
               pohjxx_czr1_.CSSJ           ,
               pohjxx_czr1_.CSDGJDQ        ,
               pohjxx_czr1_.CSDSSXQ        ,
               pohjxx_czr1_.CSDXZ          ,
               pohjxx_czr1_.DHHM           ,
               pohjxx_czr1_.JHRYXM         ,
               pohjxx_czr1_.JHRYGMSFHM     ,
               pohjxx_czr1_.JHRYJHGX       ,
               pohjxx_czr1_.JHREXM         ,
               pohjxx_czr1_.JHREGMSFHM     ,
               pohjxx_czr1_.JHREJHGX       ,
               pohjxx_czr1_.ZJLB           ,
               pohjxx_czr1_.SSXQ           ,
               pohjxx_czr1_.JLX            ,
               pohjxx_czr1_.MLPH           ,
               pohjxx_czr1_.MLXZ           ,
               pohjxx_czr1_.PCS            ,
               pohjxx_czr1_.ZRQ            ,
               pohjxx_czr1_.XZJD           ,
               pohjxx_czr1_.JCWH           ,
               pohjxx_czr1_.PXH            ,
               pohjxx_czr1_.HH             ,
               pohjxx_czr1_.HLX            ,
               pohjxx_czr1_.BDFW           ,
               pohjxx_czr1_.HHID           ,
               pohjxx_czr1_.MLPID          ,
               pohjxx_czr1_.XXQYSJ         ,
               pohjxx_czr1_.DHHM2          ,
               pohjxx_czr1_.XMX            ,
               pohjxx_czr1_.XMM            ,
               pohjxx_czr1_.JGXZ           ,
               pohjxx_czr1_.JHRYZJZL       ,
               pohjxx_czr1_.JHRYZJHM       ,
               pohjxx_czr1_.JHRYWWX        ,
               pohjxx_czr1_.JHRYWWM        ,
               pohjxx_czr1_.JHRYLXDH       ,
               pohjxx_czr1_.JHREZJZL       ,
               pohjxx_czr1_.JHREZJHM       ,
               pohjxx_czr1_.JHREWWX        ,
               pohjxx_czr1_.JHREWWM        ,
               pohjxx_czr1_.JHRELXDH       ,
               pohjxx_czr1_.FQZJZL         ,
               pohjxx_czr1_.FQZJHM         ,
               pohjxx_czr1_.FQWWX          ,
               pohjxx_czr1_.FQWWM          ,
               pohjxx_czr1_.MQZJZL         ,
               pohjxx_czr1_.MQZJHM         ,
               pohjxx_czr1_.MQWWX          ,
               pohjxx_czr1_.MQWWM          ,
               pohjxx_czr1_.POZJZL         ,
               pohjxx_czr1_.POZJHM         ,
               pohjxx_czr1_.POWWX          ,
               pohjxx_czr1_.POWWM          ,
               pohjxx_czr1_.QYLDYY         ,
               pohjxx_czr1_.YHKXZMC        ,
               pohjxx_czr1_.YHKXZSJ        ,
               pohjxx_czr1_.BZDZ           ,
               pohjxx_czr1_.BZDZID         ,
               pohjxx_czr1_.BZDZX          ,
               pohjxx_czr1_.BZDZY          ,
               pohjxx_czr1_.BZDZST         ,
               pohjxx_czr1_.CXFLDM         ,
               pohjxx_czr1_.nyzyrklhczyydm ,
               pohjxx_czr1_.lzd_cxfldm     ,
               pohjxx_czr1_.kdqqy_qrdqbm   ,
               pohjxx_czr1_.kdqqy_qrywid   ,
               pohjxx_czr1_.kdqqy_qrywlx   ,
               pohjxx_czr1_.kdqqy_qrfkzt   ,
               pohjxx_czr1_.kdqqy_qrfksj   ,
               pohjxx_czr1_.JTHZL          ,
               pohjsp_hjs2_.SPLX           ,
               pohjsp_hjs2_.DZID           ,
               pohjsp_hjs2_.CZJG           ,
               pohjsp_hjs2_.CZYJ           ,
               pohjsp_hjs2_.CZRID          ,
               pohjsp_hjs2_.CZSJ           ,
               poxt_spdzb3_.DZMC  as xydz_dzmc,
               poxt_spdzb4_.DZMC 
          from HJSP_HJSCSPSQB pohjsp_hjs0_,
               HJXX_CZRKJBXXB pohjxx_czr1_,
               HJSP_HJSPLSB   pohjsp_hjs2_,
               XT_SPDZB       poxt_spdzb3_,
               XT_SPDZB       poxt_spdzb4_               
         where (pohjsp_hjs0_.SPYWID = pohjsp_hjs2_.SPYWID)
           and (pohjsp_hjs2_.SPLX = '6')
           and (pohjsp_hjs0_.XYDZID = poxt_spdzb3_.DZID(+))
           and (pohjsp_hjs2_.DZID = poxt_spdzb4_.DZID(+))
           and (pohjsp_hjs0_.BSCRYID = pohjxx_czr1_.RYID)
           and (pohjxx_czr1_.JLBZ = '1')
           and (pohjxx_czr1_.CXBZ = '0');
comment on table V_HJSCSPXXB is '户籍删除审批信息视图';
create or replace view V_HJSPBGGZ as
select bgsp.SPYWID         as SPYWID,
       czr.RYNBID         as RYNBID,
       xt_sp.DZID           as DZID,
       bgsp.BGRYID         as BGRYID,
       bgsp.XYDZID         as XYDZID,
       bgsp.SPJG           as SPJG,
       bgsp.LSBZ           as LSBZ,
       bgsp.BGHRYNBID      as BGHRYNBID,
       bgsp.HJYWID         as HJYWID,
       bgsp.SBSJ           as SBSJ,
       bgsp.SBRYXM         as SBRYXM,
       bgsp.SBRGMSFHM      as SBRGMSFHM,
       bgsp.SPMBID         as SPMBID,
       bgsp.DJRID          as DJRID,
       bgsp.SPSM           as SPSM,
       czr.NBSFZID        as NBSFZID,
       czr.QFJG           as QFJG,
       czr.YXQXQSRQ       as YXQXQSRQ,
       czr.YXQXJZRQ       as YXQXJZRQ,
       czr.SWZXRQ         as SWZXRQ,
       czr.FQXM           as FQXM,
       czr.FQGMSFHM       as FQGMSFHM,
       czr.MQXM           as MQXM,
       czr.MQGMSFHM       as MQGMSFHM,
       czr.POXM           as POXM,
       czr.POGMSFHM       as POGMSFHM,
       czr.JGGJDQ         as JGGJDQ,
       czr.JGSSXQ         as JGSSXQ,
       czr.ZJXY           as ZJXY,
       czr.WHCD           as WHCD,
       czr.HYZK           as HYZK,
       czr.BYZK           as BYZK,
       czr.SG             as SG,
       czr.XX             as XX,
       czr.ZY             as ZY,
       czr.ZYLB           as ZYLB,
       czr.FWCS           as FWCS,
       czr.XXJB           as XXJB,
       czr.HSQL           as HSQL,
       czr.HYQL           as HYQL,
       czr.HGJDQQL        as HGJDQQL,
       czr.HSSXQQL        as HSSXQQL,
       czr.HXZQL          as HXZQL,
       czr.HSLBZ          as HSLBZ,
       czr.HYLBZ          as HYLBZ,
       czr.HGJDQLBZ       as HGJDQLBZ,
       czr.HSSSQLBZ       as HSSSQLBZ,
       czr.HXZLBZ         as HXZLBZ,
       czr.SWRQ           as SWRQ,
       czr.SWZXLB         as SWZXLB,
       czr.QCRQ           as QCRQ,
       czr.QCZXLB         as QCZXLB,
       czr.QWDGJDQ        as QWDGJDQ,
       czr.QWDSSXQ        as QWDSSXQ,
       czr.QWDXZ          as QWDXZ,
       czr.CSZMBH         as CSZMBH,
       czr.CSZQFRQ        as CSZQFRQ,
       czr.HYLB           as HYLB,
       czr.QTSSXQ         as QTSSXQ,
       czr.QTZZ           as QTZZ,
       czr.RYLB           as RYLB,
       czr.HB             as HB,
       czr.YHZGX          as YHZGX,
       czr.RYZT           as RYZT,
       czr.RYSDZT         as RYSDZT,
       czr.LXDBID         as LXDBID,
       czr.BZ             as BZ,
       czr.JLBZ           as JLBZ,
       czr.YWNR           as YWNR,
       czr.CJHJYWID       as CJHJYWID,
       czr.CCHJYWID       as CCHJYWID,
       czr.QYSJ           as QYSJ,
       czr.JSSJ           as JSSJ,
       czr.CXBZ           as CXBZ,
       czr.RYID           as RYID,
       czr.HHNBID         as HHNBID,
       czr.MLPNBID        as MLPNBID,
       czr.ZPID           as ZPID,
       czr.GMSFHM         as GMSFHM,
       czr.XM             as XM,
       czr.CYM            as CYM,
       czr.XMPY           as XMPY,
       czr.CYMPY          as CYMPY,
       czr.XB             as XB,
       czr.MZ             as MZ,
       czr.CSRQ           as CSRQ,
       czr.CSSJ           as CSSJ,
       czr.CSDGJDQ        as CSDGJDQ,
       czr.CSDSSXQ        as CSDSSXQ,
       czr.CSDXZ          as CSDXZ,
       czr.DHHM           as DHHM,
       czr.JHRYXM         as JHRYXM,
       czr.JHRYGMSFHM     as JHRYGMSFHM,
       czr.JHRYJHGX       as JHRYJHGX,
       czr.JHREXM         as JHREXM,
       czr.JHREGMSFHM     as JHREGMSFHM,
       czr.JHREJHGX       as JHREJHGX,
       czr.ZJLB           as ZJLB,
       czr.SSXQ           as SSXQ,
       czr.JLX            as JLX,
       czr.MLPH           as MLPH,
       czr.MLXZ           as MLXZ,
       czr.PCS            as PCS,
       czr.ZRQ            as ZRQ,
       czr.XZJD           as XZJD,
       czr.JCWH           as JCWH,
       czr.PXH            as PXH,
       czr.HH             as HH,
       czr.HLX            as HLX,
       czr.BDFW           as BDFW,
       czr.HHID           as HHID,
       czr.MLPID          as MLPID,
       czr.XXQYSJ         as XXQYSJ,
       czr.DHHM2          as DHHM2,
       czr.XMX            as XMX,
       czr.XMM            as XMM,
       czr.JGXZ           as JGXZ,
       czr.JHRYZJZL       as JHRYZJZL,
       czr.JHRYZJHM       as JHRYZJHM,
       czr.JHRYWWX        as JHRYWWX,
       czr.JHRYWWM        as JHRYWWM,
       czr.JHRYLXDH       as JHRYLXDH,
       czr.JHREZJZL       as JHREZJZL,
       czr.JHREZJHM       as JHREZJHM,
       czr.JHREWWX        as JHREWWX,
       czr.JHREWWM        as JHREWWM,
       czr.JHRELXDH       as JHRELXDH,
       czr.FQZJZL         as FQZJZL,
       czr.FQZJHM         as FQZJHM,
       czr.FQWWX          as FQWWX,
       czr.FQWWM          as FQWWM,
       czr.MQZJZL         as MQZJZL,
       czr.MQZJHM         as MQZJHM,
       czr.MQWWX          as MQWWX,
       czr.MQWWM          as MQWWM,
       czr.POZJZL         as POZJZL,
       czr.POZJHM         as POZJHM,
       czr.POWWX          as POWWX,
       czr.POWWM          as POWWM,
       czr.QYLDYY         as QYLDYY,
       czr.YHKXZMC        as YHKXZMC,
       czr.YHKXZSJ        as YHKXZSJ,
       czr.BZDZ           as BZDZ,
       czr.BZDZID         as BZDZID,
       czr.BZDZX          as BZDZX,
       czr.BZDZY          as BZDZY,
       czr.BZDZST         as BZDZST,
       czr.CXFLDM         as CXFLDM,
       czr.nyzyrklhczyydm as nyzyrklhczyydm,
       czr.lzd_cxfldm     as lzd_cxfldm,
       czr.kdqqy_qrdqbm   as kdqqy_qrdqbm,
       czr.kdqqy_qrywid   as kdqqy_qrywid,
       czr.kdqqy_qrywlx   as kdqqy_qrywlx,
       czr.kdqqy_qrfkzt   as kdqqy_qrfkzt,
       czr.kdqqy_qrfksj   as kdqqy_qrfksj,
       czr.JTHZL          as JTHZL,
       xt_sp.FWJB           as FWJB,
       xt_sp.DZMC           as DZMC,
       xt_sp.MS             as MS,
       xt_sp.QYBZ           as QYBZ
  from HJSP_BGSPXXB   bgsp,
       HJXX_CZRKJBXXB czr,
       XT_SPDZB       xt_sp
 where (bgsp.BGRYID = czr.RYID)
   and (xt_sp.DZID(+) = bgsp.XYDZID)
   and (czr.JLBZ = '1')
   and (czr.CXBZ = '0');
comment on table V_HJSPBGGZ is '户籍审批变更更正信息视图';
create or replace view v_hjsphjsc as
select hjsp.SPYWID         as SPYWID,
       czr.RYNBID         as RYNBID,
       xt_dz.DZID           as DZID,
       hjsp.SPMBID         as SPMBID,
       hjsp.BSCRYID        as BSCRYID,
       hjsp.CSYY           as CSYY,
       hjsp.XYDZID         as XYDZID,
       hjsp.SPJG           as SPJG,
       hjsp.LSBZ           as LSBZ,
       hjsp.HJYWID         as HJYWID,
       hjsp.SQSJ           as SQSJ,
       hjsp.SQYHID         as SQYHID,
       hjsp.SPSM           as SPSM,
       hjsp.HJSCSM         as HJSCSM,
       czr.NBSFZID        as NBSFZID,
       czr.QFJG           as QFJG,
       czr.YXQXQSRQ       as YXQXQSRQ,
       czr.YXQXJZRQ       as YXQXJZRQ,
       czr.SWZXRQ         as SWZXRQ,
       czr.FQXM           as FQXM,
       czr.FQGMSFHM       as FQGMSFHM,
       czr.MQXM           as MQXM,
       czr.MQGMSFHM       as MQGMSFHM,
       czr.POXM           as POXM,
       czr.POGMSFHM       as POGMSFHM,
       czr.JGGJDQ         as JGGJDQ,
       czr.JGSSXQ         as JGSSXQ,
       czr.ZJXY           as ZJXY,
       czr.WHCD           as WHCD,
       czr.HYZK           as HYZK,
       czr.BYZK           as BYZK,
       czr.SG             as SG,
       czr.XX             as XX,
       czr.ZY             as ZY,
       czr.ZYLB           as ZYLB,
       czr.FWCS           as FWCS,
       czr.XXJB           as XXJB,
       czr.HSQL           as HSQL,
       czr.HYQL           as HYQL,
       czr.HGJDQQL        as HGJDQQL,
       czr.HSSXQQL        as HSSXQQL,
       czr.HXZQL          as HXZQL,
       czr.HSLBZ          as HSLBZ,
       czr.HYLBZ          as HYLBZ,
       czr.HGJDQLBZ       as HGJDQLBZ,
       czr.HSSSQLBZ       as HSSSQLBZ,
       czr.HXZLBZ         as HXZLBZ,
       czr.SWRQ           as SWRQ,
       czr.SWZXLB         as SWZXLB,
       czr.QCRQ           as QCRQ,
       czr.QCZXLB         as QCZXLB,
       czr.QWDGJDQ        as QWDGJDQ,
       czr.QWDSSXQ        as QWDSSXQ,
       czr.QWDXZ          as QWDXZ,
       czr.CSZMBH         as CSZMBH,
       czr.CSZQFRQ        as CSZQFRQ,
       czr.HYLB           as HYLB,
       czr.QTSSXQ         as QTSSXQ,
       czr.QTZZ           as QTZZ,
       czr.RYLB           as RYLB,
       czr.HB             as HB,
       czr.YHZGX          as YHZGX,
       czr.RYZT           as RYZT,
       czr.RYSDZT         as RYSDZT,
       czr.LXDBID         as LXDBID,
       czr.BZ             as BZ,
       czr.JLBZ           as JLBZ,
       czr.YWNR           as YWNR,
       czr.CJHJYWID       as CJHJYWID,
       czr.CCHJYWID       as CCHJYWID,
       czr.QYSJ           as QYSJ,
       czr.JSSJ           as JSSJ,
       czr.CXBZ           as CXBZ,
       czr.RYID           as RYID,
       czr.HHNBID         as HHNBID,
       czr.MLPNBID        as MLPNBID,
       czr.ZPID           as ZPID,
       czr.GMSFHM         as GMSFHM,
       czr.XM             as XM,
       czr.CYM            as CYM,
       czr.XMPY           as XMPY,
       czr.CYMPY          as CYMPY,
       czr.XB             as XB,
       czr.MZ             as MZ,
       czr.CSRQ           as CSRQ,
       czr.CSSJ           as CSSJ,
       czr.CSDGJDQ        as CSDGJDQ,
       czr.CSDSSXQ        as CSDSSXQ,
       czr.CSDXZ          as CSDXZ,
       czr.DHHM           as DHHM,
       czr.JHRYXM         as JHRYXM,
       czr.JHRYGMSFHM     as JHRYGMSFHM,
       czr.JHRYJHGX       as JHRYJHGX,
       czr.JHREXM         as JHREXM,
       czr.JHREGMSFHM     as JHREGMSFHM,
       czr.JHREJHGX       as JHREJHGX,
       czr.ZJLB           as ZJLB,
       czr.SSXQ           as SSXQ,
       czr.JLX            as JLX,
       czr.MLPH           as MLPH,
       czr.MLXZ           as MLXZ,
       czr.PCS            as PCS,
       czr.ZRQ            as ZRQ,
       czr.XZJD           as XZJD,
       czr.JCWH           as JCWH,
       czr.PXH            as PXH,
       czr.HH             as HH,
       czr.HLX            as HLX,
       czr.BDFW           as BDFW,
       czr.HHID           as HHID,
       czr.MLPID          as MLPID,
       czr.XXQYSJ         as XXQYSJ,
       czr.DHHM2          as DHHM2,
       czr.XMX            as XMX,
       czr.XMM            as XMM,
       czr.JGXZ           as JGXZ,
       czr.JHRYZJZL       as JHRYZJZL,
       czr.JHRYZJHM       as JHRYZJHM,
       czr.JHRYWWX        as JHRYWWX,
       czr.JHRYWWM        as JHRYWWM,
       czr.JHRYLXDH       as JHRYLXDH,
       czr.JHREZJZL       as JHREZJZL,
       czr.JHREZJHM       as JHREZJHM,
       czr.JHREWWX        as JHREWWX,
       czr.JHREWWM        as JHREWWM,
       czr.JHRELXDH       as JHRELXDH,
       czr.FQZJZL         as FQZJZL,
       czr.FQZJHM         as FQZJHM,
       czr.FQWWX          as FQWWX,
       czr.FQWWM          as FQWWM,
       czr.MQZJZL         as MQZJZL,
       czr.MQZJHM         as MQZJHM,
       czr.MQWWX          as MQWWX,
       czr.MQWWM          as MQWWM,
       czr.POZJZL         as POZJZL,
       czr.POZJHM         as POZJHM,
       czr.POWWX          as POWWX,
       czr.POWWM          as POWWM,
       czr.QYLDYY         as QYLDYY,
       czr.YHKXZMC        as YHKXZMC,
       czr.YHKXZSJ        as YHKXZSJ,
       czr.BZDZ           as BZDZ,
       czr.BZDZID         as BZDZID,
       czr.BZDZX          as BZDZX,
       czr.BZDZY          as BZDZY,
       czr.BZDZST         as BZDZST,
       czr.CXFLDM         as CXFLDM,
       czr.nyzyrklhczyydm as nyzyrklhczyydm,
       czr.lzd_cxfldm     as lzd_cxfldm,
       czr.kdqqy_qrdqbm   as kdqqy_qrdqbm,
       czr.kdqqy_qrywid   as kdqqy_qrywid,
       czr.kdqqy_qrywlx   as kdqqy_qrywlx,
       czr.kdqqy_qrfkzt   as kdqqy_qrfkzt,
       czr.kdqqy_qrfksj   as kdqqy_qrfksj,
       czr.JTHZL          as JTHZL,
       xt_dz.FWJB           as FWJB,
       xt_dz.DZMC           as DZMC,
       xt_dz.MS             as MS,
       xt_dz.QYBZ           as QYBZ,
       hjsp.sbsj			as sbsj
  from HJSP_HJSCSPSQB hjsp,
       HJXX_CZRKJBXXB czr,
       XT_SPDZB       xt_dz
 where (xt_dz.DZID(+) = hjsp.XYDZID)
   and (hjsp.BSCRYID = czr.RYID)
   and (czr.JLBZ = '1')
   and (czr.CXBZ = '0');
comment on table v_hjsphjsc is '户籍审批户籍删除信息视图';
create or replace view v_hjsphjbl as
select hjbl.SPYWID     as SPYWID,
       dzb.DZID       as DZID,
       hjbl.SPMBID     as SPMBID,
       hjbl.JHRYXM     as JHRYXM,
       hjbl.JHRYGMSFHM as JHRYGMSFHM,
       hjbl.JHRYJHGX   as JHRYJHGX,
       hjbl.JHREXM     as JHREXM,
       hjbl.JHREGMSFHM as JHREGMSFHM,
       hjbl.JHREJHGX   as JHREJHGX,
       hjbl.GMSFHM     as GMSFHM,
       hjbl.XM         as XM,
       hjbl.CYM        as CYM,
       hjbl.XB         as XB,
       hjbl.MZ         as MZ,
       hjbl.CSRQ       as CSRQ,
       hjbl.CSSJ       as CSSJ,
       hjbl.CSDGJDQ    as CSDGJDQ,
       hjbl.CSDSSXQ    as CSDSSXQ,
       hjbl.CSDXZ      as CSDXZ,
       hjbl.JGGJDQ     as JGGJDQ,
       hjbl.JGSSXQ     as JGSSXQ,
       hjbl.FQGMSFHM   as FQGMSFHM,
       hjbl.FQXM       as FQXM,
       hjbl.MQGMSFHM   as MQGMSFHM,
       hjbl.MQXM       as MQXM,
       hjbl.POGMSFHM   as POGMSFHM,
       hjbl.POXM       as POXM,
       hjbl.ZJXY       as ZJXY,
       hjbl.WHCD       as WHCD,
       hjbl.HYZK       as HYZK,
       hjbl.BYZK       as BYZK,
       hjbl.SG         as SG,
       hjbl.XX         as XX,
       hjbl.ZY         as ZY,
       hjbl.ZYLB       as ZYLB,
       hjbl.FWCS       as FWCS,
       hjbl.XXJB       as XXJB,
       hjbl.HB         as HB,
       hjbl.CSZMBH     as CSZMBH,
       hjbl.SSSSXQ     as SSSSXQ,
       hjbl.SSPCS      as SSPCS,
       hjbl.SSJWH      as SSJWH,
       hjbl.SSJLX      as SSJLX,
       hjbl.SSXZJD     as SSXZJD,
       hjbl.SSJWZRQ    as SSJWZRQ,
       hjbl.SSMLPH     as SSMLPH,
       hjbl.SSXZ       as SSXZ,
       hjbl.SSHHID     as SSHHID,
       hjbl.SSHH       as SSHH,
       hjbl.SSHLX      as SSHLX,
       hjbl.YHZGX      as YHZGX,
       hjbl.RLHBZ      as RLHBZ,
       hjbl.BLYY       as BLYY,
       hjbl.DJRID      as DJRID,
       hjbl.DJSJ       as DJSJ,
       hjbl.XYDZID     as XYDZID,
       hjbl.SPJG       as SPJG,
       hjbl.LSBZ       as LSBZ,
       hjbl.RYNBID     as RYNBID,
       hjbl.HJYWID     as HJYWID,
       hjbl.SPSM       as SPSM,
       hjbl.YWXL       as YWXL,
       hjbl.XMX        as XMX,
       hjbl.XMM        as XMM,
       hjbl.JGXZ       as JGXZ,
       hjbl.JHRYZJZL   as JHRYZJZL,
       hjbl.JHRYZJHM   as JHRYZJHM,
       hjbl.JHRYWWX    as JHRYWWX,
       hjbl.JHRYWWM    as JHRYWWM,
       hjbl.JHRYLXDH   as JHRYLXDH,
       hjbl.JHREZJZL   as JHREZJZL,
       hjbl.JHREZJHM   as JHREZJHM,
       hjbl.JHREWWX    as JHREWWX,
       hjbl.JHREWWM    as JHREWWM,
       hjbl.JHRELXDH   as JHRELXDH,
       hjbl.FQZJZL     as FQZJZL,
       hjbl.FQZJHM     as FQZJHM,
       hjbl.FQWWX      as FQWWX,
       hjbl.FQWWM      as FQWWM,
       hjbl.MQZJZL     as MQZJZL,
       hjbl.MQZJHM     as MQZJHM,
       hjbl.MQWWX      as MQWWX,
       hjbl.MQWWM      as MQWWM,
       hjbl.POZJZL     as POZJZL,
       hjbl.POZJHM     as POZJHM,
       hjbl.POWWX      as POWWX,
       hjbl.POWWM      as POWWM,
       hjbl.QYLDYY     as QYLDYY,
       hjbl.SBRJTGX    as SBRJTGX,
       hjbl.BZDZ       as BZDZ,
       hjbl.BZDZID     as BZDZID,
       hjbl.BZDZX      as BZDZX,
       hjbl.BZDZY      as BZDZY,
       hjbl.BZDZST     as BZDZST,
       hjbl.CXFLDM     as CXFLDM,
       hjbl.JTHZL      as JTHZL,
       dzb.FWJB       as FWJB,
       dzb.DZMC       as DZMC,
       dzb.MS         as MS,
       dzb.QYBZ       as QYBZ
  from HJSP_HJBLSPSQB hjbl, XT_SPDZB dzb
 where (dzb.DZID(+) = hjbl.XYDZID);
comment on table v_hjsphjbl is '户籍审批户籍补录信息视图';
create or replace view v_hjsphbbg as
select hbbg.SPYWID         as SPYWID,
       czr.RYNBID         as RYNBID,
       dzb.DZID           as DZID,
       hbbg.SPMBID         as SPMBID,
       hbbg.BGRYID         as BGRYID,
       hbbg.BGYY           as BGYY,
       hbbg.BGQHB          as BGQHB,
       hbbg.BGHHB          as BGHHB,
       hbbg.XYDZID         as XYDZID,
       hbbg.SPJG           as SPJG,
       hbbg.LSBZ           as LSBZ,
       hbbg.BGHRYNBID      as BGHRYNBID,
       hbbg.HJYWID         as HJYWID,
       hbbg.SBSJ           as SBSJ,
       hbbg.SBRYXM         as SBRYXM,
       hbbg.SBRGMSFHM      as SBRGMSFHM,
       hbbg.DJRID          as DJRID,
       hbbg.SPSM           as SPSM,
       czr.NBSFZID        as NBSFZID,
       czr.QFJG           as QFJG,
       czr.YXQXQSRQ       as YXQXQSRQ,
       czr.YXQXJZRQ       as YXQXJZRQ,
       czr.SWZXRQ         as SWZXRQ,
       czr.FQXM           as FQXM,
       czr.FQGMSFHM       as FQGMSFHM,
       czr.MQXM           as MQXM,
       czr.MQGMSFHM       as MQGMSFHM,
       czr.POXM           as POXM,
       czr.POGMSFHM       as POGMSFHM,
       czr.JGGJDQ         as JGGJDQ,
       czr.JGSSXQ         as JGSSXQ,
       czr.ZJXY           as ZJXY,
       czr.WHCD           as WHCD,
       czr.HYZK           as HYZK,
       czr.BYZK           as BYZK,
       czr.SG             as SG,
       czr.XX             as XX,
       czr.ZY             as ZY,
       czr.ZYLB           as ZYLB,
       czr.FWCS           as FWCS,
       czr.XXJB           as XXJB,
       czr.HSQL           as HSQL,
       czr.HYQL           as HYQL,
       czr.HGJDQQL        as HGJDQQL,
       czr.HSSXQQL        as HSSXQQL,
       czr.HXZQL          as HXZQL,
       czr.HSLBZ          as HSLBZ,
       czr.HYLBZ          as HYLBZ,
       czr.HGJDQLBZ       as HGJDQLBZ,
       czr.HSSSQLBZ       as HSSSQLBZ,
       czr.HXZLBZ         as HXZLBZ,
       czr.SWRQ           as SWRQ,
       czr.SWZXLB         as SWZXLB,
       czr.QCRQ           as QCRQ,
       czr.QCZXLB         as QCZXLB,
       czr.QWDGJDQ        as QWDGJDQ,
       czr.QWDSSXQ        as QWDSSXQ,
       czr.QWDXZ          as QWDXZ,
       czr.CSZMBH         as CSZMBH,
       czr.CSZQFRQ        as CSZQFRQ,
       czr.HYLB           as HYLB,
       czr.QTSSXQ         as QTSSXQ,
       czr.QTZZ           as QTZZ,
       czr.RYLB           as RYLB,
       czr.HB             as HB,
       czr.YHZGX          as YHZGX,
       czr.RYZT           as RYZT,
       czr.RYSDZT         as RYSDZT,
       czr.LXDBID         as LXDBID,
       czr.BZ             as BZ,
       czr.JLBZ           as JLBZ,
       czr.YWNR           as YWNR,
       czr.CJHJYWID       as CJHJYWID,
       czr.CCHJYWID       as CCHJYWID,
       czr.QYSJ           as QYSJ,
       czr.JSSJ           as JSSJ,
       czr.CXBZ           as CXBZ,
       czr.RYID           as RYID,
       czr.HHNBID         as HHNBID,
       czr.MLPNBID        as MLPNBID,
       czr.ZPID           as ZPID,
       czr.GMSFHM         as GMSFHM,
       czr.XM             as XM,
       czr.CYM            as CYM,
       czr.XMPY           as XMPY,
       czr.CYMPY          as CYMPY,
       czr.XB             as XB,
       czr.MZ             as MZ,
       czr.CSRQ           as CSRQ,
       czr.CSSJ           as CSSJ,
       czr.CSDGJDQ        as CSDGJDQ,
       czr.CSDSSXQ        as CSDSSXQ,
       czr.CSDXZ          as CSDXZ,
       czr.DHHM           as DHHM,
       czr.JHRYXM         as JHRYXM,
       czr.JHRYGMSFHM     as JHRYGMSFHM,
       czr.JHRYJHGX       as JHRYJHGX,
       czr.JHREXM         as JHREXM,
       czr.JHREGMSFHM     as JHREGMSFHM,
       czr.JHREJHGX       as JHREJHGX,
       czr.ZJLB           as ZJLB,
       czr.SSXQ           as SSXQ,
       czr.JLX            as JLX,
       czr.MLPH           as MLPH,
       czr.MLXZ           as MLXZ,
       czr.PCS            as PCS,
       czr.ZRQ            as ZRQ,
       czr.XZJD           as XZJD,
       czr.JCWH           as JCWH,
       czr.PXH            as PXH,
       czr.HH             as HH,
       czr.HLX            as HLX,
       czr.BDFW           as BDFW,
       czr.HHID           as HHID,
       czr.MLPID          as MLPID,
       czr.XXQYSJ         as XXQYSJ,
       czr.DHHM2          as DHHM2,
       czr.XMX            as XMX,
       czr.XMM            as XMM,
       czr.JGXZ           as JGXZ,
       czr.JHRYZJZL       as JHRYZJZL,
       czr.JHRYZJHM       as JHRYZJHM,
       czr.JHRYWWX        as JHRYWWX,
       czr.JHRYWWM        as JHRYWWM,
       czr.JHRYLXDH       as JHRYLXDH,
       czr.JHREZJZL       as JHREZJZL,
       czr.JHREZJHM       as JHREZJHM,
       czr.JHREWWX        as JHREWWX,
       czr.JHREWWM        as JHREWWM,
       czr.JHRELXDH       as JHRELXDH,
       czr.FQZJZL         as FQZJZL,
       czr.FQZJHM         as FQZJHM,
       czr.FQWWX          as FQWWX,
       czr.FQWWM          as FQWWM,
       czr.MQZJZL         as MQZJZL,
       czr.MQZJHM         as MQZJHM,
       czr.MQWWX          as MQWWX,
       czr.MQWWM          as MQWWM,
       czr.POZJZL         as POZJZL,
       czr.POZJHM         as POZJHM,
       czr.POWWX          as POWWX,
       czr.POWWM          as POWWM,
       czr.QYLDYY         as QYLDYY,
       czr.YHKXZMC        as YHKXZMC,
       czr.YHKXZSJ        as YHKXZSJ,
       czr.BZDZ           as BZDZ,
       czr.BZDZID         as BZDZID,
       czr.BZDZX          as BZDZX,
       czr.BZDZY          as BZDZY,
       czr.BZDZST         as BZDZST,
       czr.CXFLDM         as CXFLDM,
       czr.nyzyrklhczyydm as nyzyrklhczyydm,
       czr.lzd_cxfldm     as lzd_cxfldm,
       czr.kdqqy_qrdqbm   as kdqqy_qrdqbm,
       czr.kdqqy_qrywid   as kdqqy_qrywid,
       czr.kdqqy_qrywlx   as kdqqy_qrywlx,
       czr.kdqqy_qrfkzt   as kdqqy_qrfkzt,
       czr.kdqqy_qrfksj   as kdqqy_qrfksj,
       czr.JTHZL          as JTHZL,
       dzb.FWJB           as FWJB,
       dzb.DZMC           as DZMC,
       dzb.MS             as MS,
       dzb.QYBZ           as QYBZ
  from HJSP_HBBGSPSQB hbbg,
       HJXX_CZRKJBXXB czr,
       XT_SPDZB       dzb
 where (dzb.DZID(+) = hbbg.XYDZID)
   and (hbbg.BGRYID = czr.RYID)
   and (czr.JLBZ = '1')
   and (czr.CXBZ = '0');
comment on table v_hjsphbbg is '户籍审批户别变更信息视图';
create or replace view v_hjspqrsp as
select sqb.SPYWID         as SPYWID,
       dzb.DZID           as DZID,
       sqb.SQRGMSFHM      as SQRGMSFHM,
       sqb.SPMBID         as SPMBID,
       sqb.SQRXM          as SQRXM,
       sqb.SQRZZSSXQ      as SQRZZSSXQ,
       sqb.SPLX           as SPLX,
       sqb.SLRQ           as SLRQ,
       sqb.XM             as XM,
       sqb.RYID           as RYID,
       sqb.XB             as XB,
       sqb.GMSFHM         as GMSFHM,
       sqb.CSRQ           as CSRQ,
       sqb.MZ             as MZ,
       sqb.JHNY           as JHNY,
       sqb.YJRCLX         as YJRCLX,
       sqb.YRDWJCYRYSJ    as YRDWJCYRYSJ,
       sqb.LXDH           as LXDH,
       sqb.BYXX           as BYXX,
       sqb.WHCD           as WHCD,
       sqb.BYSJ           as BYSJ,
       sqb.ZYZL           as ZYZL,
       sqb.BYZSH          as BYZSH,
       sqb.ZYJSZC         as ZYJSZC,
       sqb.JSZSH          as JSZSH,
       sqb.JSFZJG         as JSFZJG,
       sqb.FMZLMC         as FMZLMC,
       sqb.FMZLH          as FMZLH,
       sqb.ZLFZJG         as ZLFZJG,
       sqb.HB             as HB,
       sqb.QRHHB          as QRHHB,
       sqb.ZZSSXQ         as ZZSSXQ,
       sqb.ZZXZ           as ZZXZ,
       sqb.HKSZDDJJG      as HKSZDDJJG,
       sqb.QRDQX          as QRDQX,
       sqb.QRDPCS         as QRDPCS,
       sqb.QRDJWZRQ       as QRDJWZRQ,
       sqb.QRDXZJD        as QRDXZJD,
       sqb.QRDJWH         as QRDJWH,
       sqb.QRDJLX         as QRDJLX,
       sqb.QRDMLPH        as QRDMLPH,
       sqb.QRDZ           as QRDZ,
       sqb.QRDHKDJJG      as QRDHKDJJG,
       sqb.QRDHHID        as QRDHHID,
       sqb.QRDHLX         as QRDHLX,
       sqb.RLHBZ          as RLHBZ,
       sqb.SQQRLY         as SQQRLY,
       sqb.SQRZZXZ        as SQRZZXZ,
       sqb.BZ             as BZ,
       sqb.DJRID          as DJRID,
       sqb.DJSJ           as DJSJ,
       sqb.XYDZID         as XYDZID,
       sqb.SPJG           as SPJG,
       sqb.SQRHKDJJG      as SQRHKDJJG,
       sqb.LSBZ           as LSBZ,
       sqb.RYNBID         as RYNBID,
       sqb.HJYWID         as HJYWID,
       sqb.YSQRGX         as YSQRGX,
       sqb.SPSM           as SPSM,
       sqb.BZDZ           as BZDZ,
       sqb.BZDZID         as BZDZID,
       sqb.BZDZX          as BZDZX,
       sqb.BZDZY          as BZDZY,
       sqb.BZDZST         as BZDZST,
       sqb.cxfldm         as cxfldm,
       sqb.nyzyrklhczyydm as nyzyrklhczyydm,
       sqb.kdqqy_qchjywid as kdqqy_qchjywid,
       sqb.kdqqy_qcdqbm   as kdqqy_qcdqbm,
       sqb.kdqqy_fksj     as kdqqy_fksj,
       sqb.kdqqy_fkzt     as kdqqy_fkzt,
       sqb.kdqqy_fknr     as kdqqy_fknr,
       sqb.kdqqy_czyxm    as kdqqy_czyxm,
       sqb.kdqqy_czydw    as kdqqy_czydw,
       sqb.kdqqy_qyzbh    as kdqqy_qyzbh,
       sqb.kdqqy_lscxfldm as kdqqy_lscxfldm,
       dzb.FWJB           as FWJB,
       dzb.DZMC           as DZMC,
       dzb.MS             as MS,
       dzb.QYBZ           as QYBZ
  from HJSP_HJSPSQB sqb, XT_SPDZB dzb
 where (sqb.XYDZID = dzb.DZID(+));
comment on table v_hjspqrsp is '户籍审批迁入审批信息视图';
create or replace view v_dwxxwh as
select dwxxb.DM     as dm,
       xzqhb.DM     as xzqhbdm,
       csb1.CSID   as csb1csid,
       csb2.CSID   as csb2csid,
       dwxxb.MC     as mc,
       dwxxb.ZWPY   as zwpy,
       dwxxb.WBPY   as wbpy,
       dwxxb.DWJGDM as dwjgdm,
       dwxxb.QHDM   as qhdm,
       dwxxb.DWJB   as dwjb,
       dwxxb.DZ     as dz,
       dwxxb.BZ     as bz,
       dwxxb.QYBZ   as qybz,
       dwxxb.BDLX   as bdlx,
       dwxxb.BDSJ   as bdsj,
       dwxxb.FJJGDM as fjjgdm,
       dwxxb.FJJGMC as fjjgmc,
       xzqhb.MC     as xzqhbmc,
       xzqhb.ZWPY   as xzqhbzwpy,
       xzqhb.WBPY   as xzqhbwbpy,
       xzqhb.BZ     as xzqhbbz,
       xzqhb.QYBZ   as xzqhbqybz,
       xzqhb.BDLX   as xzqhbbdlx,
       xzqhb.BDSJ   as xzqhbbdsj,
       csb1.CSLB   as csb1cslb,
       csb1.DM     as csb1dm,
       csb1.MC     as csb1mc,
       csb1.ZWPY   as csb1zwpy,
       csb1.KZBZB  as csb1kzbzb,
       csb1.KZBZC  as csb1kzbzc,
       csb1.KZBZD  as csb1kzbzd,
       csb1.KZBZE  as csb1kzbze,
       csb1.KZBZF  as csb1kzbzf,
       csb1.KZBZG  as csb1kzbzg,
       csb1.XGBZ   as csb1xgbz,
       csb1.BDLX   as csb1bdlx,
       csb1.BDSJ   as csb1bdsj,
       csb2.CSLB   as csb2cslb,
       csb2.DM     as csb2dm,
       csb2.MC     as csb2mc,
       csb2.ZWPY   as csb2zwpy,
       csb2.KZBZB  as csb2kzbzb,
       csb2.KZBZC  as csb2kzbzc,
       csb2.KZBZD  as csb2kzbzd,
       csb2.KZBZE  as csb2kzbze,
       csb2.KZBZF  as csb2kzbzf,
       csb2.KZBZG  as csb2kzbzg,
       csb2.XGBZ   as csb2xgbz,
       csb2.BDLX   as csb2bdlx,
       csb2.BDSJ   as csb2bdsj
  from XT_DWXXB dwxxb,
       XT_XZQHB xzqhb,
       XT_XTCSB csb1,
       XT_XTCSB csb2
 where (dwxxb.DWJB = csb1.DM(+))
   and (csb1.CSLB(+) = '9024')
   and (dwxxb.QYBZ = csb2.DM(+))
   and (csb2.CSLB(+) = '9002')
   and (dwxxb.QHDM = xzqhb.DM)
 order by dwxxb.QHDM, dwxxb.DM;
comment on table V_DWXXWH is '单位信息视图';
create or replace view v_mbsplc as
select mblcxxb.MBLCID as MBLCID,
     mblcxxb.DZID as DZID,
     spmbxxb.SPMBID as SPMBID,
     mblcxxb.DZZ    as DZZ,
     mblcxxb.XGDZID as XGDZID,
     mblcxxb.DZBZ   as DZBZ,
     spmbxxb.MBMC   as MBMC,
     spmbxxb.MBDJ   as MBDJ,
     spmbxxb.CJRID  as CJRID,
     spmbxxb.CJSJ   as CJSJ,
     spmbxxb.XGRID  as XGRID,
     spmbxxb.XGSJ   as XGSJ,
     spmbxxb.QYBZ   as QYBZ,
     spmbxxb.DQSYS  as DQSYS,
     spdzb1.DZMC   as DZMC,  
     spdzb2.DZMC   as XGDZMC               
  from XT_MBLCXXB mblcxxb,
     XT_SPDZB   spdzb1,
     XT_SPMBXXB spmbxxb,
     XT_SPDZB   spdzb2
 where (mblcxxb.SPMBID = spmbxxb.SPMBID)
   and (mblcxxb.DZID = spdzb1.DZID)
   and (mblcxxb.XGDZID = spdzb2.DZID(+));
comment on table v_mbsplc is '模板审批流程';
--菜单表更新语句
update xt_xtgncdb set url = 'yw/hjyw/qryw' where gncdid = '100001';
update xt_xtgncdb set url = 'yw/hjyw/csyw' where gncdid = '100002';
update xt_xtgncdb set url = 'yw/hjyw/qczx' where gncdid = '100003';
update xt_xtgncdb set url = 'yw/hjyw/swzx' where gncdid = '100004' and cdmc = '死亡业务';
update xt_xtgncdb set url = 'yw/hjyw/zzbd' where gncdid = '100005' and cdmc = '住址变动';
update xt_xtgncdb set url = 'yw/hjyw/bggz' where gncdid = '100006' and cdmc = '变更更正';
update xt_xtgncdb set url = 'yw/hjyw/hbbg' where gncdid = '100007' and cdmc = '户别变更';
update xt_xtgncdb set url = 'yw/hjyw/hjbl' where gncdid = '100008' and cdmc = '户籍补录';
update xt_xtgncdb set url = 'yw/hjyw/hjsc' where gncdid = '100009' and cdmc = '户籍删除';
update xt_xtgncdb set url = 'yw/hjyw/sfhfpxxbl' where gncdid = '100012' and cdmc = '身份号分配信息补录';
update xt_xtgncdb set url = 'yw/hjyw/xzddj' where gncdid = '100014' and cdmc = '现住地登记';
update xt_xtgncdb set url = 'yw/hjyw/fxbg' where gncdid = '100018' and cdmc = '辅项变更';
update xt_xtgncdb set url = 'yw/hjyw/qxbg' where gncdid = '100019' and cdmc = '全项变更';
update xt_xtgncdb set url = 'yw/hjyw/hzywcl' where gncdid = '100102' and cdmc = '户政业务处理';
update xt_xtgncdb set url = 'yw/bbdy/cbhkdy' where gncdid = '140006' and cdmc = '常表户口打印';
update xt_xtgncdb set url = 'yw/spgl/qrsp' where gncdid = '170001' and cdmc = '迁入登记';
update xt_xtgncdb set url = 'yw/spgl/hjsp' where gncdid = '170002' and cdmc = '户籍审批';
update xt_xtgncdb set url = 'yw/spgl/spcl' where gncdid = '170004' and cdmc = '审批处理';
update xt_xtgncdb set url = 'cx/hjjbxx/ckxx' where gncdid = '200001' and cdmc = '常口信息查询';
update xt_xtgncdb set url = 'cx/hjjbxx/rkjbxxcx' where gncdid = '200002' and cdmc = '人口基本信息查询';
update xt_xtgncdb set url = 'cx/hjjbxx/hxxcx' where gncdid = '200003' and cdmc = '户信息查询';
update xt_xtgncdb set url = 'cx/hjjbxx/mlpxxcx' where gncdid = '200004' and cdmc = '门楼牌信息查询';
update xt_xtgncdb set url = 'cx/hjjbxx/thrycx' where gncdid = '200006' and cdmc = '同户人员查询';
update xt_xtgncdb set url = 'cx/hjjbxx/thrycx' where gncdid = '200006' and cdmc = '同户人员查询';
update xt_xtgncdb set url = 'cx/hjjbxx/xzdcx' where gncdid = '200007' and cdmc = '现住地查询';
update xt_xtgncdb set url = 'cx/hjywxx/qrdjcx' where gncdid = '210001' and cdmc = '迁入登记信息查询';
update xt_xtgncdb set url = 'cx/hjywxx/qczxcx' where gncdid = '210002' and cdmc = '迁出注销信息查询';
update xt_xtgncdb set url = 'cx/hjywxx/csdjcx' where gncdid = '210003' and cdmc = '出生登记信息查询';
update xt_xtgncdb set url = 'cx/hjywxx/swzxcx' where gncdid = '210004' and cdmc = '死亡注销信息查询';
update xt_xtgncdb set url = 'cx/hjywxx/zzbdcx' where gncdid = '210005' and cdmc = '住址变动信息查询';
update xt_xtgncdb set url = 'cx/hjywxx/bggzcx' where gncdid = '210006' and cdmc = '变更更正信息查询';
update xt_xtgncdb set url = 'cx/hjywxx/hbbgcx' where gncdid = '210007' and cdmc = '户别变更信息查询';
update xt_xtgncdb set url = 'cx/hjywxx/hjblcx' where gncdid = '210008' and cdmc = '户籍补录信息查询';
update xt_xtgncdb set url = 'cx/hjywxx/hjsccx' where gncdid = '210009' and cdmc = '户籍删除信息查询';
update xt_xtgncdb set url = 'cx/hjywxx/hcybdcx' where gncdid = '210010' and cdmc = '户成员变动信息查询';
update xt_xtgncdb set url = 'cx/hjywxx/bdqkcx' where gncdid = '210011' and cdmc = '变动情况查询(四变)';
update xt_xtgncdb set url = 'cx/hjywxx/dycx' where gncdid = '210012' and cdmc = '打印信息查询';
update xt_xtgncdb set url = 'cx/edzjxx/edzslcx' where gncdid = '220001' and cdmc = '二代证受理信息查询';
update xt_xtgncdb set url = 'cx/edzjxx/sjcx' where gncdid = '220002' and cdmc = '收交信息查询';
update xt_xtgncdb set url = 'cx/edzjxx/yscx' where gncdid = '220003' and cdmc = '验收信息查询';
update xt_xtgncdb set url = 'cx/edzjxx/lqffcx' where gncdid = '220004' and cdmc = '领取发放信息查询';
update xt_xtgncdb set url = 'cx/edzjxx/gscx' where gncdid = '220005' and cdmc = '挂失信息查询';
update xt_xtgncdb set url = 'cx/edzjxx/tdcx' where gncdid = '220008' and cdmc = '投递信息查询';
update xt_xtgncdb set url = 'cx/edzjxx/zzfkcx' where gncdid = '220009' and cdmc = '制证反馈信息查询';
update xt_xtgncdb set url = 'cx/edzjxx/zlfkcx' where gncdid = '220010' and cdmc = '质量反馈信息查询';
update xt_xtgncdb set url = 'cx/edzjxx/fjjgcx' where gncdid = '220011' and cdmc = '分检结果查询';
update xt_xtgncdb set url = 'cx/edzjxx/ffjscx' where gncdid = '220012' and cdmc = '分发接收信息查询';
update xt_xtgncdb set url = 'cx/edzjxx/zwcjcx' where gncdid = '220021' and cdmc = '指纹采集信息查询';
update xt_xtgncdb set url = 'cx/hmzjxx/sfzcx' where gncdid = '230001' and cdmc = '身份证信息查询';
update xt_xtgncdb set url = 'cx/hmzjxx/chcx' where gncdid = '230003' and cdmc = '重号信息查询';
update xt_xtgncdb set url = 'cx/hmzjxx/sfhfpcx' where gncdid = '230005' and cdmc = '身份号分配信息查询';
update xt_xtgncdb set url = 'cx/hmzjxx/qyzcx' where gncdid = '230006' and cdmc = '迁移证查询';
update xt_xtgncdb set url = 'cx/hmzjxx/zqzcx' where gncdid = '230007' and cdmc = '准迁证查询';
update xt_xtgncdb set url = 'cx/ydzzjxx/ydzslcx' where gncdid = '240001' and cdmc = '一代证受理信息查询';
update xt_xtgncdb set url = 'cx/fsjshxx/shcx' where gncdid = '260001' and cdmc = '审核查询';
update xt_xtgncdb set url = 'cx/fsjshxx/xhcx' where gncdid = '260002' and cdmc = '销毁信息查询';
update xt_xtgncdb set url = 'cx/spcx/qrspcx' where gncdid = '270001' and cdmc = '迁入审批查询';
update xt_xtgncdb set url = 'cx/spcx/bgspcx' where gncdid = '270002' and cdmc = '变更审批查询';
update xt_xtgncdb set url = 'cx/spcx/hbbgspcx' where gncdid = '270003' and cdmc = '户别变更审批查询';
update xt_xtgncdb set url = 'cx/spcx/hjblspcx' where gncdid = '270004' and cdmc = '户籍补录审批查询';
update xt_xtgncdb set url = 'cx/spcx/hjscspcx' where gncdid = '270005' and cdmc = '户籍删除审批查询';
update xt_xtgncdb set url = 'cx/spcx/hjspcx' where gncdid = '270006' and cdmc = '户籍审批信息查询';
-- 2018.09.20 刁杰
update xt_xtgncdb set url = 'yw/hjyw/qccl' where gncdid = '100013' and cdmc = '迁出处理';
-- 2018.09.27 刁杰
update xt_xtgncdb set url = 'yw/hjyw/hglgxdj' where gncdid = '100017' and cdmc = '户关联关系登记';
update xt_xtgncdb set url = 'yw/hjyw/cphbbg' where gncdid = '100020' and cdmc = '成批户别变更';
update xt_xtgncdb set url = 'yw/bbdy/qyzdy' where gncdid = '140007' and cdmc = '迁移证打印';
update xt_xtgncdb set url = 'yw/bbdy/yhybdy' where gncdid = '140008' and cdmc = '一户一表打印';
update xt_xtgncdb set url = 'yw/bbdy/sbdy' where gncdid = '140009' and cdmc = '四变打印';
update xt_xtgncdb set url = 'yw/bbdy/cbcpdy' where gncdid = '140010' and cdmc = '常表成批打印';
update xt_xtgncdb set url = 'yw/bbdy/slxxtj' where gncdid = '140011' and cdmc = '受理信息统计';
update xt_xtgncdb set url = 'gl/xtmbgl/xtcswh' where gncdid = '300001' and cdmc = '系统参数维护';
update xt_xtgncdb set url = 'gl/xtmbgl/sjzdwh' where gncdid = '300002' and cdmc = '数据字典维护';
update xt_xtgncdb set url = 'gl/xtmbgl/xzqhwh' where gncdid = '300003' and cdmc = '行政区划维护';
update xt_xtgncdb set url = 'gl/xtmbgl/dwxxwh' where gncdid = '300004' and cdmc = '单位信息维护';
update xt_xtgncdb set url = 'gl/xtmbgl/jwzrqxxwh' where gncdid = '300005' and cdmc = '警务区信息维护';
update xt_xtgncdb set url = 'gl/xtmbgl/xzjdwh' where gncdid = '300006' and cdmc = '乡镇街道维护';
update xt_xtgncdb set url = 'gl/xtmbgl/jwhxxwh' where gncdid = '300007' and cdmc = '居委会信息维护';
update xt_xtgncdb set url = 'gl/xtmbgl/jlxxxwh' where gncdid = '300008' and cdmc = '街路巷信息维护';
update xt_xtgncdb set url = 'gl/xtmbgl/jlxjwhdzwh' where gncdid = '300009' and cdmc = '街路巷居委会对照';
--管理  系统控制管理  20181214 zhangjiaming
update xt_xtgncdb set url = 'gl/xtkzgl/kzcswh' where gncdid = '310001' and cdmc = '控制参数维护';
update xt_xtgncdb set url = 'gl/xtkzgl/splwh' where gncdid = '310002' and cdmc = '审批流维护';
update xt_xtgncdb set url = 'gl/xtkzgl/ggyxipset' where gncdid = '310003' and cdmc = '公共允许IP设置';
update xt_xtgncdb set url = 'gl/xtkzgl/bssqwh' where gncdid = '310004' and cdmc = '本市市区维护';
update xt_xtgncdb set url = 'gl/xtkzgl/qyszwh' where gncdid = '310005' and cdmc = '迁移设置维护';
update xt_xtgncdb set url = 'gl/xtkzgl/hhxlwh' where gncdid = '310006' and cdmc = '户号序列维护';
update xt_xtgncdb set url = 'gl/xtkzgl/slhxlbwh' where gncdid = '310007' and cdmc = '受理号序列表维护';
update xt_xtgncdb set url = 'gl/xtkzgl/lnwsdwh' where gncdid = '310008' and cdmc = '历年尾数段维护';
update xt_xtgncdb set url = 'gl/xtkzgl/bgdykz' where gncdid = '310009' and cdmc = '变更打印控制';
update xt_xtgncdb set url = 'gl/xtkzgl/bgspkz' where gncdid = '310010' and cdmc = '变更审批控制';
update xt_xtgncdb set url = 'gl/xtkzgl/cxsjfwwh' where gncdid = '310013' and cdmc = '查询数据范围维护';
update xt_xtgncdb set url = 'gl/xtkzgl/ywblxzwh' where gncdid = '310014' and cdmc = '业务办理限制维护';

update xt_xtgncdb set url = 'gl/zxyhgl' where gncdid = '350000' and cdmc = '在线用户管理';
update xt_xtgncdb set url = 'gl/yhgl' where gncdid = '320000' and cdmc = '用户管理';
update xt_xtgncdb set url = 'gl/jsgl' where gncdid = '330000' and cdmc = '角色管理';

--分局管理 菜单下  子菜单添加
update xt_xtgncdb set url = 'yw/fjgl/fjsh' where gncdid = '150001' and cdmc = '分局审核';
update xt_xtgncdb set url = 'yw/fjgl/fjqf' where gncdid = '150002' and cdmc = '分局签发';
update xt_xtgncdb set url = 'yw/fjgl/zjxh' where gncdid = '150003' and cdmc = '证件销毁';
update xt_xtgncdb set url = 'yw/fjgl/dzzj' where gncdid = '150004' and cdmc = '地址追加';
update xt_xtgncdb set url = 'yw/fjgl/fjys' where gncdid = '150005' and cdmc = '分局验收';
update xt_xtgncdb set url = 'yw/fjgl/qxzjjs' where gncdid = '150006' and cdmc = '区县证件接收';
update xt_xtgncdb set url = 'yw/fjgl/qxzjff' where gncdid = '150007' and cdmc = '区县证件分发';

--市局管理菜单下  子菜单添加
update xt_xtgncdb set url = 'yw/sjgl/dszjff' where gncdid = '160004' and cdmc = '地市证件分发';

--异地办证菜单下  子菜单添加
update xt_xtgncdb set url = 'yw/ydbz/ydbzsl' where gncdid = '190001' and cdmc = '异地办证受理';
update xt_xtgncdb set url = 'yw/ydbz/ydbzcx' where gncdid = '190002' and cdmc = '异地办证查询';
update xt_xtgncdb set url = 'yw/ydbz/ydbzlz' where gncdid = '190003' and cdmc = '异地办证领证';
update xt_xtgncdb set url = 'yw/ydbz/dblkz' where gncdid = '190004' and cdmc = '打包量控制';

--二代证证件管理 子菜单添加
update xt_xtgncdb set url = 'yw/edzzjgl/zjsl' where gncdid = '120001' and cdmc = '证件受理';
update xt_xtgncdb set url = 'yw/edzzjgl/zjys' where gncdid = '120002' and cdmc = '证件验收';
update xt_xtgncdb set url = 'yw/edzzjgl/zjlq' where gncdid = '120003' and cdmc = '证件领取';
update xt_xtgncdb set url = 'yw/edzzjgl/sfhy' where gncdid = '120004' and cdmc = '身份核验';
update xt_xtgncdb set url = 'yw/edzzjgl/zjgs' where gncdid = '120005' and cdmc = '证件挂失';
update xt_xtgncdb set url = 'yw/edzzjgl/zjsj' where gncdid = '120006' and cdmc = '证件收交';
update xt_xtgncdb set url = 'yw/edzzjgl/zjjs' where gncdid = '120007' and cdmc = '证件接收';
update xt_xtgncdb set url = 'yw/edzzjgl/zwsfzsl' where gncdid = '120101' and cdmc = '指纹身份证受理';

--一代证证件管理
update xt_xtgncdb set url = 'yw/ydzzjgl/zjbl' where gncdid = '130001' and cdmc = '证件办理';
update xt_xtgncdb set url = 'yw/ydzzjgl/dkck' where gncdid = '130002' and cdmc = '底卡查看/打印';

--业务报表打印
update xt_xtgncdb set url = 'yw/ywbbdy/ywbbdy' where gncdid = '140001' and cdmc = '业务报表打印';
update xt_xtgncdb set url = 'yw/ywbbdy/zsbbdy' where gncdid = '140002' and cdmc = '制式报表打印';
update xt_xtgncdb set url = 'yw/bbdy/clddy' where gncdid = '140003' and cdmc = '催领单打印';
update xt_xtgncdb set url = 'yw/bbdy/cbddy' where gncdid = '140004' and cdmc = '催办单打印';
update xt_xtgncdb set url = 'yw/wsbdy/wsbdy' where gncdid = '140005' and cdmc = '尾数表打印';
update xt_xtgncdb set url = 'yw/bbdy/zjfj' where gncdid = '140012' and cdmc = '证件分拣统计';

--临时居民身份证管理
update xt_xtgncdb set url = 'yw/lsjmsfzgl/lzbl' where gncdid = '180001' and cdmc = '临证办理';
update xt_xtgncdb set url = 'yw/lsjmsfzgl/lzdy' where gncdid = '180002' and cdmc = '临证打印';
update xt_xtgncdb set url = 'yw/lsjmsfzgl/lztj' where gncdid = '180003' and cdmc = '临证统计';

--异地采集管理
update xt_xtgncdb set url = 'yw/ydcjgl/ydcj' where gncdid = '181001' and cdmc = '异地采集';
update xt_xtgncdb set url = 'yw/ydcjgl/ydcjcx' where gncdid = '181002' and cdmc = '异地采集查询';

--拍照照片信息查询
update xt_xtgncdb set url = 'cx/pzzpxx/pzlogcx' where gncdid = '280001' and cdmc = '拍照日志信息查询';
update xt_xtgncdb set url = 'cx/pzzpxx/pzlogcx_ycl' where gncdid = '280002' and cdmc = '已处理拍照的日志查询';

--临时居民身份证查询
update xt_xtgncdb set url = 'cx/lzcx/sfzxxch' where gncdid = '281003' and cdmc = '临证查询';

--住址追加信息查询
update xt_xtgncdb set url = 'cx/zzzj/czyxxcx' where gncdid = '290001' and cdmc = '操作员信息查询';
update xt_xtgncdb set url = 'cx/zzzj/zjadresscx' where gncdid = '290002' and cdmc = '追加住址查询';
update xt_xtgncdb set url = 'cx/zzzj/zzzjrzxxcx' where gncdid = '290003' and cdmc = '日志信息查询';

--比对信息查询
update xt_xtgncdb set url = 'cx/bdcx/tfbdxxcx' where gncdid = '291001' and cdmc = '逃犯比对信息查询';

update xt_xtgncdb set url = 'gl/sjcc' where gncdid = '340000' and cdmc = '数据查错';
update xt_xtgncdb set url = 'gl/zxyhgl' where gncdid = '350000' and cdmc = '在线用户管理';

--报表管理
update xt_xtgncdb set url = 'gl/ywbb/ywbbwh' where gncdid = '360001' and cdmc = '业务报表维护';
update xt_xtgncdb set url = 'gl/zsbb/zsbbwh' where gncdid = '360002' and cdmc = '制式报表维护';
update xt_xtgncdb set url = 'gl/bbgl/dssj/dssjsj' where gncdid = '360003' and cdmc = '底数数据收集';
update xt_xtgncdb set url = 'gl/bbgl/dssj/dssjcx' where gncdid = '360004' and cdmc = '底数数据查询';

update xt_xtgncdb set url = 'gl/wstg' where gncdid = '370000' and cdmc = '网上通告';
update xt_xtgncdb set url = 'cx/bdcx/xkxxrzcx' where gncdid = '400001' and cdmc = '写卡信息日志查询';
update xt_xtgncdb set url = 'cx/bdcx/dkxxrzcx' where gncdid = '400002' and cdmc = '读卡信息日志查询';

INSERT INTO xt_xtcsb VALUES ('3407000001000006733', '1070','12','户籍补录审批','HJBLSP','','','','','','','0','1','20180502000002');
INSERT INTO xt_xtcsb VALUES ('3407000001000006734', '1070','13','户籍删除审批','HJSCSP','','','','','','','0','1','20180502000002');
-- add by zhangjiaming 20190108 v_dwxxwh也有增字段
-- Add/modify columns 
alter table XT_DWXXB add dz nvarchar2(200);
-- Add comments to the columns 
comment on column XT_DWXXB.dz
  is '地址';

-- Add/modify columns 
alter table XT_SJPZB add vtype varchar2(20);
-- Add comments to the columns 
comment on column XT_SJPZB.vtype
  is 'EXT的数据校验规则，可用校验规则，由框架JS定义';

--下面是校验规则
--姓名
update xt_sjpzb set vtype='Chinese' where fieldname='xm';
--公民身份号码，母亲身份证，父亲身份证，申报人身份证，监护人1，2，配偶
update xt_sjpzb set vtype='Sfzh' 
where fieldname='gmsfhm'
 or fieldname='fqgmsfhm' 
 or fieldname='mqgmsfhm' 
 or fieldname='sbrgmsfhm' 
 or  fieldname='jhrygmsfhm'
 or fieldname='jhregmsfhm'
 or  fieldname='pogmsfhm';
 --身高
update xt_sjpzb set vtype='Sg' where fieldname='sg';
update xt_sjpzb set vtype='Dhhm' where displayname like '%电话%';
alter table hjyw_zzbdxxb  add zczjyhjzwnys_pdbz VARCHAR(20);
alter table hjyw_zzbdxxb  add nyzyrklhczyydm VARCHAR(20);
alter table hjyw_zzbdxxb  add jjqx_pdbz VARCHAR(20);
alter table hjyw_zzbdxxb  add cxsxtz_pdbz VARCHAR(20);
alter table hjyw_zzbdxxb  add cxfldm VARCHAR(20);
alter table hjyw_hjblxxb  add cxfldm VARCHAR(20);



-- Add/modify columns 
 alter table HZ_ZJ_SB add id                NUMBER not null;
 alter table HZ_ZJ_SB add sqrxm             NVARCHAR2(50);
 alter table HZ_ZJ_SB add sqrsfz            VARCHAR2(18);
 alter table HZ_ZJ_SB add sqrlxdh           VARCHAR2(50);
 alter table HZ_ZJ_SB add sqrlxdz           NVARCHAR2(150);
 alter table HZ_ZJ_SB add ywlb              NUMBER not null;
 alter table HZ_ZJ_SB add bsqrxm            NVARCHAR2(50);
 alter table HZ_ZJ_SB add bsqrsfz           VARCHAR2(18);
 alter table HZ_ZJ_SB add czdw              VARCHAR2(12);
 alter table HZ_ZJ_SB add czyh              VARCHAR2(20);
 alter table HZ_ZJ_SB add czsj              DATE;
 alter table HZ_ZJ_SB add lhbs              CHAR(1);
 alter table HZ_ZJ_SB add lhsfz             VARCHAR2(18);
 alter table HZ_ZJ_SB add lhdz              NVARCHAR2(100);
 alter table HZ_ZJ_SB add sbzt              CHAR(1);
 alter table HZ_ZJ_SB add clbs              CHAR(1);
 alter table HZ_ZJ_SB add clsj              DATE;
 alter table HZ_ZJ_SB add pch               VARCHAR2(32);
 alter table HZ_ZJ_SB add ywlbm             NVARCHAR2(50) not null;
 alter table HZ_ZJ_SB add sbid              NUMBER not null;
 alter table HZ_ZJ_SB add mz                CHAR(2);
 alter table HZ_ZJ_SB add hjbllb            NVARCHAR2(4);
 alter table HZ_ZJ_SB add swzxlb            CHAR(4);
 alter table HZ_ZJ_SB add xb                CHAR(1);
 alter table HZ_ZJ_SB add yhzgx             CHAR(2);
 alter table HZ_ZJ_SB add qcdssxq           CHAR(6);
 alter table HZ_ZJ_SB add bdfw              CHAR(2);
 alter table HZ_ZJ_SB add csrq              CHAR(8);
 alter table HZ_ZJ_SB add csdssxq           CHAR(6);
 alter table HZ_ZJ_SB add cxfldm            CHAR(3);
 alter table HZ_ZJ_SB add hb                NVARCHAR2(1);
 alter table HZ_ZJ_SB add csdjlb            CHAR(4);
 alter table HZ_ZJ_SB add jgssxq            CHAR(6);
 alter table HZ_ZJ_SB add hjsclb            CHAR(4);
 alter table HZ_ZJ_SB add zzbdlb            CHAR(1);
 alter table HZ_ZJ_SB add nbgxm             VARCHAR2(100);
 alter table HZ_ZJ_SB add cym               VARCHAR2(100);
 alter table HZ_ZJ_SB add csyxzmbh          VARCHAR2(100);
 alter table HZ_ZJ_SB add bsqrxb            VARCHAR2(20);
 alter table HZ_ZJ_SB add bsqrmz            VARCHAR2(20);
 alter table HZ_ZJ_SB add bsqrcsrq          VARCHAR2(20);
 alter table HZ_ZJ_SB add btkrgx            VARCHAR2(20);
 alter table HZ_ZJ_SB add bsqrsjhm          VARCHAR2(20);
 alter table HZ_ZJ_SB add zxyy              VARCHAR2(1000);
 alter table HZ_ZJ_SB add kdqqy_qrdqbm      VARCHAR2(20);
 alter table HZ_ZJ_SB add kdqqy_qrywid      VARCHAR2(32);
 alter table HZ_ZJ_SB add kdqqy_qrywlx      VARCHAR2(20);
 alter table HZ_ZJ_SB add kdqqy_qrfkzt      VARCHAR2(20);
 alter table HZ_ZJ_SB add kdqqy_qrfksj      VARCHAR2(20);
 alter table HZ_ZJ_SB add kdqqy_zqzbh       VARCHAR2(40);
 alter table HZ_ZJ_SB add kdqqy_qrdqh       VARCHAR2(40);
 alter table HZ_ZJ_SB add kdqqy_qyzbh       VARCHAR2(20);
 alter table HZ_ZJ_SB add kdqqy_lscxfldm    VARCHAR2(20);
 alter table HZ_ZJ_SB add kdqqy_qyldyy      VARCHAR2(20);
 alter table HZ_ZJ_SB add kdqqy_qclb        VARCHAR2(20);
 alter table HZ_ZJ_SB add sfzqr             VARCHAR2(20);
 alter table HZ_ZJ_SB add jhryxm            VARCHAR2(50);
 alter table HZ_ZJ_SB add fqxm              VARCHAR2(50);
 alter table HZ_ZJ_SB add ywdm              VARCHAR2(12);
 alter table HZ_ZJ_SB add zqzbh             NVARCHAR2(50);
 alter table HZ_ZJ_SB add qyldyy            CHAR(3);
 alter table HZ_ZJ_SB add jhryjhgx          CHAR(2);
 alter table HZ_ZJ_SB add zylb              CHAR(3);
 alter table HZ_ZJ_SB add sg                CHAR(3);
 alter table HZ_ZJ_SB add sbrjtgx           CHAR(2);
 alter table HZ_ZJ_SB add bdhhb             CHAR(1);
 alter table HZ_ZJ_SB add whcd              CHAR(2);
 alter table HZ_ZJ_SB add jndj_pdbz         CHAR(1);
 alter table HZ_ZJ_SB add ncjdzzyxbys_pdbz  CHAR(1);
 alter table HZ_ZJ_SB add ncjsbtcxy_pdbz    CHAR(1);
 alter table HZ_ZJ_SB add hlx               CHAR(1);
 alter table HZ_ZJ_SB add cszmbh            NVARCHAR2(50);
 alter table HZ_ZJ_SB add zczjyhjzwnys_pdbz CHAR(1);
 alter table HZ_ZJ_SB add zy                CHAR(4);
 alter table HZ_ZJ_SB add jjqx_pdbz         CHAR(1);
 alter table HZ_ZJ_SB add fwcs              NVARCHAR2(50);
 alter table HZ_ZJ_SB add qyzbh             NVARCHAR2(50);
 alter table HZ_ZJ_SB add nyzyrklhczyydm    CHAR(2);
 alter table HZ_ZJ_SB add zyjszc_pdbz       CHAR(1);
 alter table HZ_ZJ_SB add bdhjth            CHAR(1);
 alter table HZ_ZJ_SB add qwdssxq           CHAR(6);
 alter table HZ_ZJ_SB add qrqhb             CHAR(1);
 alter table HZ_ZJ_SB add qrlb              VARCHAR2(20);
 alter table HZ_ZJ_SB add dhhm              VARCHAR2(18);
 alter table HZ_ZJ_SB add jhrygmsfhm        VARCHAR2(18);
 alter table HZ_ZJ_SB add jthzl             CHAR(1);
 alter table HZ_ZJ_SB add qclb              VARCHAR2(20);
 alter table HZ_ZJ_SB add lhrsfz            VARCHAR2(18);
-- Add comments to the columns 
comment on column HZ_ZJ_SB.id  is 'ID，唯一标识';
comment on column HZ_ZJ_SB.sqrxm  is '申请人姓名';
comment on column HZ_ZJ_SB.sqrsfz  is '申请人身份证';
comment on column HZ_ZJ_SB.sqrlxdh  is '申请人联系电话';
comment on column HZ_ZJ_SB.sqrlxdz  is '申请人联系地址';
comment on column HZ_ZJ_SB.ywlb  is '业务类别，业务类别表的ID';
comment on column HZ_ZJ_SB.bsqrxm  is '被申请人姓名，多个被申请人逗号隔开 ';
comment on column HZ_ZJ_SB.bsqrsfz  is '被申请人身份证，多个被申请人逗号隔开';
comment on column HZ_ZJ_SB.czdw  is '操作机构代码，存在系统机构代码不一致的问题';
comment on column HZ_ZJ_SB.czyh  is '操作用户警号';
comment on column HZ_ZJ_SB.czsj  is '操作时间';
comment on column HZ_ZJ_SB.lhbs  is '落户标识 0-无落地信息 1-身份证 2-地址';
comment on column HZ_ZJ_SB.lhsfz  is '新增字段，拟入户身份证';
comment on column HZ_ZJ_SB.lhdz  is '落户地址';
comment on column HZ_ZJ_SB.sbzt  is '申办状态  0-非迁入登记 1-迁入登记';
comment on column HZ_ZJ_SB.clbs  is '处理标识 0-未处理 1-处理成功 ';
comment on column HZ_ZJ_SB.clsj  is '处理时间，记录变化处理标识的时间';
comment on column HZ_ZJ_SB.pch  is '新增字段 批次号，同一批业务 号码相同。 ';
comment on column HZ_ZJ_SB.ywlbm  is '业务类别中文名，必须';
comment on column HZ_ZJ_SB.mz  is '710新增字段,民族';
comment on column HZ_ZJ_SB.hjbllb  is '710新增字段,户籍补录类别';
comment on column HZ_ZJ_SB.swzxlb  is '新增字段，死亡注销类别';
comment on column HZ_ZJ_SB.xb  is '710新增字段,性别';
comment on column HZ_ZJ_SB.yhzgx  is '新增字段，与新户主关系';
comment on column HZ_ZJ_SB.qcdssxq  is '新增字段，迁出地省县';
comment on column HZ_ZJ_SB.bdfw  is '新增字段，变动范围';
comment on column HZ_ZJ_SB.csrq  is '新增字段，出生日期';
comment on column HZ_ZJ_SB.csdssxq  is '新增字段，出生地省县';
comment on column HZ_ZJ_SB.cxfldm  is '710新增字段,历史城乡代码';
comment on column HZ_ZJ_SB.hb  is '新增字段，户别';
comment on column HZ_ZJ_SB.csdjlb  is '新增字段，出生登记类别';
comment on column HZ_ZJ_SB.jgssxq  is '新增字段，籍贯省县';
comment on column HZ_ZJ_SB.hjsclb  is '710新增字段,户籍删除类别';
comment on column HZ_ZJ_SB.zzbdlb  is '新增字段，变动类别';
comment on column HZ_ZJ_SB.nbgxm  is '拟变更姓名';
comment on column HZ_ZJ_SB.cym  is '曾用名';
comment on column HZ_ZJ_SB.csyxzmbh  is '被申请人出生医学证明编号';
comment on column HZ_ZJ_SB.bsqrxb  is '被申请人性别（拟变更性别）';
comment on column HZ_ZJ_SB.bsqrmz  is '被申请人民族（拟变更民族）';
comment on column HZ_ZJ_SB.bsqrcsrq  is '被申请人出生日期（拟更正的出生日期）';
comment on column HZ_ZJ_SB.btkrgx  is '与被投靠人关系';
comment on column HZ_ZJ_SB.bsqrsjhm  is '被申请人手机号码';
comment on column HZ_ZJ_SB.zxyy  is '注销原因';
comment on column HZ_ZJ_SB.kdqqy_qrdqbm  is '跨地区迁移，迁入地区编码';
comment on column HZ_ZJ_SB.kdqqy_qrywid  is '跨地区迁移，迁入业务ID';
comment on column HZ_ZJ_SB.kdqqy_qrywlx  is '跨地区迁移，迁入业务类型：qrspyw 迁入审批业务';
comment on column HZ_ZJ_SB.kdqqy_qrfkzt  is '跨地区迁移，反馈状态：0 未反馈，1 已反馈';
comment on column HZ_ZJ_SB.kdqqy_qrfksj  is '跨地区迁移，反馈状态时间';
comment on column HZ_ZJ_SB.kdqqy_zqzbh  is '跨地区迁移，准迁证编号（迁入地填写）';
comment on column HZ_ZJ_SB.kdqqy_qrdqh  is '跨地区迁移，迁入地区划';
comment on column HZ_ZJ_SB.kdqqy_qyzbh  is '跨地区迁移，迁移证编号（本地迁出后回填）';
comment on column HZ_ZJ_SB.kdqqy_lscxfldm  is '跨地区迁移，历史程序编号（本地迁出后回填）';
comment on column HZ_ZJ_SB.kdqqy_qyldyy  is '跨地区迁移，迁移流动原因（迁入地填写）';
comment on column HZ_ZJ_SB.kdqqy_qclb  is '跨地区迁移，迁出类别（迁入地填写）';
comment on column HZ_ZJ_SB.sfzqr  is '是否主迁人（微信迁入审批，直接从省厅读取人口，必须填写此字段，0 否 1是）';
comment on column HZ_ZJ_SB.jhryxm  is '710新增字段,监护人一姓名';
comment on column HZ_ZJ_SB.fqxm  is '710新增字段,父亲姓名';
comment on column HZ_ZJ_SB.ywdm  is '新增字段，业务代码，常口业务代码（常口默认值）';
comment on column HZ_ZJ_SB.zqzbh  is '新增字段，准迁证编号';
comment on column HZ_ZJ_SB.qyldyy  is '新增字段，迁移（流动）原因';
comment on column HZ_ZJ_SB.jhryjhgx  is '新增字段，监护人一监护关系';
comment on column HZ_ZJ_SB.zylb  is '新增字段，职业类别';
comment on column HZ_ZJ_SB.sg  is '新增字段，身高';
comment on column HZ_ZJ_SB.sbrjtgx  is '新增字段，申报人家庭关系(与持证人关系)';
comment on column HZ_ZJ_SB.bdhhb  is '新增字段，变动后户别(入户默认和户主相同)';
comment on column HZ_ZJ_SB.whcd  is '新增字段，文化程度';
comment on column HZ_ZJ_SB.jndj_pdbz  is '新增字段，具有技能等级落户城镇';
comment on column HZ_ZJ_SB.ncjdzzyxbys_pdbz  is '新增字段，高等院校毕业落户城镇毕业';
comment on column HZ_ZJ_SB.ncjsbtcxy_pdbz  is '新增字段，退出现役落户城镇';
comment on column HZ_ZJ_SB.hlx  is '新增字段，户类型';
comment on column HZ_ZJ_SB.cszmbh  is '新增字段，出生证明编号';
comment on column HZ_ZJ_SB.zczjyhjzwnys_pdbz  is '新增字段，就业和居住五年落户城镇';
comment on column HZ_ZJ_SB.zy  is '新增字段，职业';
comment on column HZ_ZJ_SB.jjqx_pdbz  is '新增字段，举家迁徙落户城镇';
comment on column HZ_ZJ_SB.fwcs  is '新增字段，服务处所';
comment on column HZ_ZJ_SB.qyzbh  is '新增字段，证迁移编号';
comment on column HZ_ZJ_SB.nyzyrklhczyydm  is '新增字段，落户城镇原因';
comment on column HZ_ZJ_SB.zyjszc_pdbz  is '新增字段，具有专业职称落户城镇';
comment on column HZ_ZJ_SB.bdhjth  is '新增字段，变动后集体户（集体户为必填）';
comment on column HZ_ZJ_SB.qwdssxq  is '新增字段，迁往地省市县区';
comment on column HZ_ZJ_SB.qrqhb  is '新增字段，迁入前户别';
comment on column HZ_ZJ_SB.qrlb  is '新增字段，迁入类别';
comment on column HZ_ZJ_SB.dhhm  is '新增字段，电话号码';
comment on column HZ_ZJ_SB.jhrygmsfhm  is '新增字段，监护人一身份证';
comment on column HZ_ZJ_SB.jthzl  is '新增字段，集体户种类';
comment on column HZ_ZJ_SB.qclb  is '新增字段，迁出类别';
comment on column HZ_ZJ_SB.lhrsfz  is '落户人身份证';


update XT_SJPZB set displayname = '*籍贯省县' where pzlb = '10001' and displayname = '籍贯省县';
update XT_SJPZB set displayname = '*出生地省县' where pzlb = '10001' and displayname = '出生地省县';
insert into xt_xtkzcsb values('27','10027','常住人口登记表模板','0','0','[0-模板1][1-模板2]','1','I',to_char(sysdate,'yyyymmddhhmiss'));
INSERT INTO XT_XTGNCDB VALUES ('380000', '0','1','3','1','打印模板在线维护','3407','gl/dymbzxwh');
update xt_sjpzb t set vtype='Chinese' where dsname='jtcy' or t.fieldname='xm' or t.fieldname='cym';
insert  into XT_SJPZB  values('9041','10031','Hj_迁入审批子信息','0021','kdqqy_qcdqbm','跨地区迁移-迁出地区','','edit','','','011','','','1','','','','','','','','','','','','','','','=','kdqqy_qcdqbm','2','','I','20170630010101','');

alter table HJXX_CZRKJBXXB add dsgxsj varchar2(20);

--2018.09.20 刁杰
update XT_SJPZB set displayname = '*与持证人关系' where pzlb = '10024' and fieldname = 'sbrjtgx' and sjpzid = '6310';
-- 20180926 add 模板名称
alter table LODOP add lodopname varchar2(50);
-- Add comments to the columns 
comment on column LODOP.lodopname
  is '模板名称';

--2018.10.08	刁杰
update XT_SJPZB set displayname = '*变动后户别' where pzlb = '10015' and fieldname = 'zzbdhhb' and sjpzid = '384';
--2018.10.11	刁杰
update XT_SJPZB set displayname = '公民身份号码' where pzlb = '10020' and fieldname = 'gmsfhm' and sjpzid = '483';

--户籍补录业务，申报人信息丢失问题
-- Add/modify columns 
alter table HJSP_HJBLSPSQB add SBSJ CHAR(14);
alter table HJSP_HJBLSPSQB add SBRYXM NVARCHAR2(20);
alter table HJSP_HJBLSPSQB add SBRGMSFHM CHAR(18);
-- Add comments to the columns 
comment on column HJSP_HJBLSPSQB.SBSJ
  is '申报时间';
comment on column HJSP_HJBLSPSQB.SBRYXM
  is '申报人员姓名';
comment on column HJSP_HJBLSPSQB.SBRGMSFHM
  is '申报人公民身份号码';
  
--户籍删除业务，申报人丢失问题
  -- Add/modify columns 
alter table HJSP_HJSCSPSQB add SBSJ CHAR(14);
alter table HJSP_HJSCSPSQB add SBRYXM NVARCHAR2(20);
alter table HJSP_HJSCSPSQB add SBRGMSFHM CHAR(18);
-- Add comments to the columns 
comment on column HJSP_HJSCSPSQB.SBSJ
  is '申报时间';
comment on column HJSP_HJSCSPSQB.SBRYXM
  is '申报人员姓名';
comment on column HJSP_HJSCSPSQB.SBRGMSFHM
  is '申报人公民身份号码';
-- 2018.10.29 刁杰
update xt_xtgncdb set url = 'yw/dzgl/dzwh' where gncdid = '110001' and cdmc = '地址维护';
-- 2018.10.30 刁杰
update xt_xtgncdb set url = 'yw/dzgl/qhtz' where gncdid = '110004' and cdmc = '区划调整';
-- 2018.11.07 刁杰
insert into XT_XTGNCDB (GNCDID, CDCC, CDBZ, CDLX, ZDLB, CDMC, DQBM, URL)
values (390000, '0', '1', '3', '1', '重载缓存', '3407', 'gl/czhc');=======
  is '申报人公民身份号码';
  
 --系统配置新增
  -- Add/modify columns 
alter table XT_XTKZCSB add CDKZZ clob;
-- Add comments to the columns 
comment on column XT_XTKZCSB.CDKZZ
  is '超大控制值';

--新增配置
insert into xt_xtkzcsb (CSID, KZLB, KZMC, KZZ, MRZ, BZ, XGBZ, BDLX, BDSJ)
values (27, '10028', '跨地市迁出配置', '见CDKZZ字段', '0', '', '1', 'U', '20181112000000');
--手工操作，将/hz2004/src/conf/app.config.xml的JSON内容灌入此配置的cdkzz字段

--户籍补录处理 - 将SSHHID的值从HHID转换成HHNBID,并且为未处理 by刁杰
update HJSP_HJBLSPSQB a set a.sshhid = (select b.hhnbid from hjxx_hxxb b where b.jlbz = '1' and b.cxbz = '0' and a.sshhid = b.hhid) 
       where a.spjg='1' and a.lsbz='0';

--2018.12.27	刁杰	pzlb:10019新增 有效期限起始日期(yxqxqsrq)/有效期限截止日期(yxqxjzrq)/证件类别(zjlb)/签发机关(qfjg) 字段
insert into XT_SJPZB (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9061', '10019', 'Hj_户成员信息', '0063', 'yxqxqsrq', '有效期限起始日期', null, 'dateedit', null, null, '000000', '19', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '=', 'yxqxqsrq', '5', null, 'I', '20121205000000', null);

insert into XT_SJPZB (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9062', '10019', 'Hj_户成员信息', '0064', 'yxqxjzrq', '有效期限截止日期', null, 'dateedit', null, null, '000000', '19', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, '=', 'yxqxjzrq', '5', null, 'I', '20121205000000', null);

insert into XT_SJPZB (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9063', '10019', 'Hj_户成员信息', '0065', 'zjlb', '证件类别', null, 'codeedit', 'cs_zjlb', null, '000000', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'zjlb', '4', null, 'I', '20121205000000', null);

insert into XT_SJPZB (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9064', '10019', 'Hj_户成员信息', '0066', 'qfjg', '签发机关', null, 'codeedit', 'CS_QXDYQFJG', null, '000000', null, null, null, '011', '1', null, null, null, null, null, null, null, null, null, null, null, null, '=', 'qfjg', '4', null, 'I', '20121205000000', null);

--全项变更新增出身证明编号
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values (9061, '10036', 'Hj_全项变更', '0015', 'cszmbh', '出生证明编号', '', 'edit', '', '', '010', '100', '20', '1', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '=', 'cszmbh', '2', '', 'I', '20190221000001', '');
delete from PERSON_DY_SET;
-- 创建个人打印设置表    add by zjm  20190320
create table PERSON_DY_SET
(
  YHID   NUMBER(19) not null,
  DYYL_DYSZ   VARCHAR2(1) not null,
  TCDYSZ_DYSZ VARCHAR2(1) not null,
  DYZP_CBSZ   VARCHAR2(1) not null,
  JTH_SYKSZ VARCHAR2(1) not null, 
  HKBSY_HKBSZ   VARCHAR2(1) not null,
  HKBBM_HKBSZ VARCHAR2(1) not null,
  JTHFSHKSY_HKBSZ   VARCHAR2(1) not null,
  JTHFSHKY_HKBSZ VARCHAR2(1) not null,
  CSYY_HKBSZ   VARCHAR2(1) not null,
  DYZP_HJZMSZ VARCHAR2(1) not null,
  HCYXX_HJZMSZ   VARCHAR2(1) not null,
  BDYY_HJZMSZ VARCHAR2(1) not null,
  BDXX_HJZMSZ   VARCHAR2(1) not null,
  ZXRYXX_HJZMSZ VARCHAR2(1) not null,
  DYDW_HJZMSZ VARCHAR2(1) not null,
  DYHH_HJZMSZ   VARCHAR2(1) not null,
  DYHYZK_HJZMSZ VARCHAR2(1) not null,
  DYBYQK_HJZMSZ   VARCHAR2(1) not null,
  DYWHCD_HJZMSZ VARCHAR2(1) not null,
  YXTS	CHAR(4) not null,
  dyjmsfzsqb_lsbz VARCHAR2(1),
  sfzlqdxgnr_lsbz NVARCHAR2(300)
)
comment on column PERSON_DY_SET.YHID
  is '用户id';
comment on column PERSON_DY_SET.DYYL_DYSZ
  is '需要进行打印预览';
comment on column PERSON_DY_SET.TCDYSZ_DYSZ
  is '打印时弹出打印设置';
comment on column PERSON_DY_SET.DYZP_CBSZ
  is '打印照片';
comment on column PERSON_DY_SET.JTH_SYKSZ
  is '集体户';
comment on column PERSON_DY_SET.HKBSY_HKBSZ
  is '打印户口簿首页';
comment on column PERSON_DY_SET.HKBBM_HKBSZ
  is '打印户口簿背面';
comment on column PERSON_DY_SET.JTHFSHKSY_HKBSZ
  is '集体户方式户口首页';
comment on column PERSON_DY_SET.JTHFSHKY_HKBSZ
  is '集体户方式户口页';
comment on column PERSON_DY_SET.CSYY_HKBSZ
  is '打印出生原因';
comment on column PERSON_DY_SET.DYZP_HJZMSZ
  is '打印照片';
comment on column PERSON_DY_SET.HCYXX_HJZMSZ
  is '户成员信息';
comment on column PERSON_DY_SET.BDYY_HJZMSZ
  is '变动原因';
comment on column PERSON_DY_SET.BDXX_HJZMSZ
  is '变动信息';
comment on column PERSON_DY_SET.ZXRYXX_HJZMSZ
  is '注销人员信息';
comment on column PERSON_DY_SET.DYDW_HJZMSZ
  is '打印单位';
comment on column PERSON_DY_SET.DYHH_HJZMSZ
  is '打印户号';
comment on column PERSON_DY_SET.DYHYZK_HJZMSZ
  is '打印婚姻状况';
comment on column PERSON_DY_SET.DYBYQK_HJZMSZ
  is '打印兵役情况';
comment on column PERSON_DY_SET.DYWHCD_HJZMSZ
  is '打印文化程度';
comment on column PERSON_DY_SET.yxts
  is '有效天数';
comment on column PERSON_DY_SET.dyjmsfzsqb_lsbz
  is '打印居民身份证申请表';
comment on column PERSON_DY_SET.sfzlqdxgnr_lsbz
  is '身份证领取单相关内容';
-- 个人打印设置表插入所有用户默认数据，先清空表    add by zjm  20190321
insert into PERSON_DY_SET 
select yhid,
       '1' dyyl_dysz,
       '0' tcdysz_dysz,
       '1' dyzp_cbsz,
       '0' jth_syksz,
       '0' hkbsy_hkbsz,
       '0' hkbbm_hkbsz,
       '0' jthfshksy_hkbsz,
       '0' jthfshky_hkbsz,
       '0' csyy_hkbsz,
       '1' dyzp_hjzmsz,
       '1' hcyxx_hjzmsz,
       '1' bdyy_hjzmsz,
       '1' bdxx_hjzmsz,
       '1' zxryxx_hjzmsz,
       '1' dydw_hjzmsz,
       '0' dyhh_hjzmsz,
       '1' dyhyzk_hjzmsz,
       '0' dybyqk_hjzmsz,
       '0' dywhcd_hjzmsz,
       '30' yxts,
       '0' dyjmsfzsqb_lsbz,
       '请于10天后到8～9号窗口领取身份证。'  sfzlqdxgnr_lsbz
  from xt_yhxxb;
  INSERT INTO XT_XTGNCDB VALUES ('400000', '0','1','3','1','户籍打印维护','3407','gl/hjdywh');
  
-- 消息信息表  add by zjm 20190403 
drop table MESSAGEXXB 
create table MESSAGEXXB
(
  messageid  NUMBER(19) not null,
  fsryhid    NUMBER(19) not null,
  fsryhxm    NVARCHAR2(20),
  jssj       CHAR(14) not null,
  message    VARCHAR2(200) not null,
  jsryhid    NUMBER(19) not null,
  jsryhxm    NVARCHAR2(20),
  readflag   CHAR(1) not null,
  removeflag CHAR(1) not null
)
tablespace HZ2004_CZRK
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column MESSAGEXXB.messageid
  is '消息ID';
comment on column MESSAGEXXB.fsryhid
  is '发送人用户ID';
comment on column MESSAGEXXB.fsryhxm
  is '发送人用户登录名';
comment on column MESSAGEXXB.jssj
  is '接收时间';
comment on column MESSAGEXXB.message
  is '信息内容';
comment on column MESSAGEXXB.jsryhid
  is '接收人Id';
comment on column MESSAGEXXB.jsryhxm
  is '接收人姓名';
comment on column MESSAGEXXB.readflag
  is '是否已读  1已读 0未读';
comment on column MESSAGEXXB.removeflag
  is '是否删除  1已删除 0未删除';
alter table MESSAGEXXB
  add constraint PK_MESSAGEXXB primary key (MESSAGEID)
  using index 
  tablespace HZ2004_IDX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 256K
    next 1M
    minextents 1
    maxextents unlimited
  );
create sequence SID_MESSAGEXXB   --创建序列名称
increment by 1  --增长幅度
start with 1000000000000000001  --初始值
maxvalue 9999999999999999999;  --最大值
  --ZJYW_SLXXB 表查询慢  加了个索引   20190404
create index I_SLXX_NEW_RYNBID on ZJYW_SLXXB (RYNBID)
  nologging  local;
 -- 20190409 新增消息维护菜单
INSERT INTO XT_XTGNCDB VALUES ('400003', '0','1','3','1','消息维护','3407','gl/xxwh');
update xt_xtgncdb set url = 'yw/ywsl' where gncdid = '190005' and cdmc = '业务受理';
update xt_xtgncdb set url = 'yw/zwcj' where gncdid = '190006' and cdmc = '指纹采集';
update xt_xtgncdb set url = 'cx/hffcxxcx' where gncdid = '410000' and cdmc = '合肥房产信息查询';

update xt_xtgncdb set url = 'yw/sjgl/sjys' where gncdid = '160002' and cdmc = '市局验收';
update xt_xtgncdb set url = 'yw/sjgl/dssgfj' where gncdid = '160003' and cdmc = '地市手工分检';
INSERT INTO XT_XTGNCDB VALUES ('400004', '0','1','3','1','零散办证设置','3407','gl/lsbzsz');
INSERT INTO XT_XTGNCDB VALUES ('400005', '0','1','3','1','二代证设置','3407','gl/edzsz');
--单位打印设置
create table DW_DY_SET
(
  dwdm   char(9) not null,
  cbtzd NVARCHAR2(100), 
  kzlzrq   NVARCHAR2(100) ,
  mzlzrq   NVARCHAR2(100) ,
  pcslxdh  NVARCHAR2(100) ,
  dkqckh   NUMBER(5),
  mndk     VARCHAR2(1),
  ywlimit  NUMBER(5),
  xkjdk    VARCHAR2(1),
  pcsmc    NVARCHAR2(100) ,
  pcsyb    NUMBER(6),
  pcsdz    NVARCHAR2(100) ,
  pcsdh    NVARCHAR2(100) ,
  lxdh_ydbz NVARCHAR2(100) ,
  lzrq_ydbz NVARCHAR2(100) 
)
-- Add comments to the columns 
comment on column DW_DY_SET.dwdm
  is '单位代码';
comment on column DW_DY_SET.cbtzd
  is '重办通知单信息';
comment on column DW_DY_SET.kzlzrq
  is '快证领证日期';
comment on column DW_DY_SET.mzlzrq
  is '慢证领证日期';
comment on column DW_DY_SET.pcslxdh
  is '派出所联系电话';
comment on column DW_DY_SET.dkqckh
  is '读卡器串口号';
comment on column DW_DY_SET.mndk
  is '模拟读卡';
comment on column DW_DY_SET.ywlimit
  is '每次进行审核、签发的数据数量(40-1000)';
comment on column DW_DY_SET.xkjdk
  is '使用写卡机具读卡';
comment on column DW_DY_SET.pcsmc
  is '派出所名称-快证接收信息配置';
comment on column DW_DY_SET.pcsyb
  is '派出所邮编-快证接收信息配置';
comment on column DW_DY_SET.pcsdz
  is '派出所地址-快证接收信息配置';
comment on column DW_DY_SET.pcsdh
  is '派出所联系电话-快证接收信息配置';
comment on column DW_DY_SET.lxdh_ydbz
  is '联系电话-异地办证信息配置';
comment on column DW_DY_SET.lzrq_ydbz
  is '领证日期-异地办证信息配置';
  delete DW_DY_SET
  insert into DW_DY_SET 
  select dm,
  '请您重新办理身份证！',
  '七个工作日',
  '两个月',
  '',
  '1',
  '0',
  '80',
  '0',
  '',
  '',
  '',
  '',
  '',
  '两个月' 
  from xt_dwxxb where qybz='1';
drop table ECHARTSDATA
-- Create table
create table ECHARTSDATA
(
  qhdm         CHAR(6) not null,
  qhmc         NVARCHAR2(100),
  ssqhdm       CHAR(6),
  ssqhmc       NVARCHAR2(100),
  zrks         NUMBER,
  zhs          NUMBER,
  nxrk         NUMBER,
  nvrk         NUMBER,
  cslr         NUMBER,
  bllr         NUMBER,
  qrlr         NUMBER,
  swlc         NUMBER,
  qclc         NUMBER,
  sclc         NUMBER,
  nl_level1    NUMBER,
  nl_level2    NUMBER,
  nl_level3    NUMBER,
  nl_level4    NUMBER,
  nl_level5    NUMBER,
  lrxl_level1  NUMBER,
  lrxl_level2  NUMBER,
  lrxl_level3  NUMBER,
  lrxl_level4  NUMBER,
  lrxl_level5  NUMBER,
  lrxl_level6  NUMBER,
  lrxl_level7  NUMBER,
  lrxl_level8  NUMBER,
  lrxl_level9  NUMBER,
  lrxl_level10 NUMBER
)
tablespace HZ2004_CZRK
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column ECHARTSDATA.qhdm
  is '区划代码';
comment on column ECHARTSDATA.qhmc
  is '区划名称';
comment on column ECHARTSDATA.ssqhdm
  is '所属区划代码';
comment on column ECHARTSDATA.ssqhmc
  is '所属区划名称';
comment on column ECHARTSDATA.zrks
  is '总人口数';
comment on column ECHARTSDATA.zhs
  is '总户数';
comment on column ECHARTSDATA.nxrk
  is '男性人口数';
comment on column ECHARTSDATA.nvrk
  is '女性人口数';
comment on column ECHARTSDATA.cslr
  is '出生流入';
comment on column ECHARTSDATA.bllr
  is '补录流入';
comment on column ECHARTSDATA.qrlr
  is '迁入流入';
comment on column ECHARTSDATA.swlc
  is '死亡流出';
comment on column ECHARTSDATA.qclc
  is '迁出流出';
comment on column ECHARTSDATA.sclc
  is '删除流出';
comment on column ECHARTSDATA.nl_level1
  is '0~20岁人口数';
comment on column ECHARTSDATA.nl_level2
  is '21~40人口数';
comment on column ECHARTSDATA.nl_level3
  is '41~60人口数';
comment on column ECHARTSDATA.nl_level4
  is '61~80人口数';
comment on column ECHARTSDATA.nl_level5
  is '80以上人口数';
comment on column ECHARTSDATA.lrxl_level1
  is '文盲或半文盲';
comment on column ECHARTSDATA.lrxl_level2
  is '小学';
comment on column ECHARTSDATA.lrxl_level3
  is '初中';
comment on column ECHARTSDATA.lrxl_level4
  is '高中';
comment on column ECHARTSDATA.lrxl_level5
  is '技校';
comment on column ECHARTSDATA.lrxl_level6
  is '中专';
comment on column ECHARTSDATA.lrxl_level7
  is '大专';
comment on column ECHARTSDATA.lrxl_level8
  is '本科';
comment on column ECHARTSDATA.lrxl_level9
  is '研究生及以上';
comment on column ECHARTSDATA.lrxl_level10
  is '其他';
drop table SSXQQRSFFBB
-- Create table
create table SSXQQRSFFBB
(
  sffbbid NUMBER(19),
  qhdm    CHAR(6),
  qcdsdm  CHAR(6),
  qcdsmc  NVARCHAR2(100),
  count   NUMBER
)
tablespace HZ2004_CZRK
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column SSXQQRSFFBB.sffbbid
  is 'id';
comment on column SSXQQRSFFBB.qhdm
  is '区划代码';
comment on column SSXQQRSFFBB.qcdsdm
  is '迁出地省代码';
comment on column SSXQQRSFFBB.qcdsmc
  is '迁出地省名称';
comment on column SSXQQRSFFBB.count
  is '数目';
 create sequence SID_SSXQQRSFFBB   --创建序列名称
increment by 1  --增长幅度
start with 1000000000000000001  --初始值
maxvalue 9999999999999999999;  --最大值
-- 20190619 常住人口配置添加标志地址字段
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('6313', '20000', 'Qry_常住人口基本信息表', '3026', 'bzdz', '标志地址', null, 'edit', null, null, '000000', '100', 10, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'bzdz', '4', null, 'I', '20180403201010', 'null');

insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('6314', '20000', 'Qry_常住人口基本信息表', '3027', 'bzdzid', '标志地址ID', null, 'edit', 'null', null, '000000', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'bzdzid', '4', null, 'I', '20180403201010', null);
--20190619 指纹身份证分局审核菜单链接添加
update xt_xtgncdb set url = 'yw/fjgl/zwsfzfjsh' where gncdid = '150101' and cdmc = '指纹身份证分局审核';
update xt_xtgncdb set url = 'yw/sjgl/sjsh' where gncdid = '160001' and cdmc = '市局审核';
--20190627 增加直系亲属关系证明打印菜单
INSERT INTO XT_XTGNCDB VALUES ('140013', '1','1','1','1','直系亲属关系证明打印','3407','yw/bbdy/zxqsgxzmdy');
-- Add/modify columns 
alter table DW_DY_SET modify pcsyb char(6);
create sequence VoZxqsgx   --创建序列名称
increment by 1  --增长幅度
start with 00001  --初始值
maxvalue 100000;  --最大值
-- add by zjm 20190703 个人打印设置表增加迁移证编号和准迁证编号字段
-- Add/modify columns 
alter table PERSON_DY_SET add qyzbh char(8);
alter table PERSON_DY_SET add zqzbh char(8);
-- Add comments to the columns 
comment on column PERSON_DY_SET.qyzbh
  is '迁移证编号';
comment on column PERSON_DY_SET.zqzbh
  is '准迁证编号';
-- modify by zjm 20190705 原户籍打印维护 改名 个性化设置
update xt_xtgncdb set cdmc = '个性化设置' where gncdid = '400000' 
-- Add/modify columns 
alter table PERSON_DY_SET add dkqlx VARCHAR2(1) default 2;
-- Add comments to the columns 
comment on column PERSON_DY_SET.dkqlx
  is '读卡器类型';
-- add by zjm 20190705 PERSON_DY_SET 增加主键PK_PERSON_DY_SET
alter table PERSON_DY_SET
add constraint PK_PERSON_DY_SET primary key (YHID)
-- add by zjm 20190705 DW_DY_SET 增加主键Pk_DW_DY_SET
alter table DW_DY_SET
  add constraint Pk_DW_DY_SET primary key (DWDM);
--20190712中间表增加cssj,cszqfsj字段 用于跳转出生业务用
-- Add/modify columns 
alter table HZ_ZJ_BS add cszqfrq char(8);
alter table HZ_ZJ_BS add cssj char(6);
-- Add comments to the columns 
comment on column HZ_ZJ_BS.cszqfrq
  is '出生证签发日期';
comment on column HZ_ZJ_BS.cssj
  is '出生时间';
--20190712中间表增加swrq,swzmbhj字段 用于跳转死亡注销业务用  
-- Add/modify columns 
alter table HZ_ZJ_BS add swrq char(8);
alter table HZ_ZJ_BS add swzmbh varchar2(20);
-- Add comments to the columns 
comment on column HZ_ZJ_BS.swrq
  is '死亡日期';
comment on column HZ_ZJ_BS.swzmbh
  is '死亡证明编号';
-- Add/modify columns 
alter table HZ_ZJ_BS add jlx CHAR(12);
alter table HZ_ZJ_BS add mlph NVARCHAR2(20);
alter table HZ_ZJ_BS add jcwh CHAR(12);
alter table HZ_ZJ_BS add zjxy CHAR(2);
alter table HZ_ZJ_BS add hyzk CHAR(2);
alter table HZ_ZJ_BS add byzk CHAR(1);
alter table HZ_ZJ_BS add xx CHAR(1);
alter table HZ_ZJ_BS add qfjg NVARCHAR2(200);
alter table HZ_ZJ_BS add zjlb CHAR(1);
alter table HZ_ZJ_BS add yxqxqsrq CHAR(8);
alter table HZ_ZJ_BS add yxqxjzrq CHAR(8);
-- Add comments to the columns 
comment on column HZ_ZJ_BS.jlx
  is '201907新增录入字段，街路巷';
comment on column HZ_ZJ_BS.mlph
  is '201907新增录入字段，门楼牌号';
comment on column HZ_ZJ_BS.jcwh
  is '201907新增录入字段，村（居）委会';
comment on column HZ_ZJ_BS.zjxy
  is '宗教信仰';
comment on column HZ_ZJ_BS.hyzk
  is '婚姻状况';
comment on column HZ_ZJ_BS.byzk
  is '兵役状况';
comment on column HZ_ZJ_BS.xx
  is '血型';
comment on column HZ_ZJ_BS.qfjg
  is '签发机关';
comment on column HZ_ZJ_BS.zjlb
  is '证件类别';
comment on column HZ_ZJ_BS.yxqxqsrq
  is '有效期限起始日期';
comment on column HZ_ZJ_BS.yxqxjzrq
  is '有效期限截止日期';
 --个人打印设置表中新增字段印制年份 
 -- Add/modify columns 
alter table PERSON_DY_SET add yznf char(4);
-- Add comments to the columns 
comment on column PERSON_DY_SET.yznf
  is '印制年份';
--modify by zjm 20190723 迁入审批 登记时间djsj 改为可见
update xt_sjpzb set visibletype='011' where pzlb='10030' and fieldname='djsj';  
update xt_sjpzb set visibletype='011' ,displayname='申请时间'  where pzlb='10033' and fieldname='sqsj';
--add by zjm 20190725 变更审批和户籍删除审批数据配置增加sbsj字段
insert  into XT_SJPZB  values('5010','10028','Hj_变更审批信息','0028','sbsj','申报时间','','dateedit','','','01100','','','','','','','','','','','','','','','','','','=','sbsj','5','','I','20190725010101','');

create sequence SID_HZ_ZJ_SB   --创建序列名称
increment by 1  --增长幅度
start with 100000000000001  --初始值
maxvalue 999999999999999;  --最大值

insert into xt_xtkzcsb (CSID, KZLB, KZMC, KZZ, MRZ, BZ, XGBZ, BDLX, BDSJ)
values (29, '10029', '查看档案配置', '见备注', '0', 'http://10.124.3.229:9080/hz2004query/query_checksfz', '1', 'U', '20181112000000');

--add by zjm 20190903 中间表增加hzywid  spywid审批业务ID  hjdpcs户籍地派出所
-- Add/modify columns 
alter table HZ_ZJ_BS add hjywid NUMBER(19); 
alter table HZ_ZJ_BS add spywid NUMBER(19);
alter table HZ_ZJ_BS add hjdpcs char(9);
alter table HZ_ZJ_BS add blrsfz VARCHAR2(18);
-- Add comments to the columns 
comment on column HZ_ZJ_BS.hjywid
  is '户籍业务ID';
comment on column HZ_ZJ_BS.spywid
  is '审批业务ID';
comment on column HZ_ZJ_BS.hjdpcs
  is '户籍地派出所 ';
comment on column HZ_ZJ_BS.blrsfz
  is '办理人身份证';

-- 中间表序列号重新加 add by zjm 20190910
drop sequence SID_HZ_ZJ_SB;
create sequence SID_HZ_ZJ_SB   --创建序列名称
increment by 1  --增长幅度
start with 1  --初始值
maxvalue 999999;  --最大值

update xt_sjpzb set visibletype='010' where pzlb='10007' and fieldname='rylb';  
-- Add/modify columns 
alter table MESSAGEXXB add jsdw char(9);
-- Add comments to the columns 
comment on column MESSAGEXXB.jsdw
  is '发送单位时，存储单位代码';
alter table MESSAGEXXB modify jsryhid null;

-- add by zjm 20191008 当事人未登记户口查询
INSERT INTO XT_XTGNCDB VALUES ('210013', '1','1','2','1','未登记户口查询','3407','cx/hjywxx/wdjhkcx');
INSERT INTO XT_XTGNCDB VALUES ('210014', '1','1','2','1','分户查询','3407','cx/hjywxx/fhcx');
INSERT INTO XT_XTGNCDB VALUES ('210015', '1','1','2','1','公民是否同一人协助核查','3407','cx/hjywxx/gmsftyrxzhc');
--INSERT INTO XT_XTGNCDB VALUES ('320001', '0','1','3','1','公共url转发','3407','gl/ggUrlTrans');
update XT_XTGNCDB set gncdid='210016' , cdlx='2' , url='cx/hjywxx/zxqsgxzmdy' where cdmc='直系亲属关系证明打印';

-- 解锁和锁定原因,可以在系统参数中维护
--创建解锁锁定日志表 add by zjm 20191021
drop table jssdlog
create table JSSDLOGXXB
(
  jssdlogxxbid NUMBER(19) not null,
  czyid		NUMBER(19) not null,
  czsj      CHAR(14) not null,
  jssdyy    VARCHAR2(200) not null,
  ip        VARCHAR2(20) not null,
  dz        VARCHAR2(10) not null,
  ryid         NUMBER(19) not null,
  rynbid       NUMBER(19) not null,
  xm           NVARCHAR2(40) not null,
  gmsfhm       CHAR(18) not null
)

tablespace HZ2004_CZRK
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column JSSDLOGXXB.jssdlogxxbid
  is '解锁锁定日志表Id';
comment on column JSSDLOGXXB.czsj
  is '操作时间';
comment on column JSSDLOGXXB.czyid
  is '操作人';
comment on column JSSDLOGXXB.jssdyy
  is '解锁锁定原因';
comment on column JSSDLOGXXB.ip
  is 'IP';
comment on column JSSDLOGXXB.dz
  is '动作';
comment on column JSSDLOGXXB.ryid
  is '人员ID';
comment on column JSSDLOGXXB.rynbid
  is '人员内部ID';
comment on column JSSDLOGXXB.xm
  is '姓名';
comment on column JSSDLOGXXB.gmsfhm
  is '公民身份号码';
alter table JSSDLOGXXB
  add constraint PK_JSSDLOGXXB_ID primary key (JSSDLOGXXBID)
  using index 
  tablespace HZ2004_CZRK
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );  
-- 创建解锁锁定日志表  add by zjm 20191021
create sequence SID_JSSDLOGXXB   --创建序列名称
increment by 1  --增长幅度
start with 1  --初始值
maxvalue 999999;  --最大值
INSERT INTO XT_XTGNCDB VALUES ('291002', '0','1','2','1','解锁锁定历史查询','3407','cx/jssdlscx');

insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9069', '50003', '解锁锁定历史记录查询', '0001', 'xm', '姓名', null, 'edit', null, null, '111', '30', '8', '1', '5', null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'xm', '1', null, 'I', '20160601000000', 'Chinese');
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9070', '50003', '解锁锁定历史记录查询', '0002', 'gmsfhm', '公民身份号码', null, 'sfzedit', null, null, '111', '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '=', 'gmsfhm', '7', null, 'I', '20160601000000', 'Sfzh');
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9071', '50003', '解锁锁定历史记录查询', '0003', 'ip', 'IP', null, 'edit', null, null, '0111', null, '8', '1', '5', null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'ip', '1', null, 'I', '20160601000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9072', '50003', '解锁锁定历史记录查询', '0004', 'ryid', '人员ID', null, 'edit', null, null, '000000', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'ryid', '2', null, 'I', '20160601000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9073', '50003', '解锁锁定历史记录查询', '0005', 'rynbid', '人员内部ID', null, 'edit', null, null, '000000', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'rynbid', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9074', '50003', '解锁锁定历史记录查询', '0006', 'jssdlogxxbid', '解锁锁定日志表ID', null, 'edit', null, null, '000000', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'jssdlogxxbid', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9075', '50003', '解锁锁定历史记录查询', '0007', 'czyid', '操作员', null, 'codeedit', 'yhxxb', null, '0111', '19', '10', null, null, null, null, null, null, '0000000000000000000', 'yhzt', '1', null, null, null, null, null, null, '=', 'czyid', '4', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9076', '50003', '解锁锁定历史记录查询', '0008', 'czsj', '操作时间', null, 'dateedit', null, null, '111100', '20', '14', null, null, '1', null, null, null, '111111', null, null, null, null, null, null, null, null, '<', 'czsj', '5', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9077', '50003', '解锁锁定历史记录查询', '0009', 'dz', '操作类型', null, 'edit', null, null, '0111', '20', '8', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'dz', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9078', '50003', '解锁锁定历史记录查询', '0010', 'jssdyy', '解锁锁定原因', null, 'edit', null, null, '0111', '30', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'jssdyy', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9079', '50004', '不在线缴费原因', '1', 'bzxjfyy', '不在线缴费原因', null, 'codeedit', 'cs_bjfyy', null, '0111', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'qyldyy', '4', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9080', '50005', '非现金收款统计查询', '0001', 'xm', '姓名', null, 'edit', null, null, '111', '30', '8', '1', '5', null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'xm', '1', null, 'I', '20160601000000', 'Chinese');
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9081', '50005', '非现金收款统计查询', '0002', 'gmsfhm', '公民身份号码', null, 'sfzedit', null, null, '111', '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '=', 'gmsfhm', '7', null, 'I', '20160601000000', 'Sfzh');
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9082', '50005', '非现金收款统计查询', '0003', 'dylb', '打印类别', null, 'codeedit', 'cs_dylb', null, '0111', null, '8', '1', '5', null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'dylb', '1', null, 'I', '20160601000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9083', '50005', '非现金收款统计查询', '0004', 'sfxxbid', '收费信息表ID', null, 'edit', null, null, '000000', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'sfxxbid', '2', null, 'I', '20160601000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9084', '50005', '非现金收款统计查询', '0005', 'dwdm', '单位代码', null, 'codeedit', 'dwxxb', null, '000000', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'dwdm', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9085', '50005', '非现金收款统计查询', '0006', 'je', '金额', null, 'edit', null, null, '0111', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'je', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9086', '50005', '非现金收款统计查询', '0007', 'czyid', '操作员', null, 'codeedit', 'yhxxb', null, '0111', '19', '10', null, null, null, null, null, null, '0000000000000000000', 'yhzt', '1', null, null, null, null, null, null, '=', 'czyid', '4', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9087', '50005', '非现金收款统计查询', '0008', 'dysj', '打印时间', null, 'dateedit', null, null, '111100', '20', '14', null, null, '1', null, null, null, '111111', null, null, null, null, null, null, null, null, '<', 'dysj', '5', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9088', '50005', '非现金收款统计查询', '0009', 'sffs', '收费方式', null, 'codeedit', 'CS_SFFS', null, '0111', '20', '8', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'sffs', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9089', '50005', '非现金收款统计查询', '0010', 'bzxjfyy', '不在线缴费原因', null, 'codeedit', 'CS_BJFYY', null, '0111', '30', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'bzxjfyy', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9090', '50005', '非现金收款统计查询', '0011', 'jfFlag', '是否缴费', null, 'codeedit', 'cs_sfbz', null, '0111', '20', '8', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'jfFlag', '2', null, 'I', '20121205000000', null);

--系统参数表增加不在线缴费原因 add by zjm 20191022
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005275', '9999', '9039', '不在线缴费原因', '名称中文拼音', null, null, null, 'CS_BJFYY', '3', '18', '0', 'I', '20180502000002');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005276', '9039', '1', '打印错误重打', 'DYCWCD', null, null, null, null, '3', '18', '0', 'I', '20180502000002');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005277', '9039', '2', '卡纸', 'KZ', null, null, null, null, '3', '18', '0', 'I', '20180502000002');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005278', '9039', '3', '操作失误', 'CZSW', null, null, null, null, '3', '18', '0', 'I', '20180502000002');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005279', '9039', '4', '现金缴费', 'XJJF', null, null, null, null, '3', '18', '0', 'I', '20180502000002');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005280', '9039', '5', '其他', 'QT', null, null, null, null, '3', '18', '0', 'I', '20180502000002');
--系统参数表增加收费方式 add by zjm 20191030
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005271', '9999', '9040', '收费方式', '名称中文拼音', null, null, null, 'CS_SFFS', '3', '18', '0', 'I', '20180502000002');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005273', '9040', '0', '在线', 'JSRYZL', null, null, null, null, '3', '18', '0', 'I', '20180502000002');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005274', '9040', '1', '不在线', 'SDRYZL', null, null, null, null, '3', '18', '0', 'I', '20180502000002');

-- Add/modify columns 
alter table PERSON_DY_SET add hjsysf number default 0;
alter table PERSON_DY_SET add zqzsf number default 0;
alter table PERSON_DY_SET add qyzsf number default 0;
-- Add comments to the columns 
comment on column PERSON_DY_SET.hjsysf
  is '户籍首页收费';
comment on column PERSON_DY_SET.zqzsf
  is '准迁证收费';
comment on column PERSON_DY_SET.qyzsf
  is '迁移证收费';
--收费信息表
-- Create table
create table SFXXB
(
  sfxxbid NUMBER(19) not null,
  dylb    CHAR(2),
  dwdm    CHAR(9),
  dysj    CHAR(14),
  czyid   NUMBER(19),
  je      NUMBER,
  sffs    CHAR(1),
  gmsfhm  CHAR(18),
  xm      NVARCHAR2(20),
  bzxjfyy NVARCHAR2(200),
  jfflag  CHAR(1) default 0,
  qhdm    CHAR(1)
)
tablespace HZ2004_CZRK
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column SFXXB.sfxxbid
  is '收费信息表id';
comment on column SFXXB.dylb
  is '打印类别  打印类别  首页03 准迁证 07 迁移证 06';
comment on column SFXXB.dwdm
  is '单位代码';
comment on column SFXXB.dysj
  is '打印时间';
comment on column SFXXB.czyid
  is '操作人id';
comment on column SFXXB.je
  is '金额';
comment on column SFXXB.sffs
  is '收费方式  0 在线收费 1 不在线收费';
comment on column SFXXB.gmsfhm
  is '身份号码';
comment on column SFXXB.xm
  is '姓名';
comment on column SFXXB.bzxjfyy
  is '不在线缴费原因';
comment on column SFXXB.jfflag
  is '是否缴费标志  0 未收费  1 已收费';
comment on column SFXXB.qhdm
  is '区划代码';  
-- Create/Recreate primary, unique and foreign key constraints 
alter table SFXXB
  add constraint PK_SFXXB_ID primary key (SFXXBID)
  using index 
  tablespace HZ2004_CZRK
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create sequence SID_SFXXB   --创建序列名称
increment by 1  --增长幅度
start with 100000000000001  --初始值
maxvalue 999999999999999;  --最大值

  ----人口布控
-- Create table
create table HJYW_RKBKXXB
(
  ywid   varchar2(32),
  xm     varchar2(100),
  gmsfhm varchar2(20),
  bklx   varchar2(20),
  bklxmc varchar2(100),
  bktx   varchar2(200)
)
;
-- Add comments to the table 
comment on table HJYW_RKBKXXB
  is '户籍业务，人口布控信息表';
-- Add comments to the columns 
comment on column HJYW_RKBKXXB.ywid
  is 'KEY';
comment on column HJYW_RKBKXXB.xm
  is '姓名';
comment on column HJYW_RKBKXXB.gmsfhm
  is '身份证号码';
comment on column HJYW_RKBKXXB.bklx
  is '布控类型';
comment on column HJYW_RKBKXXB.bklxmc
  is '布控类型名称';
comment on column HJYW_RKBKXXB.bktx
  is '布控提醒';
-- Create/Recreate indexes 
create index HJYW_RKBKXXB_i1 on HJYW_RKBKXXB (gmsfhm);
-- Create/Recreate primary, unique and foreign key constraints 
alter table HJYW_RKBKXXB
  add constraint HJYW_RKBKXXB_PK primary key (YWID);

-- Add/modify columns 
alter table HJYW_RKBKXXB add bksj varchar2(20);
alter table HJYW_RKBKXXB add bkmjxm varchar2(20);
-- Add comments to the columns 
comment on column HJYW_RKBKXXB.bksj
  is '布控时间';
comment on column HJYW_RKBKXXB.bkmjxm
  is '布控民警姓名';

-- Create table
create table HJYW_RKBKXXB_GJ
(
  gjid     varchar2(32),
  xm       varchar2(100),
  gmsfhm   varchar2(20),
  bklx     varchar2(20),
  bklxmc   varchar2(100),
  bksj     varchar2(20),
  bjtx     varchar2(200),
  bkmjxm     varchar2(100),
  hjywlx   varchar2(20),
  hjywlxmc varchar2(100),
  hjywsj   varchar2(20),
  hjywmj   varchar2(20),
  hjywmjxm varchar2(100)
)
;
-- Add comments to the table 
comment on table HJYW_RKBKXXB_GJ
  is '人口布控报警信息表';
-- Add comments to the columns 
comment on column HJYW_RKBKXXB_GJ.gjid
  is 'KEY';
comment on column HJYW_RKBKXXB_GJ.xm
  is '姓名';
comment on column HJYW_RKBKXXB_GJ.gmsfhm
  is '身份证';
comment on column HJYW_RKBKXXB_GJ.bklx
  is '布控类型';
comment on column HJYW_RKBKXXB_GJ.bklxmc
  is '布控类型名称';
comment on column HJYW_RKBKXXB_GJ.bksj
  is '布控时间';
comment on column HJYW_RKBKXXB_GJ.bjtx
  is '布控提醒';
comment on column HJYW_RKBKXXB_GJ.hjywlx
  is '户籍业务类型';
comment on column HJYW_RKBKXXB_GJ.hjywlxmc
  is '户籍业务名称';
comment on column HJYW_RKBKXXB_GJ.hjywsj
  is '户籍业务时间';
comment on column HJYW_RKBKXXB_GJ.hjywmj
  is '户籍业务民警';
comment on column HJYW_RKBKXXB_GJ.hjywmjxm
  is '户籍业务民警姓名';
-- Create/Recreate indexes 
create index RKBKXXB_i1 on HJYW_RKBKXXB_GJ (gmsfhm);
create index RKBKXXB_i2 on HJYW_RKBKXXB_GJ (bklx);
create index RKBKXXB_i3 on HJYW_RKBKXXB_GJ (hjywlx);
create index RKBKXXB_i4 on HJYW_RKBKXXB_GJ (hjywsj);
-- Create/Recreate primary, unique and foreign key constraints 
alter table HJYW_RKBKXXB_GJ
  add constraint RKBKXXB_GJPK primary key (GJID);
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values (14, '9999', '9037', '布控类型', '名称中文拼音', null, null, null, 'CS_BKLX', '1', '8', '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values (30, '9037', '0', '涉恐', 'XK', null, null, null, null, null, null, '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values (31, '9037', '1', '精神病人', 'JSBR', null, null, null, null, null, null, '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values (32, '9037', '2', '杀人犯', 'SRF', null, null, null, null, null, null, '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values (33, '9037', '3', '贪污犯', 'TWF', null, null, null, null, null, null, '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values (34, '9037', '4', '拐卖儿童', 'GMRT', null, null, null, null, null, null, '0', 'I', '20191023010102');
-- Create/Recreate indexes 
create index HJYW_RKBKXXB_I2 on HJYW_RKBKXXB (bksj);

--add by zjm 20191029 中间表添加微信唯一标识字段，用来做WXPT.HZPT_RECEIVESTATE表的主键
-- Add/modify columns 
alter table HZ_ZJ_BS add wx_code varchar2(500);
-- Add comments to the columns 
comment on column HZ_ZJ_BS.wx_code
  is '微信唯一标识（航信）';
INSERT INTO XT_XTGNCDB VALUES ('291003', '0','1','2','1','非现金收款统计','3407','cx/fxjsktj');
--控制表增加控制类型用来控制是否打印户口簿收费 add by zjm 20191030
insert into xt_xtkzcsb (CSID, KZLB, KZMC, KZZ, MRZ, BZ, XGBZ, BDLX, BDSJ, CDKZZ)
values ('30', '10030', '打印户口簿是否收费', '1', '0', '1收费，0不收费', '1', 'I', '20180822033407', null);
--非现金收款记录统计查询条件
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9091', '50006', '非现金收款统计查询', '0006', 'sffs', '收费方式', null, 'codeedit', 'CS_SFFS', null, '111100', '20', '8', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'sffs', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9092', '50006', '非现金收款统计查询', '0005', 'dwdm', '单位代码', null, 'codeedit', 'dwxxb', null, '111100', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'dwdm', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9093', '50006', '非现金收款统计查询', '0008', 'dysj', '打印时间', null, 'dateedit', null, null, '111100', '20', '14', null, null, '1', null, null, null, '111111', null, null, null, null, null, null, null, null, '<', 'dysj', '5', null, 'I', '20121205000000', null);

-- add by zjm 20191105 增加是否更新wx_code控制
insert into xt_xtkzcsb (CSID, KZLB, KZMC, KZZ, MRZ, BZ, XGBZ, BDLX, BDSJ, CDKZZ)
values ('31', '10031', '是否更新wx_code', '0', '0', '0不更新，1更新', '1', 'U', '20181112000000', null);
-- add by zjm 20191106 常口底表文字说明
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005272', '9999', '9038', '常口底表文字说明', '名称中文拼音', null, null, null, 'CS_CKDBWZSM', '3', '18', '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('36', '9038', '1', '出生登记已办结', 'CSDJYBJ', null, null, null, null, null, null, '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('45', '9038', '10', '户籍补录已办结', 'HJBLYBJ', null, null, null, null, null, null, '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('46', '9038', '11', '全户变更已办结', 'QHBGYBJ', null, null, null, null, null, null, '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('37', '9038', '2', '迁出注销已办结', 'QCZXYBJ', null, null, null, null, null, null, '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('38', '9038', '3', '迁入审批登记已办结', 'QRSPDJYBJ', null, null, null, null, null, null, '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('39', '9038', '4', '迁入业务已办结', 'QRYWYBJ', null, null, null, null, null, null, '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('40', '9038', '5', '户籍删除已办结', 'HJSCYBJ', null, null, null, null, null, null, '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('41', '9038', '6', '变更更正已办结', 'BGGZYBJ', null, null, null, null, null, null, '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('42', '9038', '7', '辅项变更已办结', 'FXBGYBJ', null, null, null, null, null, null, '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('43', '9038', '8', '死亡注销已办结', 'SWZXYBJ', null, null, null, null, null, null, '0', 'I', '20191023010102');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('44', '9038', '9', '住址变动已办结', 'ZZBDYBJ', null, null, null, null, null, null, '0', 'I', '20191023010102');
--add by zjm 20191115 增加公共转发核验功能编号，方便日志
insert into xt_xtgnb (GNID, GNBH, GNMC, GNLX, QYBZ, DQBM)
values ('3407000002000000003', 'G0001', '准迁证核验公共url转用', '1', '1', '3407');
insert into xt_xtgnb (GNID, GNBH, GNMC, GNLX, QYBZ, DQBM)
values ('3407000002000000004', 'G0002', '迁移证核验公共url转用', '1', '1', '3407');
insert into xt_xtgnb (GNID, GNBH, GNMC, GNLX, QYBZ, DQBM)
values ('3407000002000000005', 'G0003', '出生证核验公共url转用', '1', '1', '3407');
insert into xt_xtgnb (GNID, GNBH, GNMC, GNLX, QYBZ, DQBM)
values ('3407000002000000006', 'G0004', '重人重号核验公共url转用', '1', '1', '3407');
insert into xt_xtgnb (GNID, GNBH, GNMC, GNLX, QYBZ, DQBM)
values ('3407000002000000007', 'G0005', '获取照片公共url转用', '1', '1', '3407');
--add by zjm 120191115 
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005281', '1070', '15', '当事人未登记户口的证明出具', 'DSRWDJHKZM', null, null, null, null, null, null, '0', 'I', '20190917000003');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005282', '1070', '16', '分户证明', 'FHZM', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005283', '1070', '17', '户口登记项目内容变更更正证明', 'XMNRBGGZZM', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005284', '1070', '18', '香港、澳门、台湾定居注销户口证明', 'GATZXHKZM', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005285', '1070', '19', '国外定居注销户口证明', 'GWDJHKZXZM', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005286', '1070', '20', '公民是否同一人的协助核查证明', 'SFTYRHCZM', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005287', '1070', '21', '迁出注销证明打印', 'QCZXZMDY', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005288', '1070', '22', '直系亲属关系证明打印', 'ZXQSGXZMDY', null, null, null, null, null, null, '0', 'I', '20180502000002');

update xt_sjpzb set displayfieldlength='50' where pzlb = '50005' and fieldname='dysj';

insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9094', '20004', 'Qry_迁出注销信息表', '0000', 'sbrjtgx', '与持证人关系', null, 'codeedit', 'cs_jtgx', null, '011101', '2', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'sbrjtgx', '4', null, null, '20121205000000', null);

--系统日志数据配置新增
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9095', '50007', '系统日志记录查询', '0001', 'czkssj', '操作开始时间', null, 'dateedit', null, null, '111', '70', '50', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '=', 'czkssj', '1', null, 'I', '20160601000000', null);

insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9096', '50007', '系统日志记录查询', '0002', 'czjssj', '操作结束时间', null, 'dateedit', null, null, '111', '70', '50', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'czjssj', '7', null, 'I', '20160601000000', null);

insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9097', '50007', '系统日志记录查询', '0003', 'rzlx', '日志类型', null, 'edit', null, null, '000000', null, '8', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'rzlx', '1', null, 'I', '20160601000000', null);

insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9098', '50007', '系统日志记录查询', '0004', 'rznr', '日志内容', null, 'edit', null, null, '0111', '200', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'rznr', '2', null, 'I', '20160601000000', null);

insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9099', '50007', '系统日志记录查询', '0005', 'ywbz', '业务标志', null, 'edit', null, null, '0111', '20', '8', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'ywbz', '2', null, 'I', '20121205000000', null);

insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9100', '50007', '系统日志记录查询', '0006', 'organization_id', '单位名称', null, 'codeedit', 'dwxxb', null, '111', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'organization_id', '2', null, 'I', '20121205000000', null);

insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9101', '50007', '系统日志记录查询', '0007', 'operate_result', '操作结果', null, 'edit', null, null, '000000', null, '8', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'operate_result', '4', null, 'I', '20121205000000', null);

insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9102', '50007', '系统日志记录查询', '0008', 'resource_name', '日志对象名称', null, 'edit', null, null, '111', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'resource_name', '5', null, 'I', '20121205000000', null);

insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9103', '50007', '系统日志记录查询', '0009', 'czyid', '操作员姓名', null, 'codeedit', 'yhxxb', null, '111', '40', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'czyid', '2', null, 'I', '20121205000000', null);

insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9104', '50007', '系统日志记录查询', '0010', 'xtrzid', '日志表ID', null, 'edit', null, null, '000000', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'dz', '2', null, 'I', '20121205000000', null);

--add by zjm 20200107 增加全程网办常表打印设置类别
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005292', '1070', '23', '全程网办常表打印', 'QCWBCBDY', null, null, null, null, null, null, '0', '1', '20180502000002');
--add by zjm 20200107 邮件单位设置 
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005291', '9041', '2', '芜湖市公安局', 'DWMC', null, null, null, null, null, null, '0', '1', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005290', '9041', '1', '3013165', 'DWID', null, null, null, null, null, null, '0', '1', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005289', '9999', '9041', '全程网办办结单位信息设置', 'QCWBBJDWXXSZ', null, null, null, null, null, null, '0', '1', '20180502000002');
-- add by zjm 20200108 增加是否插入邮政揽件表控制
insert into xt_xtkzcsb (CSID, KZLB, KZMC, KZZ, MRZ, BZ, XGBZ, BDLX, BDSJ, CDKZZ)
values ('32', '10032', '是否插入邮政揽件表', '0', '0', '0不插入，1插入', '1', 'U', '20181112000000', null);
-- add by zjm 20200113 非在线收费方式字典中，去除其他选择项
delete XT_XTCSB where cslb='9039' and dm='5';

update xt_sjpzb set displayfieldlength='50' where pzlb = '10010' and fieldname='zxsj';
update xt_sjpzb set displayfieldlength='50' where pzlb = '10010' and fieldname='slsj';
update  XT_XTCSB  set mc='34020010290000000000' where cslb='9041' and dm='1';

--add by zjm 20200214 处理标志字典增加新子类  已删除
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005293', '2015', '2', '已删除', 'YSC', null, null, null, null, null, null, '0', '1', '20180502000002');
--add by zjm 20200225 业务处理中间表查询配置表脚本
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9105', '50008', '户政业务查询', '0001', 'cjsj1', '创建时间起', null, 'dateedit', null, null, '111', '70', '50', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '=', 'cjsj1', '1', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9106', '50008', '户政业务查询', '0002', 'cjsj2', '创建时间止', null, 'dateedit', null, null, '111', '70', '50', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'cjsj2', '1', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9107', '50008', '户政业务查询', '0003', 'clbs', '状态', null, 'codeedit', 'cs_clbz', null, '111100', '20', '8', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'clbs', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9108', '50008', '户政业务查询', '0004', 'sqrxm', '申报人', null, 'edit', null, null, '111', '30', '8', '1', '5', null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'sqrxm', '1', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9109', '50008', '户政业务查询', '0005', 'bsqrxm', '被申报人', null, 'edit', null, null, '111', '30', '8', '1', '5', null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'bsqrxm', '1', null, 'I', '20121205000000', null);

update xt_sjpzb set readOnly = null where pzlb=10036 and fieldname = 'yxqxjzrq';
update xt_sjpzb set readOnly = null where pzlb=10035 and fieldname = 'yxqxjzrq';

--add by zjm 20200417 系统控制表中新增土地标识字典
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005296', '5025', '1', '公共户', 'GGH', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005294', '9999', '5025', '土地标识', '名称中文拼音', null, null, null, 'CS_TDBS', '1', '8', '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005295', '5025', '2', '集体土地', 'JTTD', null, null, null, null, null, null, '0', 'I', '20180502000002');

--add by zjm 20200420 集体土地历史表
create table JTTDLSB
(
  jttdlsbid NUMBER(19) not null,
  hhnbid    NUMBER(19) not null,
  hhid      NUMBER(19) not null,
  hh        CHAR(9) not null,
  slsj      CHAR(14),
  sldw      CHAR(9),
  slrid     NUMBER(19),
  bgq       CHAR(1),
  bgh       CHAR(1)
)
tablespace HZ2004_CZRK
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column JTTDLSB.jttdlsbid
  is '集体土地历史表Id';
comment on column JTTDLSB.hhnbid
  is '户号内部id';
comment on column JTTDLSB.hhid
  is '户号id';
comment on column JTTDLSB.hh
  is '户号';
comment on column JTTDLSB.slsj
  is '受理时间';
comment on column JTTDLSB.sldw
  is '受理单位';
comment on column JTTDLSB.slrid
  is '受理人';
comment on column JTTDLSB.bgq
  is '变更前值';
comment on column JTTDLSB.bgh
  is '变更后值';

-- Add by zjm 20200414 集体土地标志 1 有效 0无效
alter table HJXX_HXXB add jttdbz CHAR(1);
comment on column HJXX_HXXB.jttdbz
  is '20200414 集体土地标志 1 公共户 2集体土地';
alter table HJXX_HXXB add jttdlsbid NUMBER(19);
comment on column HJXX_HXXB.jttdlsbid
  is '集体土地历史表Id';  
-- add by zjm  20200421 集体土地标识历史表序列
create sequence SID_JTTDLSB   --创建序列名称
increment by 1  --增长幅度
start with 1000000000000000001  --初始值
maxvalue 9999999999999999999;  --最大值
-- add by zjm 20200422 集体土地配置
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9120', '5009', 'Qry_集体土地户信息表', '0015', 'jttdbz', '集体土地标志', null, 'codeedit', 'cs_tdbs', null, '111100', '1', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'jttdbz', '4', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9110', '5009', 'Qry_集体土地户信息表', '0001', 'hh', '户号', null, 'edit', null, null, '111100', '9', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'HJXX_HXXB.hh', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9111', '5009', 'Qry_集体土地户信息表', '0002', 'hlx', '户类型', null, 'codeedit', 'cs_hlx', null, '111101', '1', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'HJXX_HXXB.hlx', '4', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9112', '5009', 'Qry_集体土地户信息表', '0003', 'hhzt', '户号状态', null, 'codeedit', 'cs_hhzt', null, '010101', '1', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'hhzt', '4', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9113', '5009', 'Qry_集体土地户信息表', '0004', 'ssxq', '省市区县(区)', null, 'codeedit', 'xzqhb', null, '010101', '6', '12', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'ssxq', '4', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9114', '5009', 'Qry_集体土地户信息表', '0005', 'pcs', '派出所', null, 'codeedit', 'dwxxb', null, '011101', '9', '12', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'pcs', '4', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9115', '5009', 'Qry_集体土地户信息表', '0007', 'xzjd', '乡镇街道', null, 'codeedit', 'xzjdxxb', null, '011101', '20', '12', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'xzjd', '4', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9116', '5009', 'Qry_集体土地户信息表', '0008', 'jcwh', '居委会', null, 'codeedit', 'jwhxxb', null, '111101', '20', '12', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'jcwh', '4', null, 'I', '20160920000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9117', '5009', 'Qry_集体土地户信息表', '0009', 'jlx', '街路巷', null, 'codeedit', 'jlxxxb', null, '111101', '20', '12', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'jlx', '4', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9118', '5009', 'Qry_集体土地户信息表', '0020', 'mlph', '门楼牌号', null, 'edit', null, null, '011100', '10', '10', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'mlph', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9119', '5009', 'Qry_集体土地户信息表', '0021', 'mlxz', '门楼详址', null, 'edit', null, null, '011100', '100', '12', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'mlxz', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9121', '5009', 'Qry_集体土地户信息表', '0012', 'hhid', '户号ID', null, 'edit', null, null, '000000', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'hhid', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9122', '5009', 'Qry_集体土地户信息表', '0013', 'hhnbid', '户号内部ID', null, 'edit', null, null, '000000', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'hhnbid', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9123', '5009', 'Qry_集体土地户信息表', '0014', 'mlpnbid', '门（楼）牌内部ID', null, 'edit', null, null, '000000', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'mlpnbid', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9124', '5009', 'Qry_集体土地户信息表', '0016', 'slrid', '受理人姓名', null, 'codeedit', 'yhxxb', null, '111101', '19', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'slrid', '4', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9125', '5009', 'Qry_集体土地户信息表', '0017', 'slsj', '受理时间', null, 'dateedit', null, null, '1111', '20', '14', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '<', 'slsj', '5', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9126', '5009', 'Qry_集体土地户信息表', '0018', 'sldw', '受理单位', null, 'codeedit', 'dwxxb', null, '111101', '9', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'sldw', '4', null, 'I', '20121205000000', null);

--add by zjm 20200423 区域冻结控制
insert into xt_xtkzcsb (CSID, KZLB, KZMC, KZZ, MRZ, BZ, XGBZ, BDLX, BDSJ, CDKZZ)
values ('33', '10033', '冻结控制', '0', '0', '0未冻结，1冻结只提示，2，冻结不予办理', '1', 'U', '20181112000000', null);
insert into xt_xtkzcsb (CSID, KZLB, KZMC, KZZ, MRZ, BZ, XGBZ, BDLX, BDSJ, CDKZZ)
values ('34', '10034', '集体土地控制', '0', '0', '0其他市不管，1集体户提示', '1', 'U', '20181112000000', null);
insert into xt_xtkzcsb (CSID, KZLB, KZMC, KZZ, MRZ, BZ, XGBZ, BDLX, BDSJ, CDKZZ)
values ('35', '10035', '人口信息查询加水印', '0', '0', '0其他市不管，1显示水印', '1', 'U', '20181112000000', null);

--add by zjm 20200423 区域冻结控制配置
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9143', '5010', 'Qry_区域冻结人口基本信息表', '0009', 'hhnbid', '户号内部ID', null, 'edit', null, null, '000000', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'hhnbid', '2', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9144', '5010', 'Qry_区域冻结人口基本信息表', '0010', 'yhzgx', '与户主关系', null, 'codeedit', 'cs_jtgx', null, '011101', '2', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'yhzgx', '4', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9128', '5010', 'Qry_区域冻结人口基本信息表', '0002', 'gmsfhm', '公民身份号码', null, 'edit', null, null, '111100', '18', '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '=', 'gmsfhm', '7', null, 'I', '20180403201010', 'Sfzh');
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9129', '5010', 'Qry_区域冻结人口基本信息表', '0003', 'hh', '户号', null, 'edit', null, null, '11110', '9', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'hh', '2', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9130', '5010', 'Qry_区域冻结人口基本信息表', '0005', 'jlx', '街路巷', null, 'codeedit', 'jlxxxb', null, '111101', '20', '10', null, null, null, null, null, null, null, 'qybz', '0', null, null, null, null, null, null, '=', 'jlx', '4', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9131', '5010', 'Qry_区域冻结人口基本信息表', '0006', 'jcwh', '居委会', null, 'codeedit', 'jwhxxb', null, '111101', '20', '12', null, null, null, null, null, null, null, 'qybz', '0', null, null, null, null, null, null, '=', 'jcwh', '4', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9132', '5010', 'Qry_区域冻结人口基本信息表', '0008', 'djzt', '冻结状态', null, 'codeedit', 'cs_djzt', null, '111100', '1', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'jttdbz', '4', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9133', '5010', 'Qry_区域冻结人口基本信息表', '0004', 'ssxq', '省市县（区）', null, 'codeedit', 'xzqhb', null, '010101', '6', '14', null, null, null, null, null, null, '00,00,00', 'qybz', '0', null, null, null, null, null, null, '=', 'ssxq', '4', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9134', '5010', 'Qry_区域冻结人口基本信息表', '0007', 'rynbid', '人员内部ID', null, 'edit', null, null, '000000', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'rynbid', '2', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9127', '5010', 'Qry_区域冻结人口基本信息表', '0001', 'xm', '姓名', null, 'edit', null, null, '011100', '30', '8', '1', '5', null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'xm', '1', null, 'I', '20180403201010', 'Chinese');

--add by zjm 20200423 区域冻结字典
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005297', '9999', '5026', '冻结状态', '名称中文拼音', null, null, null, 'CS_DJZT', '1', '8', '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005298', '5026', '1', '已冻结', 'YDJ', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005299', '5026', '2', '未冻结', 'WDJ', null, null, null, null, null, null, '0', 'I', '20180502000002');

--add by zjm 冻结状态字段  20200423
alter table HJXX_CZRKJBXXB add djzt CHAR(1);
-- Add comments to the columns 
comment on column HJXX_CZRKJBXXB.djzt
  is '冻结状态';
-- add by zjm 20200424 居委会增加 会章照片ID 签名照片ID 字段
alter table XT_JWHXXB add hzzpid number(19);
alter table XT_JWHXXB add qmzpid number(19);
alter table XT_JWHXXB add czrid NUMBER(19);
-- Add comments to the columns 
comment on column XT_JWHXXB.hzzpid
  is '会章照片ID';
comment on column XT_JWHXXB.qmzpid
  is '签名照片ID';
comment on column XT_JWHXXB.czrid
  is '操作人ID';  
-- add by zjm  20200427 集体土地标识历史表序列
create sequence SID_HJXX_JWHZPLSB   --创建序列名称
increment by 1  --增长幅度
start with 1000000000000000001  --初始值
maxvalue 9999999999999999999;  --最大值
--add by zjm 居委会照片表
create table HJXX_JWHZPLSB
(
  jwhzpid NUMBER(19) not null,
  zp      LONG RAW,
  czrid   NUMBER(19),
  czsj    CHAR(14),
  zptype  CHAR(1) not null,
  jwhdm   VARCHAR2(15)
)
tablespace HZ2004_CZRK
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column HJXX_JWHZPLSB.jwhzpid
  is '居委会照片id';
comment on column HJXX_JWHZPLSB.zp
  is '照片流';
comment on column HJXX_JWHZPLSB.czrid
  is '操作人';
comment on column HJXX_JWHZPLSB.czsj
  is '操作时间';
comment on column HJXX_JWHZPLSB.zptype
  is '照片类型1会章，2签名';
comment on column HJXX_JWHZPLSB.jwhdm
  is '居委会代码';
  --add by zjm 20200427 常口表的冻结状态 增加索引
create index I_NEW_CZRK_DJZT on HJXX_CZRKJBXXB (DJZT)
  tablespace HZ2004_CZRK
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
--add by zjm 20200427 居委会照片配置
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9135', '5011', 'Qry_居委会照片维护查询', '0001', 'dwdm', '单位名称', null, 'codeedit', 'dwxxb', null, '111101', '9', '12', null, null, null, null, null, null, null, 'dwjb', '1', null, null, null, null, null, null, '=', 'dwdm', '4', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9136', '5011', 'Qry_居委会照片维护查询', '0002', 'xzjddm', '乡镇（街道）', null, 'codeedit', 'xzjdxxb', null, '010101', '20', '12', null, null, null, null, null, null, null, 'qybz', '0', null, null, null, null, null, null, '=', 'xzjddm', '4', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9137', '5011', 'Qry_居委会照片维护查询', '0003', 'dm', '居委会名称', null, 'codeedit', 'jwhxxb', null, '111101', '20', '12', null, null, null, null, null, null, null, 'qybz', '0', null, null, null, null, null, null, '=', 'dm', '4', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9138', '5011', 'Qry_居委会照片维护查询', '0005', 'czrid', '受理人姓名', null, 'codeedit', 'yhxxb', null, '111101', '19', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'czrid', '4', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9139', '5011', 'Qry_居委会照片维护查询', '0006', 'bdsj', '受理时间', null, 'dateedit', null, null, '1111', '20', '14', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '<', 'bdsj', '5', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9140', '5011', 'Qry_居委会照片维护查询', '0007', 'hzzpid', '会章照片ID', null, 'edit', null, null, '000000', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'hzzpid', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9141', '5011', 'Qry_居委会照片维护查询', '0008', 'qmzpid', '签名照片ID', null, 'edit', null, null, '000000', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'qmzpid', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9142', '5011', 'Qry_居委会照片维护查询', '0004', 'dm', '居委会代码', null, 'edit', null, null, '111101', '20', '12', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'dm', '4', null, 'I', '20180403201010', null);
--add by zjm 20200428 菜单表增加集体土地维护、居委会会章和签名维护、区域冻结维护三个菜单
insert into xt_xtgncdb (GNCDID, CDCC, CDBZ, CDLX, ZDLB, CDMC, DQBM, URL)
values ('400009', '0', '1', '3', '1', '集体土地维护', '3407', 'gl/jttdwh');
insert into xt_xtgncdb (GNCDID, CDCC, CDBZ, CDLX, ZDLB, CDMC, DQBM, URL)
values ('410001', '0', '1', '3', '1', '居委会会章和签名维护', '3407', 'gl/jwhhzhqmwh');
insert into xt_xtgncdb (GNCDID, CDCC, CDBZ, CDLX, ZDLB, CDMC, DQBM, URL)
values ('410002', '0', '1', '3', '1', '区域冻结维护', '3407', 'gl/qydjwh');
--add by zjm 20200519 
alter table HZ_ZJ_BS add pj CHAR(1);
comment on column HZ_ZJ_BS.pj
  is '评价1非常满意2满意3基本满意4不满意';
--add by zjm 20200519 评价器启用控制
insert into xt_xtkzcsb (CSID, KZLB, KZMC, KZZ, MRZ, BZ, XGBZ, BDLX, BDSJ, CDKZZ)
values ('36', '10036', '评价器控制标志', '0', '0', '0不启动，1启动', '1', 'U', '20181112000000', null);
--add by 20200520  50008 中间表配置查询增加评价结果选项
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9145', '50008', '户政业务查询', '0006', 'pj', '评价结果', null, 'codeedit', 'cs_pj', null, '111100', '20', '8', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'pj', '2', null, 'I', '20121205000000', null);
--add by zjm 20200520 增加评价结果字典
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005300', '9999', '5027', '评价结果', '名称中文拼音', null, null, null, 'CS_PJ', '1', '8', '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005301', '5027', '1', '非常满意', 'FCMY', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005302', '5027', '2', '满意', 'MY', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005303', '5027', '3', '基本满意', 'JBMY', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005304', '5027', '4', '不满意', 'BMY', null, null, null, null, null, null, '0', 'I', '20180502000002');
--add by zjm 20200521 区域冻结配置增加派出所
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9146', '5010', 'Qry_区域冻结人口基本信息表', '0007', 'pcs', '派出所', null, 'codeedit', 'dwxxb', null, '111101', '9', '12', null, null, null, null, null, null, null, 'dwjb', '1', null, null, null, null, null, null, '=', 'pcs', '4', null, 'I', '20180403201010', null);
--add by zjm 20200601 出生婚姻房产核验页面
insert into xt_xtgncdb (GNCDID, CDCC, CDBZ, CDLX, ZDLB, CDMC, DQBM, URL)
values ('410003', '0', '1', '3', '1', '出生婚姻房产核验页面', '3407', 'gl/hyym');
  
--add by zjm 20200603 单位表增加照片流字段
alter table XT_DWXXB add zp long raw;
comment on column XT_DWXXB.zp
  is '照片流';
 
-- add by zjm 20200615 开具范围  字典
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005305', '9999', '5028', '开具范围', '名称中文拼音', null, null, null, 'CS_KJFW', '1', '8', '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005306', '5028', '1', '户口登记项目内容变更更正证明', 'HKDJXMNRBGGZZM', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005307', '5028', '2', '注销户口证明', 'ZXHKZM', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005308', '5028', '3', '曾经同户人员亲属关系证明', 'CJTHRYQSGXZM', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005309', '5028', '4', '法律法规规定的其他情形', 'FLFGGDDQTQX', null, null, null, null, null, null, '0', 'I', '20180502000002');

-- add by zjm 20200615 领取方式字典
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005310', '9999', '5029', '领取方式', '名称中文拼音', null, null, null, 'CS_LQFS', '1', '8', '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005311', '5029', '1', '网上下载', 'WSXZ', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005312', '5029', '2', '物流快递', 'WLKD', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005313', '5029', '3', '线下自取', 'XXZQ', null, null, null, null, null, null, '0', 'I', '20180502000002');

--add by zjm 20200616 一网通办理跳转页面
insert into xt_xtgncdb (GNCDID, CDCC, CDBZ, CDLX, ZDLB, CDMC, DQBM, URL)
values ('410004', '0', '1', '3', '1', '一网通办理跳转页面', '3407', 'gl/ywtbltz');

--add by zjm 20200618 公共户维护
insert into xt_xtgncdb (GNCDID, CDCC, CDBZ, CDLX, ZDLB, CDMC, DQBM, URL)
values ('410005', '0', '1', '3', '1', '公共户维护', '3407', 'gl/gghwh');

--modify by zjm 20200618 集体土地标间隐藏，并更名为户标识
update xt_sjpzb set visibletype = '011100' , displayname = '户标识' where sjpzid='9120'

--modify by zjm 20200619 冻结字典更新名字
update XT_XTCSB set mc ='拆迁冻结' ,zwpy='CQDJ' where csid='9000000001000005298';
update XT_XTCSB set mc ='已解冻' ,zwpy='YJD' where csid='9000000001000005299';

--add by zjm 20200619 冻结字典增加类别3 已拆迁 
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005314', '5026', '3', '已拆迁', 'YCQ', null, null, null, null, null, null, '0', 'I', '20180502000002');

--modify by zjm 20200624 集体土地查询条件增加门楼牌号和门楼地址
update xt_sjpzb set visibletype = '111100'  where sjpzid='9118';
update xt_sjpzb set visibletype = '111100'  where sjpzid='9119';

--add by zjm 20200710 增加控制参数控自动生成待办事项
insert into xt_xtkzcsb (CSID, KZLB, KZMC, KZZ, MRZ, BZ, XGBZ, BDLX, BDSJ, CDKZZ)
values ('37', '10037', '自动生成待办事项', '0', '0', '0不启动，1启动', '1', 'U', '20181112000000', null);

--add by zjm 20200710  全国人口普查所需人口资料清单配置表
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9147', '5012', '全国人口普查所需人口资料清单', '0019', 'hh', '户号', null, 'edit', null, null, '11110', '9', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'hh', '2', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9148', '5012', '全国人口普查所需人口资料清单', '3026', 'bzdz', '标志地址', null, 'edit', null, null, '000000', '100', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'bzdz', '4', null, 'I', '20180403201010', 'null');
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9149', '5012', '全国人口普查所需人口资料清单', '3002', 'jgxz', '籍贯详细地址', null, 'edit', null, null, '010', '100', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'jgxz', '1', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9150', '5012', '全国人口普查所需人口资料清单', '0001', 'xm', '姓名', null, 'edit', null, null, '111100', '30', '8', '1', '5', null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'xm', '1', null, 'I', '20180403201010', 'Chinese');
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9151', '5012', '全国人口普查所需人口资料清单', '0009', 'yhzgx', '与户主关系', null, 'codeedit', 'cs_jtgx', null, '011101', '2', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'yhzgx', '4', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9152', '5012', '全国人口普查所需人口资料清单', '0011', 'xb', '性别', null, 'codeedit', 'cs_xb', null, '111101', '1', '4', null, null, null, null, null, null, '0', null, null, null, null, null, null, null, null, '=', 'xb', '4', null, 'I', '20180403201010', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9153', '5012', '全国人口普查所需人口资料清单', '0002', 'gmsfhm', '公民身份号码', null, 'edit', null, null, '111100', '18', '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '=', 'gmsfhm', '7', null, 'I', '20180403201010', 'Sfzh');
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9154', '5012', '全国人口普查所需人口资料清单', '0020', 'hlx', '户类型', null, 'codeedit', 'cs_hlx', null, '010101', '1', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'hlx', '4', null, 'I', '20180403201010', null);

-- add by zjm 20200807 增加区域冻结配置 门楼牌号门楼详址俩字段
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9155', '5010', 'Qry_区域冻结人口基本信息表', '0020', 'mlph', '门楼牌号', null, 'edit', null, null, '111100', '10', '10', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'mlph', '2', null, 'I', '20121205000000', null);
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9156', '5010', 'Qry_区域冻结人口基本信息表', '0021', 'mlxz', '门楼详址', null, 'edit', null, null, '111100', '100', '12', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'mlxz', '2', null, 'I', '20121205000000', null);

-- add by zjm  20200814 菜单表增加户籍证明律师查询页面
insert into Xt_Xtgncdb (GNCDID, CDCC, CDBZ, CDLX, ZDLB, CDMC, DQBM, URL)
values ('200008', '1', '1', '2', '1', '户籍证明律师查询', '3407', 'cx/hjjbxx/hjzmlscx');

-- modify by zjm 20200818 区域冻结维护，集体土地维护，公共户维护
update xt_sjpzb set visibletype='111101' where pzlb='5010' and fieldname='ssxq';  
update xt_sjpzb set visibletype='111101' where pzlb='5009' and fieldname='ssxq';

--add by zjm  20200821 新建个照片中间表，用于储存上传的照片
create sequence SID_UPLOAD_TEMP   --创建序列名称
increment by 1  --增长幅度
start with 1000000000000000001  --初始值
maxvalue 9999999999999999999;  --最大值
-- add by zjm 20200821 临时照片表建表语句
create table UPLOAD_TEMP
(
  uploadzpid NUMBER(19) not null,
  zp         LONG RAW,
  czrid      NUMBER(19),
  czsj       CHAR(14)
)
tablespace HZ2004_CZRK
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column UPLOAD_TEMP.uploadzpid
  is '临时照片表主键';
comment on column UPLOAD_TEMP.zp
  is '照片流';
comment on column UPLOAD_TEMP.czrid
  is '操作人';
comment on column UPLOAD_TEMP.czsj
  is '操作时间';
--add by zjm 20200907 死亡注销证明编号增加限制，只能20位数内的数字
update xt_sjpzb set vtype = 'Swzmbh' where pzlb ='10014' and fieldname='swzmbh';   

--modify 户籍业务信息mlph mlxz结果集显示
update xt_sjpzb set visibletype='01110' where pzlb='20003' and fieldname='mlph'; 
update xt_sjpzb set visibletype='01110' where pzlb='20003' and fieldname='mlxz';
update xt_sjpzb set visibletype='01110' where pzlb='20004' and fieldname='mlph'; 
update xt_sjpzb set visibletype='01110' where pzlb='20004' and fieldname='mlxz';
update xt_sjpzb set visibletype='01110' where pzlb='20005' and fieldname='mlph'; 
update xt_sjpzb set visibletype='01110' where pzlb='20005' and fieldname='mlxz';
update xt_sjpzb set visibletype='01110' where pzlb='20006' and fieldname='mlph'; 
update xt_sjpzb set visibletype='01110' where pzlb='20006' and fieldname='mlxz';
update xt_sjpzb set visibletype='01110' where pzlb='20007' and fieldname='mlph'; 
update xt_sjpzb set visibletype='01110' where pzlb='20007' and fieldname='mlxz';
update xt_sjpzb set visibletype='01110' where pzlb='20008' and fieldname='mlph'; 
update xt_sjpzb set visibletype='01110' where pzlb='20008' and fieldname='mlxz';
update xt_sjpzb set visibletype='01110' where pzlb='20009' and fieldname='mlph'; 
update xt_sjpzb set visibletype='01110' where pzlb='20009' and fieldname='mlxz';
update xt_sjpzb set visibletype='01110' where pzlb='20010' and fieldname='mlph'; 
update xt_sjpzb set visibletype='01110' where pzlb='20010' and fieldname='mlxz';
update xt_sjpzb set visibletype='01110' where pzlb='20011' and fieldname='mlph'; 
update xt_sjpzb set visibletype='01110' where pzlb='20011' and fieldname='mlxz';

update xt_xtgncdb set url = 'yw/spgl/hjsp/type1' where gncdid = '170002' and cdmc = '户籍审批';
update xt_xtgncdb set url = 'yw/spgl/spcl/type1' where gncdid = '170004' and cdmc = '审批处理';

-- modify by zjm 20201222 修改非现金收费配置里单位代码为受理机构
update xt_sjpzb set displayname = '受理机构' where pzlb='50006' and fieldname='dwdm';
update xt_sjpzb set displayname = '受理机构' where pzlb='50005' and fieldname='dwdm';

-- Add by zjm 20201223 增加收费表字段注释
comment on column SFXXB.jfflag
  is '是否缴费标志  0 未缴费  1 已缴费';  
  
--add by zjm 20201224
-- Create table
create table SFJFFJB
(
  sfjffjbid NUMBER(19) not null,
  sfxxbid   NUMBER(19) not null,
  czrid     NUMBER(19),
  czsj      CHAR(14),
  sfqd      BLOB,
  jfqd      BLOB,
  gxrid     NUMBER(19),
  gxsj      CHAR(14)
)
tablespace HZ2004_CZRK
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column SFJFFJB.sfjffjbid
  is '收费缴费附件表主键ID';
comment on column SFJFFJB.sfxxbid
  is '收费信息表主键ID';
comment on column SFJFFJB.czrid
  is '操作人ID';
comment on column SFJFFJB.czsj
  is '操作时间';
comment on column SFJFFJB.sfqd
  is '收费清单';
comment on column SFJFFJB.jfqd
  is '缴费清单'; 
comment on column SFJFFJB.gxrid
  is '更新人ID';
comment on column SFJFFJB.gxsj
  is '更新时间';
--add by zjm 20201225 收费缴费附件表索引  
create sequence SID_SFJFFJB   --创建序列名称
increment by 1  --增长幅度
start with 1000000000000000001  --初始值
maxvalue 9999999999999999999;  --最大值  

--add by zjm 20201225 实缴款信息管理菜单
INSERT INTO XT_XTGNCDB VALUES ('291004', '0','1','2','1','缴款信息管理','3407','cx/sjkxxgl');

--add by zjm 20201225 实缴款附件管理
INSERT INTO XT_XTGNCDB VALUES ('291005', '0','1','2','1','缴款附件管理','3407','gl/sjkfjgl');

--Modify by zjm 20201228 收费表增加缴款人和缴款时间
alter table SFXXB add jksj CHAR(14);
alter table SFXXB add jkrid NUMBER(19);
-- Add comments to the columns 
comment on column SFXXB.jksj
  is '缴款时间';
comment on column SFXXB.jkrid
  is '缴款人id';
  
--add by zjm 20201228 缴费字典
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005315', '9999', '9042', '是否缴费', 'SFJF', null, null, null, 'CS_SFJF', null, null, '0', '1', '20180502000002');        
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005316', '9042', '0', '未缴费', 'WJF', null, null, null, null, '3', '18', '0', 'I', '20180502000002');
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005317', '9042', '1', '已缴费', 'YJF', null, null, null, null, '3', '18', '0', 'I', '20180502000002');
--modify by zjm 20201228 缴费标志配置表上关联字典
update xt_sjpzb set dsname = 'CS_SFJF',fieldname='jfflag' where pzlb='50005' and sjpzid='9090';

-- Modify by zjm 20201229 原非现金收款统计改名收费信息统计
update xt_xtgncdb set cdmc = '收费信息统计' where gncdid = '291003';

-- Modify by zjm 20201229 收费方式字典
update  XT_XTCSB  set mc='非现金' where cslb='9040' and dm='0';
update  XT_XTCSB  set mc='现金' where cslb='9040' and dm='1';
--Modify by zjm 20201230 配置50006打印时间改为收费时间
update xt_sjpzb set displayname='收费时间' where pzlb = '50006' and fieldname='dysj';
update xt_sjpzb set displayname = '收费时间' where pzlb='50005' and fieldname='dysj';
update xt_sjpzb set visibletype = '111' where pzlb='50005' and fieldname='dwdm';
update xt_sjpzb set visibletype = '111' where pzlb='50005' and fieldname='jfflag';

-- Modify by zjm 20210108 缴款附件管理菜单名称改为收缴凭证管理
update xt_xtgncdb set cdmc = '收缴凭证管理' where gncdid = '291005';

-- Modify by zjm 20210108 缴款信息管理菜单名称改为缴款信息统计
update xt_xtgncdb set cdmc = '缴款信息统计' where gncdid = '291004';

--add by zjm 20210111 分县局审核签发菜单
INSERT INTO XT_XTGNCDB VALUES ('291006', '0','1','2','1','分县局审核签发','3407','cx/fxjshqf');

--add by zjm 20210111 收费信息表增加审核状态 审核时间
alter table SFXXB add shzt CHAR(1) default 0;
alter table SFXXB add shsj CHAR(14);
alter table SFXXB add shrid NUMBER(19);
comment on column SFXXB.shzt
  is '审核状态  0待审核  1审核通过';
comment on column SFXXB.shsj
  is '审核时间';
comment on column SFXXB.shrid
  is '审核人id';
  
-- add by zjm 20200111 增加审签状态字典
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005318', '9999', '5030', '审签状态', '名称中文拼音', null, null, null, 'CS_SQZT', '1', '8', '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005319', '5030', '0', '待审核', 'DSH', null, null, null, null, null, null, '0', 'I', '20180502000002');
insert into XT_XTCSB (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005320', '5030', '1', '审核通过', 'SHTG', null, null, null, null, null, null, '0', 'I', '20180502000002');

-- add by zjm 20200111 收费配置表增加审签字段 
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9147', '50005', '非现金收款统计查询', '0011', 'shzt', '审签状态', null, 'codeedit', 'CS_SQZT', null, '0111', '20', '8', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'shzt', '2', null, 'I', '20121205000000', null);
update xt_sjpzb set id = '0013' where pzlb='50005' and fieldname='jfflag';

-- Modify by zjm 非现金收款统计查询条件增加操作人条件查询
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9157', '50006', '非现金收款统计查询', '0007', 'czrid', '操作人姓名', null, 'codeedit', 'yhxxb', null, '111101', '19', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'czrid', '4', null, 'I', '20121205000000', null);
-- add by zjm 收费方式增加首次打印字典值
insert into xt_xtcsb (CSID, CSLB, DM, MC, ZWPY, KZBZB, KZBZC, KZBZD, KZBZE, KZBZF, KZBZG, XGBZ, BDLX, BDSJ)
values ('9000000001000005321', '9040', '2', '首次打印', 'SCDY', null, null, null, null, '3', '18', '0', 'I', '20180502000002');

-- Modify by zjm 20210127 打印信息form表单中受理时间显示
update xt_sjpzb set fieldlength = '20'  where pzlb='20041' and fieldname='slsj'; 

-- Modify by zjm 20210127 不在线缴费原因标题改为作废原因
update xt_sjpzb set displayname = '作废原因'  where pzlb='50004' and fieldname='bzxjfyy'; 

--add by zjm 20210129 增加人员布控维护菜单
insert into xt_xtgncdb (GNCDID, CDCC, CDBZ, CDLX, ZDLB, CDMC, DQBM, URL)
values ('410006', '0', '1', '3', '1', '人员布控维护', '3407', 'gl/bk/rywh');

--add By zjm 20210201 收费信息表新增修改次数字段
alter table SFXXB add xgcs char(1) default 0;
-- Add comments to the columns 
comment on column SFXXB.xgcs
  is '修改次数';
 
--Modify by zjm 增加修改次数
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9158', '50005', '非现金收款统计查询', '0031', 'xgcs', '修改次数', null, 'edit', null, null, '000000', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'xgcs', '2', null, 'I', '20121205000000', null);

--add by zjm 20210207 增加控制参数跨地区省外迁出
insert into xt_xtkzcsb (CSID, KZLB, KZMC, KZZ, MRZ, BZ, XGBZ, BDLX, BDSJ, CDKZZ)
values ('38', '10038', '跨地区省外迁出', '0', '0', 'http://192.168.0.155:8082/hz2004qxgl', '1', 'U', '20181112000000', null);

--add by zjm 20210219 迁入登记字段设为必填项
update xt_sjpzb set displayname='*联系电话' where pzlb='10030' and fieldname='lxdh';  

-- add by zjm 20210220 单位信息表新增电话号码字段 
alter table XT_DWXXB add dhhm NVARCHAR2(200);
comment on column XT_DWXXB.dhhm
  is '电话号码';
  
-- modify by zjm 20210220 修改单位信息视图，增加电话号码字段
create or replace view v_dwxxwh as
select dwxxb.DM     as dm,
       xzqhb.DM     as xzqhbdm,
       csb1.CSID   as csb1csid,
       csb2.CSID   as csb2csid,
       dwxxb.MC     as mc,
       dwxxb.dhhm   as dhhm,
       dwxxb.ZWPY   as zwpy,
       dwxxb.WBPY   as wbpy,
       dwxxb.DWJGDM as dwjgdm,
       dwxxb.QHDM   as qhdm,
       dwxxb.DWJB   as dwjb,
       dwxxb.DZ     as dz,
       dwxxb.BZ     as bz,
       dwxxb.QYBZ   as qybz,
       dwxxb.BDLX   as bdlx,
       dwxxb.BDSJ   as bdsj,
       dwxxb.FJJGDM as fjjgdm,
       dwxxb.FJJGMC as fjjgmc,
       xzqhb.MC     as xzqhbmc,
       xzqhb.ZWPY   as xzqhbzwpy,
       xzqhb.WBPY   as xzqhbwbpy,
       xzqhb.BZ     as xzqhbbz,
       xzqhb.QYBZ   as xzqhbqybz,
       xzqhb.BDLX   as xzqhbbdlx,
       xzqhb.BDSJ   as xzqhbbdsj,
       csb1.CSLB   as csb1cslb,
       csb1.DM     as csb1dm,
       csb1.MC     as csb1mc,
       csb1.ZWPY   as csb1zwpy,
       csb1.KZBZB  as csb1kzbzb,
       csb1.KZBZC  as csb1kzbzc,
       csb1.KZBZD  as csb1kzbzd,
       csb1.KZBZE  as csb1kzbze,
       csb1.KZBZF  as csb1kzbzf,
       csb1.KZBZG  as csb1kzbzg,
       csb1.XGBZ   as csb1xgbz,
       csb1.BDLX   as csb1bdlx,
       csb1.BDSJ   as csb1bdsj,
       csb2.CSLB   as csb2cslb,
       csb2.DM     as csb2dm,
       csb2.MC     as csb2mc,
       csb2.ZWPY   as csb2zwpy,
       csb2.KZBZB  as csb2kzbzb,
       csb2.KZBZC  as csb2kzbzc,
       csb2.KZBZD  as csb2kzbzd,
       csb2.KZBZE  as csb2kzbze,
       csb2.KZBZF  as csb2kzbzf,
       csb2.KZBZG  as csb2kzbzg,
       csb2.XGBZ   as csb2xgbz,
       csb2.BDLX   as csb2bdlx,
       csb2.BDSJ   as csb2bdsj,
       dwxxb.zp    as zp
  from XT_DWXXB dwxxb,
       XT_XZQHB xzqhb,
       XT_XTCSB csb1,
       XT_XTCSB csb2
 where (dwxxb.DWJB = csb1.DM(+))
   and (csb1.CSLB(+) = '9024')
   and (dwxxb.QYBZ = csb2.DM(+))
   and (csb2.CSLB(+) = '9002')
   and (dwxxb.QHDM = xzqhb.DM)
 order by dwxxb.QHDM, dwxxb.DM;
comment on table V_DWXXWH is '单位信息视图';

--Modify by zjm 20210220 审核状态隐藏掉，审核功能不需要了
update xt_sjpzb set visibletype = '000000'  where sjpzid='9147';
--Modify by zjm 20210220 将收费信息表中审核状态默认值设为1，收费信息表中原先审核状态都设为1
alter table SFXXB modify shzt default 1;
update sfxxb set shzt = 1;

-- Add by zjm 20210222 准迁证信息表新增字段迁移原因
alter table HJSP_ZQZXXB add qyyy NVARCHAR2(100);
comment on column HJSP_ZQZXXB.qyyy
  is '迁移原因';
-- Add by zjm 20210222 户籍审批申请表新增字段迁移原因  
alter table HJSP_HJSPSQB add qyyy NVARCHAR2(100);
comment on column HJSP_HJSPSQB.qyyy
  is '迁移原因';
-- add by zjm 20210222  迁入审批信息配置增加迁移原因
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9159', '10030', 'Hj_迁入审批信息', '0008', 'qyyy', '*迁移原因', null, 'codeedit', 'cs_qyldyy', null, '0111', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'qyyy', '4', null, 'I', '20121205000000', null);

-- Add by zjm 20210224 户籍审批申请表新增字段增加是否长三角通办字段  
alter table HJSP_HJSPSQB add sfcsjtb CHAR(1);
comment on column HJSP_HJSPSQB.sfcsjtb
  is '是否长三角通办：0否 1是';
-- Add by zjm 20210224 准迁证信息表新增字段增加是否长三角通办字段  
alter table HJSP_ZQZXXB add sfcsjtb CHAR(1);
comment on column HJSP_ZQZXXB.sfcsjtb
  is '是否长三角通办：0否 1是'; 
  
-- add by zjm 20210224  迁入审批信息配置增加是否长三角通办
insert into xt_sjpzb (SJPZID, PZLB, PZMC, ID, FIELDNAME, DISPLAYNAME, FIELDTYPE, FIELDTYPENAME, DSNAME, READONLY, VISIBLETYPE, FIELDLENGTH, DISPLAYFIELDLENGTH, ISCHINESE, MBMOD, ALLOWSELFINPUT, CODEFIELD, PYFIELD, NAMEFIELD, PARTMASK, LSFIELD, SHOWLS, ENABLECOPY, ENABLEDEFAULTFILTER, ENTERDROPDOWN, ENTERUNIQUEEXIT, USETABLEFILTERED, USEFORSFZ, CONDITIONOPERATOR, CONDITIONFIELD, CONDITIONTYPE, GROUPS, BDLX, BDSJ, VTYPE)
values ('9160', '10030', 'Hj_迁入审批信息', '0008', 'sfcsjtb', '*是否长三角通办', null, 'codeedit', 'cs_sfbz', null, '0111', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '=', 'sfcsjtb', '4', null, 'I', '20121205000000', null);
