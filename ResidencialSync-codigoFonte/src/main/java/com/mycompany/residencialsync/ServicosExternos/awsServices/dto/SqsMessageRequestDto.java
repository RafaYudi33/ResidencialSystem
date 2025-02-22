package com.mycompany.residencialsync.ServicosExternos.awsServices.dto;

public record SqsMessageRequestDto(String to, String subject, String body, String sender) {
}
