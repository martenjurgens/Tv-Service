package com.martenj.tvservices.controller;


import com.martenj.tvservices.model.TvPackage;
import com.martenj.tvservices.service.TvPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/tvpackages")
public class TvPackageController {

    @Autowired
    private TvPackageService tvPackageService;

    @PostMapping("/add")
    public String add (@RequestBody TvPackage tvPackage){
        tvPackageService.saveTvPackage(tvPackage);
        return "New Package has been added";
    }

    @GetMapping("/getAll")
    public List<TvPackage> getAllTvPackages() {
        return tvPackageService.getAllTvPackages();
    }

}
