package com.ra.base_spring_mvc.model.dao.productdetail.size;

import com.ra.base_spring_mvc.model.entity.Size;

import java.util.List;

public interface SizeDAO {
    List<Size> findAll();


    Size findById(int id);

}
