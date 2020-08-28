package oil.oil_test.controller;

import oil.oil_test.POJO.Power;
import oil.oil_test.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PowerController {

    @Autowired
    private PowerService powerService;

    /**
     * 权限种类查询
     * @return
     */
    @ResponseBody
    @RequestMapping("/powerInfoValue")
    public List<Power> PowerLook()
    {
        List<Power> powers = powerService.powerLook();
        return powers;
    }
}
