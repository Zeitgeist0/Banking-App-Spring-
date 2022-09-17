package com.bankingappfinal.service;

import java.util.List;
import java.util.Optional;

public interface Service <T> {
  Optional<T> findById(Integer id);
  List<T> findAll();

  void save(T obj);
  void saveAll(List<T> entities);
  void delete(T obj);

  void deleteById(Integer id);
  void deleteAll(List<T> entities);


}
