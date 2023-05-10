package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.request.EmployeeRequestDto;

public interface ISaveEmployee {
    void saveEmployeeRestaurant(EmployeeRequestDto employeeRequestDto, String token);
}
