package com.issuePages.Entity;


import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Date datetime;
    private String username;
    private String associateId;
    private boolean sameIssue;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issues issue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAssociateId() {
        return associateId;
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    public boolean isSameIssue() {
        return sameIssue;
    }

    public void setSameIssue(boolean sameIssue) {
        this.sameIssue = sameIssue;
    }

    public Issues getIssue() {
        return issue;
    }

    public void setIssue(Issues issue) {
        this.issue = issue;
    }


}