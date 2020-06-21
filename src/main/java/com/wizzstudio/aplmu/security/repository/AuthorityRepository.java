package com.wizzstudio.aplmu.security.repository;

import io.swagger.annotations.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wizzstudio.aplmu.security.model.Authority;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
@Api(tags = {"权限信息"})
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
