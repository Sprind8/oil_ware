package oil.oil_test.dao;

import oil.oil_test.POJO.Power;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PowerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power_table
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long powerID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power_table
     *
     * @mbg.generated
     */
    int insert(Power record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power_table
     *
     * @mbg.generated
     */
    int insertSelective(Power record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power_table
     *
     * @mbg.generated
     */
    Power selectByPrimaryKey(Long powerID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Power record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table power_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Power record);

    Power selectByCode(int powerCode);

    List<Power> selectPowerInf();
 }