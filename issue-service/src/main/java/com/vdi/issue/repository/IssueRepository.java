package com.vdi.issue.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vdi.issue.entity.Issues;

public interface IssueRepository extends JpaRepository<Issues, Long> {
    Optional<Issues> findByAssociateIdAndIssueTypeAndStatus(Long associateId, String issueType, String status);

}
