package com.myexample.bootwithmybatis.test;

import com.myexample.bootwithmybatis.BootWithMybatisApplication;
import com.myexample.bootwithmybatis.entity.Dept;
import com.myexample.bootwithmybatis.mapper.DeptMapper;
import com.myexample.bootwithmybatis.service.DeptService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {BootWithMybatisApplication.class})
public class TestMyBatisDept {
    @Autowired
    private DeptMapper deptMapper;
    @Test
    public void testInsertDept() {
        deptMapper.insertDept(Dept.builder().deptName("林场部").status(1).build());
    }

    @Autowired
    @Qualifier(value = "deptServiceImpl2")
    private DeptService deptService;

    @Test
    public void testAutowired(){
        deptService.addDept(Dept.builder().deptName("SB部").status(1).build());
    }

    @Test
    public void testSelect(){
        System.out.println(deptMapper.selectDeptByDeptId(10001));
    }
}
