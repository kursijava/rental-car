package com.roi.rentalcar.static_data;

public enum StaticMessages {
    NOTFOUND("%s with id %s not found"),
    IDNULL("Id must be null"),
    IDNOTNULL("Id must not be null"),
    NAMEEXISTS("%s with name %s already exists"),

    ;
    private final String message;
    StaticMessages(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public static String setIdNotFound(Class c, Object id){
        return String.format(NOTFOUND.getMessage(), c.getSimpleName(), id);
    }

    public static String setNameExists(Class c, String name){
        return String.format(NAMEEXISTS.getMessage(), c.getSimpleName(), name);
    }

    public static String deleted(Class c, Object id) {
        return String.format("%s with id %s has ben deleted", c.getSimpleName(), id);
    }

}
