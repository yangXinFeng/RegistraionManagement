package com.xf.registration.service;

import com.xf.registration.dao.PartMapper;
import com.xf.registration.pojo.Part;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("partService")
public class PartServiceImpl implements PartService{

    Logger logger = Logger.getLogger(DoctorServiceImpl.class);

    @Resource
    PartMapper partMapper;


    @Override
    public List<Part> queryAllPart() {
        return partMapper.selectAllPart();
    }

    @Override
    public int addPart(Part part) {
        return partMapper.insertPart(part);
    }

    @Override
    public int deletePart(String partCode) {
        return partMapper.deletePart(partCode);
    }

    @Override
    public int updatePart(Part part) {
        return partMapper.updatePart(part);
    }
}
