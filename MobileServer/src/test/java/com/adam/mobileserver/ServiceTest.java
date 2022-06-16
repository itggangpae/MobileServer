package com.adam.mobileserver;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adam.mobileserver.dto.MemberDTO;
import com.adam.mobileserver.service.MemberService;

@SpringBootTest
public class ServiceTest {
	@Autowired
	private MemberService memberService;
	
	//회원 가입 테스트
	//@Test
	public void testRegisterMember() {
		//처음 추가를 할 때는 성공해야 하고 email 과 name을 중복된 데이터로 설정해서 확인
		
		MemberDTO dto = MemberDTO.builder()
				.email("ggangpae2@gmail.com")
				.password("123456")
				.name("아담")
				.imageurl("adam.png")
				.build();
		String result = memberService.registerMember(dto);
		System.out.println(result);
	}
	
	//회원 정보 가져오기
	//@Test
	public void testGetMember() {
		MemberDTO dto = MemberDTO.builder()
				.email("ggangpae2@gmail.com")
				.build();
		MemberDTO result = memberService.getMemer(dto);
		System.out.println(result);
	}
	
	//로그인 테스트
	@Test
	public void testLoginMember() {
		MemberDTO dto = MemberDTO.builder()
				.email("ggangpae1@gmail.com")
				.password("123456")
				.build();
		MemberDTO result = memberService.loginMemer(dto);
		System.out.println(result);
		
		//오늘 날짜를 문자열로 생성
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String d = sdf.format(date);
		try {
			FileOutputStream fos = new FileOutputStream(
					"C:\\Users\\tjoeun302\\lecture\\" + d + ".txt", true);
			PrintWriter pw = new PrintWriter(fos);
			pw.println("내용");
			pw.flush();
			pw.close();
		}catch(Exception e) {}
		
	}
}
