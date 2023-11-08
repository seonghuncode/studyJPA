package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
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

            //조회
            Member findMember = em.find(Member.class, 1L); //자바 컬렉션과 같은 의미(객체를 대신 저장해주는 역할..?)
            System.out.println("findMember.id : " + findMember.getId());
            System.out.println("findMember.name : " + findMember.getName());

            //삭제
//            em.remove(findMember);

            //수정 (jpa통해 가지고온 데이터는 jpa가 관리하기 때문에 jpa가 commit시점에 변경이 있는지 확인하기 대문에 따로 저장을 하지 않아도 된다.)
            //commit시점에 마지막에 update쿼리를 날려서 업데이트 해준다.
            findMember.setName("HelloJPA");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //동작이 끝나면 항상 닫아준다
        }

        emf.close();
    }

}
