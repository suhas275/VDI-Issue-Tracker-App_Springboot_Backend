package com.issuePages.Controller;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.issuePages.Entity.Comment;
import com.issuePages.Entity.Issues;
import com.issuePages.Entity.service.IssueService;
import com.issuePages.Repo.CommentRepository;
import com.issuePages.Repo.IssueRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/add")
    public ResponseEntity<?> raiseIssue(@RequestBody Issues issue) {
        try {
            Issues createdIssue = issueService.raiseIssue(issue);
            return new ResponseEntity<>(createdIssue, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Issues>> getAllIssues() {
        List<Issues> allIssues = issueRepository.findAll();
        return new ResponseEntity<>(allIssues, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIssue(@PathVariable Long id) {
        try {
            issueService.deleteIssue(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/issues/{id}")
    public ResponseEntity<?> getIssueById(@PathVariable Long id) {
        try {
            Issues issue = issueService.getIssueById(id);
            return new ResponseEntity<>(issue, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateIssue(@PathVariable Long id, @RequestBody Issues issue) {
        try {
            Issues updatedIssue = issueService.updateIssue(id, issue);
            return new ResponseEntity<>(updatedIssue, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  viewIssue
    @GetMapping("/viewissues/{id}")
    public ResponseEntity<?> viewIssueById(@PathVariable Long id) {
        try {
            Issues issue = issueService.getIssueById(id);
            return new ResponseEntity<>(issue, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Comment section
    @GetMapping("/comments/issues/{id}")
    public List<Comment> getCommentsByIssueId(@PathVariable Long id) {
        return commentRepository.findByIssueId(id);
    }


    @PostMapping("/comments/issues/{id}")
    public ResponseEntity<Comment> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Issues> issueOptional = issueRepository.findById(id);
        if (issueOptional.isPresent()) {
            Issues issue = issueOptional.get();
            comment.setIssue(issue);
            Comment savedComment = commentRepository.save(comment);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}