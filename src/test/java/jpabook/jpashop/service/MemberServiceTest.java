package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void join() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        //then
        em.flush();
        Assertions.assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void valid() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        // when
        memberService.join(member1);
        memberService.join(member2);

        //then
        Assertions.fail("예외가 발생해야 한다.");

    }

}