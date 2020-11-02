package com.github.lucasnpires;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class DatabaseLifecycle implements QuarkusTestResourceLifecycleManager {
	
	
	private static PostgreSQLContainer POSTGRESQL = new PostgreSQLContainer("postgres:11.1");
	
	@Override
	public Map<String, String> start() {
		POSTGRESQL.start();
		
		Map<String, String> propriedades = new HashMap<>();
		propriedades.put("quarkus.datasource.jdbc.url", POSTGRESQL.getJdbcUrl());
		propriedades.put("quarkus.datasource.username", POSTGRESQL.getUsername());
		propriedades.put("quarkus.datasource.password", POSTGRESQL.getPassword());
		propriedades.put("quarkus.hibernate-orm.database.generation", "update");
		
		return propriedades;
	}

	@Override
	public void stop() {
		if(POSTGRESQL != null) {
			POSTGRESQL.stop();
		}		
	}
}

