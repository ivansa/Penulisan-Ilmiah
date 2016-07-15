/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.controller;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ivans
 */
@RestController
@RequestMapping("/api")
public class Mp3Controller {
    
    private final Logger LOGGER = LoggerFactory.getLogger(Mp3Controller.class);
    
    @RequestMapping(value = "/sound/{filename}", method = RequestMethod.GET)
    public void getMp3(@PathVariable String filename, HttpServletResponse response) {
        try {
            // get your file as InputStream
            InputStream is = getClass().getResourceAsStream("/sounds/" + filename.toLowerCase() + ".mp3");
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.setContentType("audio/wav");
            response.flushBuffer();
        } catch (IOException ex) {
            LOGGER.info("Error writing file to output stream. Filename was '{}'", filename, ex);
        }
    }
}
