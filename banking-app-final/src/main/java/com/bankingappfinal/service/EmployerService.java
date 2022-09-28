package com.bankingappfinal.service;


import com.bankingappfinal.dao.EmployerJpaRepository;
import com.bankingappfinal.domain.Employer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
@Transactional
public class EmployerService implements Service<Employer> {

  private final EmployerJpaRepository employerJpaRepository;

  @Override
  public Optional<Employer> findById(Integer id) {
    return employerJpaRepository.findById(id);
  }

  @Override
  public List<Employer> findAll() {
    return employerJpaRepository.findAll();
  }

  @Override
  public void save(Employer employer) {
    employerJpaRepository.save(employer);
  }

  @Override
  public void saveAll(List<Employer> employers) {
    employerJpaRepository.saveAll(employers);
  }

  @Override
  public void delete(Employer employer) {
    employerJpaRepository.delete(employer);
  }

  @Override
  public void deleteById(Integer id) {
    employerJpaRepository.deleteById(id);
  }

  @Override
  public void deleteAll(List<Employer> employer) {
    employerJpaRepository.deleteAll(employer);
  }



  public Set<Employer> findAllByIdIn (Set<Integer> employerIds) {
    return employerJpaRepository.findAllByIdIn(employerIds);
  }

 }
