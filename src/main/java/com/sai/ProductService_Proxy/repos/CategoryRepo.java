package com.sai.ProductService_Proxy.repos;

import com.sai.ProductService_Proxy.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Categories, Long> {

    public Categories save(Categories category);

    public List<Categories> findAll();

    public void deleteById(Long id);
}