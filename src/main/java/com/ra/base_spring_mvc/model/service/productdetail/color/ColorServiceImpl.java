package com.ra.base_spring_mvc.model.service.productdetail.color;

import com.ra.base_spring_mvc.model.dao.productdetail.color.ColorDAO;
import com.ra.base_spring_mvc.model.entity.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorDAO colorDAO;
    @Override
    public List<Color> findAll() {
        return colorDAO.findAll();
    }


    @Override
    public Color findById(int id) {
        return colorDAO.findById(id);
    }

}
