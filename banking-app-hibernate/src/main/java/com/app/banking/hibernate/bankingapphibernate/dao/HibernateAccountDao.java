package com.app.banking.hibernate.bankingapphibernate.dao;


import com.app.banking.hibernate.bankingapphibernate.domain.Account;
import org.hibernate.HibernateException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class HibernateAccountDao implements Dao<Account> {

  @PersistenceUnit
  private EntityManagerFactory entityManagerFactory;


  @Override
  public List<Account> findAll() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
 return  entityManager.createQuery(" select a from Account a ", Account.class).getResultList();

  }

  @Override
  public boolean deleteById(long id) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    Account account = entityManager.find(Account.class, id);
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.remove(account);
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



  public Account getOne(long id) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    return entityManager.find(Account.class, id);
  }


  public void update(Account account) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.merge(account);
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
  public void save(Account account) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(account);
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
  public boolean delete(Account account) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.remove(account);
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
  public void deleteAll(List<Account> accounts) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.remove(accounts);
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
  public void saveAll(List<Account> accounts) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(accounts);
      entityManager.getTransaction().commit();

    } catch (HibernateException ex) {
      entityManager.getTransaction().rollback();
    } finally {
      if (entityManager != null) {
        entityManager.close();
      }
    }
  }

  public List<Account> getByCustomerId (Long customerId) {

    EntityManager entityManager = entityManagerFactory.createEntityManager();

    return entityManager.createQuery("SELECT a from Account a where a.customer.id = :customerId", Account.class)
      .setParameter("customerId", customerId).getResultList();

  }

  public  Account getByNumber (String number) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    return entityManager.createQuery("SELECT a from Account a where a.number = :number", Account.class)
      .setParameter("number", number).getSingleResult();
  }

public void setFunds (String number, Double funds) {
  EntityManager entityManager = entityManagerFactory.createEntityManager();
  Account account = entityManager.createQuery("SELECT a from Account a where a.number = :number", Account.class)
    .setParameter("number", number).getSingleResult();
  account.setBalance(funds);

  try {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.persist(account);
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