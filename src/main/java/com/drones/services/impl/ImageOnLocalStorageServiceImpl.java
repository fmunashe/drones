package com.drones.services.impl;

import com.drones.exception.ImageNotFoundException;
import com.drones.services.ImageOnLocalStorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Objects;

@Slf4j
@Service
public class ImageOnLocalStorageServiceImpl implements ImageOnLocalStorageService {


    @Override
    public String getImageOnDisk(String imageName) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/medication_imgs/" + imageName + ".jpeg");
        return convertImageToBase64String(in);
    }


    public String convertImageToBase64String(InputStream in) throws IOException {
        if (Objects.isNull(in)) {
            log.error("Could not find the image on the path ");
            throw new ImageNotFoundException("Image not found: ");
        }
        byte[] image = IOUtils.toByteArray(in);
        return Base64
                .getEncoder()
                .encodeToString(image);
    }


}
