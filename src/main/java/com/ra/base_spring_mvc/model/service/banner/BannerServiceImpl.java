package com.ra.base_spring_mvc.model.service.banner;

import com.ra.base_spring_mvc.common.MethodCommon;
import com.ra.base_spring_mvc.model.dao.banner.BannerDAO;
import com.ra.base_spring_mvc.model.entity.Banner;
import com.ra.base_spring_mvc.model.entity.dto.BannerDto;

import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerDAO bannerDAO;
    @Override
    public List<Banner> getListBanner() {
        return bannerDAO.getListBanner();
    }

    @Override
    public boolean addBanner(Banner banner) {
        return bannerDAO.addBanner(banner);
    }

    @Override
    public boolean updateBanner(Banner banner) {
        return bannerDAO.updateBanner(banner);
    }

    @Override
    public boolean deleteBanner(Banner banner) {
        return bannerDAO.deleteBanner(banner);
    }

    @Override
    public Banner findById(int id) {
        return bannerDAO.findById(id);
    }

    @Override
    public List<Banner> getListPagination(int page, int size) {
        return bannerDAO.getListPagination(page, size);
    }

    @Override
    public Banner converseBannerDto(BannerDto bannerDto) {
        Banner banner = new Banner();
        banner.setBannerName(bannerDto.getBannerName());
        banner.setLocation(bannerDto.getLocation());

        banner.setContent(bannerDto.getContent());

        if(bannerDto.getImage().getSize() > 0){
            banner.setImage(MethodCommon.uploadImage(bannerDto.getImage()));
        }
        return banner ;
    }

    @Override
    public BannerDto converseBanner(Banner banner) {
       BannerDto bannerDto = new BannerDto();
       bannerDto.setBannerName(banner.getBannerName());
       bannerDto.setLocation(banner.getLocation());

       bannerDto.setContent(banner.getContent());
       return bannerDto;
    }

    @Override
    public Banner getBannerByLocation(String location) {
        return bannerDAO.getBannerByLocation(location);
    }

}
