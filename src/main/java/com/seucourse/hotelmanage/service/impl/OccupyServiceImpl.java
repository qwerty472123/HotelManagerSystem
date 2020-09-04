package com.seucourse.hotelmanage.service.impl;

import com.seucourse.hotelmanage.entity.Occupy;
import com.seucourse.hotelmanage.mapper.OccupyMapper;
import com.seucourse.hotelmanage.service.OccupyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("occupyService")
public class OccupyServiceImpl implements OccupyService {
    @Autowired
    private OccupyMapper occupyMapper;

    @Override
    public void addOccupy(Occupy occupy) {
        occupyMapper.insertOccupy(occupy);
    }

    @Override
    public void deleteOccupy(Integer id) {
        occupyMapper.deleteOccupy(id);
    }

    @Override
    public List<Occupy> listOccupy(Occupy occupy) {
        return occupyMapper.selectOccupy(occupy);
    }

    @Override
    public void updateOccupy(Occupy occupy) {
        occupyMapper.updateOccupy(occupy);
    }
}
