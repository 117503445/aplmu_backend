package com.wizzstudio.aplmu.repository;

import com.wizzstudio.aplmu.entity.Feedback;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel = "feedback", path = "feedback")
public interface FeedBackRepository extends CrudRepository<Feedback, Integer> {

}
