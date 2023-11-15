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

            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("Member1");
            member.setTeam(team); //team을 넣어주면 JPA에서 알아서 team에서 PK값을 꺼내 insert할때 FK값으로 사용한다
            em.persist(member);

            //캐시값을 초기화시켜 실제 DB에서 값을 꺼내오는 과정을 확인하고 싶을 경우 캐시 초기화 방법법
           em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            //값을 수정하는 방법 (Team을 수정 하는 방법)
            //1. 원하는 데이터를 찾는다
            Team newTeam = em.find(Team.class, 100L);
            //2. 수정
            findMember.setTeam(newTeam);

           tx.commit(); // -> 이때 DB에 쿼라가 날라간다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //동작이 끝나면 항상 닫아준다
        }

        emf.close(); //was가 내려갈대 종료 (리소스가 내부적으로 종료)
    }

}
