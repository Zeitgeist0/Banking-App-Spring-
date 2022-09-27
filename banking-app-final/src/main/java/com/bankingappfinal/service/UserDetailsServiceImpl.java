package com.bankingappfinal.service;

import com.bankingappfinal.dao.UserJpaRepository;
import com.bankingappfinal.domain.SysUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserJpaRepository userJpaRepository;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<SysUser> sysUser = userJpaRepository.findSysUserByName(username);
    System.out.println(sysUser);
    if (sysUser.isEmpty()) {
      throw new UsernameNotFoundException(username);
    }

    List<SimpleGrantedAuthority> authorities = sysUser.get().getSysRoles().stream()
      .map(r -> new SimpleGrantedAuthority(r.getName()))
      .toList();
    return new User(sysUser.get().getName(), sysUser.get().getPassword(), authorities);
  }
}
