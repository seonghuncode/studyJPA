package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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

            //조회
            Member findMember = em.find(Member.class, 1L); //자바 컬렉션과 같은 의미(객체를 대신 저장해주는 역할..?)
            /*
            JPQL : 원하는 데이터를 특정해서 가지고 오기 위한 개념
            jpa는 대상을 기준으로 쿼리를 만들지 않는다 - >객체를 대상으로 쿼리를 만든다(엔티티 객체를 대상으로 쿼리를 날리는 객체지향 언어 jpsql을 사용가능)
            (sql은 데이터베이스 테이블을 대상으로 쿼리)
            jpa는 객체지향 쿼리로 persistence.xml에서 설정한 DB방언에 맞게 쿼리를 작성해 준다.
            * */
            List<Member> result = em.createQuery("select m from Member as m", Member.class) //Member.class : 타입 (m : member 엔티티)
                    .setFirstResult(1) 
                    .setMaxResults(10) //1번 부터 10개 가지로 오라는 의미(페이지 네이션)
                   .getResultList();

            for(Member member : result){
                System.out.println("member.name : " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //동작이 끝나면 항상 닫아준다
        }

        emf.close(); //was가 내려갈대 종료 (리소스가 내부적으로 종료)
    }

}
