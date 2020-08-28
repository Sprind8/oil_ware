package oil.oil_test.POJO.data;

import oil.oil_test.POJO.CheckDetail;

import java.util.Date;

public class CheckDetailAll extends CheckDetail {
    private String oilName;
    private String oilCode;
    private Float stockNumber;
    private Date stockProduceDate;


    public String getOilName() {
        return oilName;
    }

    public void setOilName(String oilName) {
        this.oilName = oilName;
    }

    public String getOilCode() {
        return oilCode;
    }

    public void setOilCode(String oilCode) {
        this.oilCode = oilCode;
    }

    public Float getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(Float stockNumber) {
        this.stockNumber = stockNumber;
    }

    public Date getStockProduceDate() {
        return stockProduceDate;
    }

    public void setStockProduceDate(Date stockProduceDate) {
        this.stockProduceDate = stockProduceDate;
    }
}
