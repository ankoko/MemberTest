package com.kosta.com;

import java.util.Date;
 
public class MemberDTO {
	
	private int memberno;
	private String id;
	private String pw;
	private String name; 
	private String email;
	private Date dates;
	
	//생성자
	
	public MemberDTO() {
		super();
	}
	
	public MemberDTO(int memberno, String id, String pw, String name, String email, Date dates) {
		super();
		this.memberno = memberno;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.dates = dates;
	}
	
	public MemberDTO(int memberno, String id, String name, String email, Date dates) {
		super();
		this.memberno = memberno;
		this.id = id;
		this.name = name;
		this.email = email;
		this.dates = dates;
	}


	//toString
	@Override
	public String toString() {
		return "MemberDTO [memberno=" + memberno + ", id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email
				+ ", dates=" + dates + "]";
	}

	//getter
	public int getMemberno() {
		return memberno;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Date getDates() {
		return dates;
	}
	
	//setter
	public void setMemberno(int memberno) {
		this.memberno = memberno;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDates(Date dates) {
		this.dates = dates;
	}
}
