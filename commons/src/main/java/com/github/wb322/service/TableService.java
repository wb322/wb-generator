package com.github.wb322.service;

import com.github.wb322.dao.TableMapper;
import com.github.wb322.entity.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wubo
 * @date 2019年11月11日 10:02
 */
@Service
@Transactional
public class TableService {

    @Autowired
    private TableMapper tableMapper;

    public List<Table> getTables(){
        List<Table> tables = tableMapper.getTables ();
        return tables;
    }

}
