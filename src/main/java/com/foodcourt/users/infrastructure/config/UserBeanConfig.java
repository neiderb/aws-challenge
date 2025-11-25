package com.foodcourt.users.infrastructure.config;

import com.foodcourt.users.domain.gateways.UserRepositoryGateway;
import com.foodcourt.users.domain.ports.CreateUserPort;
import com.foodcourt.users.domain.ports.GetUserByIdPort;
import com.foodcourt.users.domain.usecases.CreateUserUseCase;
import com.foodcourt.users.domain.usecases.GetUserByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserBeanConfig {
	
	@Bean
	public CreateUserPort createUserPort(
		UserRepositoryGateway userRepositoryGateway
	) {
		return new CreateUserUseCase(
			userRepositoryGateway
		);
	}
	
	@Bean
	public GetUserByIdPort getUserByIdPort(UserRepositoryGateway userRepositoryGateway) {
		return new GetUserByIdUseCase(userRepositoryGateway);
	}
	
}
