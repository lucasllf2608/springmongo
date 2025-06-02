package com.spmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spmongo.domain.User;
import com.spmongo.dto.UserDTO;
import com.spmongo.repository.UserRepository;
import com.spmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
	    return userRepository.findById(id)
	        .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com id: " + id));
	}
	
	
	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
}
