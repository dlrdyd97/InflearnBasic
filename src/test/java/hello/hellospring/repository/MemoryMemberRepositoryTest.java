package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository=new MemoryMemberRepository();

    @AfterEach // 메서드가 호출될때마다 실행 , 콜백 메서드
    public void afterEach(){
        repository.clearStore();
    }

    // test를 먼저하고 개발을 진행하면 테스트주도개발 = tdd

    @Test
    public void save(){
        Member member=new Member();
        member.setName("good");

        Member member1=new Member();
        member1.setName("good1");

        repository.save(member);
        

        Member result = repository.findById(member.getId()).get();

        Assertions.assertEquals(result,member);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);

    }
    
    @Test
    public void findByName(){
        Member member1 =new Member();
        member1.setName("good1");
        repository.save(member1);

        Member member2 =new Member(); // 복사 후 shift + F6 으로 변수명 쉽게 변경가능
        member2.setName("good2");
        repository.save(member2);

        Member result= repository.findByName("good1").get();

        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);

                
    }

    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("good1");
        repository.save(member1);


        Member member2=new Member();
        member2.setName("good2");
        repository.save(member2);

        List<Member> result=repository.findAll();

        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);


    }

}
