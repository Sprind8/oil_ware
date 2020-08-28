package oil.oil_test.service;

import oil.oil_test.POJO.Oil;
import oil.oil_test.POJO.OilType;
import oil.oil_test.POJO.data.OilAll;
import oil.oil_test.dao.OilMapper;
import oil.oil_test.dao.OilTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OilService {

    @Autowired
    private OilTypeMapper oilTypeMapper;

    @Autowired
    private OilMapper oilMapper;

    /**
     * 油品大类信息管理
     */
    public int oilTypeAdd(OilType oilType)
    {
        return oilTypeMapper.insertSelective(oilType);
    }
    public int oilTypeUpdate(OilType oilType)
    {
        return oilTypeMapper.updateByPrimaryKeySelective(oilType);
    }
    public int oilTypeDelete(OilType oilType)
    {
        return oilTypeMapper.deleteByPrimaryKey(oilType.getOilTypeID());
    }
    public List<OilType> oilTypeLook()
    {
        return oilTypeMapper.selectInfo();
    }

    /**
     * 油品种类信息管理
     */
    public int oilAdd(Oil oil)
    {
        return oilMapper.insertSelective(oil);
    }

    public int oilUpdate(Oil oil)

    {
        return oilMapper.updateByPrimaryKeySelective(oil);
    }

    public int oilDelete(Oil oil)
    {
        return oilMapper.deleteByPrimaryKey(oil.getOilID());
    }

    public List<OilAll> oilLook(Long oilTypeID)
    {
        return oilMapper.selectInfo(oilTypeID);
    }
}
