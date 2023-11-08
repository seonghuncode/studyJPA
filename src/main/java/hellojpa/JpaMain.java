package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args){
        //애플리케이션 로딩 시점에 한개만 만든다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //일괄적인 트랜잭션 단위마다 만든다.
        EntityManager em = emf.createEntityManager();

        //트랜잭션을 얻는 작업
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랜잭션 시작

       //실제 동작하는 코드 작성(DB데이터 저장, 불러오기등)
        Member member = new Member();

        try {
            //JPA에서 모든 변경하는 작업은 트랜잭션 안에서 해야한다.
            member.setId(1L);
            member.setName("HelloA");
            member.setId(2L);
            member.setName("HelloB");

            em.persist(member); //jpa저장

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close(); //동작이 끝나면 항상 닫아준다
        }
        
        emf.close();
    }

}
