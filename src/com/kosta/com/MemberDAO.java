package com.kosta.com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MemberDAO {
	
	
	public int create(String id, String pw, String name, String email) {
		Connection conn = getConn();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Member(memberno,id,pw,name,email,dates)");
		sql.append("values (seq_mem.nextval,?,?,?,?,sysdate)");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
						
			result = pstmt.executeUpdate();	
		}
		catch(SQLException e){
			System.out.println(e);
		}
		finally {
		   close(pstmt, conn);
		}
		
		return result;
	}//end create
	

	private Connection getConn() {
		String className = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "hr";
		String pwd = "hr";
		Connection conn = null;
				
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("작동중");
		}
		catch(SQLException| ClassNotFoundException e) {
			System.out.println(e);
		}
		return conn;
	}//end getConn 
	
	
	private void close(PreparedStatement pstmt, Connection conn) {
		if(conn!=null) try {conn.close();}catch(SQLException e){System.out.println(e);}
		if(pstmt!=null) try {pstmt.close();} catch(SQLException e){System.out.println(e);}	
	}

	//ID 유무
	private void checkID() {
		
	}

}
