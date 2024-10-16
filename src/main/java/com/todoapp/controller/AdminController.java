package com.todoapp.controller;

import com.todoapp.modal.User;
import com.todoapp.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/adminconsole")

public class AdminController {

    @Autowired
    private QueryService queryService;

    @PostMapping
    public ResponseEntity<List<Map<String, Object>>> executeQuery(@RequestBody String query, @AuthenticationPrincipal User user) {
        try {
            List<Map<String, Object>> result = queryService.executeQuery(query,user.getId());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
