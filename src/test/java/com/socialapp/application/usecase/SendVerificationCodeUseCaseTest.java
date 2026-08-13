package com.socialapp.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.socialapp.application.port.EmailNotifier;

@DisplayName("Caso de Uso: Envío de Código de Verificación (SendVerificationCodeUseCase)")
public class SendVerificationCodeUseCaseTest {

    @Test
    @DisplayName("Debe invocar al notificador exactamente una vez al enviar el código")
    public void shouldInvokeNotifierExactlyOnceWhenSendingCode() {
        // Arrange
        EmailNotifier notifierMock = Mockito.mock(EmailNotifier.class);
        SendVerificationCodeUseCase useCase = new SendVerificationCodeUseCase(notifierMock);

        // Act
        useCase.execute("usuario@gmail.com", "123456");

        // Assert
        Mockito.verify(notifierMock, Mockito.times(1))
                .sendEmail("usuario@gmail.com", "Tu código de verificación es: 123456");
    }
}
