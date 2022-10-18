package com.pthttt.Phone_AI.service.serviceImp;

import com.pthttt.Phone_AI.model.entity.CPU;
import com.pthttt.Phone_AI.repository.CPURepository;
import com.pthttt.Phone_AI.service.CPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CPUServiceImp implements CPUService {
    @Autowired
    private CPURepository cpuRepository;

    @Override
    public List<CPU> findAll() {
        return cpuRepository.findAll();
    }
}
