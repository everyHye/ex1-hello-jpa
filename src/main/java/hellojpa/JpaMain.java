package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain  {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");

            // 수정할 때
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

            // 조회할 때
            // 테이블이 아니라 객체를 조
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();
            for (Member member : result){
                System.out.println("member = " +member.getName());
            }

//            em.persist(member); // 수정할 때는 필요없음 bc 컬랙션에 저장되는 것처럼 실행되어있어서
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
