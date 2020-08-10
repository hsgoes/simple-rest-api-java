package com.springapi.expensetrackerapi.resources;


import com.springapi.expensetrackerapi.domain.Category;
import com.springapi.expensetrackerapi.exception.EtAuthException;
import com.springapi.expensetrackerapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token != null){
            int userId = (Integer) request.getAttribute("userId");
            List<Category> categories = categoryService.fetchAllCategories(userId);
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } else {
            throw new EtAuthException("Token must be provided");
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(HttpServletRequest request,
                                                @PathVariable Integer categoryId) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            int userId = (Integer) request.getAttribute("userId");
            Category category = categoryService.fetchCategoryById(userId, categoryId);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            throw new EtAuthException("The token must be provided");
        }
    }

    @PostMapping("")
    public ResponseEntity<Category> addCategory(HttpServletRequest request,
                                                @RequestBody Map<String, Object> categoryMap) {
        int userId = (Integer) request.getAttribute("userId");
        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");
        Category category = categoryService.addCategory(userId, title, description);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Map<String,Boolean>> updateCategory(HttpServletRequest request,
                                                              @PathVariable Integer categoryId,
                                                              @RequestBody Category category){
        String token = request.getHeader("Authorization");
        if(token != null){
            int userId = (Integer) request.getAttribute("userId");
            categoryService.UpdateCategory(userId, categoryId, category);
            Map<String, Boolean> map = new HashMap<>();
            map.put("success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            throw new EtAuthException("The token must be provided");
        }
    }
}
