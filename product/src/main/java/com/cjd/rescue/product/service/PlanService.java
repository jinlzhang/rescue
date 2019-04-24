package com.cjd.rescue.product.service;

import com.cjd.rescue.api.plan.PlanApi;
import com.cjd.rescue.api.product.ProductApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.common.util.IdUtil;
import com.cjd.rescue.dao.anno.JDBCException;
import com.cjd.rescue.dao.common.SysKeyValueMapper;
import com.cjd.rescue.dao.product.ModuleMapper;
import com.cjd.rescue.dao.product.PlanMapper;
import com.cjd.rescue.dao.product.ProjectMapper;
import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.cjd.rescue.entity.common.TypeOfKV;
import com.cjd.rescue.entity.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlanService implements PlanApi{

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private SysKeyValueMapper sysKeyValueMapper;

    @Autowired
    private ModuleMapper moduleMapper;


    @SysLog
    @JDBCException
    @Override
    public ReturnT list() {

        Plan params = new Plan();
        params.setIs_delete("0");
        return ReturnT.result(Err.SUCCESS).dataMap("planList",planMapper.select(params));

    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT add(AddPlanParams addProjectParams) {
        Plan plan = addProjectParams.getPlan();
        plan.setId(IdUtil.generateID());
        List<SysKeyValue> list = addProjectParams.getKvs();
        plan.setIs_delete("0");
        planMapper.insertSelective(plan);
        if(null != list && list.size() > 0){
            for(SysKeyValue sysKeyValue:list){
                sysKeyValue.setAssociate(TypeOfKV.PLAN.getPrefix() + plan.getId());
                sysKeyValue.setId(IdUtil.generateID());
                sysKeyValueMapper.insertSelective(sysKeyValue);
            }
        }

        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT delete(Plan plan) {
        plan.setIs_delete("1");
        planMapper.updateByPrimaryKeySelective(plan);
        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT get(Plan plan) {


        Plan planTemp =planMapper.selectByPrimaryKey(plan.getId());
        SysKeyValue sysKeyValue = new SysKeyValue();
        sysKeyValue.setAssociate(TypeOfKV.PLAN.getPrefix() + planTemp.getId());
        List sysKeyValues = sysKeyValueMapper.select(sysKeyValue);




        return ReturnT.result(Err.SUCCESS).dataMap("sysKeyValues",sysKeyValues).dataMap("plan",planTemp);
    }
}
