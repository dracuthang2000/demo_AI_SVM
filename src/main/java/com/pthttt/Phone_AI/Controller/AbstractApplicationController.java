package com.pthttt.Phone_AI.Controller;
import com.pthttt.Phone_AI.mapper.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public abstract class AbstractApplicationController {
    @Autowired
    ApplicationMapper mapper;
}
