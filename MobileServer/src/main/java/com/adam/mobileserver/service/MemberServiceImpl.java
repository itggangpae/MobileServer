package com.adam.mobileserver.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.adam.mobileserver.dto.MemberDTO;
import com.adam.mobileserver.model.Member;
import com.adam.mobileserver.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;

	@Override
	public String registerMember(MemberDTO dto) {
		Member member = dtoToEntity(dto);
		//이메일 중복 체크
		Optional<Member> optional = memberRepository.findById(dto.getEmail());
		if(optional.isPresent()) {
			return "존재하는 이메일";
		}
		//이름 중복 체크
		List<Member> list = memberRepository.findMemberByName(dto.getName());
		if(list.size() > 0) {
			return "존재하는 이름";
		}
		
		memberRepository.save(member);
		return member.getEmail();
	}

	@Override
	public MemberDTO loginMemer(MemberDTO memberDTO) {
		//이메일을 가지고 데이터를 찾아옵니다.
		Optional <Member> optional = 
				memberRepository.findById(memberDTO.getEmail());
		//존재하는 이메일
		if(optional.isPresent()) {
			//비밀번호 확인
			Member member = optional.get();
			if(BCrypt.checkpw(memberDTO.getPassword(), member.getPassword())) {
				//로그인에 성공했을 때 로그인 한 시간을 업데이트
				ZonedDateTime nowUTC = ZonedDateTime.now(ZoneId.of("UTC"));
				LocalDateTime now = nowUTC.withZoneSameInstant(
						ZoneId.of("Asia/Seoul")).toLocalDateTime();
				Member updateMember = Member.builder()
						.email(member.getEmail())
						.password(member.getPassword())
						.imageurl(member.getImageurl())
						.name(member.getName())
						.lastlogindate(now)
						.build();
				memberRepository.save(updateMember);
				
				return entityToDto(member);
			}else {
				return null;
			}
		}
		//존재하지 않는 이메일
		else {
			return null;
		}
	}

	@Override
	public MemberDTO getMemer(MemberDTO memberDTO) {
		//이메일을 가지고 데이터를 찾아옵니다.
		Optional <Member> optional = 
				memberRepository.findById(memberDTO.getEmail());
		//존재하는 이메일
		if(optional.isPresent()) {
			//비밀번호 확인
			Member member = optional.get();
			return entityToDto(member);
		}else {
			return null;
		}
	}

	@Override
	public String updateMember(MemberDTO dto) {
		Member member = dtoToEntity(dto);
		memberRepository.save(member);
		return member.getEmail();
	}

	@Override
	public String deleteMember(MemberDTO dto) {
		Member member = dtoToEntity(dto);
		memberRepository.delete(member);
		return member.getEmail();
	}

}
