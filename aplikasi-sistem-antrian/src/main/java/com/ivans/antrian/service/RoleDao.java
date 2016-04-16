/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.service;

import com.ivans.antrian.domain.Role;
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
public interface RoleDao extends PagingAndSortingRepository<Role, String>{
    @Query("SELECT r from Role r where r.name like :param or r.description like :param")
    public List<Role> findByAny(@Param("param") String param);
    
    public Role findByName(String name);
}
