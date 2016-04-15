package org.itechart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.itechart.entity.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping(value = "api/fileUpload")
public class UploadController {
    private static final Logger LOGGER = LogManager.getLogger(UploadController.class);

    @RequestMapping(method = RequestMethod.POST)
    public void handleFormUpload(@RequestPart(value = "user") String userString, @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(userString, User.class);

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
