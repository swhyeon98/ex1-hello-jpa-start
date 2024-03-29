package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();


        try {
            // 영속
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            em.clear();
            System.out.println("====================");

            Member member2 = em.find(Member.class, 150L);

            System.out.println("====================");
            tx.commit();
        } catch (Exception e) {
            em.clear();
        }
        emf.close();
    }
}
