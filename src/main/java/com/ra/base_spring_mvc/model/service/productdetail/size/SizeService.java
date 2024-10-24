package com.ra.base_spring_mvc.model.service.productdetail.size;

import com.ra.base_spring_mvc.model.entity.Color;
import com.ra.base_spring_mvc.model.entity.Size;

import java.util.List;

public interface SizeService {
    List<Size> findAll();

    Size findById(int id);

}
