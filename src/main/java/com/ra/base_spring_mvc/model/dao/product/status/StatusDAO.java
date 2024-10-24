package com.ra.base_spring_mvc.model.dao.product.status;

import com.ra.base_spring_mvc.model.entity.Status;

import java.util.List;

public interface StatusDAO {
    List<Status> findAll();
}
