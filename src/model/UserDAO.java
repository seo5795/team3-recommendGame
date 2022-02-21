package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	
	Connection conn;
	PreparedStatement pstmt;

	final String insertUser="insert into userdb values((select nvl(max(unum),0)+1 from userdb),?,?)";
	final String selectOne="select * from userdb where id = ? and pw=?";
	final String update="update userdb set pw = ? where id = ?";
	final String delete="delete from userdb where id = ?";
	
	public boolean insertUser(UserVO vo) {	//회원가입
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(insertUser);
	
			pstmt.setString(1, vo.getUid());
			pstmt.setString(2, vo.getPwd());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
	
	public UserVO selectOneUser(UserVO vo) {	//로그인
		UserVO data=null;

		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(selectOne);
			pstmt.setString(1, vo.getUid());
			pstmt.setString(2, vo.getPwd());
			ResultSet rs=pstmt.executeQuery();

			if(rs.next()) {
	            data=new UserVO();
	           
	            data.setUnum(rs.getInt("unum"));
	            data.setUid(rs.getString("id"));
	            data.setPwd(rs.getString("pw"));
	            //후에 비밀번호 추가입력을 통한 로그인 기능구현           
	         }

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		System.out.println("로그:" +data);
		return data;
	}
	
	public boolean update(UserVO vo) {	//회원 비밀번호 변경
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(update);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getUid());
			int res=pstmt.executeUpdate();
			if(res==0) {
				//System.out.println("로그: UserDAO: 비밀번호 변경 대상 없음!");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public boolean delete(UserVO vo) {	//회원 정보 삭제
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(delete);
			pstmt.setString(1, vo.getUid());
			int res=pstmt.executeUpdate();
			if(res==0) {
				System.out.println("로그: UserDAO: 삭제대상없음!");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		System.out.println("로그: UserDAO: 삭제완료!");
		return true;
	}
	
}
