package com.pragma.plazoletas.infrastructure.security.jwt;

public interface IExtractAndValidateToken {
    Long extract(String token);
}
