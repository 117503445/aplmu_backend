package com.wizzstudio.aplmu.repository;

import com.wizzstudio.aplmu.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
