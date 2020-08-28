package oil.oil_test.POJO.data;

import oil.oil_test.POJO.House;
import oil.oil_test.POJO.StoreOut;
import oil.oil_test.POJO.User;

public class StoreOutAll extends StoreOut {

    private User user;

    private House house;

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
