package com.b1.bankingapp1.dao;



import java.util.List;

public interface Dao<T> {
  void save(T obj);

  boolean delete(T obj);
  void deleteAll(List<T> entities);
  void saveAll(List<T> entities);
  List<T> findAll();
  boolean deleteById(long id);

  T getOne(long id);
}
