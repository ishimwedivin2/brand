package com.brand.brand.controller;

import com.brand.brand.entity.BlogPost;
import com.brand.brand.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogposts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    // Create a new blog post
    @PostMapping
    public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPost blogPost) {
        BlogPost createdBlogPost = blogPostService.createBlogPost(blogPost);
        return new ResponseEntity<>(createdBlogPost, HttpStatus.CREATED);
    }

    // Update an existing blog post
    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable Long id, @RequestBody BlogPost updatedBlogPost) {
        BlogPost updatedPost = blogPostService.updateBlogPost(id, updatedBlogPost);
        if (updatedPost != null) {
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get a blog post by ID
    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id) {
        Optional<BlogPost> blogPost = blogPostService.getBlogPostById(id);
        if (blogPost.isPresent()) {
            return new ResponseEntity<>(blogPost.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all blog posts
    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllBlogPosts() {
        List<BlogPost> blogPosts = blogPostService.getAllBlogPosts();
        return new ResponseEntity<>(blogPosts, HttpStatus.OK);
    }

    // Get blog posts by a specific author
    @GetMapping("/author/{author}")
    public ResponseEntity<List<BlogPost>> getBlogPostsByAuthor(@PathVariable String author) {
        List<BlogPost> blogPosts = blogPostService.getBlogPostsByAuthor(author);
        return new ResponseEntity<>(blogPosts, HttpStatus.OK);
    }

    // Get blog posts within a date range
    @GetMapping("/date-range")
    public ResponseEntity<List<BlogPost>> getBlogPostsByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<BlogPost> blogPosts = blogPostService.getBlogPostsByDateRange(startDate, endDate);
        return new ResponseEntity<>(blogPosts, HttpStatus.OK);
    }

    // Delete a blog post by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id) {
        blogPostService.deleteBlogPost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get all blog posts ordered by creation date (descending)
    @GetMapping("/ordered/desc")
    public ResponseEntity<List<BlogPost>> getBlogPostsOrderedByDateDesc() {
        List<BlogPost> blogPosts = blogPostService.getBlogPostsOrderedByDateDesc();
        return new ResponseEntity<>(blogPosts, HttpStatus.OK);
    }

    // Get all blog posts ordered by creation date (ascending)
    @GetMapping("/ordered/asc")
    public ResponseEntity<List<BlogPost>> getBlogPostsOrderedByDateAsc() {
        List<BlogPost> blogPosts = blogPostService.getBlogPostsOrderedByDateAsc();
        return new ResponseEntity<>(blogPosts, HttpStatus.OK);
    }

    // Search blog posts by title or content
    @GetMapping("/search")
    public ResponseEntity<List<BlogPost>> searchBlogPosts(@RequestParam String searchTerm) {
        List<BlogPost> blogPosts = blogPostService.searchBlogPosts(searchTerm);
        return new ResponseEntity<>(blogPosts, HttpStatus.OK);
    }
}
