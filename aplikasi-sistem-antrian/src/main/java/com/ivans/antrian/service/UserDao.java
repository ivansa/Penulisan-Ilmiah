/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.service;

import com.ivans.antrian.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ivans
 */
@Repository
public interface UserDao extends PagingAndSortingRepository<User, String>{
    public User findByUsername(String username);
    public User findByEmail(String email);
    public List<User> findByRoleName(String role);
    
    @Query("SELECT u from User u where u.username like :param or u.fullname like :param or u.email like :param or u.role.name like :param or u.loket.nomorLoket like :param")
    List<User> findByAny(@Param("param") String param);
}
