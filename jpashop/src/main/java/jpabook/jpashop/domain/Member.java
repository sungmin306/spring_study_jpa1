package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "name_id")
    private Long id;

    private String name;

    @Embedded // @Embeddable 하나만 있어도 되긴함 둘다 적어주는 것이 좋음
    private Address address;

    @OneToMany(mappedBy = "member") // order테이블의 member가 주인이다.
    private List<Order> orders = new ArrayList<>();

}
