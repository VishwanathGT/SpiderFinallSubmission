package com.vishwa.dunzo.rest;

/**
*
* @author  Vishwanath G T, Shiva Chetan K S, Manasa D, Vinuta 
* @version 1.0
* @since   2019-08-04
* 
* All rights reserved. © Copyright 2019
* 
*/

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vishwa.ImageProcessing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
public class UploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C:\\FileUpload\\";

    @PostMapping("/upload") // //new annotation since 4.3
    public ResponseEntity singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws Exception {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

        try {

            // Get the file and save it somewhere
        	String filename = UPLOADED_FOLDER + file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filename);
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            ImageProcessing.processImage(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}
