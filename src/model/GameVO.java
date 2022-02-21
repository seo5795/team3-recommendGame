package model;

public class GameVO {
	private int gnum;
	private String gname;
	private String genre;
	private int price;
	private int discount;
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
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
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
		return "No."+gnum+", 게임명: "+gname+", 장르: "+genre+", 가격: "+price+", 할인율: "
				+discount+", 할인가: "+(int)(price*discount*0.01)+", 다운로드수: "+dcnt+", 추천수: "+rcnt+", 등록일자: "+gdate;
	}
}