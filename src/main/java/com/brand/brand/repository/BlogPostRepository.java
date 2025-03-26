package com.brand.brand.repository;

import com.brand.brand.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    Optional<BlogPost> findByTitle(String title); // Check if a post with this title exists
    List<BlogPost> findByAuthor(String author); // Get posts by author
    List<BlogPost> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate); // Get posts by date range
    List<BlogPost> findAllByOrderByCreatedAtDesc(); // Get posts ordered by created date (desc)
    List<BlogPost> findAllByOrderByCreatedAtAsc(); // Get posts ordered by created date (asc)
    List<BlogPost> findByTitleContainingOrContentContaining(String title, String content); // Search in title or content
}
