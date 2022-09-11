package com.app.banking.hibernate.bankingapphibernate.dao;

import com.app.banking.hibernate.bankingapphibernate.domain.Account;
import com.app.banking.hibernate.bankingapphibernate.domain.Customer;

import org.hibernate.HibernateException;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class HibernateCustomerDao implements Dao<Customer> {
  @PersistenceUnit
  private EntityManagerFactory entityManagerFactory;

  @Override
  public void save(Customer customer) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(customer);
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
  public boolean delete(Customer customer) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.remove(customer);
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
  public void  update(Customer customer) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.merge(customer);
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
  public void deleteAll(List<Customer> entities) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.remove(entities);
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
  public void saveAll(List<Customer> entities) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(entities);
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
  public List<Customer> findAll() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    return  entityManager.createQuery(" select c from Customer c ", Customer.class).getResultList();

  }

  @Override
  public boolean deleteById(long id) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    Customer customer = entityManager.find(Customer.class, id);
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.remove(customer);
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
  public Customer getOne(long id) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    return entityManager.find(Customer.class, id);
  }
}
