package com.unicartagena.ApiHexagonalV2.infrastructure.utils;
public interface EmailService {
    void enviarCorreoRecuperacion(String destinatario, String link);
}