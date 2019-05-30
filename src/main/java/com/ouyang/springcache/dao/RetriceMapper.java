package com.ouyang.springcache.dao;

import com.ouyang.springcache.entity.Retrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RetriceMapper {

    public void add(Retrice retrice);

    List<Retrice> list();

    public void update(@Param("deleteId") String deleteId,@Param("status") int status);
}
