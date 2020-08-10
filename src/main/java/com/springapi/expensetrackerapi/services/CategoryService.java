package com.springapi.expensetrackerapi.services;

import com.springapi.expensetrackerapi.domain.Category;
import com.springapi.expensetrackerapi.exception.EtBadRequestException;
import com.springapi.expensetrackerapi.exception.EtResourceNotFoundException;

import java.util.List;

public interface CategoryService {

    List<Category> fetchAllCategories(Integer userId);

    Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    Category addCategory(Integer userId, String title, String description) throws EtBadRequestException;

    void UpdateCategory(Integer userId, Integer categoryId, Category category) throws EtBadRequestException;

    void RemoveCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException;
}
