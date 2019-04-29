package com.capgemini.mibank.transactional.api.auth;

import com.capgemini.mibank.transactional.api.helper.ConstantHelper.TokenParameter;
import com.capgemini.mibank.transactional.api.helper.TokenHelper;
import com.capgemini.mibank.transactional.api.service.CustomUserDetailsService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Jaime Cadena
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private TokenHelper tokenHelper;
	private CustomUserDetailsService userDetailsService;

	public TokenAuthenticationFilter(TokenHelper tokenHelper, CustomUserDetailsService userDetailsService) {
		this.tokenHelper = tokenHelper;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		System.out.println("recibiendo petición.....");
		String username;
		String authToken = tokenHelper.getToken(request);
		System.out.println("toquen obtenido: " + authToken);
		if (authToken != null) {
			// get username from token
			username = tokenHelper.GetClimb(authToken, TokenParameter.USER).toString();
			System.out.println("user from token: " + username);
			if (username != null) {
				// get user
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				System.out.println("User Detail: " + userDetails.getUsername() + "-" + userDetails.getPassword());
				boolean isOk = tokenHelper.validateToken(authToken, userDetails);
				System.err.println("resultado validacion del token: " + isOk);
				if (isOk) {
					// create authentication
					System.out.println("Auth OK");
					TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
					authentication.setToken(authToken);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}else {
					System.out.println("Auth Error");
				}
			}
		}
		chain.doFilter(request, response);

	}

}
