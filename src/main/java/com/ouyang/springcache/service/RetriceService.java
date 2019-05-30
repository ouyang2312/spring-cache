package com.ouyang.springcache.service;

import com.ouyang.springcache.dao.RetriceMapper;
import com.ouyang.springcache.entity.Retrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author oy
 * @description
 * @date 2019/5/5
 */
@Service
public class RetriceService {

    @Autowired
    private RetriceMapper retriceMapper;

    public void add(Retrice retrice){
        retriceMapper.add(retrice);
    }

    public List<Retrice> list(){
        return retriceMapper.list();
    }

    public void update(String deleteId,int status){
        retriceMapper.update(deleteId,status);
    }

}
