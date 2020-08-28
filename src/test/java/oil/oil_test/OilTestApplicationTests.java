package oil.oil_test;

import com.github.pagehelper.PageInfo;
import oil.oil_test.POJO.StoreOut;
import oil.oil_test.POJO.data.StoreOutAll;
import oil.oil_test.controller.StoreOutController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OilTestApplicationTests {
    @Autowired
    private StoreOutController storeOutController;

    @Test
    public void contextLoads() {
        PageInfo<StoreOutAll> storeOutAllPageInfos = storeOutController.receiptShow(new Long(5),false,0,0);
            System.out.println(storeOutAllPageInfos.toString());
        }
    }


