package com.ra.base_spring_mvc.model.service.banner;

import com.ra.base_spring_mvc.model.entity.Banner;
import com.ra.base_spring_mvc.model.entity.dto.BannerDto;

import java.util.List;

public interface BannerService {
    List<Banner> getListBanner();
    boolean addBanner(Banner banner);
    boolean updateBanner(Banner banner);
    boolean deleteBanner(Banner banner);
    Banner findById(int id);
    List<Banner> getListPagination(int page , int size);
    Banner converseBannerDto(BannerDto bannerDto);
    BannerDto converseBanner(Banner banner);

    Banner getBannerByLocation(String location);

}
