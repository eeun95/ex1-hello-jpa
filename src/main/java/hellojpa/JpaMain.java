package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        // application 로딩 시점에 딱 하나만 만듦
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //code (db에 데이터를 저장한다거나 불러온다거나)

            /*Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");

             */

            //Member findMember = em.find(Member.class, 1L);
            //System.out.println(findMember.getId() + " " + findMember.getName());
            //findMember.setName("HelloJPA");

            // JPQL 은 테이블을 대상으로 쿼리를 짜지 않음 Entity 객체를 대상으로 가져옴
            List<Member> result = em.createQuery("select m from Member as m where m.name='HelloB'", Member.class)
                    //.setFirstResult(1)    pagenation  1번부터
                    //.setMaxResults(10)    pagenation  10개가져와
                    .getResultList();

            for (Member member : result) {
                System.out.println(member.getName());
            }
            Member m = new Member(150L, "A");
            em.persist(m);

            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZ");

            System.out.println("===============");
            // java 컬렉션을 다루는 것처럼 설계됐기 때문에 수정 시 따로 persist 안해줘도됨
            // JPA는 값을 바꾸면 트랜잭션이 커밋 되는 시점에 값을 변경함

            /*
            삽입
            em.persist(member);
             */
            /*
            삭제
            em.remove(findMember);
             */

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
