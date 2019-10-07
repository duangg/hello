package com.nolan.es;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;

public class PoiEsModel {
    private EsConn esConn;
    public static final String ES_INDEX = "gis";
    public static final String ES_TYPE = "poi";
    private final static Logger logger = LogManager.getLogger(PoiEsModel.class);
    PoiEsModel() {
        esConn = new EsConn();
    }
    public void index() {
        IndexRequest request = new IndexRequest(ES_INDEX,ES_TYPE, "1");
        request.source("id",1, "name", "新华金融大厦", "lat", 40.0, "lon", 119.0);
        IndexResponse response = esConn.index(request);
        if (response == null) {
            logger.error("Index failed");
        } else {
            logger.info("Index success");
        }
    }

    public void get() {
        GetRequest request = new GetRequest(ES_INDEX, ES_TYPE, "1");
        GetResponse response = esConn.get(request);
        if (response.getSource() != null) {
            String msg = response.getSource().toString();
            logger.info(msg);
        } else {
            logger.error("not found");
        }
    }

    public void delete() {
        DeleteRequest request = new DeleteRequest(ES_INDEX, ES_TYPE, "1");
        DeleteResponse response = esConn.delete(request);
        ReplicationResponse.ShardInfo shardInfo = response.getShardInfo();
        logger.info(String.format("Total:%d, Success:%d, Failed:%d\n",
                shardInfo.getTotal(), shardInfo.getSuccessful(), shardInfo.getFailed()));
    }
}