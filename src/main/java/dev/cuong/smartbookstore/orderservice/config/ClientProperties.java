package dev.cuong.smartbookstore.orderservice.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;

@ConfigurationProperties(prefix = "store")
record ClientProperties(
    @NotNull URI catalogServiceUri
) {
}
