package com.wuchang;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.action.index.IndexRequest;

/**
 * Created by oukinsei on 2019/12/5.
 */
public class Main {

    //参照https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.4/java-rest-high-document-get.html
    public static void main(String[] args) throws Exception{
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        IndexRequest indexRequest = createIndex();
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse);
        GetRequest getRequest = new GetRequest(
                "test",
                "1");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse);
        client.close();
    }

    //创建index，通过GET /test/_doc/1可以查到此数据
    public static IndexRequest createIndex(){
        IndexRequest indexRequest = new IndexRequest("test") //index 命名为test
                .id("1") //id 设为1
                .source("name","wuchang", "message","create index"); //设置字段名和值

        return indexRequest;
    }
}
