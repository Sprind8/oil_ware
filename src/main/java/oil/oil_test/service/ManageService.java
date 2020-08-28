package oil.oil_test.service;

import oil.oil_test.POJO.Apply;
import oil.oil_test.POJO.Manage;
import oil.oil_test.POJO.data.ManageAll;
import oil.oil_test.dao.ApplyMapper;
import oil.oil_test.dao.ManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ManageService {

    @Autowired
    private ManageMapper manageMapper;
    @Autowired
    private ApplyMapper applyMapper;

    @Transactional
    public int ManageAdd(Manage manageWeb)
    {
        Date date = new Date();
        java.sql.Date transDate = new java.sql.Date(date.getTime());
        manageWeb.setManageDate(transDate);

        //修改申请状态
        Apply apply = new Apply();
        apply.setAppID(manageWeb.getAppID());
        apply.setAppState(3);
        applyMapper.updateByPrimaryKeySelective(apply);

        return manageMapper.insertSelective(manageWeb);
    }

    public int ManageUpade(Manage manageWeb)
    {
        if(manageWeb.getManageState() != 3) {
            Date date = new Date();
            java.sql.Date transDate = new java.sql.Date(date.getTime());
            manageWeb.setManageDate(transDate);
            return manageMapper.updateByPrimaryKeySelective(manageWeb);
        }
        return 3;
    }


    @Transactional
    public int ManageDelete(Long manageID,Long appID)
    {
        Apply apply = new Apply();
        apply.setAppID(appID);
        apply.setAppState(2);
        applyMapper.updateByPrimaryKeySelective(apply);
        return manageMapper.deleteByPrimaryKey(manageID);
    }


    /**
     * 审核记录查看
     */
    public List<ManageAll> ManageShowAll()
    {
        return manageMapper.selectAll();
    }


    /**
     * 申请者查看审核信息
     */
    public List<ManageAll> ManageShow(Long appID){return manageMapper.selectByAppID(appID);}



    /**
     * 调拨者看这个,所有未划拨的
     */
    public List<ManageAll> ManageNeedDis()
    {
        return manageMapper.selectByState();
    }



}
