package com.kosta.com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class MemberDAO {
	
	//회원가입
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
	
	
	//회원수정
	public int update(String id, Scanner sc) {
		 Connection conn = getConn();
		 PreparedStatement pstmt = null;
		 StringBuilder sql = new StringBuilder();
		 MemberDTO dto= checkID(id);
		 int result = 0;
		 
		 try {
			 if(dto.getId() == null) {
				 System.out.println("수정할 수 있는 ID가 없습니다.");
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
	
	
	//삭제
	public int delete(String id) {
		MemberDTO dto = checkID(id);
		int result = 0;
		
		if(dto.getId() == null) {
			System.out.println("삭제할 ID가 없습니다.");
		}
		else { 
			Connection conn = getConn();
			PreparedStatement pstmt = null;
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from member ");
			sql.append(" where              ");
			sql.append(" id = ? ");
			   
			try {
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				result = pstmt.executeUpdate();
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				close(pstmt, conn);
			}
		}
		return result;
	}
	
	
	//회원조회
	public void read(String id, Scanner sc) {
		
		MemberDTO dto = checkID(id);
		
		 if(dto.getId() == null) {
			 System.out.println("조회할 ID가 없습니다.");
		 }
		 else {
			 System.out.printf("아이디: %s\n",dto.getId());
			 System.out.printf("패스워드: %s\n",dto.getPw());
			 System.out.printf("이름: %s\n",dto.getName());
			 System.out.printf("이메일: %s\n",dto.getEmail());
			 System.out.printf("가입일자: %s\n",dto.getDates());
		
		 }	
	}//end read
	
	
	//전체조회
	public void showAll() {
		Connection conn = getConn();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		ResultSet rs = null;
		
		try {
			sql.append("  select       ");
			sql.append("  memberno     ");
			sql.append("  , id         ");
			sql.append("  , name       ");
			sql.append("  , email      ");
			sql.append("  , dates      ");
			sql.append("  from member  ");
			
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.printf("번호:%d  아이디:%s 이름:%s 이메일:%s 가입일자:%s\n"
						,rs.getInt("memberno")
						,rs.getString("id")
						,rs.getString("name")
						,rs.getString("email")
						,rs.getDate("dates")
						);
			}
		}
		catch(SQLException e){
			System.out.println(e);
		}
		finally {
			close(pstmt, conn);
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
		}
	}//end showAll
	
	
	
	
	
	private Connection getConn() {
		String className = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "hr";
		String pwd = "hr";
		Connection conn = null;
				
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, pwd);
			//System.out.println("작동중");
		}catch(SQLException| ClassNotFoundException e) {
			System.out.println(e);
		}
		return conn;
	}//end getConn 
	
	
	private void close(PreparedStatement pstmt, Connection conn) {
		if(conn!=null) try {conn.close();}catch(SQLException e){System.out.println(e);}
		if(pstmt!=null) try {pstmt.close();} catch(SQLException e){System.out.println(e);}	
	}

	
	//ID조회
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
