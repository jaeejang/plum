package org.plum.config;

import org.plum.security.MyAuthenticationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected UserDetailsService userDetailsService() {
	    return userDetailsService;
	}
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
      throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(new Md5PasswordEncoder());
    }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
    	MyAuthenticationHandler myAuthenticationHandler = new MyAuthenticationHandler();
		http.authorizeRequests()
        .antMatchers("/resources/**").permitAll()
		.antMatchers("/login**").anonymous()
		.antMatchers("/**").access("hasRole('USER')")
		//.anyRequest().authenticated()
		.and().formLogin().loginPage("/login")
			.loginProcessingUrl("/login")
			.successHandler(myAuthenticationHandler)
			.failureHandler(myAuthenticationHandler)
			.usernameParameter("username").passwordParameter("password")
			.permitAll()
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
			.permitAll();
		//.and().exceptionHandling().accessDeniedPage("/403");
		http.csrf().disable();
	}
    

}