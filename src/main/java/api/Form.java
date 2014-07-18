package api;

public interface Form {
    void setInput(String inputLocator, String value);
    Page submit()throws InvalidFieldException;
}
