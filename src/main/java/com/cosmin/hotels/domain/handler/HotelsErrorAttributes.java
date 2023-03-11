package com.cosmin.hotels.domain.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class HotelsErrorAttributes extends DefaultErrorAttributes  {

    final Logger LOG = LoggerFactory.getLogger(HotelsErrorAttributes.class);

    private static final String TRACE = "trace";
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        final Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options.including(ErrorAttributeOptions.Include.values()));
        LOG.error("Error! Trace: {}", errorAttributes.get(TRACE));
        errorAttributes.remove(TRACE);
        return errorAttributes;
    }

}
