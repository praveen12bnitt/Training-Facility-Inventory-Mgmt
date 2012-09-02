package com.smartworks.test;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class FileUploadBean extends SimpleFormController {


        private MultipartFile file;

        public void setFile(MultipartFile file) {
            this.file = file;
        }

        public MultipartFile getFile() {
            return file;
        }

}
