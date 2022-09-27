package com.bankingappfinal.dao;

import com.bankingappfinal.domain.SysRole;
import com.bankingappfinal.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleJpaRepository extends JpaRepository<SysRole, Integer>, JpaSpecificationExecutor<SysRole> {
}
