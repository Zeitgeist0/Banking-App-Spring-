package com.app.banking.hibernate.bankingapphibernate.service;

import java.util.List;

public interface Service <T> {
  void save(T obj);

  boolean delete(T obj);
  void deleteAll(List<T> entities);
  void saveAll(List<T> entities);
  List<T> findAll();
  boolean deleteById(long id);

  T getOne(long id);
}
