package com.pthttt.Phone_AI.service;

import com.pthttt.Phone_AI.model.entity.Phone;

import java.util.List;

public interface PhoneService {
    List<Phone> findAll();
    String save(Phone phone);
    List<Phone> findPhoneByLabel(String label);
}
