package com.cjd.rescue.product.service;

import com.cjd.rescue.api.product.ProductApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.common.util.IdUtil;
import com.cjd.rescue.dao.anno.JDBCException;
import com.cjd.rescue.dao.common.SysKeyValueMapper;
import com.cjd.rescue.dao.product.ModuleMapper;
import com.cjd.rescue.dao.product.ProjectMapper;
import com.cjd.rescue.dao.product.TeamMapper;
import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.cjd.rescue.entity.common.TypeOfKV;
import com.cjd.rescue.entity.product.AddProjectParams;
import com.cjd.rescue.entity.product.Module;
import com.cjd.rescue.entity.product.Project;
import com.cjd.rescue.entity.product.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectService implements ProductApi{

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private SysKeyValueMapper sysKeyValueMapper;

    @Autowired
    private ModuleMapper moduleMapper;


    @Autowired
    private TeamMapper teamMapper;


    @SysLog
    @JDBCException
    @Override
    public ReturnT list(Project resource) {

        Project params = new Project();
        params.setIs_delete("0");
        return ReturnT.result(Err.SUCCESS).dataMap("projectList",projectMapper.select(params));

    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT add(AddProjectParams addProjectParams) {
        Project project = addProjectParams.getProject();
        project.setId(IdUtil.generateID());
        List<SysKeyValue> list = addProjectParams.getKvs();

        List<Module> modules = addProjectParams.getModules();
        projectMapper.insertSelective(project);
        if(null != list && list.size() > 0){
            for(SysKeyValue sysKeyValue:list){
                sysKeyValue.setAssociate(TypeOfKV.PROJECT.getPrefix() + project.getId());
                sysKeyValue.setId(IdUtil.generateID());
                sysKeyValueMapper.insertSelective(sysKeyValue);
            }
        }
        if(null != modules && modules.size() > 0){
            for(Module module:modules){
                String moduleId = IdUtil.generateID();
                module.setId(moduleId);
                module.setProject_id(project.getId());

                moduleMapper.insertSelective(module);

                List<SysKeyValue> sysKeyValues =  module.getSysKeyValues();
                if(null != sysKeyValues && sysKeyValues.size() > 0){
                    for(SysKeyValue sysKeyValue:sysKeyValues){
                        sysKeyValue.setAssociate(TypeOfKV.MODULE.getPrefix() + moduleId);
                        sysKeyValue.setId(IdUtil.generateID());
                        sysKeyValueMapper.insertSelective(sysKeyValue);
                    }
                }
            }
        }


        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT delete(Project project) {
        project.setIs_delete("1");
        projectMapper.updateByPrimaryKeySelective(project);
        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT get(Project project) {


        Project project1 =projectMapper.selectByPrimaryKey(project.getId());
        SysKeyValue sysKeyValue = new SysKeyValue();
        sysKeyValue.setAssociate(TypeOfKV.PROJECT.getPrefix() + project.getId());
        List sysKeyValues = sysKeyValueMapper.select(sysKeyValue);

        Module module = new Module();
        module.setProject_id(project.getId());
        List<Module> modules = moduleMapper.select(module);

        if(null != modules && modules.size() > 0 ){
            for (Module moduleTemp:modules){
                String associate =  TypeOfKV.MODULE.getPrefix() + moduleTemp.getId();
                SysKeyValue sysKeyValueTemp = new SysKeyValue();
                sysKeyValueTemp.setAssociate(associate);
                List<SysKeyValue> sysKeyValueModuleList = sysKeyValueMapper.select(sysKeyValueTemp);
                moduleTemp.setSysKeyValues(sysKeyValueModuleList);
            }
        }
        Team teamParam = new Team();
        teamParam.setId(project1.getTeam_id());
        Team teamResult = teamMapper.selectByPrimaryKey(teamParam);


        return ReturnT.result(Err.SUCCESS).dataMap("sysKeyValues",sysKeyValues).dataMap("project",project1).dataMap("modules",modules).dataMap("team",teamResult);
    }
}
