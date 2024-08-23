package com.sai.ProductService_Proxy.services;

import com.sai.ProductService_Proxy.models.Categories;
import com.sai.ProductService_Proxy.models.Product;
import com.sai.ProductService_Proxy.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfCategoryService implements ICategoryService{

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<Categories> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Categories addNewCategory(Categories category) {
        return categoryRepo.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        categoryRepo.deleteById(categoryId);
        return "Product with id : "+categoryId+" deleted successfully";
    }
}