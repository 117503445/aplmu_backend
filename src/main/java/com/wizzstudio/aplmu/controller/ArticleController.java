package com.wizzstudio.aplmu.controller;

import com.wizzstudio.aplmu.entity.Article;
import com.wizzstudio.aplmu.repository.ArticleRepository;
import com.wizzstudio.aplmu.security.repository.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = {"文章"})
@RestController
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleController(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Secured("ROLE_USER")
    @PostMapping()
    public Article save(@RequestBody Article article) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;

        var oJwtUser = userRepository.findOneByUsername(authentication.getName());
        assert oJwtUser.isPresent();

        var jwtUser = oJwtUser.get();

        article.setAuthorID(jwtUser.getId());

        return articleRepository.save(article);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        //todo 只有管理员和编辑者可以进行删除

        articleRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Article update(@PathVariable("id") int id, @RequestBody Article article) {
        //todo 只有管理员和编辑者可以进行更新
        article.setId(id);
        return articleRepository.save(article);
    }

    @GetMapping("/{id}")
    public Article getUser(@PathVariable("id") int id) {

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
