package com.example.policymanagementservice.Controller;

import com.example.policymanagementservice.Model.Policy;
import com.example.policymanagementservice.Service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")

public class PolicyController {

        @Autowired
        private PolicyService policyService;

        @PostMapping("/create")
        public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
            return ResponseEntity.ok(policyService.createPolicy(policy));
        }

        @GetMapping("/getAll")
        public ResponseEntity<List<Policy>> getAllPolicies() {
            return ResponseEntity.ok(policyService.getAllPolicies());
        }

        @GetMapping("/get/{id}")
        public ResponseEntity<Policy> getPolicyById(@PathVariable Long id) {
            return policyService.getPolicyById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<Policy> updatePolicy(@PathVariable Long id, @RequestBody Policy policyDetails) {
            return ResponseEntity.ok(policyService.updatePolicy(id, policyDetails));
        }

        @DeleteMapping("delete/{id}")
        public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
            policyService.deletePolicy(id);
            return ResponseEntity.noContent().build();
        }
    }
