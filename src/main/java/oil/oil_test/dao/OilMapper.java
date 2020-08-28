package oil.oil_test.dao;

import oil.oil_test.POJO.Oil;
import oil.oil_test.POJO.data.OilAll;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OilMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oil_table
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long oilID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oil_table
     *
     * @mbg.generated
     */
    int insert(Oil record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oil_table
     *
     * @mbg.generated
     */
    int insertSelective(Oil record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oil_table
     *
     * @mbg.generated
     */
    Oil selectByPrimaryKey(Long oilID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oil_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Oil record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oil_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Oil record);

    List<OilAll> selectInfo(Long oilTypeID);

}