package oil.oil_test.controller;

import oil.oil_test.POJO.Oil;
import oil.oil_test.POJO.OilType;
import oil.oil_test.POJO.data.OilAll;
import oil.oil_test.service.OilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OilController {

    @Autowired
    private OilService oilService;

/**
* 油品大类信息管理
*/
    @ResponseBody
    @RequestMapping("/oilTypeAdd")
    public String oilTypeAdd(OilType oilType)
    {
      if (oilService.oilTypeAdd(oilType) != 0)
      {
          return "添加成功";
      }
      return "添加油品大类信息失败";
    }

    @ResponseBody
    @RequestMapping("/oilTypeLook")
    public List<OilType> oilTypeLook()
    {
        List<OilType> oilTypes = oilService.oilTypeLook();
        return oilTypes;
    }

    @ResponseBody
    @RequestMapping("/oilTypeUpdate")
    public String oilTypeUpdate(OilType oilType)
    {
        if (oilService.oilTypeUpdate(oilType) != 0) {
            return "更改油品大类信息成功";
        }
        return "更改油品大类信息失败";
    }

    @ResponseBody
    @RequestMapping("/oilTypeDelete")
    public String oilTypeDelete(OilType oilType)
    {

          try {
              oilService.oilTypeDelete(oilType);
          }
          catch (Exception e)
          {
              return "删除失败,也许该油品大类已被使用";
          }
          return "删除成功";
    }


/**
* 油品种类信息管理
*/
    @ResponseBody
    @RequestMapping("/oilAdd")  //下拉框已经录入oilTypeID
    public String oilAdd(Oil oil)
    {
        if (oilService.oilAdd(oil) != 0)
        {
            return "添加成功";
        }
        return "添加油品种类信息失败";
    }


    @ResponseBody
    @RequestMapping("/oilUpdate")
    public String oilUpdate(Oil oil) //下拉框已经录入oilTypeID
    {
        if (oilService.oilUpdate(oil) != 0) {
            return "更改油品种类成功";
        }
        return "更改失败";
    }

    @ResponseBody
    @RequestMapping("/oilDelete")
    public String oilDelete(Oil oil)
    {
            try {
                oilService.oilDelete(oil);
            }
            catch (Exception e)
            {
                return "删除失败,也许该油品种类已被使用";
            }
        return "删除成功";
    }

    @ResponseBody
    @RequestMapping("/oilLook")
    public List<OilAll> oilLook(Long oilTypeID)
    {
        List<OilAll> oilAlls = oilService.oilLook(oilTypeID);
        return oilAlls;
    }

}
