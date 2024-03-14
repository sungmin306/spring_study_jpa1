package jpabook.jpashop;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext //스프링부트 위에있으면 스프링 부트가 자동으로 주입해줌 엔티티 메니저 생성하는 것이 자동으로 다 들어감(스프링부트가)
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // 굳이 Id만 반환하는이유 커멘드와 쿼리를 분리해라 라는 의미로 (id만 있으면 조회가능하기 떄문에)
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

}
