package com.enes.user.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BusinessLogicException extends RuntimeException implements Supplier<BusinessLogicException> {

    private final ERROR error;

    private Map<String, Object> parameters = new HashMap<>();

    public BusinessLogicException(ERROR error) {
        super(error.getDefaultMessage());
        this.error = error;
    }

    public BusinessLogicException(ERROR error, String message) {
        super(message);
        this.error = error;
    }

    public BusinessLogicException addParameter(String key, Object value) {
        parameters.put(key, value);
        return this;
    }

    public String getCategory() {
        return error.getCategory();
    }

    public String getType() {
        return error.getType();
    }

    public String getCode() {
        return error.getCode();
    }

    public int getHttpStatus() {
        return error.getHttpStatus();
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public BusinessLogicException get() {
        return this;
    }

    public enum ERROR {
        USER_ALREADY_EXISTS("VALIDATION", "RECORD_EXISTS", "422001", "User with this email already exists", 422);

        private final String category;

        private final String type;

        private final String code;

        private final String defaultMessage;

        private final int httpStatus;

        ERROR(String category, String type, String code, String defaultMessage, int httpStatus) {
            this.category = category;
            this.type = type;
            this.code = code;
            this.defaultMessage = defaultMessage;
            this.httpStatus = httpStatus;
        }

        public String getCategory() {
            return category;
        }

        public String getType() {
            return type;
        }

        public String getCode() {
            return code;
        }

        public String getDefaultMessage() {
            return defaultMessage;
        }

        public int getHttpStatus() {
            return httpStatus;
        }
    }
}
