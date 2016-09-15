package com.fynesy.ssodemos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Tmfsso3Application {

	public static void main(String[] args) {
		if ("true".equals(System.getenv("SKIP_SSL_VALIDATION"))) {
            SSLValidationDisabler.disableSSLValidation();
        }
		SpringApplication.run(Tmfsso3Application.class, args);
	}
	
	@RequestMapping("/")
	public String index() {
		return "Security Demo";
	}
	
	@RequestMapping("/foo")
	@PreAuthorize("#oauth2.hasScope('todo.read')")
	public String foo() {
		return "foo";
	}
	
	@RequestMapping("/bar")
	@PreAuthorize("#oauth2.hasScope('todo.write')")
	public String bar() {
		return "bar";
	}
	
}
