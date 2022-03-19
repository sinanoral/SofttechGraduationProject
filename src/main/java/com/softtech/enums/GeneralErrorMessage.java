package com.softtech.enums;

public enum GeneralErrorMessage implements BaseErrorMessage {
    ENTITY_NOT_FOUND("Entity not found!", "Entity could not find with this id."),
    ENTITIES_NOT_FOUND("Entities Could Not Found", "There are not any saved entities."),
    INVALID_REQUEST("Invalid parameter", "The request sent with parameters is incorrect."),
    INTERNAL_SERVER_ERROR("Encounter internal server", "Server encountered an unexpected condition that prevented it from fulfilling the request"),
    ;

    private final String message;
    private final String detailMessage;

    GeneralErrorMessage(String message, String detailMessage) {
        this.message = message;
        this.detailMessage = detailMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDetailMessage() {
        return detailMessage;
    }
}
