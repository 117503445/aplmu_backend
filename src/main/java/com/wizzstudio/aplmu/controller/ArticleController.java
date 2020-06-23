package com.wizzstudio.aplmu.controller;

import com.wizzstudio.aplmu.dto.ArticleGetDto;
import com.wizzstudio.aplmu.entity.Article;
import com.wizzstudio.aplmu.error.CustomException;
import com.wizzstudio.aplmu.repository.ArticleRepository;
import com.wizzstudio.aplmu.util.SecurityUtil;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
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

    @Secured("ROLE_USER")
    @PostMapping()
    public Article save(@RequestBody Article article) {
        var oUserId = SecurityUtil.getCurrentUserId();
        assert oUserId.isPresent();
        article.setAuthorID(oUserId.get());
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
    public ArticleGetDto get(@PathVariable("id") int id) throws CustomException {

        Optional<Article> optional = articleRepository.findById(id);

        if (optional.isPresent()) {
            optional.get().IncPageView();
            articleRepository.save(optional.get());
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Article Not Found");
        }

        var article = optional.get();

        return new ArticleGetDto(article);
    }

    @GetMapping()
    public Page<ArticleGetDto> pageQuery(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                         @RequestParam(value = "authorId", required = false) Integer authorId) {
        if (authorId == null) {
            return articleRepository.findAll(PageRequest.of(pageNum - 1, pageSize)).map(ArticleGetDto::new);
        } else {
            return articleRepository.findAllByAuthorID(PageRequest.of(pageNum - 1, pageSize), authorId).map(ArticleGetDto::new);
        }
    }
}
