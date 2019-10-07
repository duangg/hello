package com.nolan.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.main.MainResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class EsConn {
    RestHighLevelClient client = null;
    EsConn() {
        this("localhost", 9200);
    }
    EsConn(String host, Integer port) {
        RestClient restClient = RestClient.builder(new HttpHost(host, port, "http")).build();
        client = new RestHighLevelClient(restClient);
    }

    public void info() {
        try {
            MainResponse response = client.info();
            System.out.println(response.getClusterName());
            System.out.println(response.getVersion());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IndexResponse index(IndexRequest request) {
        try {
            return client.index(request);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public GetResponse get(GetRequest request) {
        try {
            return client.get(request);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DeleteResponse delete(DeleteRequest request) {
        try {
            return client.delete(request);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
