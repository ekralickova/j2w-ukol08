package cz.czechitas.java2webapps.ukol8.service;

import cz.czechitas.java2webapps.ukol8.entity.Post;
import cz.czechitas.java2webapps.ukol8.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Page<Post> list(Pageable page) {
        return postRepository.findAll(page);
    }

    public Page<Post> findBySlug(String slug, Pageable pageable) {
        return postRepository.findBySlug(slug, Pageable.unpaged());
    }

    public Page<Post> findByPublishedBeforeAndPublishedNotNull() {
        LocalDate dateOd = LocalDate.of(1900, 1,1);
        LocalDate dateDo = LocalDate.now();
        return postRepository.findByPublishedBetweenAndPublishedNotNull(dateOd, dateDo, PostRepository.ownPage);
    }
}
