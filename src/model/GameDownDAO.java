package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameDownDAO {
	Connection conn;
	PreparedStatement pstmt;
	ArrayList<GameVO> datas; // 리스트 삽입용

	// 다운로드 리스트(구매내역)
	// sql_selectUser은 사용자를 위한 구매내역
	// 관리자는 셋 다 사용가능
	// 다운로드 리스트는 임의 삭제 불가(구매 히스토리이므로 등록만 가능)
	final String sql_selectAll="select * from gamedown";
	final String sql_selectUser="select * from gamedown where unum=?";
	final String sql_selectGame="select * from gamedown where gnum=?";

	// 다운로드 내역 삽입
	// 유저가 다운로드하고 추천여부까지 넘어가면 발동
	// ?는 유저번호(unum), 게임번호(gnum), 추천여부 순
	// 추천여부는 1/0 이지만 int가 아닌 String인 점 주의
	final String sql_insertDown="insert into gamedown values((select nvl(max(dnum),0)+1 from gamedown),?,?,?)";

	// 다운로드 리스트(All) 관리자용
	public ArrayList<GameDownVO> selectAll(GameDownVO vo) {
		ArrayList<GameDownVO> datas=new ArrayList<GameDownVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectAll);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				GameDownVO data=new GameDownVO();
				data.setDnum(rs.getInt("dnum"));
				data.setUnum(rs.getInt("unum"));
				data.setGnum(rs.getInt("gnum"));
				data.setReYN(rs.getString("reYN"));
				datas.add(data);
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}

	// 다운로드 리스트(user) 유저용
	public ArrayList<GameDownVO> selectUser(GameDownVO vo) {
		ArrayList<GameDownVO> datas=new ArrayList<GameDownVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectUser);
			pstmt.setInt(1, vo.getUnum());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				GameDownVO data=new GameDownVO();
				data.setDnum(rs.getInt("dnum"));
				data.setUnum(rs.getInt("unum"));
				data.setGnum(rs.getInt("gnum"));
				data.setReYN(rs.getString("reYN"));
				datas.add(data);
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}

	// 다운로드 리스트(game) 관리자용
	public ArrayList<GameDownVO> selectGame(GameDownVO vo) {
		ArrayList<GameDownVO> datas=new ArrayList<GameDownVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectGame);
			pstmt.setInt(1, vo.getGnum());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				GameDownVO data=new GameDownVO();
				data.setDnum(rs.getInt("dnum"));
				data.setUnum(rs.getInt("unum"));
				data.setGnum(rs.getInt("gnum"));
				data.setReYN(rs.getString("reYN"));
				datas.add(data);
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}
	
	// 다운로드 내역 등록
	// 게임 다운로드 > 추천여부(recommend())까지 진행하면 작동
	public boolean insertDown(GameDownVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_insertDown);
			pstmt.setInt(1, vo.getUnum());
			pstmt.setInt(2, vo.getGnum());
			pstmt.setString(3, vo.getReYN());
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		//System.out.println("log: insert complete");
		return true;
	}
}