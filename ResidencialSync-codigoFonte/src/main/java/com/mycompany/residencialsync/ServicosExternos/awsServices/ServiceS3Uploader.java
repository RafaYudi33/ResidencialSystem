package com.mycompany.residencialsync.ServicosExternos.awsServices;


import com.mycompany.residencialsync.Model.BoletoCondominial;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.nio.file.Paths;
import java.time.Duration;

@Service
public class ServiceS3Uploader {
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final String bucketName = "boletos-residencial-sync";

    public ServiceS3Uploader(S3Client s3Client, S3Presigner s3Presigner) {
        this.s3Client = s3Client;
        this.s3Presigner = s3Presigner;
    }

    public String uploadFile(String filePath, BoletoCondominial boleto) throws Exception {

        String fileKey = makeFileKey(boleto);
        s3Client.putObject(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileKey)
                        .build(),
                Paths.get(filePath));


        return generatePresignedUrl(fileKey);
    }

    private String generatePresignedUrl(String fileKey) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileKey)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofDays(7))
                .getObjectRequest(getObjectRequest)
                .build();

        return s3Presigner.presignGetObject(presignRequest).url().toString();
    }

    public String makeFileKey(BoletoCondominial boletoCondominial) {
        return boletoCondominial.getPropriedade().getCep() +
                "-" + boletoCondominial.getPropriedade().getProprietario().getCpf();
    }

}
