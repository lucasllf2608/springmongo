package com.spmongo.dto;

import java.io.Serializable;

import com.spmongo.domain.User;

public class AuthorDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	
	public AuthorDTO() {
		
	}
	
	public AuthorDTO(User user) {
		id = user.getId();
		name = user.getName();
	}
}
