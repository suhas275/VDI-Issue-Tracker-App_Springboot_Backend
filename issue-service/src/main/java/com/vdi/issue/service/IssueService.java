package com.vdi.issue.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vdi.issue.entity.Comment;
import com.vdi.issue.entity.Issues;
import com.vdi.issue.exception.ResourceNotFoundException;
import com.vdi.issue.repository.CommentRepository;
import com.vdi.issue.repository.IssueRepository;

@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final CommentRepository commentRepository;

    public IssueService(IssueRepository issueRepository, CommentRepository commentRepository) {
        this.issueRepository = issueRepository;
        this.commentRepository = commentRepository;
    }

    /**
     * Get all comments for a given issue by its ID.
     */
    public List<Comment> getCommentsByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }

    /**
     * Add a comment to an issue, update the issue's impacted count if necessary.
     */
    @Transactional
    public Comment addComment(Long issueId, Comment comment) {
        Issues issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new ResourceNotFoundException("Issue not found with id " + issueId));
        comment.setIssue(issue);
        Comment savedComment = commentRepository.save(comment);
        if (comment.isSameIssue()) {
            issue.setImpacts(issue.getImpacts() + 1);
            issueRepository.save(issue);
        }
        return savedComment;
    }

    /**
     * Raise a new issue. Validate that no similar open issue already exists for the associate.
     */
    public Issues raiseIssue(Issues issue) {
        boolean exists = issueRepository.findByAssociateIdAndIssueTypeAndStatus(
                issue.getAssociateId(), issue.getIssueType(), "Open").isPresent();
        if (exists) {
            throw new IllegalArgumentException("An open issue of the same type has already been raised by this associate.");
        }
        issue.setStatus("Open");
        return issueRepository.save(issue);
    }

    /**
     * Delete an issue by its ID.
     */
    public void deleteIssue(Long id) {
        if (!issueRepository.existsById(id)) {
            throw new ResourceNotFoundException("Issue not found with id " + id);
        }
        issueRepository.deleteById(id);
    }

    /**
     * Get an issue by its ID. Throws an exception if the issue does not exist.
     */
    public Issues getIssueById(Long id) {
        return issueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Issue not found with id " + id));
    }

    /**
     * View an issue by its ID. This method reuses getIssueById().
     */
    public Issues viewIssueById(Long id) {
        return getIssueById(id);
    }

    /**
     * Update an issue by its ID. Validates that the issue exists and that the path ID matches the body ID.
     */
    public Issues updateIssue(Long id, Issues issue) {
        if (!issueRepository.existsById(id)) {
            throw new ResourceNotFoundException("Issue not found with id " + id);
        }
        if (!id.equals(issue.getId())) {
            throw new IllegalArgumentException("ID in path and body must match");
        }
        return issueRepository.save(issue);
    }
}
