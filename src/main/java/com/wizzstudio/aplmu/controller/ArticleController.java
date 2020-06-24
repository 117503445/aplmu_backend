package com.wizzstudio.aplmu.controller;

import com.wizzstudio.aplmu.dto.ArticleGetDto;
import com.wizzstudio.aplmu.entity.Article;
import com.wizzstudio.aplmu.error.CustomException;
import com.wizzstudio.aplmu.repository.ArticleRepository;
import com.wizzstudio.aplmu.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("提交文章 ROLE_USER")
    @Secured("ROLE_USER")
    @PostMapping()
    public Article save(@RequestBody Article article) {
        //todo 改变签名
        var oUserId = SecurityUtil.getCurrentUserId();
        assert oUserId.isPresent();
        article.setAuthorID(oUserId.get());
        return articleRepository.save(article);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) throws CustomException {
        authorOrAdmin(id);

        articleRepository.deleteById(id);
    }

    //只有当前用户为作者或者管理员,才不抛出异常
    private void authorOrAdmin(int id) throws CustomException {
        var oArticle = articleRepository.findById(id);
        if (oArticle.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "未找到文章");
        }

        var oCurrentUserId = SecurityUtil.getCurrentUserId();
        assert oCurrentUserId.isPresent();

        if (!SecurityUtil.isAdmin() && oCurrentUserId.get() != oArticle.get().getAuthorID()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "无法编辑他人的文章,除非你有管理员权限");
        }
    }

    @ApiOperation("更新某个文章 ROLE_USER")
    @Secured("ROLE_USER")
    @PutMapping("/{id}")
    public Article update(@PathVariable("id") int id, @RequestBody Article article) throws CustomException {
        authorOrAdmin(id);

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
