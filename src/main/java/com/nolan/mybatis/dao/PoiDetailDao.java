package com.nolan.mybatis.dao;

import com.nolan.mybatis.PoiModel;

public interface PoiDetailDao {
    PoiModel selectOne(Long id);
}
