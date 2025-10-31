package Graveyard.repositories;

import Graveyard.entities.CategoryEntity;
import Graveyard.utils.HibernateHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class CategoryRepository {

    public void create(CategoryEntity category) {
        try (Session session = HibernateHelper.getSession()) {
            Transaction tx = session.beginTransaction();
            if (category.getDateCreated() == null) {
                category.setDateCreated(java.time.LocalDateTime.now());
            }
            session.persist(category);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error while creating category: " + e.getMessage());
        }
    }

    public List<CategoryEntity> findAll() {
        try (Session session = HibernateHelper.getSession()) {
            return session.createSelectionQuery("from CategoryEntity", CategoryEntity.class).getResultList();
        }
    }

    public Optional<CategoryEntity> findById(int id) {
        try (Session session = HibernateHelper.getSession()) {
            CategoryEntity category = session.get(CategoryEntity.class, id);
            return Optional.ofNullable(category);
        }
    }

    public void update(CategoryEntity category) {
        try (Session session = HibernateHelper.getSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(category);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error while updating category: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try (Session session = HibernateHelper.getSession()) {
            Transaction tx = session.beginTransaction();
            CategoryEntity category = session.get(CategoryEntity.class, id);
            if (category != null) {
                session.remove(category);
                System.out.println("🗑 Deleted category with ID: " + id);
            } else {
                System.out.println("⚠ Category with ID " + id + " not found.");
            }
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error while deleting category: " + e.getMessage());
        }
    }
}
