package hellojpa;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        // application 로딩 시점에 딱 하나만 만듦
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//
//            Member member = em.find(Member.class, 1L);
//            printMemberAndTeam(member);
//            printMember(member);

            Team team = new Team();
            team.setName("A");
            em.persist(team);

            Member member = new Member();
            member.setName("HI");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member m = em.find(Member.class, member.getId());

            System.out.println("m = "+m.getTeam().getClass());

            System.out.println("==============");
            m.getTeam().getName();
            System.out.println("==============");

            tx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void printMember(Member member) {
        System.out.println("member = " + member.getName());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getName();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team.getName());

    }
}
