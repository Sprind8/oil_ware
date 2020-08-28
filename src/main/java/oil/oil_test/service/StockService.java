package oil.oil_test.service;

import oil.oil_test.POJO.*;
import oil.oil_test.POJO.data.PieChart;
import oil.oil_test.POJO.data.StockAll;
import oil.oil_test.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StoreOutMapper storeOutMapper;
    @Autowired
    private StoreOutDetailMapper storeOutDetailMapper;
    @Autowired
    private CheckMainMapper checkMainMapper;
    @Autowired
    private CheckDetailMapper checkDetailMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private OilMapper oilMapper;
    @Autowired
    private GroupMapper groupMapper;

    /**
     * 现有库存表入库
     */
    @Transactional
    public int stockInput(Long storeOutID)
    {
        StoreOut storeOut = storeOutMapper.selectByPrimaryKey(storeOutID);
        System.out.println(storeOut);
        if (storeOut.getStoreOutState() == 0)
        {
                List<StoreOutDetail> storeOutDetails = storeOutDetailMapper.selectByStoreOutID(storeOutID);
                Date date = new Date();
                java.sql.Date transDate = new java.sql.Date(date.getTime());

                for(int i=0;i<storeOutDetails.size();i++)
                {
                    int code = 0;
                    Stock stock = new Stock();
                    stock.setWareID(storeOutDetails.get(i).getWareID());
                    stock.setOilID(storeOutDetails.get(i).getOilID());
                    stock.setStockProduceDate(storeOutDetails.get(i).getStoreDetailProDate());
                    stock.setStockDate(transDate);
                    stock.setStockReGruantee(storeOutDetails.get(i).getStoreDetailGra());
                    stock.setStockNumber(storeOutDetails.get(i).getStoreDetailNum()); //记录的是操作数量

                    //时效状态(根据生产日期和实际保质期计算得出的过期时间)
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(stock.getStockProduceDate());
                    calendar.add(Calendar.DATE,stock.getStockReGruantee());
                    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                    String deadTime = s.format(calendar.getTime());
                    stock.setStockState(deadTime);

                    //差条形码
                    String oilCode = oilMapper.selectByPrimaryKey(stock.getOilID()).getOilCode();
                    String produceDate = s.format(stock.getStockProduceDate());
                    String gruantee = stock.getStockReGruantee().toString();
                    String produceDateNeed = produceDate.replaceAll("[-]","");
                    String barCode = oilCode + '-' + produceDateNeed + '-' + gruantee;
                    stock.setStockBarCode(barCode);

                    //判断库存条目是否有对应的库存
                    List<Stock> stockResults = stockMapper.selectByTwoID(stock.getOilID(),stock.getWareID());
                    for(int j=0;j<stockResults.size();j++)
                    {
                        //有则改变库存量，并更新操作时间
                        if (stock.getStockProduceDate().equals(stockResults.get(j).getStockProduceDate())
                                && stock.getStockReGruantee().equals(stockResults.get(j).getStockReGruantee()))
                        {
                            float num = stock.getStockNumber() + stockResults.get(j).getStockNumber();
                            stockResults.get(j).setStockNumber(num);
                            stockResults.get(j).setStockDate(transDate);
                            if (stockMapper.updateByPrimaryKeySelective(stockResults.get(j)) != 0)
                            {
                                code = 1;
                            }
                        }
                    }
                    //没有则新增一条记录
                    if (code != 1)
                    {
                        stockMapper.insertSelective(stock);
                    }

                }
                //操作完毕，更改主记录表信息
                storeOut.setStoreOutDate(transDate);
                storeOut.setStoreOutState(1);
                storeOutMapper.updateByPrimaryKey(storeOut);
                return 0;
            }
        return 1; //已经提交过，不能在更改库存
    }


    /**
     * 现有库存出库
     */
    @Transactional
    public int stockOutput(Long storeOutID)
    {
        StoreOut storeOut = storeOutMapper.selectByPrimaryKey(storeOutID);

        if (storeOut.getStoreOutState() == 0)
        {
            List<StoreOutDetail> storeOutDetails = storeOutDetailMapper.selectByStoreOutID(storeOutID);
            Date date = new Date();
            java.sql.Date transDate = new java.sql.Date(date.getTime());

            for(int i=0;i<storeOutDetails.size(); i++)
            {
                int code = 0;
                Stock stock = new Stock();
                stock.setWareID(storeOutDetails.get(i).getWareID());
                stock.setOilID(storeOutDetails.get(i).getOilID());
                stock.setStockProduceDate(storeOutDetails.get(i).getStoreDetailProDate());
                stock.setStockDate(transDate);
                stock.setStockReGruantee(storeOutDetails.get(i).getStoreDetailGra());
                stock.setStockNumber(storeOutDetails.get(i).getStoreDetailNum()); //记录的是操作数量
                //差条形码

                //判断库存条目是否有对应的库存（生产日期和保质期）
                List<Stock> stockResults = stockMapper.selectByTwoID(stock.getOilID(),stock.getWareID());
                for(int j=0;j<stockResults.size();j++)
                {
                    //有则改变库存量，并更新操作时间
                    if (stock.getStockProduceDate().equals(stockResults.get(j).getStockProduceDate())
                            && stock.getStockReGruantee().equals(stockResults.get(j).getStockReGruantee()))
                    {
                        float num = stockResults.get(j).getStockNumber()- stock.getStockNumber() ;
                        if (num < 0)
                        {
                            num = 0;
                        }
                        stockResults.get(j).setStockNumber(num);
                        stockResults.get(j).setStockDate(transDate);
                        if (stockMapper.updateByPrimaryKeySelective(stockResults.get(j)) != 0) {
                            code = 1;
                        }
                    }
                }
                //没有则报错
                if (code != 1)
                {
                   return 2;//库存没有对应条目，无法出库
                }
            }
            //操作完毕，更改主记录表信息
            storeOut.setStoreOutDate(transDate);
            storeOut.setStoreOutState(1);
            storeOutMapper.updateByPrimaryKeySelective(storeOut);
            return 0;
        }
        return 1; //已经提交过，不能在更改库存
    }

    /**
     * 删除条目
     */
    public int stockDelete(Long StockID)
    {
        return stockMapper.deleteByPrimaryKey(StockID);
    }




    /**
     * 盘点信息更改(GAI)
     */
    @Transactional
    public int stockCheck(Long checkMainID)
    {
        CheckMain checkMain = checkMainMapper.selectByPrimaryKey(checkMainID);
        Date date = new Date();
        java.sql.Date transDate = new java.sql.Date(date.getTime());
        if (checkMain.getCheckState() == 4)
        {
            List<CheckDetail> checkDetails = checkDetailMapper.seletctByCheckMainID(checkMainID);
            for (int i = 0; i < checkDetails.size();i++)
            {
                Stock stock = stockMapper.selectByPrimaryKey(checkDetails.get(i).getStockID());
                float num = checkDetails.get(i).getCheckChangeNum() + stock.getStockNumber();
                Stock result = new Stock();
                result.setStockID(stock.getStockID());
                result.setStockNumber(num);
                result.setStockDate(transDate);
                stockMapper.updateByPrimaryKeySelective(result);
            }
            //更新库存以后，更新盘点主表
            checkMain.setCheckState(5);
            checkMainMapper.updateByPrimaryKeySelective(checkMain);
            return 0;
        }
        return 1; //已经提交过，无法更改
    }

    /**
     * 显示库存
     */
    public List<StockAll> stockShow(Long wareID)
    {
        return stockMapper.selectByWareID(wareID);
    }



    /**
     * 根据组织信息
     * 查询油品储量查询
     */
   @Transactional
    public List<StockAll> stockLook(String zqName, String jtName, String ltName, Long oilTypeIDWeb)
    {

        List<House> houses = houseMapper.selectByGroupName(zqName, jtName, ltName);
        List<Long> wareIDs = houses.stream().map(House::getWareID).collect(Collectors.toList());


        List<StockAll> stockAlls = new ArrayList<>();
        for(int i = 0; i < wareIDs.size(); i++)
        {
            //根据组织所属所有油库id,遍历所有id下的库存。
            List<StockAll> list = stockMapper.findStockNum(wareIDs.get(i));
            stockAlls.addAll(list);//stockAlls是所有结果
        }


        if(oilTypeIDWeb != null && oilTypeIDWeb != 0)
        {
            //筛选本类油品的结果集
            List<StockAll> stockOfSameOilType = new ArrayList<>();
            List<StockAll> resultOfSameOil = new ArrayList<>();
            for (StockAll res : stockAlls)
            {
                if(res.getOilTypeID() == oilTypeIDWeb )
                {
                    stockOfSameOilType.add(res);
                }
            }
            //合并oilID相同的油料
            for(StockAll resultOld : stockOfSameOilType)
            {
                //看新的list里是否存在oilID相同的条目，存在则number相加
                boolean con = false;
                for (StockAll resultNew : resultOfSameOil) {
                    if (resultNew.getOilID() == resultOld.getOilID()) {
                        float num = resultNew.getStockNumber();
                        resultNew.setStockNumber(num + resultOld.getStockNumber());
                        con = true;
                    }
                }
                //新的list不存在
                if (!con) {
                    resultOfSameOil.add(resultOld);
                }
            }
            return resultOfSameOil;
        }
        else {

            //合并oilTypeID相同的油料
            List<StockAll> resultsOfSameOilType = new ArrayList<>();
            for (StockAll resultOld : stockAlls) {
                //看新的list里是否存在oilTypeID相同的条目，存在则number相加
                boolean flag = false;
                for (StockAll resultNew : resultsOfSameOilType) {
                    if (resultOld.getOilTypeID() == resultNew.getOilTypeID() ) {
                        float num = resultNew.getStockNumber();
                        resultNew.setStockNumber(num + resultOld.getStockNumber());
                        flag = true;
                    }
                }
                //新的list不存在
                if (!flag) {
                    resultOld.setOilName(null);
                    resultOld.setOilCode(null);
                    resultOld.setOilID(null);
                    resultsOfSameOilType.add(resultOld);
                }
            }
            return resultsOfSameOilType;
        }
    }

    @Transactional
    public List<PieChart> stockPie(String zqName, String jtName, String ltName)
    {
        List<Group> groupsNeed = new ArrayList<Group>();
        List<PieChart> pieCharts = new ArrayList<PieChart>();
        if(zqName == null && jtName == null && ltName == null)
        {
            groupsNeed = groupMapper.selectByGroupName(zqName, jtName,ltName,3);
        }
        else if (zqName != null && jtName == null && ltName == null)
        {
            groupsNeed = groupMapper.selectByGroupName(zqName, jtName,ltName,2);
        }
        else if (zqName == null && jtName != null && ltName == null)
        {
            groupsNeed = groupMapper.selectByGroupName(zqName, jtName,ltName,1);
        }
        System.out.println(groupsNeed.size());

        for (Group groupRes: groupsNeed)
        {
            List<Group> groups = new ArrayList<Group>();
            if(groupRes.getGroupNature() == 3)
            {
                groups = groupMapper.selectByGroupName(groupRes.getGroupName(),null,null,0);
            }
            else if (groupRes.getGroupNature() == 2)
            {
                groups = groupMapper.selectByGroupName(null,groupRes.getGroupName(),null,0);
            }
            else if(groupRes.getGroupNature() == 1)
            {
                groups = groupMapper.selectByGroupName(null,null,groupRes.getGroupName(),0);
            }

            float total = 0f;
            for(Group result : groups) {
                List<House> houseRes = houseMapper.selectByGroupID(result.getGroupID());
                for (House Res : houseRes) {
                    if (Res.getWareTotal() == null) {
                        Res.setWareTotal(0f);
                    }
                    total = total + Res.getWareTotal();
                }
            }
            PieChart pieChart = new PieChart();
            pieChart.setGroupID(groupRes.getGroupID());
            pieChart.setGroupCode(groupRes.getGroupCode());
            pieChart.setGroupName(groupRes.getGroupName());
            pieChart.setGroupTotal(total);
            pieChart.setGroupNature(groupRes.getGroupNature());
            pieCharts.add(pieChart);
        }
        return pieCharts;
    }


}
