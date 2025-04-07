package com.omnify.blogs.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.omnify.blogs.model.BlogModel;
import com.omnify.blogs.model.UserModel;
import com.omnify.blogs.repository.UserRepository;
import com.omnify.blogs.service.BlogService;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserRepository userRepository;

    //Pagination
    @GetMapping
    public String listBlogs(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "4") int size,
                            Model model) {
        Page<BlogModel> blogPage = blogService.getPaginatedBlogs(page, size);
        model.addAttribute("blogPage", blogPage);
        model.addAttribute("blogs", blogPage.getContent()); // so blog-list.html can loop over "blogs"
        model.addAttribute("currentPage", page);
        return "blog-list";
    }

    //Create form
    @GetMapping("/new")
    public String newBlogForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return "redirect:/login";
        model.addAttribute("blog", new BlogModel());
        return "blog-form";
    }

    //Detail view
    @GetMapping("/{id}")
    public String blogDetail(@PathVariable Long id, Model model) {
        BlogModel blog = blogService.getBlogById(id);
        if (blog == null) return "redirect:/blogs";
        model.addAttribute("blog", blog);
        return "blog-detail";
    }

    //Edit form — only for author
    @GetMapping("/edit/{id}")
    public String editBlog(@PathVariable Long id, Model model,
                           @AuthenticationPrincipal UserDetails userDetails) {
        BlogModel blog = blogService.getBlogById(id);
        if (blog == null || !blog.getAuthor().getEmail().equals(userDetails.getUsername())) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "blog-form";
    }

    //Create or update
    @PostMapping
    public String createOrUpdateBlog(@Valid @ModelAttribute("blog") BlogModel blog,
                                     BindingResult result,
                                     @AuthenticationPrincipal UserDetails userDetails,
                                     Model model) {
        if (userDetails == null) return "redirect:/login";

        if (result.hasErrors()) return "blog-form";

        Optional<UserModel> author = userRepository.findByEmail(userDetails.getUsername());

        if (blog.getId() == null) {
            author.ifPresent(blog::setAuthor);
        } else {
            BlogModel existingBlog = blogService.getBlogById(blog.getId());
            if (existingBlog == null || !existingBlog.getAuthor().getEmail().equals(userDetails.getUsername())) {
                return "redirect:/blogs";
            }
            blog.setAuthor(existingBlog.getAuthor());
            blog.setCreatedAt(existingBlog.getCreatedAt());
        }

        blogService.saveBlog(blog);
        return "redirect:/blogs";
    }

    //Delete (only for author)
    @PostMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id,
                             @AuthenticationPrincipal UserDetails userDetails) {
        BlogModel blog = blogService.getBlogById(id);
        if (blog != null && blog.getAuthor().getEmail().equals(userDetails.getUsername())) {
            blogService.deleteBlog(id);
        }
        return "redirect:/blogs";
    }
}

