package sample;

public class StringSubject extends Subject {

    private String field = "";

    public String getField() {
        return field;
    }

    public void setField(String field) {
        if(!this.field.equals(field) && field != null) {
            this.field = field;
            notifyObservers();
        }
    }
}
