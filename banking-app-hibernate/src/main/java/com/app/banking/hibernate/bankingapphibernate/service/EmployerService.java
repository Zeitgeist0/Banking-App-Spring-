package com.app.banking.hibernate.bankingapphibernate.service;

import com.app.banking.hibernate.bankingapphibernate.dao.Dao;
import com.app.banking.hibernate.bankingapphibernate.dao.HibernateCustomerDao;
import com.app.banking.hibernate.bankingapphibernate.dao.HibernateEmployerDao;
import com.app.banking.hibernate.bankingapphibernate.domain.Customer;
import com.app.banking.hibernate.bankingapphibernate.domain.Employer;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployerService implements Service<Employer> {
  private HibernateEmployerDao employerDao;

  public EmployerService(HibernateEmployerDao hibernateEmployerDao) { // List<UserDao> dao - inject all deps
    this.employerDao = hibernateEmployerDao;
  }


  @Override
  public void save(Employer employer) {
    employerDao.save(employer);
  }

  @Override
  public boolean delete(Employer employer) {
    return employerDao.delete(employer);
  }

  @Override
  public void deleteAll(List<Employer> employers) {
employerDao.deleteAll(employers);
  }

  @Override
  public void saveAll(List<Employer> employers) {
employerDao.saveAll(employers);
  }

  @Override
  public List<Employer> findAll() {
    return employerDao.findAll();
  }

  @Override
  public boolean deleteById(long id) {
   return employerDao.deleteById(id);
  }

  @Override
  public Employer getOne(long id) {
    return employerDao.getOne(id);
  }


  public void update (Employer employer) {
    employerDao.update(employer);
  }
}
