package org.example.BTree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @className: Test
 * @description:
 * @author: rainple
 * @create: 2021-06-30 14:23
 **/
public class Test {

    public static void main(String[] args) throws IOException {
        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.zip("D:\\2022考研\\数二\\考研数学张宇数学题源探析经典1000题（数学二  解析分册）.zip",
                "D:\\2022考研\\数二\\考研数学张宇数学题源探析经典1000题（数学二  解析分册）.pdf");
    }

    private static void translate() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        String address = "https://fanyi-api.baidu.com/api/trans/vip/translate?";
        String salt = "123456";
        String appid = "20210714000887628";
        String pwd = "cF3kBdbB9lPjnr89QHVl";
        String q = "咩啊";
        String sign = DigestUtils.md5Hex(appid + q + salt + pwd);
        String url = address + "q=" + q + "&from=zh&to=en&appid=" + appid
                + "&salt=" + salt + "&sign=" + sign;
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = JSON.parseObject(result);
        System.out.println(jsonObject.getJSONArray("trans_result").getJSONObject(0).get("dst"));
    }
}
