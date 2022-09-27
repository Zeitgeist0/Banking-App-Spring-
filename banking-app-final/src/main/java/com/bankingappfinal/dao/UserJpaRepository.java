package com.bankingappfinal.dao;

import com.bankingappfinal.domain.Employer;
import com.bankingappfinal.domain.SysUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<SysUser, Integer>, JpaSpecificationExecutor<SysUser> {

  Optional<SysUser> findSysUserByName(String userName);
}
