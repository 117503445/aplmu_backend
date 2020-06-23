package com.wizzstudio.aplmu.controller;

import com.wizzstudio.aplmu.entity.Article;
import com.wizzstudio.aplmu.repository.ArticleRepository;
import io.swagger.annotations.Api;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = {"文章"})
@RestController
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @PostMapping()
    public Article saveUser(@RequestBody Article user) {
        return articleRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int userId) {
        articleRepository.deleteById(userId);
    }

    @PutMapping("/{id}")
    public Article updateUser(@PathVariable("id") int userId, @RequestBody Article user) {
        user.setId(userId);
        return articleRepository.save(user);
    }

    @GetMapping("/{id}")
    public Article getUserInfo(@PathVariable("id") int userId) {

        LoggerFactory.getLogger(ArticleController.class).info("getUserInfo");

        Optional<Article> optional = articleRepository.findById(userId);

        if (optional.isPresent()) {
            optional.get().IncPageView();
            articleRepository.save(optional.get());
        }

        return optional.orElseGet(Article::new);
    }

    @GetMapping("")
    public Page<Article> pageQuery(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return articleRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
    }
}
