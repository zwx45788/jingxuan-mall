package com.example.shopping.pkg.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Service
public class OssService {

    // 1. 直接注入由 Starter 自动配置好的 OSS Client
    @Autowired
    private OSS ossClient;

    // 2. 从配置文件读取 Bucket 名称
    @Value("${oss.bucket-name}")
    private String bucketName;

   @Value("${oss.endpoint}")
   private String endpoint;

    public String uploadFile(MultipartFile file) {
        // 获取文件原始名
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            // 抛出异常，终止当前方法执行
            throw new IllegalArgumentException("上传文件名不能为空");
        }
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 拼接路径：2023/10/27/uuid.jpg
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                + "/" + UUID.randomUUID().toString() + extension;

        try (InputStream inputStream = file.getInputStream()) {
            // 构建上传请求
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    bucketName,
                    fileName,
                    inputStream
            );

            // 执行上传
            ossClient.putObject(putObjectRequest);

            // 路径
            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("文件上传失败", e);
        }
    }

    public String downloadFile(String filename) {
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);

        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, filename);

        request.setExpiration(expiration);

        URL url = ossClient.generatePresignedUrl(request);

        return url.toString();
    }
}
