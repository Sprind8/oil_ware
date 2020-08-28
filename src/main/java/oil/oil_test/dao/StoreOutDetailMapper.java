package oil.oil_test.dao;

import oil.oil_test.POJO.StoreOutDetail;
import oil.oil_test.POJO.data.StoreDetailAll;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StoreOutDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storeoutdetail_table
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long storeDetailID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storeoutdetail_table
     *
     * @mbg.generated
     */
    int insert(StoreOutDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storeoutdetail_table
     *
     * @mbg.generated
     */
    int insertSelective(StoreOutDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storeoutdetail_table
     *
     * @mbg.generated
     */
    StoreOutDetail selectByPrimaryKey(Long storeDetailID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storeoutdetail_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StoreOutDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storeoutdetail_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StoreOutDetail record);

    List<StoreDetailAll> selectAllByStoreOutID(@Param("storeOutID") Long storeOutID);

    List<StoreOutDetail> selectByStoreOutID(@Param("storeOutID") Long storeOutID);
}

//