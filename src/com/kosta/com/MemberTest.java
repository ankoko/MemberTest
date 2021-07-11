package com.kosta.com;

import java.util.Scanner;

public class MemberTest {
	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO();
		Scanner scanner = new Scanner(System.in);


			// create
			
//				System.out.println("원하는 ID를 입력하세요");
//				String id = scanner.nextLine();
//
//				System.out.println("원하는 비밀번호를 입력하세요");
//				String pw = scanner.nextLine();
//
//				System.out.println("이름을 입력하세요");
//				String name = scanner.nextLine();
//
//				System.out.println("이메일을 입력하세요");
//				String email = scanner.nextLine();
//
//				int result = dao.create(id, pw, name, email);
//				if (result >= 1) {
//					System.out.println("입력성공");
//				} else {
//					System.out.println("입력실패");
//				}



		
		System.out.println("원하는 ID를 입력하세요");
		String id = scanner.nextLine();
		int result = dao.update(id, scanner);
		if(result>=1)
			System.out.println("수정 완료");
		else
			System.out.println("수정 실패");

	}

}