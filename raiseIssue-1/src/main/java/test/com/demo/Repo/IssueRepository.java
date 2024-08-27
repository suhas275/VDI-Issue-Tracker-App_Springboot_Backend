package test.com.demo.Repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import test.com.demo.Entity.Issues;

public interface IssueRepository extends JpaRepository<Issues, Long> {
Optional<Issues> findByAssociateIdAndIssueTypeAndStatus(Long associateId, String issueType, String status);

}
