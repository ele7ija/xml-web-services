package rs.ac.uns.ftn.tim5.apipoverenik.model.exception;

public class InvalidXmlException extends RuntimeException {

    private Class classObject;
    private String text;

    public InvalidXmlException() {
    }

    public InvalidXmlException(Class classObject, String text) {
        this.classObject = classObject;
        this.text = text;
    }

    public Class getClassObject() {
        return classObject;
    }

    public void setClassObject(Class classObject) {
        this.classObject = classObject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
