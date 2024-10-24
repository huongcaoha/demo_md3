package com.ra.base_spring_mvc.model.service.product.status;

import com.ra.base_spring_mvc.model.dao.product.status.StatusDAO;
import com.ra.base_spring_mvc.model.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService{
    @Autowired
    private StatusDAO statusDAO;
    @Override
    public List<Status> findAll() {
        return statusDAO.findAll();
    }
}
