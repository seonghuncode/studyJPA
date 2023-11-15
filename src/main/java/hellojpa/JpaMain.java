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

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("Member1");
            member.setTeamId(team.getId()); //member를 Team에 소속 시키기 위해서
            em.persist(member);

            //특정 멤버에 대한 팀을 찾을 경우 거쳐야 하는 단계가 복잡하다. -> 연관관계가 없기 때문에
            Member findMember = em.find(Member.class, member.getId());
            Long findTeamId = findMember.getTeamId() ;
            Team findTeam = em.find(Team.class, findTeamId);

           tx.commit(); // -> 이때 DB에 쿼라가 날라간다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //동작이 끝나면 항상 닫아준다
        }

        emf.close(); //was가 내려갈대 종료 (리소스가 내부적으로 종료)
    }

}
