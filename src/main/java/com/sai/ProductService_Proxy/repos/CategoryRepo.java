package com.sai.ProductService_Proxy.repos;

import com.sai.ProductService_Proxy.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Categories, Long> {

    Categories save(Categories category);

    Categories findById(long id);


}