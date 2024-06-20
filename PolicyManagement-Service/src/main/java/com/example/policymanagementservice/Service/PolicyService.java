package com.example.policymanagementservice.Service;

import com.example.policymanagementservice.Model.Policy;
import com.example.policymanagementservice.Repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

    @Service
    public class PolicyService {

        @Autowired
        private PolicyRepository policyRpository;

        public Policy createPolicy(Policy policy) {
            return policyRpository.save(policy);
        }

        public List<Policy> getAllPolicies() {
            return policyRpository.findAll();
        }

        public Optional<Policy> getPolicyById(Long id) {
            return policyRpository.findById(id);
        }

        public Policy updatePolicy(Long id, Policy policyDetails) {
            Policy policy = policyRpository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Policy not found"));

            policy.setPolicyNumber(policyDetails.getPolicyNumber());
            policy.setPolicyType(policyDetails.getPolicyType());
            policy.setPremium(policyDetails.getPremium());
            policy.setCoverageAmount(policyDetails.getCoverageAmount());
            policy.setStartDate(policyDetails.getStartDate());
            policy.setEndDate(policyDetails.getEndDate());
            policy.setClientId(policyDetails.getClientId());

            return policyRpository.save(policy);
        }

        public void deletePolicy(Long id) {
          policyRpository.deleteById(id);
        }
    }

