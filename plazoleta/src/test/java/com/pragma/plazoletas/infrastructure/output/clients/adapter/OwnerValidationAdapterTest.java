package com.pragma.plazoletas.infrastructure.output.clients.adapter;

import com.pragma.plazoletas.application.dto.response.RoleResponseDto;
import com.pragma.plazoletas.infrastructure.exception.RequestException;
import com.pragma.plazoletas.infrastructure.output.clients.feignclients.IUserFeignClient;
import com.pragma.plazoletas.infrastructure.security.jwt.JwtService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.mock.Expectation;
import org.mockserver.netty.MockServer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockitoExtension.class)
class OwnerValidationAdapterTest {
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private OwnerValidationAdapter ownerValidationAdapter;
    private final String TOKEN ="Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRo";
    private final String JWT = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRo";
    private static ClientAndServer mockServer;

    @BeforeAll
    public static  void startMockServer(){
        mockServer = ClientAndServer.startClientAndServer(8081);
    }
    @AfterAll
    public static void stopMockServer(){
        mockServer.stop();
    }

    @Test
    void validationOwnerAdapterSuccessTest() {
        Long OWNER_ID = 2L;
        String roleJson="{\"id\": 2, \"name\": \"Propietario\"}";
        Expectation[] expectations = new MockServerClient("localhost", 8081)
                .when(request().withMethod("GET")
                        .withPath("/users/role/2")).respond(response().withBody(roleJson));

        when(jwtService.parseJwt(TOKEN)).thenReturn(JWT);
        when(jwtService.isValidToken(JWT)).thenReturn(true);
        assertDoesNotThrow(() -> ownerValidationAdapter.validation(OWNER_ID, TOKEN));

    }
    @Test
    void validationOwnerAdapterExceptionTest() {
        Long OWNER_ID = 3L;
        String roleJson2="{\"id\": 3, \"name\": \"Propietario\"}";
        Expectation[] expectations = new MockServerClient("localhost", 8081)
                .when(request().withMethod("GET")
                        .withPath("/users/role/3")).respond(response().withBody(roleJson2));

        when(jwtService.parseJwt(TOKEN)).thenReturn(JWT);
        when(jwtService.isValidToken(JWT)).thenReturn(true);

        RequestException exception = assertThrows(RequestException.class,
                ()->ownerValidationAdapter.validation(OWNER_ID, TOKEN));
        assertEquals("El usuario ingresado no tiene el rol, para esta acción",
                exception.getMessage());
    }
}