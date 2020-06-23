package com.wizzstudio.aplmu.repository;

import com.wizzstudio.aplmu.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

}
