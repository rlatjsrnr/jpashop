package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    private Address address;

    @OneToMany(mappedBy = "member") // 나는 order 테이블 내의 member 필드 의 거울이다.(읽기전용)
    private List<Order> orders = new ArrayList<>(); // 하이버네이트가 관리할 것이기 때문에 객체 생성 시에 초기화 한 후 건들지말자
}
