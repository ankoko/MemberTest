package com.kosta.com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class MemberDAO {
	private ArrayList<MemberDTO> arr = new ArrayList<>();
	
	//create
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
	
	
	//update
	public int update(String id, Scanner sc) {
		 Connection conn = getConn();
		 PreparedStatement pstmt = null;
		 StringBuilder sql = new StringBuilder();
		 MemberDTO dto= checkID(id);
		 int result = 0;
		 
		 try {
			 if(dto.getId() == null) {
				 System.out.println("해당 ID가 없습니다.");
			 }
	         else{
				 sql.append("   update  member   ");
				 sql.append("     set            ");
				 sql.append("     pw=?     ");
				 sql.append("     , email= ?     ");
				 sql.append("   where            ");
				 sql.append("         id = ?     ");
				 pstmt = conn.prepareStatement(sql.toString());
				 System.out.println("변경할 비밀번호를 입력하세요");
				 pstmt.setString(1, sc.nextLine());
				 System.out.println("변경할 이메일을 입력하세요");
				 pstmt.setString(2, sc.nextLine());
				 pstmt.setString(3, dto.getId());
				 result = pstmt.executeUpdate();
			 }			 
		 }
		 catch(SQLException e) {
			 System.out.println(e);
		 }
		 finally {
			 close(pstmt, conn);
		 }
		 return result;
	}//end update
	
	//read
	public void read(String id, Scanner sc) {
		
		MemberDTO dto = checkID(id);
		
		 if(dto.getId() == null) {
			 System.out.println("해당 ID가 없습니다.");
		 }
		 else {
			 System.out.printf("아이디: %s\n",dto.getId());
			 System.out.printf("패스워드: %s\n",dto.getPw());
			 System.out.printf("이름: %s\n",dto.getName());
			 System.out.printf("이메일: %s\n",dto.getEmail());
			 System.out.printf("가입일자: ");
			 System.out.println(dto.getDates());
		 }	
	}//end read
	
	
	
	
	
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
		}catch(SQLException| ClassNotFoundException e) {
			System.out.println(e);
		}
		return conn;
	}//end getConn 
	
	
	private void close(PreparedStatement pstmt, Connection conn) {
		if(conn!=null) try {conn.close();}catch(SQLException e){System.out.println(e);}
		if(pstmt!=null) try {pstmt.close();} catch(SQLException e){System.out.println(e);}	
	}


	private MemberDTO checkID(String id) {
		Connection conn = getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO dto = new MemberDTO();
		
		StringBuilder sql = new StringBuilder();
		sql.append("                select             ");
		sql.append("  memberno,id,pw,name,email,dates  ");
		sql.append("    from member                    ");
		sql.append("     where id = ?                  ");
		
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
					dto.setMemberno(rs.getInt("memberno"));
					dto.setId(rs.getString("id"));
					dto.setPw(rs.getString("pw"));
					dto.setName(rs.getString("name"));
					dto.setEmail(rs.getString("email"));
					dto.setDates(rs.getDate("dates"));
			}
				
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e){}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e){}
			if(conn!=null) try {conn.close();}catch(SQLException e){}
 		}
		return dto;
	}//end checkID

	
	
}
