package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;
import edu.ucsb.cs156.spring.backenddemo.services.UniversityQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="University Information from http://universities.hipolabs.com")
@Slf4j
@RestController
@RequestMapping("/api/university")
public class UniversityController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UniversityQueryService universityQueryService;

    @Operation(summary = "Get university info about a certain school or state.")
    @GetMapping("/get")
    public ResponseEntity<String> getUniversity(
        @Parameter(name="universityName", description="name of a University") @RequestParam String universityName
    ) throws JsonProcessingException {
        log.info("getUniversity: universityName={}", universityName);
        String result = universityQueryService.getJSON(universityName);
        return ResponseEntity.ok().body(result);
    }


}