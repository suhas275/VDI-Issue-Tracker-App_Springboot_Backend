
package test.com.demo.Entity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import test.com.demo.Entity.Comment;
import test.com.demo.Entity.Issues;
import test.com.demo.Repo.CommentRepository;
import test.com.demo.Repo.IssueRepository;

@Service
public class IssueService {
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }

    public Comment addComment(Long issueId, Comment comment) {
        Optional<Issues> issueOptional = issueRepository.findById(issueId);
        if (issueOptional.isPresent()) {
            Issues issue = issueOptional.get();
            comment.setIssue(issue);
            Comment savedComment = commentRepository.save(comment);
            if (comment.isSameIssue()) {
                issue.setImpactes(issue.getImpactes() + 1);
                issueRepository.save(issue);
            }
            return savedComment;
        } else {
            throw new ResourceNotFoundException("Issue not found with id " + issueId);
        }
    }
    public Issues raiseIssue(Issues issue) throws IllegalArgumentException {
        Optional<Issues> existingIssue = issueRepository.findByAssociateIdAndIssueTypeAndStatus(
            issue.getAssociateId(), issue.getIssueType(), "Open"
        );
        if (existingIssue.isPresent()) {
            throw new IllegalArgumentException("An open issue of the same type has already been raised by this associate.");
        }
        issue.setStatus("Open");
        return issueRepository.save(issue);
    }

    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);
    }

    public Issues getIssueById(Long id) {
        Optional<Issues> issue = issueRepository.findById(id);
        if (issue.isPresent()) {
            return issue.get();
        } else {
            throw new IllegalArgumentException("Issue not found");
        }
    }

    public Issues viewIssueById(Long id) {
        Optional<Issues> issue = issueRepository.findById(id);
        if (issue.isPresent()) {
            return issue.get();
        } else {
            throw new IllegalArgumentException("Issue not found");
        }
    }

    public Issues updateIssue(Long id, Issues issue) {
        if (!issueRepository.existsById(id)) {
            throw new IllegalArgumentException("Issue not found");
        }
        issue.setId(id); // Ensure the ID is set to the path variable ID
        return issueRepository.save(issue);
    }
}
