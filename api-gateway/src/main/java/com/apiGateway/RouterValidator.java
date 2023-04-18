package com.apiGateway;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
@Component
public class RouterValidator {

	public static final List<String> openApiEndpoints = List.of("/api/admin/sendOtp","/api/admin/register","/api/admin/myProfile","/api/admin/changePassword","/api/login","/api/user/sendOtp","/api/user/register","/api/user/myProfile");


	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));


}
