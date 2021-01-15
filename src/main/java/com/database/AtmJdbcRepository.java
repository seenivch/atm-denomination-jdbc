package com.database;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.model.Atm;

import java.util.List;

/**
 * @author Seenivasan Chandrasekaran
 */

@Repository
public class AtmJdbcRepository{
	
@Autowired
JdbcTemplate jdbcTemplate;

@Autowired
AtmRowMapper atmRowMapper;

@SuppressWarnings("unchecked")
public List<Atm> findAll(){
    return jdbcTemplate.query("select * from atm", atmRowMapper);
}
}
