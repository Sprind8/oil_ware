package oil.oil_test.service;


import oil.oil_test.POJO.Apply;
import oil.oil_test.POJO.Manage;
import oil.oil_test.POJO.data.ApplyAll;
import oil.oil_test.dao.ApplyMapper;
import oil.oil_test.dao.ManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ApplyService {


    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private ManageMapper manageMapper;

    /**
     * 申请流程
     */
    //State:   1、已保存、2、已提交(待审核) 3、已审核（不能操作）
    public int applyAdd(Apply apply)
    {
        apply.setAppState(1);
        return applyMapper.insertSelective(apply);
    }

    public int applySubmit(Long appID)
    {
        Date date = new Date();
        Apply apply = applyMapper.selectByPrimaryKey(appID);
        java.sql.Date transDate = new java.sql.Date(date.getTime());

        apply.setAppID(appID);
        apply.setAppDate(transDate);
        apply.setAppState(2);
        return applyMapper.updateByPrimaryKeySelective(apply);
    }

    public int applyChange(Long manageID)
    {
       Manage manage = manageMapper.selectByPrimaryKey(manageID);
       manage.setManageState(4);
       return manageMapper.updateByPrimaryKeySelective(manage);
    }


    /**
     * 申请修改 State为3：已审核时，不能进行操作
     */
    public int applyUpdate(Apply apply)
    {
        if (apply.getAppState() == 1 )// if (apply.getAppState() == 2)?
        {
            Date date = new Date();
            java.sql.Date transDate = new java.sql.Date(date.getTime());
            apply.setAppDate(transDate);
            //apply.setAppState(3);?
            applyMapper.updateByPrimaryKeySelective(apply);
            return 1;
        }
        return 0;
    }

    /**
     * 申请删除 State为3：已审核时，不能进行操作
     */

    public int applyDelete(Long appID,int appState)
    {
        if (appState == 1)
        {
            return applyMapper.deleteByPrimaryKey(appID);
        }
        return 0;
    }


    /**
     * 某油库的申请记录查看（未完成的）
     */
    public List<ApplyAll> applyShow(Long wareID)
    {
        return applyMapper.selectByWareID(wareID);
    }


    /**
     * 某油库的申请记录查看（所有记录）
     */
    public List<ApplyAll> applyShowAll(Long wareID)
    {
        return applyMapper.selectAll(wareID);
    }


    /**
     * 油料处审核时的查看这个（看所有未审核的）
     */
    public List<ApplyAll> applyNeedManage()
    {
        return applyMapper.selectByState();
    }


    /**
     * 划拨的时候，看这个（根据申请来划拨）
     */
    public List<ApplyAll> applyNeedDis(Long appID)
    {
        return applyMapper.selectByAppID(appID);
    }
}
