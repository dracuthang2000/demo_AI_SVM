package com.pthttt.Phone_AI.service.serviceImp;

import com.pthttt.Phone_AI.model.entity.Brand;
import com.pthttt.Phone_AI.repository.BrandRepository;
import com.pthttt.Phone_AI.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImp implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
