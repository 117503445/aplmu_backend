package com.wizzstudio.aplmu.repository;

import com.wizzstudio.aplmu.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
    Page<ArticleEntity> findAllByAuthorID(Pageable pageable, long authorId);
}
