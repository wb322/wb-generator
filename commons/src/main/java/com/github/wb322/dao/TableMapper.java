package com.github.wb322.dao;

import com.github.wb322.entity.Table;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wubo
 * @date 2019年11月11日 10:01
 */
@Repository
public interface TableMapper {
    List<Table> getTables();
}
