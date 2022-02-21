package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Test01 {
	public static void main(String[] args) {
		GameDAO dao=new GameDAO();
		Scanner sc=new Scanner(System.in);
		
		/*
		// 리스트 보기
		GameVO vo=new GameVO();
		ArrayList<GameVO> datas=dao.selectAllDate(vo);
		// ArrayList<GameVO> datas=dao.selectAllDiscount(vo);
		// ArrayList<GameVO> datas=dao.selectAllGenre(vo);
		// ArrayList<GameVO> datas=dao.selectAllRcnt(vo);
		// ArrayList<GameVO> datas=dao.selectAllPrice(vo);
		System.out.println("===검색결과===");
		for(GameVO v:datas) {
			System.out.println(v);
		}
		System.out.println("============");
		*/

		// 특정장르 리스트 보기
		System.out.print("장르입력>>> ");
		String genre=sc.nextLine();
		
		GameVO vo=new GameVO();
		vo.setGenre(genre);
		ArrayList<GameVO> datas=dao.selectGenre(vo);
		System.out.println("===검색결과===");
		for(GameVO v:datas) {
			System.out.println(v);
		}
		System.out.println("============");

		/*
		// 상세 보기
		System.out.print("번호입력>>> ");
		int gnum=sc.nextInt();
		GameVO vo=new GameVO();
		vo.setGnum(gnum);
		vo=dao.selectOne(vo);

		System.out.println("===검색결과===");
		System.out.println(vo);
		System.out.println("============");
		 */

		/*
		// 등록
		System.out.print("게임명입력>>> ");
		String gname=sc.nextLine();
		System.out.print("장르입력>>> ");
		String genre=sc.nextLine();
		System.out.print("가격입력>>> ");
		int price=sc.nextInt();
		System.out.print("할인율입력>>> ");
		int discount=sc.nextInt();

		GameVO vo=new GameVO();
		vo.setGname(gname);
		vo.setGenre(genre);
		vo.setPrice(price);
		vo.setDiscount(discount);
		dao.insertGame(vo);
		System.out.println("insert success");
		 */

		/*
		// 변경
		System.out.println("번호입력: ");
		System.out.print(">> ");
		int gnum=sc.nextInt();
		System.out.println("수치입력: ");
		System.out.print(">> ");
		int number=sc.nextInt();

		GameVO vo=new GameVO();
		vo.setGnum(gnum);
		//vo.setPrice(number);
		vo.setDiscount(number);
		//dao.updatePrice(vo);
		dao.updateDiscount(vo);
		System.out.println("update success");
		 */

		/*
		// 삭제
		System.out.println("번호입력: ");
		System.out.print(">> ");
		int gnum=sc.nextInt();

		// Controller
		GameVO vo=new GameVO();
		vo.setGnum(gnum);
		dao.deleteGame(vo);
		System.out.println("delete success");
		 */
	}
}
