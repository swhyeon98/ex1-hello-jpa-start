package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 영속
            Member memberA = new Member(300L, "memberA");
            Member memberB = new Member(400L, "memberB");
            Member memberC = new Member(500L, "memberC");
            em.persist(memberA);
            em.persist(memberB);
            em.persist(memberC);
            em.setFlushMode(FlushModeType.COMMIT);

            System.out.println("==========FLUSH==========");
            TypedQuery<Member> query = em.createQuery("select m from Member as m", Member.class);
            List<Member> members = query.getResultList();
            for (Member member : members) {
                System.out.println("member = " + member.getName());
            }

            System.out.println("====================");
            tx.commit();
        } catch (Exception e) {
            em.clear();
        }

        emf.close();
    }
}
