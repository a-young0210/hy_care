package com.example.hycare.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import static com.example.hycare.entity.ApiResult.S3_FAIL;

@Component
@RequiredArgsConstructor
public class S3Uploader {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(File uploadFile, String filePath) {
        Date today = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "HHmmss"; //HHmmss 형태로 변환
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);

        String fileName = filePath + "/" + formatter.format(today) + "_chatGPT.json";   // S3에 저장될 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        return uploadImageUrl;
    }

    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
        try{
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        }catch (Exception e) {
            return S3_FAIL.getMessage();
        }
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }
}