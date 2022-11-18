package com.securityDevelopment.projectService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@FeignClient("project-service")
public interface ProjectService {
    @GetMapping("/api/project")
    ResponseEntity<List<Object>> listAll();

    @PostMapping("/api/project")
    ResponseEntity<Object> create(@RequestBody Object dto);

    @GetMapping("/api/project/{id}")
    ResponseEntity<Object> findById(@PathVariable("id") UUID id);

    @PutMapping("/api/project/{id}")
    ResponseEntity<Object> update(@PathVariable("id") UUID id, @RequestBody Object dto);

    @DeleteMapping("/api/project/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") UUID id);
}
