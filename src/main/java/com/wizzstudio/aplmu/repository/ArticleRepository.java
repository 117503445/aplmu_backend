package com.wizzstudio.aplmu.repository;

import com.wizzstudio.aplmu.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel = "article", path = "article")
public interface ArticleRepository extends CrudRepository<Article, Integer> {

}
