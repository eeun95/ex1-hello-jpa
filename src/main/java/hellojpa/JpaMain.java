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

            team.getMemberList().add(member);

            Member findMember = em.find(Member.class, member.getId());

            System.out.println(findMember.getTeam().getId());

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
