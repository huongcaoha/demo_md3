package com.ra.base_spring_mvc.model.dao.banner;

import com.ra.base_spring_mvc.model.entity.Banner;

import java.util.List;

public interface BannerDAO {
    List<Banner> getListBanner();
    List<Banner> getListPagination(int page , int size);
    boolean addBanner(Banner banner);
    boolean updateBanner(Banner banner);
    boolean deleteBanner(Banner banner);
    Banner findById(int id);

    Banner getBannerByLocation(String location);

}
