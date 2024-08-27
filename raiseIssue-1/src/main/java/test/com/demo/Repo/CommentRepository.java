package test.com.demo.Repo;

import java.util.List;

//import javax.xml.stream.events.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.com.demo.Entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByIssueId(Long issueId);
}

