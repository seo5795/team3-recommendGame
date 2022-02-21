package model;

public class UserVO {
	private int unum;
	private String uid;
	private String pwd;
	
	public int getUnum() {
		return unum;
	}
	public void setUnum(int unum) {
		this.unum = unum;
	}
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "UserVO [unum=" + unum + ", uid=" + uid + ", pwd=" + pwd + "]";
	}

	
	
	
}	
