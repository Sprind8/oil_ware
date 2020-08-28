package oil.oil_test.POJO.data;

import oil.oil_test.POJO.Oil;
import oil.oil_test.POJO.StoreOutDetail;

public class StoreDetailAll extends StoreOutDetail {

    private Long oilID;
    private Long oilTypeID;
    private String oilName;
    private String oilCode;
    private String oilType;

    @Override
    public Long getOilID() {
        return oilID;
    }

    @Override
    public void setOilID(Long oilID) {
        this.oilID = oilID;
    }

    public Long getOilTypeID() {
        return oilTypeID;
    }

    public void setOilTypeID(Long oilTypeID) {
        this.oilTypeID = oilTypeID;
    }

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

    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }
}
