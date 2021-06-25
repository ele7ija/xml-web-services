package rs.ac.uns.ftn.tim5.model.exception;

public class XmlDatabaseException extends RuntimeException {

    private String text;

    public XmlDatabaseException() {
    }

    public XmlDatabaseException(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
