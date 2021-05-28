package cz.czechitas.java2webapps.ukol8.controller;

import cz.czechitas.java2webapps.ukol8.service.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public ModelAndView zakladniSeznam() {
        return new ModelAndView("seznam")
                .addObject("seznam", postService.findByPublishedBeforeAndPublishedNotNull());
    }

    @GetMapping(path = "/", params = "slug")
    public ModelAndView zobrazDetail(String slug) {
        ModelAndView result = new ModelAndView("detail");
        result.addObject("seznam", postService.findBySlug(slug, Pageable.unpaged()));
        return result;
    }
}
