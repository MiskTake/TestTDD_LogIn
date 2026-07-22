package com.socialapp.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("Envío de Código de Verificación (VerificationCodeSender)")
public class VerificationCodeSenderTest {

    @Test
    @DisplayName("Debe invocar al notificador exactamente una vez al enviar el código")
    public void shouldInvokeNotifierExactlyOnceWhenSendingCode() {
        // Arrange
        EmailNotifier notifierMock = Mockito.mock(EmailNotifier.class);
        VerificationCodeSender sender = new VerificationCodeSender(notifierMock);

        // Act
        sender.sendVerificationCode("usuario@gmail.com", "123456");

        // Assert
        Mockito.verify(notifierMock, Mockito.times(1))
                .sendEmail("usuario@gmail.com", "Tu código de verificación es: 123456");
    }
}
