package com.martenj.tvservices.repository;

import com.martenj.tvservices.model.TvPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvPackageRepository extends JpaRepository <TvPackage, Integer>{
}
