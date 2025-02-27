package com.project.linktree_backend_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class LinktreeBackendJavaApplication {
	public static void main(String[] args) {
		SpringApplication.run(LinktreeBackendJavaApplication.class, args);
	}
}

