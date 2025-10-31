package Graveyard;

import Graveyard.entities.CategoryEntity;
import Graveyard.utils.HibernateHelper;
import Graveyard.repositories.CategoryRepository;

import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("Привіт козаки!");

        CategoryRepository repo = new CategoryRepository();

        // CREATE
        // repo.create(new CategoryEntity("Борщ"));
        // repo.create(new CategoryEntity("Вареники"));

        // READ ALL
        List<CategoryEntity> categories = repo.findAll();
        System.out.println("Categories in DB:");
        categories.forEach(System.out::println);

        // READ BY ID
        repo.findById(1).ifPresentOrElse(
                c -> System.out.println("Found: " + c),
                () -> System.out.println("Category not found.")
        );

        // UPDATE
        // repo.findById(1).ifPresent(cat -> {
        //     cat.setName("Оновлена категорія");
        //     repo.update(cat);
        // });

        // DELETE
        // repo.delete(2);

        // Show final list
        System.out.println("After operations:");
        repo.findAll().forEach(System.out::println);

        // OLD CODE
        // 
        // // SimpleInsert();
        // // SimpleInsertFactory();

        // var session = HibernateHelper.getSession();
        // try {
        //     var result = session.createSelectionQuery("from CategoryEntity", CategoryEntity.class)
        //             .getResultList();
        //     result.forEach(System.out::println);
        // }catch (Exception e) {
        //     System.out.println("Хюсто у нас проблеми "+e);
        // }
    }

    private static void SimpleInsert() {
        var session = HibernateHelper.getSession();
        try {
            session.beginTransaction();
            CategoryEntity [] list = new CategoryEntity[2];
            list[0] = new CategoryEntity();
            list[0].setName("Калабуджа");
            //session.save(category);
            session.persist(list[0]);

            list[1] = new CategoryEntity();
            list[1].setName("Пельмені");
            session.persist(list[1]);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.out.println("У нас проблеми Хюстон :("+e);
        }
        finally {
            session.close();
        }
    }

    private static void SimpleInsertFactory() {
        var sessionFactory = HibernateHelper.getSessionFactory();
        sessionFactory.inTransaction(session -> {
            session.persist(new CategoryEntity("Кабачок"));
            session.persist(new CategoryEntity("Диня"));
        });
        // sessionFactory.close();
    }
}
