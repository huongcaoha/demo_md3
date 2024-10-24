package com.ra.base_spring_mvc.model.dao.productdetail.color;

import com.ra.base_spring_mvc.model.entity.Color;

import com.ra.base_spring_mvc.model.entity.Size;
import org.hibernate.Session;


import java.util.List;

public interface ColorDAO {
    List<Color> findAll();

    Color findById(int id);
    List<Color> findByDetailId(int id);

}
