package oil.oil_test.dao;

import oil.oil_test.POJO.Stock;
import oil.oil_test.POJO.StockMain;
import oil.oil_test.POJO.data.StockAll;
import oil.oil_test.POJO.data.StockMainAll;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StockMainMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stockmain_table
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long stockMainID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stockmain_table
     *
     * @mbg.generated
     */
    int insert(StockMain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stockmain_table
     *
     * @mbg.generated
     */
    int insertSelective(StockMain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stockmain_table
     *
     * @mbg.generated
     */
    StockMain selectByPrimaryKey(Long stockMainID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stockmain_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StockMain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stockmain_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StockMain record);

    List<StockMain> selectMainByTwoID(@Param("oilID") Long oilID, @Param("wareID") Long wareID);

    List<StockMainAll> selectMainByWareID(@Param("wareID") Long wareID);
    List<StockMain>  selectInfo();
}