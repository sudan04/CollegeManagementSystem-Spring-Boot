package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
  public Users findByUserName(String userName);

  public Users findByUserId(Long id);
public Users findByUserId(long id);

public Users findByEmail(String email);


public void deleteByUserId(Long userId);
}
