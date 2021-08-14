package com.xf.registration.dao;

import com.xf.registration.pojo.Part;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PartMapper {

    @Select("select * from doc_part")
    List<Part> selectAllPart();

    @Insert("insert into doc_part(part_code,name,remark) values(#{partCode},#{name},#{remark})")
    int addPart(Part part);

    @Delete("delete from doc_part where part_code = {partCode}")
    int deletePart(String partCode);


}
