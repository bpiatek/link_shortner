package pl.bpiatek.linkshortner.infrastructure.mvc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.bpiatek.linkshortner.link.api.InvalidLinkException;
import pl.bpiatek.linkshortner.link.api.LinkNotFoundException;

/**
 * Created by Bartosz Piatek on 16/08/2019
 */
@ControllerAdvice
class ExceptionHandlingAdvice {

  @ExceptionHandler(LinkNotFoundException.class)
  ResponseEntity<ErrorMessage> handleNoFoundLinks(LinkNotFoundException e) {
    ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidLinkException.class)
  ResponseEntity<ErrorMessage> handleInvalidLinkValidation(InvalidLinkException e) {
    ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @Getter
  @AllArgsConstructor
  class ErrorMessage {
    private Integer code;
    private String message;
    private String details;

    ErrorMessage(Integer code, String message) {
      this.code = code;
      this.message = message;
    }
  }
}
