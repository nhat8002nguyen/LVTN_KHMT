package com.lvtn.resource_server.lvtn_resource_server.infra.common.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "spring.security.oauth2.resouceserver.jwt")
public class JwtIssuerProps {
	private String issuerUri = "http://localhost:9001";
}