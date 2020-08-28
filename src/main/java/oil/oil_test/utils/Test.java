package oil.oil_test.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {


        public static void main(String[] args) {

            String oilCode = "XDD123";
            Date date = new Date();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            String produceDate = s.format(date);

            String gruantee = "50";
            String produceDateNeed = produceDate.replaceAll("[-]","");
            String barCode = oilCode + '-' + produceDateNeed + '-' + gruantee;

            System.out.println(barCode);


        }
    }


