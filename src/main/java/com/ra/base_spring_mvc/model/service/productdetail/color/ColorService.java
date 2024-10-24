package com.ra.base_spring_mvc.model.service.productdetail.color;

import com.ra.base_spring_mvc.model.entity.Color;

import java.util.List;

public interface ColorService {
    List<Color> findAll();

    Color findById(int id);

}
