package hellojpa;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        // application 로딩 시점에 딱 하나만 만듦
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("memberA");
            member.setTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setName("memberB");
            member2.setTeam(team);
            em.persist(member2);

            team.getMemberList().add(member);
            team.getMemberList().add(member2);

            em.flush();
            em.clear();

//            Member findMember = em.find(Member.class, member.getId());
//
//            System.out.println("==");
//
//            // fetchType LAZY 일땐 team이 프록시 객체라 member에 대한 select 쿼리가 나감
//            // EAGER면 member team 조인해서 한방 쿼리로 나옴 둘다 실제 객체
//            System.out.println(findMember.getClass());
//            System.out.println(findMember.getTeam().getClass());
//
//            // 실제 team의 값에 접근할 때 select 쿼리가 나감
//            System.out.println(findMember.getTeam().getName());

            System.out.println("==N+1==");
            // EAGER는 JPQL에서 N+1 문제 발생
            List<Member> m = em.createQuery("select m from Member m", Member.class).getResultList();

            tx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }


}
