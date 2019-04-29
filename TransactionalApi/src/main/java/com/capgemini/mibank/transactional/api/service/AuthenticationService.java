package com.capgemini.mibank.transactional.api.service;

import com.capgemini.mibank.transactional.api.auth.UserTokenState;
import com.capgemini.mibank.transactional.api.dto.ResponseOut;
import com.capgemini.mibank.transactional.api.dto.AuthenticationRequest;
import com.capgemini.mibank.transactional.api.entity.Customer;
import com.capgemini.mibank.transactional.api.helper.ConstantHelper;
import com.capgemini.mibank.transactional.api.helper.TokenHelper;
import com.capgemini.mibank.transactional.api.helper.UtilHelper;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Jaime Cadena
 */
@Service
public class AuthenticationService {

    @Autowired
    TokenHelper tokenHelper;
    @Autowired
    private AuthenticationManager authenticationManager;

    public String authenticate(AuthenticationRequest request) {
        final Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Customer user = (Customer) authentication.getPrincipal();
        String jws = tokenHelper.generateToken(user.getCustomerId());

        return jws;
    }

    public ResponseEntity<ResponseOut> refreshToken(HttpServletRequest request, Principal principal) {
        String authToken = tokenHelper.getToken(request);
        if (authToken != null && principal != null) {
            String refreshedToken = tokenHelper.refreshToken(authToken);
            return ResponseEntity.ok(UtilHelper.mapperResponse(
                    new UserTokenState(
                            refreshedToken,
                            ConstantHelper.EXPIRES_IN),
                    ConstantHelper.CODE_EXITO));
        } else {
            UserTokenState userTokenState = new UserTokenState();
            return ResponseEntity.ok(UtilHelper.mapperResponse(
                    ConstantHelper.CODE_ERROR_REFRESCAR_TOKEN));
        }
    }
}
