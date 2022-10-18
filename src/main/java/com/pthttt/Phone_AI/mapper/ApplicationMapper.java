package com.pthttt.Phone_AI.mapper;

import com.pthttt.Phone_AI.model.dto.BrandDto;
import com.pthttt.Phone_AI.model.dto.CPUDto;
import com.pthttt.Phone_AI.model.dto.PhoneDto;
import com.pthttt.Phone_AI.model.entity.Brand;
import com.pthttt.Phone_AI.model.entity.CPU;
import com.pthttt.Phone_AI.model.entity.Phone;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public PhoneDto phoneToPhoneDto(Phone entity){
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setCpu(this.cpuToCpuDto(entity.getCpu()));
        phoneDto.setBrand(this.brandToBrandDto(entity.getBrand()));
        phoneDto.setId(entity.getId());
        phoneDto.setName(entity.getName());
        phoneDto.setLabel(entity.getLabel());
        phoneDto.setPrice(entity.getPrice());
        phoneDto.setRam(entity.getRam());
        phoneDto.setResolution1(entity.getResolution1());
        phoneDto.setResolution2(entity.getResolution2());
        phoneDto.setWidescreen(entity.getWidescreen());
        phoneDto.setImage(entity.getImage());
        phoneDto.setRom(entity.getRom());
        return phoneDto;
    }

    public Phone phoneDtoToPhone(PhoneDto dto){
        Phone en = new Phone();
        en.setCpu(this.cpuDtoToCpu(dto.getCpu()));
        en.setBrand(this.brandDtoToBrand(dto.getBrand()));
        en.setId(0l);
        en.setName(dto.getName());
        en.setPrice(dto.getPrice());
        en.setRam(dto.getRam());
        en.setResolution1(dto.getResolution1());
        en.setResolution2(dto.getResolution2());
        en.setWidescreen(dto.getWidescreen());
        en.setImage(dto.getImage());
        en.setRom(dto.getRom());
        return en;
    }

    public CPU cpuDtoToCpu (CPUDto dto){
        CPU en = new CPU();
        en.setCpuName(dto.getCpuName());
        en.setId(dto.getId());
        en.setPoint(dto.getPoint());
        return en;
    }

    public CPUDto cpuToCpuDto(CPU entity){
        CPUDto dto = new CPUDto();
        dto.setCpuName(entity.getCpuName());
        dto.setId(entity.getId());
        dto.setPoint(entity.getPoint());
        return dto;
    }
    public BrandDto brandToBrandDto(Brand entity){
        BrandDto dto = new BrandDto();
        dto.setBrandName(entity.getBrandName());
        dto.setId(entity.getId());
        dto.setPoint(entity.getPoint());
        return dto;
    }

    public Brand brandDtoToBrand(BrandDto dto){
        Brand en = new Brand();
        en.setBrandName(dto.getBrandName());
        en.setId(dto.getId());
        en.setPoint(dto.getPoint());
        return en;
    }
}
