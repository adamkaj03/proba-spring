package com.adam.buzas.onlab.main.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileRequest {
    private MultipartFile file;
}
