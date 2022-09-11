package com.app.banking.hibernate.bankingapphibernate.dao;

import com.app.banking.hibernate.bankingapphibernate.domain.Customer;
import com.app.banking.hibernate.bankingapphibernate.domain.Employer;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class HibernateEmployerDao implements Dao<Employer> {
  @PersistenceUnit
  private EntityManagerFactory entityManagerFactory;

  @Override
  public void save(Employer employer) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(employer);
      entityManager.getTransaction().commit();
    } catch (HibernateException ex) {
      entityManager.getTransaction().rollback();
    } finally {
      if (entityManager != null) {
        entityManager.close();
      }
    }
  }

  @Override
  public boolean delete(Employer employer) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.remove(employer);
      entityManager.getTransaction().commit();
      return true;
    } catch (HibernateException ex) {
      entityManager.getTransaction().rollback();
    } finally {
      if (entityManager != null) {
        entityManager.close();
      }
    }
    return false;
  }

  @Override
  public void deleteAll(List<Employer> employers) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.remove(employers);
      entityManager.getTransaction().commit();

    } catch (HibernateException ex) {
      entityManager.getTransaction().rollback();
    } finally {
      if (entityManager != null) {
        entityManager.close();
      }
    }
  }

  @Override
  public void saveAll(List<Employer> employers) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(employers);
      entityManager.getTransaction().commit();

    } catch (HibernateException ex) {
      entityManager.getTransaction().rollback();
    } finally {
      if (entityManager != null) {
        entityManager.close();
      }
    }
  }

  @Override
  public List<Employer> findAll() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    return  entityManager.createQuery(" select e from Employer e ", Employer.class).getResultList();

  }

  @Override
  public boolean deleteById(long id) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    Employer employer = entityManager.find(Employer.class, id);
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.remove(employer);
      entityManager.getTransaction().commit();
      return true;
    } catch (HibernateException ex) {
      entityManager.getTransaction().rollback();
    } finally {
      if (entityManager != null) {
        entityManager.close();
      }
    }
    return false;
  }

  @Override
  public Employer getOne(long id) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    return entityManager.find(Employer.class, id);
  }
  public void  update(Employer employer) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.merge(employer);
      entityManager.getTransaction().commit();
    } catch (HibernateException ex) {
      entityManager.getTransaction().rollback();
    } finally {
      if (entityManager != null) {
        entityManager.close();
      }
    }

  }
}
