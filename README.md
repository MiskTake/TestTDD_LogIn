# Sistema de Autenticación

Sistema de autenticación para una red social, construido en Java aplicando **TDD (Test-Driven Development)**: cada clase nace de un test que primero falla, y solo se agrega la lógica mínima necesaria para hacerlo pasar (ciclo Red-Green-Refactor).

## Tecnologías

- Java 17
- Maven
- JUnit 5 (6.1.2)
- Mockito (5.23.0)
- JaCoCo (cobertura de código)

## Estructura del proyecto

```
src/main/java/com/socialapp/domain/            -> entidades y lógica de negocio
src/main/java/com/socialapp/domain/exception/  -> excepciones propias del dominio
src/test/java/com/socialapp/domain/            -> tests unitarios
src/test/java/com/socialapp/                   -> utilidades de test (logger de consola)
```

## Componentes actuales

| Clase | Responsabilidad |
|---|---|
| `User` | Entidad de dominio: representa a un usuario (email + contraseña) |
| `PasswordValidator` | Valida que la contraseña tenga 8+ caracteres |
| `EmailValidator` | Valida que el correo tenga formato básico válido |
| `VerificationCodeSender` | Envía un código de verificación por correo (usa `EmailNotifier`) |
| `VerificationCodeValidator` | Compara el código ingresado contra el generado |
| `UserRepository` | Interfaz que simula guardar/buscar usuarios (sin base de datos real) |
| `RegistrationService` | Registra un usuario nuevo, validando email, contraseña y duplicados |
| `LoginService` | Autentica a un usuario existente contra el repositorio |

## Cómo correr los tests

```
mvn test
```

Cada test se imprime individualmente en la consola, agrupado por clase (gracias a una extensión de JUnit 5 personalizada: `ConsoleTestLogger`). Al final se muestra el reporte de cobertura de JaCoCo.

## Cobertura de código

![Reporte de cobertura JaCoCo](image/image.png)

## Metodología

Cada clase nace de un test que primero falla (**RED**), luego se escribe la mínima lógica necesaria para pasarlo (**GREEN**), y solo entonces se limpia el código si hace falta (**REFACTOR**). Las dependencias externas (envío de correos, base de datos de usuarios) se simulan con **mocks de Mockito**, para no depender de servicios reales en los tests.
