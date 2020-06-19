package com.wizzstudio.aplmu.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/")
public class ProjectInfoController {
    /**
     * 当前版本
     */
    @Value("${app.version}")
    private String version;
    /**
     * 打包时间
     */
    @Value("${app.build.time}")
    private String buildTime;

    @Secured("ROLE_ADMIN")
    @GetMapping(path = "ver", produces = "application/json")
    public @ResponseBody
    String getAllRecords() {
        return String.format("{\"version\":%s,\"buildTime\":%s}", version, buildTime);
    }
}
