package com.app.banking.hibernate.bankingapphibernate.dao;

import com.app.banking.hibernate.bankingapphibernate.domain.Employer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class HibernateEmployerDao implements Dao<Employer> {
  @PersistenceUnit
  private EntityManagerFactory entityManagerFactory;

  @Override
  public void save(Employer obj) {

  }

  @Override
  public boolean delete(Employer obj) {
    return false;
  }

  @Override
  public void deleteAll(List<Employer> entities) {

  }

  @Override
  public void saveAll(List<Employer> entities) {

  }

  @Override
  public List<Employer> findAll() {
    return null;
  }

  @Override
  public boolean deleteById(long id) {
    return false;
  }

  @Override
  public Employer getOne(long id) {
    return null;
  }
}
