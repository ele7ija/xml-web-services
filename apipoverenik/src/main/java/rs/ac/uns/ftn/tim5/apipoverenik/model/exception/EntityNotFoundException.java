package rs.ac.uns.ftn.tim5.apipoverenik.model.exception;

public class EntityNotFoundException extends RuntimeException{

    private Long id;
    private Class classObject;

    public EntityNotFoundException(Long id, Class classObject) {
        this.id = id;
        this.classObject = classObject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Class getClassObject() {
        return classObject;
    }

    public void setClassObject(Class classObject) {
        this.classObject = classObject;
    }
}
