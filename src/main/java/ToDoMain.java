import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ToDoMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    }
}
