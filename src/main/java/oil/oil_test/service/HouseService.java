package oil.oil_test.service;

import oil.oil_test.POJO.House;
import oil.oil_test.dao.GroupMapper;
import oil.oil_test.dao.HouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService
{
    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private GroupMapper groupMapper;

    public int houseAdd(House house)
    {
        return houseMapper.insertSelective(house);
    }

    public int houseUpdate(House house)
    {
        return houseMapper.updateByPrimaryKeySelective(house);

    }

    public int houseDelete(House house)
    {
        return  houseMapper.deleteByPrimaryKey(house.getGroupID());
    }

    public List<House> houseShow(Long groupID)
    {
        return houseMapper.selectByGroupID(groupID);
    }

    public List<House> houseCheck (String zqName, String jtName, String ltName)
    {
        return houseMapper.selectByGroupName(zqName,jtName,ltName);
    }
}
