package com.nolan.es;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EsTest {
    private final static Logger logger = LogManager.getLogger(EsTest.class);

    public static void main(String[] args) {
        PoiEsModel poiEsModel = new PoiEsModel();
        poiEsModel.index();
        poiEsModel.get();
        poiEsModel.delete();
        poiEsModel.get();
    }
}
