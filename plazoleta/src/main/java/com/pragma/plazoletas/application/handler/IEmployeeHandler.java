package com.pragma.plazoletas.application.handler;

import com.pragma.plazoletas.application.dto.request.EmployeeRequestDto;

public interface IEmployeeHandler {
    void saveEmployeeHandler(EmployeeRequestDto employeeRequestDto, String token);
}
