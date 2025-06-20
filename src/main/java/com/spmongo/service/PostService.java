package com.spmongo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spmongo.domain.Post;
import com.spmongo.repository.PostRepository;
import com.spmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		Optional<Post> postFind = postRepository.findById(id);
		Post post = new Post();
		
		if(postFind.get() == null) {
			throw new ObjectNotFoundException("Post não encontrado");
		} else {
			post = postFind.get();
		}
		
		return post;
	}
	
	
	public List<Post> findByTitle(String text){
		return postRepository.findByTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24*60*60*1000);
		return postRepository.fullSearch(text, minDate, maxDate);
	}
	

}
