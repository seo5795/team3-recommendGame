package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameDAO {
	Connection conn;
	PreparedStatement pstmt;
	ArrayList<GameVO> datas; // 리스트 삽입용

	final String sql_selectAll="select * from game";
	final String sql_selectAllDiscount="select * from game order by discount desc";
	final String sql_selectAllGenre="select * from game order by genre";
	final String sql_selectAllDate="select * from game order by gdate";
	final String sql_selectAllRcnt="select * from game order by rcnt desc";
	final String sql_selectAllPrice="select * from game order by price";
	final String sql_selectGenre="select * from game where genre=?";
	final String sql_selectOne="select * from game where gnum=?";
	final String sql_download="update game set dcnt=dcnt+1 where gnum=?";
	final String sql_recommend="update game set rcnt=rcnt+1 where gnum=?";

	// 관리자용 SQL
	final String sql_insertGame="insert into game values((select nvl(max(gnum),0)+1 from game),?,?,?,?,0,0,TO_CHAR(SYSDATE, 'DD MON, YYYY'))";
	final String sql_updatePrice="update game set price=? where gnum=?";
	final String sql_updateDiscount="update game set discount=? where gnum=?";
	final String sql_deleteGame="delete from game where gnum=?";

	// 목록 - 게임번호순 정렬
	public ArrayList<GameVO> selectAll(GameVO vo) {
		ArrayList<GameVO> datas=new ArrayList<GameVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectAll);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				GameVO data=new GameVO();
				data.setGnum(rs.getInt("gnum"));
				data.setGname(rs.getString("gname"));
				data.setGenre(rs.getString("genre"));
				data.setPrice(rs.getInt("price"));
				data.setDiscount(rs.getInt("discount"));
				data.setDcnt(rs.getInt("dcnt"));
				data.setRcnt(rs.getInt("rcnt"));
				data.setGdate(rs.getString("gdate"));
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

	// 목록 - 할인율순(디폴트)
	public ArrayList<GameVO> selectAllDiscount(GameVO vo) {
		ArrayList<GameVO> datas=new ArrayList<GameVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectAllDiscount);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				GameVO data=new GameVO();
				data.setGnum(rs.getInt("gnum"));
				data.setGname(rs.getString("gname"));
				data.setGenre(rs.getString("genre"));
				data.setPrice(rs.getInt("price"));
				data.setDiscount(rs.getInt("discount"));
				data.setDcnt(rs.getInt("dcnt"));
				data.setRcnt(rs.getInt("rcnt"));
				data.setGdate(rs.getString("gdate"));
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

	// 목록 - 장르순
	public ArrayList<GameVO> selectAllGenre(GameVO vo) {
		ArrayList<GameVO> datas=new ArrayList<GameVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectAllGenre);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				GameVO data=new GameVO();
				data.setGnum(rs.getInt("gnum"));
				data.setGname(rs.getString("gname"));
				data.setGenre(rs.getString("genre"));
				data.setPrice(rs.getInt("price"));
				data.setDiscount(rs.getInt("discount"));
				data.setDcnt(rs.getInt("dcnt"));
				data.setRcnt(rs.getInt("rcnt"));
				data.setGdate(rs.getString("gdate"));
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

	// 목록 - 등록일자순
	public ArrayList<GameVO> selectAllDate(GameVO vo) {
		ArrayList<GameVO> datas=new ArrayList<GameVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectAllDate);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				GameVO data=new GameVO();
				data.setGnum(rs.getInt("gnum"));
				data.setGname(rs.getString("gname"));
				data.setGenre(rs.getString("genre"));
				data.setPrice(rs.getInt("price"));
				data.setDiscount(rs.getInt("discount"));
				data.setDcnt(rs.getInt("dcnt"));
				data.setRcnt(rs.getInt("rcnt"));
				data.setGdate(rs.getString("gdate"));
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

	// 목록 - 추천순
	public ArrayList<GameVO> selectAllRcnt(GameVO vo) {
		ArrayList<GameVO> datas=new ArrayList<GameVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectAllRcnt);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				GameVO data=new GameVO();
				data.setGnum(rs.getInt("gnum"));
				data.setGname(rs.getString("gname"));
				data.setGenre(rs.getString("genre"));
				data.setPrice(rs.getInt("price"));
				data.setDiscount(rs.getInt("discount"));
				data.setDcnt(rs.getInt("dcnt"));
				data.setRcnt(rs.getInt("rcnt"));
				data.setGdate(rs.getString("gdate"));
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

	// 목록 - 가격순
	public ArrayList<GameVO> selectAllPrice(GameVO vo) {
		ArrayList<GameVO> datas=new ArrayList<GameVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectAllPrice);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				GameVO data=new GameVO();
				data.setGnum(rs.getInt("gnum"));
				data.setGname(rs.getString("gname"));
				data.setGenre(rs.getString("genre"));
				data.setPrice(rs.getInt("price"));
				data.setDiscount(rs.getInt("discount"));
				data.setDcnt(rs.getInt("dcnt"));
				data.setRcnt(rs.getInt("rcnt"));
				data.setGdate(rs.getString("gdate"));
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

	// 특정장르목록
	 public ArrayList<GameVO> selectGenre(GameVO vo) {
	      ArrayList<GameVO> datas=new ArrayList<GameVO>();
	      conn=JDBCUtil.connect();
	      try {
	         pstmt=conn.prepareStatement(sql_selectGenre);
	         pstmt.setString(1, vo.getGenre());
	         ResultSet rs=pstmt.executeQuery();
	         while(rs.next()) {
	            GameVO data=new GameVO();
	            data.setGnum(rs.getInt("gnum"));
	            data.setGname(rs.getString("gname"));
	            data.setGenre(rs.getString("genre"));
	            data.setPrice(rs.getInt("price"));
	            data.setDiscount(rs.getInt("discount"));
	            data.setDcnt(rs.getInt("dcnt"));
	            data.setRcnt(rs.getInt("rcnt"));
	            data.setGdate(rs.getString("gdate"));
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

	// 상세보기
	public GameVO selectOne(GameVO vo) {
		// 검색실패시 null 출력
		GameVO data=null;
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectOne);
			pstmt.setInt(1, vo.getGnum());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				data=new GameVO();
				data.setGnum(rs.getInt("gnum"));
				data.setGname(rs.getString("gname"));
				data.setGenre(rs.getString("genre"));
				data.setPrice(rs.getInt("price"));
				data.setDiscount(rs.getInt("discount"));
				data.setDcnt(rs.getInt("dcnt"));
				data.setRcnt(rs.getInt("rcnt"));
				data.setGdate(rs.getString("gdate"));
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}

	// 다운로드
	// GameDownDAO와 연계 필요
	// Download 테이블에 다운로드번호, 유저번호(FK), 게임번호(FK), 추천여부 삽입
	public boolean download(GameVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_download);
			pstmt.setInt(1, vo.getGnum());
			int result=pstmt.executeUpdate();
			if(result==0) {
				System.out.println("log: not data");
				return false;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	// 추천
	// GameDownDAO와 연계 필요
	// Download 테이블에 다운로드번호, 유저번호(FK), 게임번호(FK), 추천여부 삽입
	public boolean recommend(GameVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_recommend);
			pstmt.setInt(1, vo.getGnum());
			int result=pstmt.executeUpdate();
			if(result==0) {
				System.out.println("log: not data");
				return false;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	// 게임등록(관리자용)
	public boolean insertGame(GameVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_insertGame);
			pstmt.setString(1, vo.getGname());
			pstmt.setString(2, vo.getGenre());
			pstmt.setInt(3, vo.getPrice());
			pstmt.setInt(4, vo.getDiscount());
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	
		return true;
	}

	// 게임가격변경(관리자용)
	public boolean updatePrice(GameVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_updatePrice);
			pstmt.setInt(1, vo.getPrice());
			pstmt.setInt(2, vo.getGnum());
			int result=pstmt.executeUpdate();
			if(result==0) {
				System.out.println("log: not data");
				return false;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	// 게임할인율변경(관리자용)
	public boolean updateDiscount(GameVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_updateDiscount);
			pstmt.setInt(1, vo.getDiscount());
			pstmt.setInt(2, vo.getGnum());
			int result=pstmt.executeUpdate();
			if(result==0) {
				System.out.println("log: not data");
				return false;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	// 게임삭제(관리자용)
	public boolean deleteGame(GameVO vo) {
		// System.out.println("log DAO delete: "+vo);
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_deleteGame);
			pstmt.setInt(1, vo.getGnum());
			int result=pstmt.executeUpdate();
			if(result==0) {
				System.out.println("log: not data");
				return false;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
}