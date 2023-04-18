package com.apiGateway;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.time.Duration;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
@Component
public class ApiGatewayConfiguration {

	@Autowired
	AuthenticationFilter authenticationFilter;
	private static final String CART_SERVICE = "cartService";

	@Bean
	public RouteLocator gateWayLocator(RouteLocatorBuilder builder) {
	
		Function<PredicateSpec, Buildable<Route>> profileService = p -> p.path("/api/admin/*","/api/login","/api/user/*","/api/password/*","/api/pAgentRegister/**","/api/dAgent/*").filters(
				f -> f.circuitBreaker(c -> c.setName("custom").setFallbackUri("/testService")))
				.uri("lb://PROFILESERVICE");
		Function<PredicateSpec, Buildable<Route>> productService = p -> p.path("/api/products/**").filters(
				f -> f.filter(authenticationFilter).circuitBreaker(c -> c.setName("custom").setFallbackUri("/fallback/testService")))
				.uri("lb://PRODUCTSERVICE");
		Function<PredicateSpec, Buildable<Route>> cartService = p -> p.path("/api/cart/**").filters(
				f -> f.filter(authenticationFilter).circuitBreaker(c -> c.setName("custom").setFallbackUri("/fallback/testService")))
				.uri("lb://CARTSERVICE");
		Function<PredicateSpec, Buildable<Route>> inventoryService = p -> p.path("/api/inventory/**").filters(
				f -> f.filter(authenticationFilter).circuitBreaker(c -> c.setName("custom").setFallbackUri("/fallback/testService")))
				.uri("lb://INVENTORYSERVICE");
		Function<PredicateSpec, Buildable<Route>> orderService = p -> p.path("/api/orders/**").filters(
				f -> f.filter(authenticationFilter).circuitBreaker(c -> c.setName("custom").setFallbackUri("/fallback/testService")))
				.uri("lb://ORDERSERVICE");
		Function<PredicateSpec, Buildable<Route>> deliveryAgentService = p -> p.path("/api/dAdmin/**").filters(
				f -> f.filter(authenticationFilter).circuitBreaker(c -> c.setName("custom").setFallbackUri("/fallback/testService")))
				.uri("lb://DELIVERYAGENTSERVICE");
		Function<PredicateSpec, Buildable<Route>> deliveryBoyService = p -> p.path("/api/delivery/**").filters(
				f -> f.filter(authenticationFilter).circuitBreaker(c -> c.setName("custom").setFallbackUri("/fallback/testService")))
				.uri("lb://DELIVERYBOYSERVICE");
		return builder
				.routes()
				.route(profileService)	
				.route(productService)
				.route(cartService)
				.route(inventoryService)
				.route(orderService)
				.build();
	}
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer(
			CircuitBreakerRegistry circuitBreakerRegistry, TimeLimiterRegistry timeLimiterRegistry) {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(circuitBreakerRegistry.getConfiguration("backendB")
						.orElse(circuitBreakerRegistry.getDefaultConfig()))
				.timeLimiterConfig(timeLimiterRegistry.getConfiguration("backendB")
						.orElse(TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(30000)).build()))
				.build());
	}
	
}
