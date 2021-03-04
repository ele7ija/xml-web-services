package rs.ac.uns.ftn.tim5.apipoverenik.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rs.ac.uns.ftn.tim5.apipoverenik.model.exception.*;
import rs.ac.uns.ftn.tim5.apipoverenik.model.exception.Error;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(InvalidXmlException.class)
    public ResponseEntity<Error> getError(InvalidXmlException e) {
        String message = "Invalid xml. Error parsing xml for document " + e.getClassObject().getSimpleName() + ".\n Message: " + e.getText();
        return new ResponseEntity<>(new Error(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidXmlDatabaseException.class)
    public ResponseEntity<Error> getError(InvalidXmlDatabaseException e) {
        String message = "Invalid xml. Error parsing xml retrieved from database for document " + e.getClassObject().getSimpleName() + ".\n Message: " + e.getText();
        return new ResponseEntity<>(new Error(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(XmlDatabaseException.class)
    public ResponseEntity<Error> getError(XmlDatabaseException e) {
        String message = "Xml database exception.\n Message: " + e.getText();
        return new ResponseEntity<>(new Error(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> getError(EntityNotFoundException e) {
        String message = "Xml document " + e.getClassObject().getSimpleName() + " with id " + e.getId() + " not found! Make sure your request is valid!";
        return new ResponseEntity<>(new Error(message), HttpStatus.NOT_FOUND);
    }
}
