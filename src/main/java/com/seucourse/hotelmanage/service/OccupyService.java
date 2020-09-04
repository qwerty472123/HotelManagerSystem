package com.seucourse.hotelmanage.service;

import com.seucourse.hotelmanage.entity.Occupy;
import com.seucourse.hotelmanage.provider.OccupySQLProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface OccupyService {
    void addOccupy(Occupy occupy);

    void deleteOccupy(Integer id);

    List<Occupy> listOccupy(Occupy occupy);

    void updateOccupy(Occupy occupy);
}
