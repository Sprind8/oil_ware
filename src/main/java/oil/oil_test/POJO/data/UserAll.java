package oil.oil_test.POJO.data;

import oil.oil_test.POJO.Group;
import oil.oil_test.POJO.User;

import java.util.Arrays;


public class UserAll extends User{

    private Group group;

    Integer[] userPowerCode;

    String[] userPowerName;


    public Group getGroup()
    {
        return group;
    }

    public void setGroup(Group group)
    {
        this.group = group;
    }

    public Integer[] getUserPowerCode() {
        return userPowerCode;
    }

    public void setUserPowerCode(Integer[] userPowerCode) {
        this.userPowerCode = userPowerCode;
    }

    public String[] getUserPowerName() {
        return userPowerName;
    }

    public void setUserPowerName(String[] userPowerName) {
        this.userPowerName = userPowerName;
    }

    @Override
    public String toString() {
        return "UserAll{" +
                "group=" + group +
                ", userPowerCode=" + Arrays.toString(userPowerCode) +
                ", userPowerName=" + Arrays.toString(userPowerName) +
                '}';
    }
}
