package com.adam.mobileserver;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adam.mobileserver.model.Member;
import com.adam.mobileserver.persistence.MemberRepository;

@SpringBootTest
public class RepositoryTest {
	@Autowired
	private MemberRepository memberRepository;

	//Member에 데이터 삽입
	@Test
	public void testRegisterMember() {
		String password = BCrypt.hashpw("123456", BCrypt.gensalt());
		Member member = 
				Member.builder()
				.email("ggangpae1@gmail.com")
				.password(password)
				.name("아담")
				.imageurl("adam.png")
				.build();

		memberRepository.save(member);
	}

	//회원 정보 가져오기 - 수정이나 로그인에서 사용
	//@Test
	public void testGetMember() {
		Optional<Member> optional = memberRepository.findById("ggangpae1@gmail.com");
		if(optional.isPresent()) {
			Member member = optional.get();
			System.out.println(member);
			//로그인은 이렇게 데이터를 가져와서 비밀번호를 비교하면 됩니다.
		}else {
			System.out.println("존재하지 않는 데이터 입니다.");
		}
	}

	//데이터 수정
	//@Test
	public void testUpdateMember() {
		String password = BCrypt.hashpw("111111", BCrypt.gensalt());
		Member member = 
				Member.builder()
				.email("ggangpae1@gmail.com")
				.password(password)
				.name("군계")
				.imageurl("user.png")
				.build();

		memberRepository.save(member);
	}
	
	//데이터 삭제
	//@Test
	public void testDeleteMember() {
		Member member = 
				Member.builder()
				.email("ggangpae1@gmail.com")
				.build();

		memberRepository.delete(member);
		
	}


}
