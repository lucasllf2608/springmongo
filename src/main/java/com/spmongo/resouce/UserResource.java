package com.spmongo.resouce;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.Map;
import java.util.HashMap;

import com.spmongo.domain.Post;
import com.spmongo.domain.User;
import com.spmongo.dto.UserDTO;
import com.spmongo.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = userService.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	// @RequestMapping(value="/{id}", method=RequestMethod.GET)
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User obj = userService.fromDTO(objDto);
		obj = userService.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> delete(@PathVariable String id) {
		
		Map<String, String> response = new HashMap<>();
		User user = userService.findById(id);
		
		if(user != null) {
			userService.delete(id);
			response.put("message", "Deletado com sucesso");
		} 
		 
		 response.put("message", "Deletado com sucesso");
		 return ResponseEntity.ok(response); 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		User obj = userService.fromDTO(objDto);
		obj.setId(id);
		obj = userService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user.getPosts());
	}
	
	
	
}
