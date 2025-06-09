package com.spmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.spmongo.domain.Post;
import com.spmongo.domain.User;
import com.spmongo.dto.AuthorDTO;
import com.spmongo.dto.CommentDTO;
import com.spmongo.repository.PostRepository;
import com.spmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com"); 
		User alex = new User(null, "Alex Green", "alex@gmail.com"); 
		User bob = new User(null, "Bob Grey", "bob@gmail.com"); 
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		Post post1 = new Post(null, sdf.parse("21/03/2025"), "Partiu viagem", "Partiu viajar para Fortaleza", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("18/03/2025"), "Partiu viagem", "Partiu viajar para São Paulo", new AuthorDTO(maria));
		
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2025"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("21/03/2025"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Seja Feliz!", sdf.parse("21/03/2025"), new AuthorDTO(maria));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
	}

}
