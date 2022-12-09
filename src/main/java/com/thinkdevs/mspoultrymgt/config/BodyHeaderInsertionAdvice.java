package com.thinkdevs.mspoultrymgt.config;


import com.thinkdevs.mspoultrymgt.controller.*;
import com.thinkdevs.mspoultrymgt.data.respose.Response;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import static com.thinkdevs.mspoultrymgt.data.respose.BodyHeader.header;
import static com.thinkdevs.mspoultrymgt.utils.Constants.Constants.*;


@RestControllerAdvice
public class BodyHeaderInsertionAdvice implements ResponseBodyAdvice<Response> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return methodParameter.getContainingClass() == BirdController.class
                ||methodParameter.getContainingClass() == BirdMortalityController.class ||
                methodParameter.getContainingClass() == BirdPurchaseController.class
                ||methodParameter.getContainingClass()== EggsController.class ||
                methodParameter.getContainingClass() == FeedController.class ||
                methodParameter.getContainingClass() == OrderController.class;
    }

    @Override
    public Response beforeBodyWrite(Response resource,
                                    MethodParameter methodParameter, MediaType mediaType,
                                    Class<? extends HttpMessageConverter<?>> aClass,
                                    ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (resource == null) {
            resource = new Response<String>();
            resource.setHeader(header(SUCCESS_CODE, SUCCESS_RESPONSE_MESSAGE, SUCCESS_CUSTOMER_MESSAGE));
        }

        if (resource.getResponseCode() != null)
            resource.setHeader(header(resource.getResponseCode(), SUCCESS_RESPONSE_MESSAGE, SUCCESS_CUSTOMER_MESSAGE));
        else
            resource.setHeader(header(SUCCESS_CODE, SUCCESS_RESPONSE_MESSAGE, SUCCESS_CUSTOMER_MESSAGE));

        return resource;
    }
}
