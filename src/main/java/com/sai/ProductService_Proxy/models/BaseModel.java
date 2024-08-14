package com.sai.ProductService_Proxy.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseModel {

    private Long id;
    private Date createdAt;
    private Date lastModifiedAt;
    private boolean isDeleted;
}