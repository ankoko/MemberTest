package com.kosta.com;

import java.util.Scanner;

public class MemberTest {
	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO();
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("=======================================================");
			System.out.println("Menu\n1.회원가입\t 2.회원수정\t 3.회원삭제\t 4.회원보기\t 5.전체조회\t 6.종료");
			System.out.println("=======================================================");
			System.out.println(" 원하는 메뉴를 입력하세요 " );
			String choice = scanner.nextLine();
					

			if(choice.trim().equals("1")) {
				System.out.println("1. 회원가입");
				System.out.println("원하는 ID를 입력하세요");
				String id = scanner.nextLine();

				System.out.println("원하는 비밀번호를 입력하세요");
				String pw = scanner.nextLine();

				System.out.println("이름을 입력하세요");
				String name = scanner.nextLine();

				System.out.println("이메일을 입력하세요");
				String email = scanner.nextLine();

				int result = dao.create(id, pw, name, email);
				if (result >= 1) {
					System.out.println("입력성공");
				} else {
					System.out.println("입력실패");
				}
			}
			
			else if(choice.trim().equals("2")){
				System.out.println("2. 회원수정");
				System.out.println("수정할 ID를 입력하세요");
				String id = scanner.nextLine();
				int result = dao.update(id, scanner);
				if(result>=1)
					System.out.println("수정 완료");
				else
					System.out.println("수정 실패");
			}
			
			else if (choice.trim().equals("3")) {
				System.out.println("3.회원삭제");
				System.out.println("삭제할 ID를 입력하세요 ");
				String id = scanner.nextLine();
				int result = dao.delete(id);
				if(result >=1)
					System.out.println("삭제완료");
				else 
					System.out.println("삭제 실패");
			}

			else if(choice.trim().equals("4")) {
				System.out.println("4. 회원보기");
				System.out.println("조회할 ID를 입력하세요");
				String id = scanner.nextLine();
				dao.read(id, scanner);
			}
			
			else if(choice.trim().equals("5")) {
				System.out.println("5. 전체조회");
				dao.showAll();
			}
			
			else if(choice.trim().equals("6")) {
				System.exit(0);
			}
			
			else {
				System.out.println("※ 잘못된 입력어입니다 ※");
			}
			

		
		}//end while
		
	}//end main

}