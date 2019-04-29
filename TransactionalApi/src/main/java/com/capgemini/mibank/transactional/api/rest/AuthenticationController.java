package com.capgemini.mibank.transactional.api.rest;

import com.capgemini.mibank.transactional.api.dto.AuthenticationRequest;
import com.capgemini.mibank.transactional.api.helper.ConstantHelper;
import com.capgemini.mibank.transactional.api.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jaime Cadena
 */
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @ApiOperation(value = "Perimite verificar si un usuario tiene permisos para acceder a consumir el api",
                  notes = "En caso de exito se devolvera un token que lo acredita como usuario valido. En caso contrario retornara un error.")
    @PostMapping(value = "/login")
    public ResponseEntity createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
            HttpServletResponse response) throws AuthenticationException, IOException {
        String jws = authenticationService.authenticate(authenticationRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", ConstantHelper.BEARER + " " + jws);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(null);
    }

    @ApiOperation(value = "Perimite renovar el token que lo acredita como un usuario valido en el sistema",
                  notes = "Debe usar este metodo para mantener viva su sesion una vez este cerca a finalizar el tiempo de vida util del token")
    @PostMapping(value = "/refresh")
    public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request, HttpServletResponse response,
            Principal principal) {
        return authenticationService.refreshToken(request, principal);
    }

}
