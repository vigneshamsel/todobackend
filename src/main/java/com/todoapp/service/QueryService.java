package com.todoapp.service;

import com.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    public List<Map<String, Object>> executeQuery(String sql, Long id) {
       String name= userRepository.findUserByid(id).get().getName();
        if(!name.equals("adminlogin")) {
            return jdbcTemplate.queryForList(sql);
        }else {
            throw new AuthorizationDeniedException("admin login only allowed",() -> false);
        }
    }
}
