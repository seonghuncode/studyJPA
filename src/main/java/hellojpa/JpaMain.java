package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        //실제 동작하는 코드 작성(DB데이터 저장, 불러오기등)
        em.close(); //동작이 끝나면 항상 닫아준다

        emf.close();
    }

}
