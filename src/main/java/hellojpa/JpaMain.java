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

            Address address = new Address("city", "street", "10000");
            Member member = new Member();
            member.setName("DD");
            member.setHomeAddress(address);
            em.persist(member);

            member.getHomeAddress().setCity("dd");

            Address copyAddress = new Address(address.getCity(),address.getStreet(), address.getZipcode());

            Member member2 = new Member();
            member2.setName("DD2");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

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
