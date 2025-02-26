package dio.spring.projeto.spring.user.and.address.infra;

import dio.spring.projeto.spring.user.and.address.exceptions.emptyOrBlankValues.CepIsEmptyException;
import dio.spring.projeto.spring.user.and.address.exceptions.exist.NameExistException;
import dio.spring.projeto.spring.user.and.address.exceptions.exist.PhoneExistException;
import dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat.InvalidAddressCepException;
import dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat.InvalidEmailException;
import dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat.InvalidFormatPhoneException;
import dio.spring.projeto.spring.user.and.address.exceptions.invalidFormat.InvalidNumberCepException;
import dio.spring.projeto.spring.user.and.address.exceptions.notfound.CepNotFoundException;
import dio.spring.projeto.spring.user.and.address.exceptions.exist.EmailExistException;
import dio.spring.projeto.spring.user.and.address.exceptions.notfound.UserNotFoundException;
import dio.spring.projeto.spring.user.and.address.exceptions.emptyOrBlankValues.NameIsEmptyException;
import dio.spring.projeto.spring.user.and.address.exceptions.emptyOrBlankValues.PhoneIsEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> genericException(Exception ex) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CepNotFoundException.class)
    public ResponseEntity<ApiError> cepNotFoundException(CepNotFoundException cp) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .errors(List.of(cp.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidNumberCepException.class)
    public ResponseEntity<ApiError> invalidCepException(InvalidNumberCepException cp) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(cp.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAddressCepException.class)
    public ResponseEntity<ApiError> invalidAddressCepException(InvalidAddressCepException ex) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CepIsEmptyException.class)
    public ResponseEntity<ApiError> invalidCepEmpty(CepIsEmptyException ex) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> userExistException(UserNotFoundException ex) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameExistException.class)
    public ResponseEntity<ApiError> nameExistException(NameExistException ex) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.CONFLICT.value())
                .status(HttpStatus.CONFLICT.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<ApiError> emailExistException(EmailExistException ex) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.CONFLICT.value())
                .status(HttpStatus.CONFLICT.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PhoneExistException.class)
    public ResponseEntity<ApiError> phoneExistException(PhoneExistException ex) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.CONFLICT.value())
                .status(HttpStatus.CONFLICT.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NameIsEmptyException.class)
    public ResponseEntity<ApiError> nameValueNullOrEmpty(NameIsEmptyException ex) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PhoneIsEmptyException.class)
    public ResponseEntity<ApiError> phoneValueNullOrEmpty(PhoneIsEmptyException ex) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidEmailException.class, InvalidFormatPhoneException.class})
    public ResponseEntity<ApiError> argumentNotValidException(RuntimeException ex) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}

