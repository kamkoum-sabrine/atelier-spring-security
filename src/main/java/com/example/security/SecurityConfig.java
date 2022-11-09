package com.example.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.example.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
public static final String AUTHORITIES_CLAIM_NAME = "roles";
@Autowired
 UserService userDetailsService; 
@Autowired
private AccessDeniedHandler accessDeniedHandler;
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 auth.userDetailsService(userDetailsService);
 }
@Override
protected void configure(HttpSecurity http) throws Exception {

http.csrf().disable()
.authenticationProvider(getProvider())
.formLogin()
.loginProcessingUrl("/login")
.successHandler(new AuthentificationLoginSuccessHandler())
.failureHandler(new SimpleUrlAuthenticationFailureHandler())
.and()
.logout()
.logoutUrl("/logout")
.logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
.invalidateHttpSession(true)
.and()
.authorizeRequests()
.antMatchers("/login").permitAll()
.antMatchers("/logout").permitAll()
.antMatchers("/user").authenticated()
.anyRequest().authenticated();
}
private class AuthentificationLoginSuccessHandler extends
SimpleUrlAuthenticationSuccessHandler {
@Override
public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
Authentication authentication) throws IOException, ServletException {
response.setStatus(HttpServletResponse.SC_OK);
 }
 }

/*private class AuthentificationLoginFailureHandler extends
SimpleUrlAuthenticationFailureHandler {

	@Override
	protected RedirectStrategy getRedirectStrategy() {
		// TODO Auto-generated method stub
		return super.getRedirectStrategy();
	}

	@Override
	protected boolean isAllowSessionCreation() {
		// TODO Auto-generated method stub
		return super.isAllowSessionCreation();
	}

	@Override
	protected boolean isUseForward() {
		// TODO Auto-generated method stub
		return super.isUseForward();
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//super.onAuthenticationFailure(request, response, exception);
		System.out.println(response.getStatus());
		response.setStatus(403);
		
		
	}

	@Override
	public void setAllowSessionCreation(boolean allowSessionCreation) {
		// TODO Auto-generated method stub
		super.setAllowSessionCreation(allowSessionCreation);
	}

	@Override
	public void setDefaultFailureUrl(String defaultFailureUrl) {
		// TODO Auto-generated method stub
		super.setDefaultFailureUrl(defaultFailureUrl);
	}

	@Override
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		// TODO Auto-generated method stub
		super.setRedirectStrategy(redirectStrategy);
	}

	@Override
	public void setUseForward(boolean forwardToDestination) {
		// TODO Auto-generated method stub
		super.setUseForward(forwardToDestination);
	}

 }
*/
private class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK); 

		
	}

 }


@Bean
public AuthenticationProvider getProvider() {
 AppAuthProvider provider = new AppAuthProvider();
provider.setUserDetailsService(userDetailsService); 
return provider;
}
@Bean
public AccessDeniedHandler accessDeniedHandler(){
return new AccessDeniedHandlerImpl();
}
} 