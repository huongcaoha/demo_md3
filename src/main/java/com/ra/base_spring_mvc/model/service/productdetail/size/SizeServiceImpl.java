package com.ra.base_spring_mvc.model.service.productdetail.size;

import com.ra.base_spring_mvc.model.dao.productdetail.color.ColorDAO;
import com.ra.base_spring_mvc.model.dao.productdetail.size.SizeDAO;
import com.ra.base_spring_mvc.model.entity.Color;
import com.ra.base_spring_mvc.model.entity.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeDAO sizeDAO;
    @Override
    public List<Size> findAll() {
        return sizeDAO.findAll();
    }


    @Override
    public Size findById(int id) {
        return sizeDAO.findById(id);
    }

}
