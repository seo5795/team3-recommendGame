package model;

public class SteamVO {
	private int gnum;
	private String gname;
	private String genre;
	private int price;
	private int dcnt;
	private int rcnt;
	private String gdate;
	
	public int getGnum() {
		return gnum;
	}
	public void setGnum(int gnum) {
		this.gnum = gnum;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDcnt() {
		return dcnt;
	}
	public void setDcnt(int dcnt) {
		this.dcnt = dcnt;
	}
	public int getRcnt() {
		return rcnt;
	}
	public void setRcnt(int rcnt) {
		this.rcnt = rcnt;
	}
	public String getGdate() {
		return gdate;
	}
	public void setGdate(String gdate) {
		this.gdate = gdate;
	}
	@Override
	public String toString() {
		return "SteamVO [gnum=" + gnum + ", gname=" + gname + ", genre=" + genre + ", price=" + price + ", dcnt=" + dcnt
				+ ", rcnt=" + rcnt + ", gdate=" + gdate + "]";
	}
}
