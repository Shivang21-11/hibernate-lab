import jakarta.persistence.*;
import java.util.List;

public class App {
    public static void main(String[] args) {

        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("lab-pu");
        EntityManager em = emf.createEntityManager();

        // CREATE
        em.getTransaction().begin();
        Student s1 = new Student("Rahul", "rahul@gmail.com");
        Student s2 = new Student("Amit", "amit@gmail.com");
        em.persist(s1);
        em.persist(s2);
        em.getTransaction().commit();
        System.out.println("Saved: " + s1.getName() + ", " + s2.getName());

        // READ
        Student found = em.find(Student.class, s1.getId());
        System.out.println("Found: " + found.getName());

        // SEARCH using JPQL
        List<Student> list = em.createQuery(
            "SELECT s FROM Student s WHERE s.name = :name", Student.class)
            .setParameter("name", "Rahul")
            .getResultList();
        for (Student s : list)
            System.out.println("Query result: " + s.getEmail());

        // UPDATE
        em.getTransaction().begin();
        found.setName("Rahul Sharma");
        em.getTransaction().commit();
        System.out.println("Updated name: " + found.getName());

        // DELETE
        em.getTransaction().begin();
        em.remove(s2);
        em.getTransaction().commit();
        System.out.println("Deleted: Amit");

        // SHOW TABLE (replaces the MySQL screenshot)
        System.out.println();
        System.out.println("SELECT * FROM student;");
        System.out.println("+----+-----------------+---------------+");
        System.out.println("| id | email           | name          |");
        System.out.println("+----+-----------------+---------------+");
        for (Student s : em.createQuery("SELECT s FROM Student s", Student.class).getResultList())
            System.out.printf("| %-2d | %-15s | %-13s |%n", s.getId(), s.getEmail(), s.getName());
        System.out.println("+----+-----------------+---------------+");

        em.close();
        emf.close();
    }
}
