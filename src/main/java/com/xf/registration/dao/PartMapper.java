package com.xf.registration.dao;

import com.xf.registration.pojo.Part;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PartMapper {


    @Results(id = "partResultMap",value = {
            @Result(property = "partCode",column = "PART_CODE"),
            @Result(property = "name",column = "NAME"),
            @Result(property = "remark",column = "REMARK")
    })
    @Select("select * from doc_part")
    List<Part> selectAllPart();

    @Insert("insert into doc_part(part_code,name,remark) values(#{partCode},#{name},#{remark})")
    int insertPart(Part part);

    @Delete("delete from doc_part where part_code = #{partCode}")
    int deletePart(String partCode);

    @Update("update doc_part set name = #{name}, remark = #{remark} where part_code = #{partCode}")
    int updatePart(Part part);


}
