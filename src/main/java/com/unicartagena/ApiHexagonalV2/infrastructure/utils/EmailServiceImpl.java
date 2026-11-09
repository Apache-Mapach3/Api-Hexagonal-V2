package com.unicartagena.ApiHexagonalV2V2.infrastructure.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void enviarCorreoRecuperacion(String destinatario, String link) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject("Recuperación de Contraseña - Api Hexagonal");
        message.setText("Haz clic en el siguiente enlace para restablecer tu contraseña (válido por 1 hora):\n\n" + link);
        mailSender.send(message);
    }
}