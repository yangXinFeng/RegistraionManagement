package com.xf.registration.service;

import com.xf.registration.pojo.Part;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PartService {

    List<Part> queryAllPart();

    int addPart(Part part);

    int deletePart(String partCode);

    int updatePart(Part part);
}
