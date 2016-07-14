/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 *
 * @author ivans
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
public class SecurityConfig extends ResourceServerConfigurerAdapter {

    private static final String SERVER_RESOURCE_ID = "antrian-server";

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(SERVER_RESOURCE_ID)
                .tokenStore(new JdbcTokenStore(dataSource));
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sounds/**").permitAll()
                
                //Management
                .antMatchers("/api/system/**").hasRole("SYSTEM")
                .antMatchers("/api/master/**").hasRole("MASTER")
                
                //Loket
                .antMatchers("/api/loket/**").hasRole("LOKET")
                
                //Laporan
                .antMatchers("/api/report/**").hasRole("REPORT")
                
                //Generate
                .antMatchers("/api/generate/**").hasRole("GENERATE")
                
                .antMatchers("/api/pendaftaran/**").hasRole("PENDAFTARAN")
                .antMatchers("/api/check/bod").hasRole("PENDAFTARAN")
                
                
                .antMatchers("/api/screen/tv/**").hasRole("SCREEN_TV")
                
                
                .anyRequest().authenticated();
    }
}
