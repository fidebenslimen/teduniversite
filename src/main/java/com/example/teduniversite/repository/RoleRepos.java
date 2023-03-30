package com.example.teduniversite.repository;

import com.example.teduniversite.entities.Role;
import com.example.teduniversite.entities.TypeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RoleRepos extends JpaRepository<Role,Integer> {
    public Role findByRolename(TypeRole roletype);
}
