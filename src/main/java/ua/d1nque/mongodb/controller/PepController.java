package ua.d1nque.mongodb.controller;

import org.springframework.web.bind.annotation.*;

import ua.d1nque.mongodb.services.MongoService;
import ua.d1nque.mongodb.services.UnZipAndParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class PepController {

    private final UnZipAndParseService unZipAndParseService;
    private final MongoService mongoService;

    @Autowired
    public PepController(UnZipAndParseService unZipService, MongoService mongoService){
        this.unZipAndParseService = unZipService;
        this.mongoService = mongoService;
    }

    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        unZipAndParseService.parseUnZipJsonFilestodb(file);
    }

    @GetMapping("/findByFullNameEn")
    public List<Object> findByFullNameEnContaining(@RequestParam("fullNameEn") String fullNameEn){
        return mongoService.findByFullNameEnContaining(fullNameEn);
    }

    @GetMapping("/getTopTenPepNames")
    public List<Object> getTopTenPepNames(){
        return mongoService.getTopTenPepNames();
    }


}
