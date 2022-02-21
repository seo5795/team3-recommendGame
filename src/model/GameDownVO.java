package model;

public class GameDownVO {
	private int dnum;
	private int unum;
	private int gnum;
	private String reYN;
	//private int reYN;
	
	public int getDnum() {
		return dnum;
	}
	public void setDnum(int dnum) {
		this.dnum = dnum;
	}
	public int getUnum() {
		return unum;
	}
	public void setUnum(int unum) {
		this.unum = unum;
	}
	public int getGnum() {
		return gnum;
	}
	public void setGnum(int gnum) {
		this.gnum = gnum;
	}
	public String getReYN() {
		return reYN;
	}
	public void setReYN(String reYN) {
		this.reYN = reYN;
	}
	
	@Override
	public String toString() {
		return "구매번호: "+dnum+", 구매유저: "+unum+", 구매게임: "+gnum+", 추천여부: "+reYN;
	}
}