package com.book.store.Controller;

import com.book.store.Model.FileDb;
import com.book.store.Service.BookService;
import com.book.store.Service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/general")
@Tag(name = "General Controller", description = "This is a controller for CRUD operations" +
        " being shared between users, book and authors.")
public class GenericController {

    @Autowired
    ImageService imageService;

    @PostMapping(value = "/image")
    @RolesAllowed({"ADMIN","CUSTOMER"})
    @Operation(summary = "Add New Image", description = "Add new image to database and return it's ID")
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile image) throws IOException {
        String imageId = "";
        try {
            imageId = imageService.uploadImage(image);
        } catch (Exception e) {
            return new ResponseEntity("Not Working!:", HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity(imageId, HttpStatus.OK);
    }

    @GetMapping(value = "/image/{id}")
    @Operation(summary = "Get Image by ID", description = "Using Image ID return the image to the user.")
    public void loadImage(
            @PathVariable("id") String id,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = imageService.getImage(id);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

    @GetMapping("/image/all")
    @Operation(summary = "Get All Image IDs", description = "Get All Image IDs hosted on the database")
    public ResponseEntity getAllImages(){
        List<FileDb> result = imageService.getAllImages();
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
