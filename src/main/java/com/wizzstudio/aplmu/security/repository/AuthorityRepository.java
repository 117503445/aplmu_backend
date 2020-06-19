package com.wizzstudio.aplmu.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wizzstudio.aplmu.security.model.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
