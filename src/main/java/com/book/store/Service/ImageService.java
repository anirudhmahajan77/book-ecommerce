package com.book.store.Service;

import com.book.store.Model.FileDb;
import com.book.store.Repository.FileDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    @Autowired
    FileDbRepository fileDbRepository;

    public String uploadImage(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        String imageId = UUID.randomUUID().toString();
        FileDb filedb =  new FileDb( imageId, fileName, file.getContentType(), file.getBytes());
        fileDbRepository.save(filedb);
        return imageId;
    }

    public InputStream getImage(String id) {
        FileDb fileDb = fileDbRepository.findById(id).get();

        InputStream image = new ByteArrayInputStream(fileDb.getData());
        return image;
    }

    public List<FileDb> getAllImages(){
        return fileDbRepository.findAll();
    }
}
