package com.cosmin.hotels.domain.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringJoiner;

@Component
public class HotelsErrorAttributes extends DefaultErrorAttributes {

    private static final Logger LOG = LoggerFactory.getLogger(HotelsErrorAttributes.class);

    private static final String KEY_ERROR = "error";
    private static final String KEY_ERRORS = "errors";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_STATUS = "status";
    private static final String KEY_TRACE = "trace";
    private static final String KEY_PATH = "path";
    private static final String KEY_EXCEPTION = "exception";
    private static final String KEY_MESSAGE = "message";

    private static final String KEY_CODE = "code";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";

    private final MessageSource messageSource;

    public HotelsErrorAttributes(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {

        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options.including(ErrorAttributeOptions.Include.values()));
        final Throwable error = super.getError(webRequest);
        if (error instanceof HotelsNotFoundException) {
            final HotelsNotFoundException hotelsNotFoundException = (HotelsNotFoundException) error;
            errorAttributes.put(KEY_CODE, HttpStatus.NOT_FOUND.value());
            errorAttributes.put(KEY_NAME, HttpStatus.NOT_FOUND.getReasonPhrase());
            errorAttributes.put(KEY_DESCRIPTION, String.valueOf(errorAttributes.get(KEY_MESSAGE)));
        } else {
            errorAttributes.put(KEY_CODE, String.valueOf(errorAttributes.get(KEY_STATUS)));
            errorAttributes.put(KEY_NAME, String.valueOf(errorAttributes.get(KEY_ERROR)));
            errorAttributes.put(KEY_DESCRIPTION, String.valueOf(errorAttributes.get(KEY_MESSAGE)));
        }
        //LOG.error("ERROR! trace: {}",  errorAttributes.get(KEY_TRACE));

        resolveBindingErrors(errorAttributes);

        errorAttributes.remove(KEY_ERROR);
        errorAttributes.remove(KEY_TIMESTAMP);
        errorAttributes.remove(KEY_STATUS);
        errorAttributes.remove(KEY_TRACE);
        errorAttributes.remove(KEY_PATH);
        errorAttributes.remove(KEY_EXCEPTION);
        errorAttributes.remove(KEY_ERRORS);
        errorAttributes.remove(KEY_MESSAGE);

        LOG.error("ERROR! code: {}, name: {}, description: {}",  errorAttributes.get(KEY_CODE), errorAttributes.get(KEY_NAME), errorAttributes.get(KEY_DESCRIPTION));
        return errorAttributes;
    }

    private void resolveBindingErrors(Map<String, Object> errorAttributes) {
        List<ObjectError> errors = (List<ObjectError>) errorAttributes.get(KEY_ERRORS);
        if (errors == null) {
            return;
        }
        StringJoiner sj = new StringJoiner(", ");
        for (ObjectError error : errors) {
            String resolved = messageSource.getMessage(error,  Locale.US);
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                sj.add("'" + fieldError.getField() + "' " + resolved + " and value was '" + fieldError.getRejectedValue() + "'");
            } else {
                sj.add(resolved);
            }
        }
        errorAttributes.put(KEY_DESCRIPTION, sj.toString());
    }

}
