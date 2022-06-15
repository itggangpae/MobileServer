package com.adam.mobileserver.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adam.mobileserver.model.Member;

//JpaRepository를 상속받을 때는 Entity 이름 과 Id로 설정한 속성의 자료형이 필요
public interface MemberRepository extends JpaRepository<Member, String>{
	//회원가입, 로그인 => 회원정보 가져오기, 회원정보 수정, 회원 탈퇴
}
