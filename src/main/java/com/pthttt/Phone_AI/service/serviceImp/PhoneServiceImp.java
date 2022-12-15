package com.pthttt.Phone_AI.service.serviceImp;

import com.pthttt.Phone_AI.model.entity.Phone;
import com.pthttt.Phone_AI.repository.PhoneRepository;
import com.pthttt.Phone_AI.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImp implements PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    @Override
    public String save(Phone phone) {
        try{
            phoneRepository.save(phone);
            return "SUCCESS";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @Override
    public List<Phone> findPhoneByLabel(String label) {
        return phoneRepository.findByLabelOrderByPrice(label);
    }
}
