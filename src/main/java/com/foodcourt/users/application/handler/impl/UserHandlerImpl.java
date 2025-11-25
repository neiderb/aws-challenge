package com.foodcourt.users.application.handler.impl;

import com.foodcourt.users.application.dto.request.UserRequest;
import com.foodcourt.users.application.dto.response.UserResponse;
import com.foodcourt.users.application.handler.UserHandler;
import com.foodcourt.users.application.mappers.UserRequestMapper;
import com.foodcourt.users.application.mappers.UserResponseMapper;
import com.foodcourt.users.domain.model.User;
import com.foodcourt.users.domain.model.UserRole;
import com.foodcourt.users.domain.ports.CreateUserPort;
import com.foodcourt.users.domain.ports.GetUserByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements UserHandler {
	
	private final CreateUserPort createUserPort;
	private final GetUserByIdPort getUserByIdPort;
	
	@Override
	public UserResponse createUser(UserRequest userRequest) {
		log.trace("Creating user with email: {}", userRequest.email());
		User userToSave = UserRequestMapper.INSTANCE.toDomain(userRequest);
		userToSave.setRole(UserRole.getRoleof(userRequest.role()));
		User userSaved = createUserPort.execute(userToSave, null);
		
		log.debug("Created user with ID: {}", userSaved.getId());
		return UserResponseMapper.INSTANCE.toResponse(userSaved);
	}
	
	@Override
	public UserResponse getUserById(Long id) {
		log.trace("Getting user by ID: {}", id);
		User user = getUserByIdPort.execute(id);
		
		log.debug("Found user with ID: {}", user.getId());
		return UserResponseMapper.INSTANCE.toResponse(user);
	}
}
