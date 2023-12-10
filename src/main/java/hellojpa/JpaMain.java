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
            //jsql은 엔티티를 대상으로 한다.
           // Member는 테이블이 아닌 엔티티를 가르킨다. (엔티티에 대한 쿼리를 실행하면 jpa에서 엔티티의 매핑 정보를 읽고 적절한 sql 쿼리를 만들어 DB에 요청한다.)
            List<Member> result = em.createQuery(
                    "select m From Member as m where m.username like '%kim%'",
                    Member.class
            ).getResultList();

            for(Member member : result){
                System.out.println("member : " + member);
            }

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
