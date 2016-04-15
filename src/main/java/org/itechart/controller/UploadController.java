package org.itechart.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping(value = "api/fileUpload")
public class UploadController {
    private static final Logger LOGGER = LogManager.getLogger(UploadController.class);

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void handleFormUpload(@RequestParam(value = "user", required = false) String user, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try (InputStream in = file.getInputStream()) {
                Files.copy(in, new File("d:\\IdeaProjects\\angular-adventure\\src\\main\\webapp\\img\\img1.jpg").toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                LOGGER.error("File wasn't saved correctly", e);
            }
        }
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        LOGGER.error(exc.getMessage(), exc);
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
