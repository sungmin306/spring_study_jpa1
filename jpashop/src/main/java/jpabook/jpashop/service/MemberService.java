package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) // jpa의 모든 데이터 변경은 transactional에서 실행되야함 스프링에서 제공하는 어노테이션을 이용 (자바 x) -> 기능이 더 많음
// 조회때 readOnly 사용 -> 성능 최적화에 도움
//@AllArgsConstructor // 필드값을 가지고 생성자 자동 생성
//@RequiredArgsConstructor // final이 붙은 필드를 가지고 생성자 자동 생성
public class MemberService {

    //@Autowired
    private final MemberRepository memberRepository; // 이렇게 하면 변경이 안됨 -> setterInjection을 이용 -> 애플리케이션을 돌아가는 시점에 누군가가 바꿀 수 있음
                                                // 생성사 injection 사용 final 사용 추천
    //@Autowired // 생성자가 한개면 스프링이 자동으로 injection을 해줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     *  회원가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
