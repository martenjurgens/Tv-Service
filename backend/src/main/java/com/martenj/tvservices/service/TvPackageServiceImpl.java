package com.martenj.tvservices.service;


import com.martenj.tvservices.model.TvPackage;
import com.martenj.tvservices.repository.TvPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvPackageServiceImpl implements TvPackageService {

    @Autowired
    private TvPackageRepository tvPackageRepository;

    @Override
    public TvPackage saveTvPackage(TvPackage tvPackage) {
        return tvPackageRepository.save(tvPackage);
    }

    @Override
    public List<TvPackage> getAllTvPackages() {
        return tvPackageRepository.findAll();
    }
}
