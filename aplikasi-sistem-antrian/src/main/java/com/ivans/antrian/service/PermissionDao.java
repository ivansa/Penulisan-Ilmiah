/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.service;

import com.ivans.antrian.domain.Permission;
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
public interface PermissionDao extends PagingAndSortingRepository<Permission, String> {

    public Permission findByValue(String value);
    public Permission findByLabel(String label);
    
    @Query("SELECT p from Permission p where p.value like :param or p.label like :param")
    public List<Permission> findByAny(@Param("param") String param);
}
