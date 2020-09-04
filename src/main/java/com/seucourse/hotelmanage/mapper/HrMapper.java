package com.seucourse.hotelmanage.mapper;

import com.seucourse.hotelmanage.entity.Hr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HrMapper {
    @Insert("INSERT INTO hr VALUES (#{empId} ,#{empPass} )")
    void insertHr(Hr hr);

    @Select("SELECT empid, emppass FROM hr WHERE empid = #{empId}")
    Hr selectHrByEmpId(Integer empId);
}
