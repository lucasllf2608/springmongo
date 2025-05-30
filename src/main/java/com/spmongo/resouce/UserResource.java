package com.spmongo.resouce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spmongo.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	//@RequestMapping(method=RequestMethod.GET)
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		
		User  u = new User("1","Maria", "maria@mail.com");
		User  u1 = new User("2","Mari", "marea@mail.com");
		User  u2 = new User("3","Mara", "mara@mail.com");
		
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(u,u1,u2));
		
		return ResponseEntity.ok().body(list);
	}

}
