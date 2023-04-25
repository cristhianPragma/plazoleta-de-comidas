package com.pragma.plazoletas.infrastructure.exceptionhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import java.io.IOException;
import java.util.Map;

public class ExceptionFeignClient implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        Response.Body responseBody = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        ObjectMapper objectMapper;
        String errorMessage;
        byte[] bodyBytes;
        try {
            bodyBytes = Util.toByteArray(responseBody.asInputStream());
            objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(bodyBytes, Map.class);
            errorMessage = (String) responseMap.get("message");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (responseStatus.is5xxServerError()) {
            return new RequestException(errorMessage+ " Desde: " + requestUrl, responseStatus);
        } else if (responseStatus.is4xxClientError()) {
            return new RequestException(requestUrl + " no encontrada ", responseStatus);
        } else {
            return new RequestException(errorMessage+ " Desde: " + requestUrl, responseStatus);
        }
    }
}
