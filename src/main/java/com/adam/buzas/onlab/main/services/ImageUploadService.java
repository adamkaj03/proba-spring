package com.adam.buzas.onlab.main.services;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageUploadService {
    private final String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=webshopadam;AccountKey=yHIuEPU8r6dwsdVb6nrY1/UGxfgaLusnT8OZgtngMHIAhri4F7kysNf1tMvbzhKTUw2C5oB+DyjA+ASt8gP3fQ==;EndpointSuffix=core.windows.net";

    public String uploadImage(MultipartFile file, String blobName) {
        try {
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
            CloudBlobContainer container = blobClient.getContainerReference("kepek"); // Az Azure Blob Storage tároló neve

            CloudBlockBlob blob = container.getBlockBlobReference(blobName); // Blob azonosítója
            blob.upload(file.getInputStream(), file.getSize());
            return blob.getUri().toString();
        } catch (Exception e) {
            // Kezeld az exception-t
        }
        return "";
    }
}
