package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // JPA 사용시 필요
public class MemberService {

    // ctrl + shift + t => test 만들기

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
    * 회원가입
    * */
    public Long join(Member member){

        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();

    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
    * 전체 회원 조회
    * */
    public List<Member> fidMembers(){
        return memberRepository.findAll();
    }

    // ctrl + alt + v => Optional 단축키
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
