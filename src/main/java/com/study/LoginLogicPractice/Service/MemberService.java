package com.study.LoginLogicPractice.Service;

import com.study.LoginLogicPractice.Dto.MemberDto;
import com.study.LoginLogicPractice.Entity.MemberEntity;
import com.study.LoginLogicPractice.Repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public void save(MemberDto memberDto) {
        // dto -> entity로 변환
        // repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
        memberRepository.save(memberEntity);
    }

    public MemberDto login(MemberDto memberDto) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        */
        Optional<MemberEntity> byMemberEmail =  memberRepository.findByMemberEmail(memberDto.getMemberEmail());
        if(byMemberEmail.isPresent()){
            // 조회결과가 있다(해당 이메일을 가진 회원 정보가 있다.)
            MemberEntity memberEntity = byMemberEmail.get();
            if(memberEntity.getMemberPassword().equals(memberDto.getMemberPassword())){
                //비밀번호가 일치하는 경우
                // entity -> dto 변환 후 리턴
                MemberDto dto = MemberDto.toMemberDto(memberEntity);
                return dto;

            }
            else{
                return null;
            }
        }
        else{
            // 조회결과가 없다(해당 이메이릉ㄹ 가진 회원이 없다.)
            return null;
        }

    }

    public List<MemberDto> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();
        for(MemberEntity memberEntity: memberEntityList){
            memberDtoList.add(MemberDto.toMemberDto(memberEntity));
            //MemberDto memberDto = MemberDto.toMemberDto(memberEntity);
            //memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }

    public MemberDto findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()){
            //MemberEntity memberEntity = optionalMemberEntity.get();
            //MemberDto memberDto = MemberDto.toMemberDto(memberEntity);
            //return memberDto;
            return MemberDto.toMemberDto(optionalMemberEntity.get());
        }
        else{
            return null;
        }
    }

    public MemberDto updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if(optionalMemberEntity.isPresent()){
            return MemberDto.toMemberDto(optionalMemberEntity.get());
        }
        else{
            return null;
        }
    }

    public void update(MemberDto memberDto) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDto));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public String emailCheck(String memberEmail) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
        if(byMemberEmail.isPresent()){
            return null;
        }
        else{
            return "ok";
        }
    }
}
