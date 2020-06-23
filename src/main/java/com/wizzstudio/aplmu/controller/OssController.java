package com.wizzstudio.aplmu.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.wizzstudio.aplmu.error.CustomException;
import com.wizzstudio.aplmu.repository.ArticleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;


@Api(tags = {"OSS上传"})
@RestController
@RequestMapping(path = "/api/oss")
public class OssController {
    @Autowired
    private ArticleRepository articleRepository;

    @Value("${alioss.accessId}")
    private String accessId;
    @Value("${alioss.accessKey}")
    private String accessKey;
    @Value("${alioss.endpoint}")
    private String endpoint;
    @Value("${alioss.bucket}")
    private String bucket;

    @ApiOperation("获取某个用户的上传凭证")
    @Secured("ROLE_USER")
    @GetMapping(path = "user", produces = "application/json")
    public @ResponseBody
    String getJsonUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        String dir = String.format("user/%s/", authentication.getName()); // 用户上传文件时指定的前缀。
        return getUploadJson(dir);
    }

    @ApiOperation("获取某个文章的上传凭证")
    @Secured("ROLE_USER")
    @GetMapping(path = "article", produces = "application/json")
    public @ResponseBody
    String getJsonArticle(@RequestParam(name = "articleId", required = true) Integer articleId) throws CustomException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        authentication.getAuthorities();
        var article = articleRepository.findById(articleId);
        if (article.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "文章不存在");
        }
        //todo 管理员也可以进行修改
        if (article.get().getAuthor().equals(authentication.getName())) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "不可以编辑他人的文章");
        }

        String dir = String.format("article/%s/", article.get().getId()); // 用户上传文件时指定的前缀。
        return getUploadJson(dir);
    }


    private String getUploadJson(String dir) {


        String host = "http://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
        String callbackUrl = "https://aplmu.backend.117503445.top/api/oss/callback";
        OSS client = new OSSClientBuilder().build(endpoint, accessId, accessKey);
        long expireTime = 30;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = client.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = client.calculatePostSignature(postPolicy);

        Map<String, String> respMap = new LinkedHashMap<>();
        respMap.put("accessid", accessId);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));
        // respMap.put("expire", formatISO8601Date(expiration));

        JSONObject jasonCallback = new JSONObject();
        jasonCallback.put("callbackUrl", callbackUrl);
        jasonCallback.put("callbackBody",
                "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
        jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
        String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
        respMap.put("callback", base64CallbackBody);

        JSONObject jsonObject = JSONObject.fromObject(respMap);
        client.shutdown();
        return jsonObject.toString();
    }

    @GetMapping(path = "callback", produces = "application/json")
    public String callback() {
        System.out.println("oss callback");
        return "success callback";
    }

}
