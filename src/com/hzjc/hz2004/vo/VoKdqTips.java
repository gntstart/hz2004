package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.hz2004.po.*;

public class VoKdqTips
extends DefaultVO   {
	private String kdqqy_fkzt_sqb;
	private String kdqqy_qcdqbm_sqb;
	private PoHJSP_HJSPZB poHJSP_HJSPZB;
	public PoHJSP_HJSPZB getPoHJSP_HJSPZB() {
		return poHJSP_HJSPZB;
	}
	public VoKdqTips(String kdqqy_fkzt_sqb, String kdqqy_qcdqbm_sqb, PoHJSP_HJSPZB poHJSP_HJSPZB) {
		super();
		this.kdqqy_fkzt_sqb = kdqqy_fkzt_sqb;
		this.kdqqy_qcdqbm_sqb = kdqqy_qcdqbm_sqb;
		this.poHJSP_HJSPZB = poHJSP_HJSPZB;
	}
	public void setPoHJSP_HJSPZB(PoHJSP_HJSPZB poHJSP_HJSPZB) {
		this.poHJSP_HJSPZB = poHJSP_HJSPZB;
	}

	public String getKdqqy_fkzt_sqb() {
		return kdqqy_fkzt_sqb;
	}
	public void setKdqqy_fkzt_sqb(String kdqqy_fkzt_sqb) {
		this.kdqqy_fkzt_sqb = kdqqy_fkzt_sqb;
	}
	public String getKdqqy_qcdqbm_sqb() {
		return kdqqy_qcdqbm_sqb;
	}
	public void setKdqqy_qcdqbm_sqb(String kdqqy_qcdqbm_sqb) {
		this.kdqqy_qcdqbm_sqb = kdqqy_qcdqbm_sqb;
	}
	public VoKdqTips() {
	  }


}