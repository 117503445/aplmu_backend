package com.wizzstudio.aplmu.controller;

import com.wizzstudio.aplmu.entity.Article;
import com.wizzstudio.aplmu.repository.ArticleRepository;
import io.swagger.annotations.Api;
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
    public Article saveUser(@RequestBody Article article) {
        return articleRepository.save(article);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        articleRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Article updateUser(@PathVariable("id") int id, @RequestBody Article article) {
        article.setId(id);
        return articleRepository.save(article);
    }

    @GetMapping("/{id}")
    public Article getUserInfo(@PathVariable("id") int id) {

        Optional<Article> optional = articleRepository.findById(id);

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
