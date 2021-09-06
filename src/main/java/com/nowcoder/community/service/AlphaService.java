package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlphaService {

    @Autowired
    private AlphaDao dao;

    public String find() {
        return dao.select();
    }

}
