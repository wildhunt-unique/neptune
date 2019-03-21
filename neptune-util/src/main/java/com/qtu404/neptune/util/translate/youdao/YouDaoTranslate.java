package com.qtu404.neptune.util.translate.youdao;

import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.util.model.ServiceException;
import com.qtu404.neptune.util.translate.TranslateRequest;
import com.qtu404.neptune.util.translate.TranslateTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.qtu404.neptune.util.model.Executor.execute;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/21 上午11:04
 */
@Component
@Primary
@Slf4j
public class YouDaoTranslate implements TranslateTemplate {

    @Value("$qtu404.util.translate.youdao.url")
    private String YOUDAO_URL = "http://openapi.youdao.com/api";

    @Value("$qtu404.util.translate.youdao.appKey")
    private String APP_KEY = "438e2cfe9df4cf0b";

    @Value("$qtu404.util.translate.youdao.appSecret")
    private String APP_SECRET = "v5PZncU9NGbCV0zi6vezQzUE83jscZpV";

    public static void main(String[] args) {
        YouDaoTranslate youDaoTranslate = new YouDaoTranslate();
        youDaoTranslate.doTranslate(
                TranslateRequest.builder()
                        .content("商品 id not be null")
                        .from("EN")
                        .to("zh-CHS")
                        .build()
        );
    }

    @Override
    public Response<String> doTranslate(TranslateRequest request) {
        return execute(request, param -> {
            Map<String, String> params = new HashMap<>();
            String q = request.getContent();
            String salt = String.valueOf(System.currentTimeMillis());
            params.put("from", request.getFrom());
            params.put("to", request.getTo());
            params.put("signType", "v3");
            String curtime = String.valueOf(System.currentTimeMillis() / 1000);
            params.put("curtime", curtime);
            String signStr = APP_KEY + truncate(q) + salt + curtime + APP_SECRET;
            String sign = getDigest(signStr);
            params.put("appKey", APP_KEY);
            params.put("q", q);
            params.put("salt", salt);
            params.put("sign", sign);
            // 处理结果
            try {
                requestForHttp(YOUDAO_URL, params);
            } catch (IOException e) {
                throw new ServiceException(e.getMessage());
            }
            return "服务器错误";
        });
    }

    private void requestForHttp(String url, Map<String, String> params) throws IOException {

        // 创建HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // httpPost
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> paramsList = new ArrayList<>();
        for (Map.Entry<String, String> en : params.entrySet()) {
            String key = en.getKey();
            String value = en.getValue();
            paramsList.add(new BasicNameValuePair(key, value));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try {
            Header[] contentType = httpResponse.getHeaders("Content-Type");
            log.info("Content-Type:" + contentType[0].getValue());
            HttpEntity httpEntity = httpResponse.getEntity();
            String json = EntityUtils.toString(httpEntity, "UTF-8");
            EntityUtils.consume(httpEntity);
            log.info(json);
            System.out.println(json);
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                log.info("## release resouce error ##" + e);
            }
        }
    }

    /**
     * 生成加密字段
     */
    private String getDigest(String string) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = string.getBytes();
        try {
            MessageDigest mdInst = MessageDigest.getInstance("SHA-256");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        String result;
        return len <= 20 ? q : (q.substring(0, 10) + len + q.substring(len - 10, len));
    }

}
