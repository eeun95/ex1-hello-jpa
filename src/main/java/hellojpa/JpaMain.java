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

            Member member = new Member();
            member.setName("DD");
            member.setHomeAddress(new Address("dd", "dd", "dd"));

            em.persist(member);
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
