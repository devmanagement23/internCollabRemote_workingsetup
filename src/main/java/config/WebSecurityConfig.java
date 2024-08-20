package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ict.internCollab.services.CustomUserDetailsService;

@Configuration
public class WebSecurityConfig {
	
	private final CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	public WebSecurityConfig(CustomUserDetailsService customUserDetailService) {
		
		this.customUserDetailsService = customUserDetailService;
	}

	//list of url made public - that no need for user to login
	private final String[] publicUrl = {"/",
            "/global-search/**",
            "/register",
            "/register/**",
            "/webjars/**",
            "/resources/**",
            "/assets/**",
            "/css/**",
            "/summernote/**",
            "/js/**",
            "/*.css",
            "/*.js",
            "/*.js.map",
            "/fonts**", "/favicon.ico", "/resources/**", "/error"};
	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authenticationProvider( authenticationProvider() );
		
		http.authorizeHttpRequests( auth -> {
			
			//giving permission to all url defined above
			auth.requestMatchers(publicUrl).permitAll();
			
			auth.anyRequest().authenticated();
			
		});		
		
		return http.build();
	}

	//our custom authenticationProvider 
	//Tell spring security how to find our users and how to authenticate passwords
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		
		authenticationProvider.setPasswordEncoder( passwordEncoder() );
				
		//to tell spring security how to retrieve the users form the database
		authenticationProvider.setUserDetailsService(customUserDetailsService);
		
		return authenticationProvider; 
	}

	//our custom password encoder
	// to tell spring security ho to authenticate passwords (plain text or encryption)
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	

}
