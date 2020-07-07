package com.wizzstudio.aplmu.controller;

import com.wizzstudio.aplmu.dto.CourseSetDto;
import com.wizzstudio.aplmu.entity.CourseEntity;
import com.wizzstudio.aplmu.error.CustomException;
import com.wizzstudio.aplmu.repository.CourseRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = {"售卖的课程"})
@RestController
@RequestMapping("/api/course")
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @ApiOperation("提交课程 ROLE_ADMIN")
    @Secured("ROLE_ADMIN")
    @PostMapping()
    public CourseEntity save(@RequestBody CourseSetDto courseSetDto) {
        return courseRepository.save(courseSetDto.toEntity());
    }

    @ApiOperation("删除课程 ROLE_ADMIN")
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable("id") long id) throws CustomException {
        courseRepository.deleteById(id);
    }


    @ApiOperation("更新某个课程 ROLE_ADMIN")
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public CourseEntity update(@PathVariable("id") long id, @RequestBody CourseSetDto courseSetDto) throws CustomException {
        var c = courseSetDto.toEntity();
        c.setId(id);
        return courseRepository.save(c);
    }

    @GetMapping("/{id}")
    public Optional<CourseEntity> get(@PathVariable("id") long id) throws CustomException {
        return courseRepository.findById(id);
    }

    @GetMapping()
    public Page<CourseEntity> pageQuery(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        return courseRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
    }
}
