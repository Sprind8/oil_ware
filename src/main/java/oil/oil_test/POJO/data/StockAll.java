package oil.oil_test.POJO.data;

import oil.oil_test.POJO.House;
import oil.oil_test.POJO.Oil;
import oil.oil_test.POJO.Stock;

public class StockAll extends Stock {
    private String oilName;
    private String oilCode;
    private Long oilTypeID;
    private String oilType;

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

    public Long getOilTypeID() {
        return oilTypeID;
    }

    public void setOilTypeID(Long oilTypeID) {
        this.oilTypeID = oilTypeID;
    }
    public String getOilType()
    {
        return oilType;
    }
    public void setOilType(String oilType)
    {
        this.oilType = oilType;
    }

    @Override
    public String toString() {
        return "StockAll{" +
                "oilName='" + oilName + '\'' +
                ", oilCode='" + oilCode + '\'' +
                ", oilTypeID=" + oilTypeID +
                ", oilType='" + oilType + '\'' +
                '}';
    }
}
