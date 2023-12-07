package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        //애플리케이션 로딩 시점에 한개만 만든다.(EntityManagerFactory : DB당 하나만 생성 / 애플리케이션이 실행될때 실행)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //일괄적인 트랜잭션 단위마다 만든다. (EntityManager : 요청이 올때마다 사용 삭재를 반복 하면서 사용 -> 쓰레드 공간을 공유하면 안된다.(쓰고 버린다는 개념으로 사용))
        //jpa모든 변경은 트랜잭션 안에서 실행해야 한다.
        EntityManager em = emf.createEntityManager();

        //트랜잭션을 얻는 작업
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작


        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "1000"));

            member.getFavoriteFoods().add("aaa");
            member.getFavoriteFoods().add("bbb");
            member.getFavoriteFoods().add("ccc");

            member.getAddressHistory().add(new AddressEntity("old1", "street1", "1111"));
            member.getAddressHistory().add(new AddressEntity("old2", "street2", "1111"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("======================================");
            Member findMember = em.find(Member.class, member.getId());

            //homeCity -> newCity로 수정할 경우
            //값타입 잘못된 수정 방법
            //findMember.getHomeAddress().setCity("newCity");

            //값 타입 옳은 수정 방법 : Address 인스턴스 자체를 새것으로 바구어 주어야 한다.
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newcity", a.getStreet(), a.getZipcode()));

            //컬렉션에 있는 치킨 -> 한식으로 수정할 경우
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            //주소를 변경할 경우
            System.out.println("======================================");
//            findMember.getAddressHistory().remove(new Address("old1", "street1", "1111")); //지우기 위해서는 Address클래스에 equeals를 오버라이드 구현을 해주어야 한다.(이유  : 기본은 == 비교이기 대문에 변경 필요)
//            findMember.getAddressHistory().add(new Address("newCity1", "street1", "1111"));

           tx.commit(); // -> 이때 DB에 쿼라가 날라간다.

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close(); //동작이 끝나면 항상 닫아준다
        }

        emf.close(); //was가 내려갈대 종료 (리소스가 내부적으로 종료)
    }



}
