package  com.hzjc.hz2004.po;

public class PoV_IN_CHRYB implements com.hzjc.wsstruts.po.PO
{
  private Long chryid;
  private String bchrszpcs;
  private String chrszpcs;

  public void setChryid(Long chryid) {
    this.chryid = chryid;
  }

  public Long getChryid() {
    return chryid;
  }

  public void setBchrszpcs(String bchrszpcs) {
    this.bchrszpcs = bchrszpcs;
  }

  public String getBchrszpcs() {
    return bchrszpcs;
  }

  public void setChrszpcs(String chrszpcs) {
    this.chrszpcs = chrszpcs;
  }

  public String getChrszpcs() {
    return chrszpcs;
  }

}
