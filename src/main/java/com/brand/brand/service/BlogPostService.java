package com.brand.brand.service;

import com.brand.brand.entity.BlogPost;
import com.brand.brand.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    // Create a new blog post
    public BlogPost createBlogPost(BlogPost blogPost) {
        blogPost.setCreatedAt(LocalDate.now()); // Set created date as today's date
        return blogPostRepository.save(blogPost);
    }

    // Update an existing blog post
    public BlogPost updateBlogPost(Long id, BlogPost updatedBlogPost) {
        Optional<BlogPost> existingPostOptional = blogPostRepository.findById(id);
        if (existingPostOptional.isPresent()) {
            BlogPost existingPost = existingPostOptional.get();
            existingPost.setTitle(updatedBlogPost.getTitle());
            existingPost.setContent(updatedBlogPost.getContent());
            existingPost.setAuthor(updatedBlogPost.getAuthor());
            // Update createdAt if needed (optional, you can keep it unchanged)
            existingPost.setCreatedAt(updatedBlogPost.getCreatedAt() != null ? updatedBlogPost.getCreatedAt() : existingPost.getCreatedAt());
            return blogPostRepository.save(existingPost);
        }
        return null; // Or throw an exception if post not found
    }

    // Get a blog post by ID
    public Optional<BlogPost> getBlogPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    // Get a list of all blog posts
    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    // Get blog posts by a specific author
    public List<BlogPost> getBlogPostsByAuthor(String author) {
        return blogPostRepository.findByAuthor(author);
    }

    // Get blog posts within a date range
    public List<BlogPost> getBlogPostsByDateRange(LocalDate startDate, LocalDate endDate) {
        return blogPostRepository.findByCreatedAtBetween(startDate, endDate);
    }

    // Delete a blog post by ID
    public void deleteBlogPost(Long id) {
        blogPostRepository.deleteById(id);
    }

    // Get all blog posts ordered by creation date (descending)
    public List<BlogPost> getBlogPostsOrderedByDateDesc() {
        return blogPostRepository.findAllByOrderByCreatedAtDesc();
    }

    // Get all blog posts ordered by creation date (ascending)
    public List<BlogPost> getBlogPostsOrderedByDateAsc() {
        return blogPostRepository.findAllByOrderByCreatedAtAsc();
    }

    // Search blog posts by title or content
    public List<BlogPost> searchBlogPosts(String searchTerm) {
        return blogPostRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    // Check if a blog post with the same title already exists
    public boolean titleExists(String title) {
        return blogPostRepository.findByTitle(title).isPresent();
    }
}
