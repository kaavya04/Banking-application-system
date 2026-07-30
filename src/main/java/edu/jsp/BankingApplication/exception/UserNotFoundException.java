package edu.jsp.BankingApplication.exception;

public class UserNotFoundException extends RuntimeException {

    String resourceName;
    String fieldName;
    long fieldValue;

    public UserNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super();
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    @Override
    public String getMessage() {
        return resourceName + " not found for " + fieldName + " = " + fieldValue;
    }
}