package oil.oil_test.dao;

import oil.oil_test.POJO.Stock;
import oil.oil_test.POJO.data.StockAll;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StockMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_table
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long stockID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_table
     *
     * @mbg.generated
     */
    int insert(Stock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_table
     *
     * @mbg.generated
     */
    int insertSelective(Stock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_table
     *
     * @mbg.generated
     */
    Stock selectByPrimaryKey(Long stockID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Stock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Stock record);

    List<Stock> selectByTwoID(@Param("oilID") Long oilID, @Param("wareID") Long wareID);

    List<StockAll> selectByWareID(@Param("wareID") Long wareID);

    List<Stock>  selectInfo();

    List<StockAll>  findStockNum(Long wareID);
}