package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

@Repository // 열어보면 @Component가 있음 -> 빈으로 자동 등록
public class MemberRepository {

    @PersistenceContext // 엔티티 메니저 펙토리 자동 주입 @Autowired로 변경 가능 -> @RequiredArgsConstructor로 일관되게 사용 가능 // 스프링부트가 지원해줌
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //JPQL은 객체를 기반으로 SQL은 테이블을 기반으로
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
