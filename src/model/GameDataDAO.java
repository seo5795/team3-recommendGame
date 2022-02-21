package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GameDataDAO {
	Connection conn;
	PreparedStatement pstmt;

	public static void main() {//컨트롤러 생성자에서 한번 불러내기 위해 main메서드의 input값 삭제
		Connection conn=null;
		PreparedStatement pstmt=null;

		ArrayList<GameVO> datas=new ArrayList<GameVO>();
		Random rand=new Random();

		// 데이터 가져올 사이트
		final String url="https://store.steampowered.com/search/?sort_by=Price_ASC&filter=topsellers";
		Document doc=null;
		try {
			doc=Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 데이터 가져오기
		Elements eles=doc.select("div#search_result_container > div#search_resultsRows");
		Iterator<Element> itr=eles.iterator();

		// 게임명
		Iterator<Element> itr1=eles.select("span.title").iterator();
		// 장르
		// Iterator<Element> itr2=eles.select("").iterator();
		// 가격
		Iterator<Element> itr3=eles.select("div.col.search_price.discounted.responsive_secondrow > span").iterator();
		// 할인율
		Iterator<Element> itr4=eles.select("div.col.search_discount.responsive_secondrow").iterator();
		// 다운로드수
		// Iterator<Element> itr5=eles.select("").iterator();
		// 추천수
		// Iterator<Element> itr6=eles.select("").iterator();
		// 판매개시일자
		Iterator<Element> itr7=eles.select("div.col.search_released.responsive_secondrow").iterator();

		while(itr1.hasNext()) {
			GameVO vo=new GameVO();
			//System.out.println(itr1.next().text());
			vo.setGname(itr1.next().text());
			// 화폐단위 제거
			String str1=itr3.next().text();
			String intstr1=str1.replaceAll("[^0-9]", "");
			// 금액을 int형으로 변환
			vo.setPrice(Integer.parseInt(intstr1));
			// %제거 
			String str2=itr4.next().text();
			String intstr2=str2.replaceAll("[^0-9]", "");
			// 할인율을 int형으로 변환
			vo.setDiscount(Integer.parseInt(intstr2));
			vo.setGdate(itr7.next().text());
			// 초기 다운로드수는 10000안에서 랜덤
			vo.setDcnt(rand.nextInt(10000));
			// 초기 추천수는 다운로드수보다 적은 범위에서 랜덤
			vo.setRcnt(rand.nextInt(vo.getDcnt()));
			
			//장르: 랜덤수 받아 받은 숫자에 따라 장르입력
			int num = rand.nextInt(7);
			if(num==0) {
				vo.setGenre("Action");
			}else if(num==1) {
				vo.setGenre("Casual");
			}else if(num==2) {
				vo.setGenre("Simulation");
			}else if(num==3) {
				vo.setGenre("RPG");
			}else if(num==4) {
				vo.setGenre("Racing");
			}else if(num==5) {
				vo.setGenre("Novel");
			}else if(num==6) {
				vo.setGenre("Adventure");
			}
			
			datas.add(vo);
		}

		// DB에 삽입
		String sql_check="select * from game";
		String sql_insert="insert into game values((select nvl(max(gnum),0)+1 from game),?,?,?,?,?,?,?)";

		conn=JDBCUtil.connect();

		try {
			pstmt=conn.prepareStatement(sql_check);
			ResultSet rs=pstmt.executeQuery(sql_check);
			System.out.println("기존 데이터 유무: "+rs.next());

			if(rs.next()!=false) {
				System.out.println("기존 데이터가 존재하여 추가하지 않습니다.");
			}
			else {
				pstmt=conn.prepareStatement(sql_insert);
				for(GameVO v:datas) {
					pstmt.setString(1, v.getGname());
					pstmt.setString(2, v.getGenre());
					pstmt.setInt(3, v.getPrice());
					pstmt.setInt(4, v.getDiscount());
					pstmt.setInt(5, v.getDcnt());
					pstmt.setInt(6, v.getRcnt());
					pstmt.setString(7, v.getGdate());
					pstmt.executeUpdate();
				}
				System.out.println("데이터를 추가하였습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}