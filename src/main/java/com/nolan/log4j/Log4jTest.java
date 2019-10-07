package com.nolan.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log4jTest {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(Log4jTest.class);
        logger.info("Hello");
        logger.debug("world");
        logger.error("error");
    }
}
