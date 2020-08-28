package oil.oil_test.service;

import oil.oil_test.POJO.Power;
import oil.oil_test.dao.PowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowerService {

    @Autowired
    private PowerMapper powerMapper;

    public List<Power> powerLook ()
    {
        return powerMapper.selectPowerInf();
    }
}
