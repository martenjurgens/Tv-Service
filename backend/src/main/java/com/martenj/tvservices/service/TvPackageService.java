package com.martenj.tvservices.service;

import com.martenj.tvservices.model.TvPackage;

import java.util.List;


public interface TvPackageService {
    public TvPackage saveTvPackage(TvPackage tvPackage);
    public List<TvPackage> getAllTvPackages();
}
