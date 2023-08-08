package ma.gestionnaire.DocGestionnaire.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MultipartExeptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity handleFileUploadingError(MultipartException exception) {
        System.out.println("Failed to upload attachment " + exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
