package oil.oil_test.service;


import oil.oil_test.POJO.Apply;
import oil.oil_test.POJO.Dis;
import oil.oil_test.POJO.Manage;
import oil.oil_test.POJO.data.DisAll;
import oil.oil_test.dao.ApplyMapper;
import oil.oil_test.dao.DisMapper;
import oil.oil_test.dao.ManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DisService {

    @Autowired
    private DisMapper disMapper;
    @Autowired
    private ManageMapper manageMapper;



    @Transactional
    public int disAdd(Dis dis)
    {
        return disMapper.insertSelective(dis);
    }

    public int disFinish(Long manageID)
    {
        Manage manage = new Manage();
        manage.setManageID(manageID);
        manage.setManageState(3);
        return manageMapper.updateByPrimaryKeySelective(manage);
    }



    @Transactional
    public int disDelete(Long disID,Long manageID)
    {
        Manage manage = new Manage();
        manage.setManageID(manageID);
        manage.setManageState(2);
        manageMapper.updateByPrimaryKeySelective(manage);
        return disMapper.deleteByPrimaryKey(disID);
    }



    public List<DisAll> disShow(Long appID)
    {
        return disMapper.selectByAppID(appID);
    }

    public List<DisAll> disLookHis()
    {
        return disMapper.selectAll();
    }
}
