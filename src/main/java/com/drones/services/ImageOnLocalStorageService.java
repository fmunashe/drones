package com.drones.services;

import java.io.IOException;
import java.io.InputStream;

public interface ImageOnLocalStorageService {

    String getImageOnDisk(String imageName) throws IOException;
    String convertImageToBase64String(InputStream in) throws IOException;

}
