package com.sai.ProductService_Proxy.services;

import com.sai.ProductService_Proxy.models.Categories;
import com.sai.ProductService_Proxy.models.Product;

import java.util.List;

public interface ICategoryService {

    List<Categories> getAllCategories();

    Categories addNewCategory(Categories category);

    String deleteCategory(Long categoryId);
}