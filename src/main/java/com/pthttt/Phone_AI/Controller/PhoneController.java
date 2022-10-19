package com.pthttt.Phone_AI.Controller;

import com.pthttt.Phone_AI.Utils.ImageUtils;
import com.pthttt.Phone_AI.Utils.RenderLabel;
import com.pthttt.Phone_AI.model.dto.PhoneDto;
import com.pthttt.Phone_AI.model.entity.Phone;
import com.pthttt.Phone_AI.service.BrandService;
import com.pthttt.Phone_AI.service.CPUService;
import com.pthttt.Phone_AI.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/phone/")
public class PhoneController extends AbstractApplicationController {

    private static final String URL_IMG="D:\\PTIT_MTT\\PTUDTT\\Phone_AI\\src\\main\\resources\\image\\";

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CPUService cpuService;

    @GetMapping("getListPhone")
    public ResponseEntity<Object> getListPhone(){
        return new ResponseEntity<>(
        phoneService.findAll()
                .stream().map(mapper::phoneToPhoneDto)
                .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("findPhoneByLabel")
    public ResponseEntity<Object> getListPhoneByLabel(@RequestParam("filter") String label){
        return new ResponseEntity<>(
                phoneService.findPhoneByLabel(label)
                        .stream().map(mapper::phoneToPhoneDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("getListCPU")
    public ResponseEntity<Object> getListCPU(){
        return new ResponseEntity<>(
                cpuService.findAll()
                        .stream().map(mapper::cpuToCpuDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("getListBrand")
    public ResponseEntity<Object> getListBrand(){
        return new ResponseEntity<>(
                brandService.findAll()
                        .stream().map(mapper::brandToBrandDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }
        @PostMapping("save")
        public ResponseEntity<Object> savePhone(@RequestBody PhoneDto dto){
            if(!Objects.isNull(dto)&&!Objects.isNull(dto.getBinaryImage())){
                ImageUtils.downloadImage(dto.getImage(),dto.getBinaryImage(), URL_IMG);
            }
            Phone phone = mapper.phoneDtoToPhone(dto);
            phone.setLabel(RenderLabel.renderLabel(dto));
        return new ResponseEntity<>(
               phoneService.save(phone),
                HttpStatus.OK
        );
    }
    @GetMapping(value = "image/load/{image}")
    public ResponseEntity<Object> loadImageProduct(@PathVariable("image")String image){
        try{
            Path path = Paths.get(URL_IMG + image);
            byte[] bytes = Files.readAllBytes(path);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
