package com.securityDevelopment.projectService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FeignClient("project-service")
public interface ProjectService {
    @GetMapping("/api/project")
    ResponseEntity<List<Object>> listAll();

    @PostMapping("/api/project")
    ResponseEntity<Object> create();

    @GetMapping("/api/project/{id}")
    ResponseEntity<Object> findById();

    @PutMapping("/api/project/{id}")
    ResponseEntity<Object> update();

    @DeleteMapping("/api/project/{id}")
    ResponseEntity<Object> delete();
}
