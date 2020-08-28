package oil.oil_test.POJO.data;

import oil.oil_test.POJO.Group;

public class PieChart  {

    private Long groupID;

    private String groupCode;

    private String groupName;

    private Float groupTotal;

    private int groupNature;

    public int getGroupNature() {
        return groupNature;
    }

    public void setGroupNature(int groupNature) {
        this.groupNature = groupNature;
    }

    public Long getGroupID() {
        return groupID;
    }

    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Float getGroupTotal() {
        return groupTotal;
    }

    public void setGroupTotal(Float groupTotal) {
        this.groupTotal = groupTotal;
    }
}
