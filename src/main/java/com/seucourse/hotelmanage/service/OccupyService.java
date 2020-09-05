package com.seucourse.hotelmanage.service;

import com.seucourse.hotelmanage.entity.Occupy;

import java.util.List;

public interface OccupyService {
    void addOccupy(Occupy occupy);

    void deleteOccupy(Integer id);

    List<Occupy> listOccupy(Occupy occupy);

    void updateOccupy(Occupy occupy);
}
