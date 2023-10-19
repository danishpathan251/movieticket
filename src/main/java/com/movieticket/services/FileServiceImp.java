//package com.movieticket.services;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//@Service
//public class FileServiceImp implements FileService{
//    @Override
//    public String uploadImage(String path, MultipartFile file) {
//
//        String name = file.getOriginalFilename();
//
//        String filePath = path+ File.separator+name;
//
//        File f = new File(path);
//
//                if(!f.exists()){
//                    f.mkdir();
//                }
////        return null;
//        Files.copy(file.getInputStream(), Paths.get());
//    }
//}
