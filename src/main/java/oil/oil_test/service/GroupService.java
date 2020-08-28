package oil.oil_test.service;

import oil.oil_test.POJO.Group;
import oil.oil_test.dao.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupMapper groupMapper;

    public int groupAdd(Group group)
    {
        Group check = new Group();
        check.setGroupName(group.getGroupUpName());
        if (groupMapper.selectByNature(check) != null)
        {
            groupMapper.insertSelective(group);
            return 3;
        }
        return 0;
    }

    public int groupDelete(Long groupID)
    {
        Group group = groupMapper.selectByPrimaryKey(groupID);

        if(groupMapper.selectByUpName(group.getGroupName()) == null) {
            return groupMapper.deleteByPrimaryKey(groupID);
        }
        else {
            return 2;
        }
    }

    public int groupUpdate(Group group)
    {
        Group check = new Group();
        check.setGroupName(group.getGroupUpName());
        if (groupMapper.selectByNature(check) != null)
        {
            return groupMapper.updateByPrimaryKeySelective(group);
        }
        return 0;

    }

    /**
     *  用户所属组织信息显示
     */
    public Group groupLook(Long groupID)
    {
        return groupMapper.selectByPrimaryKey(groupID);
    }

    /**
     *  全体组织信息查询
     */
    public List<Group> groupCheck(String zqName,String jtName,String ltName,Integer level
    )
    {
        return groupMapper.selectByGroupName(zqName,jtName,ltName,level);
    }

    /**
     * 下拉框
     */
    public List<Group> findJTJ(String zqName)
    {
        return groupMapper.findJTJ(zqName);
    }

    public List<Group> findLT(String jtName)
    {
        return groupMapper.findLT(jtName);
    }

}
