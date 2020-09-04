package com.seucourse.hotelmanage.test;

import com.seucourse.hotelmanage.entity.Dept;
import org.junit.jupiter.api.Test;

public class TestLombok {

    @Test
    public void test() {
        Dept dept = new Dept();
        dept.setDeptId(1);
        dept.setDeptName("human");
        System.out.println(dept);

        System.out.println(Dept.builder().deptId(1).deptName("human").build());
    }
}
