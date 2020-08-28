package oil.oil_test.dao;

import oil.oil_test.POJO.Dis;
import oil.oil_test.POJO.data.DisAll;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DisMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dis_table
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long disID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dis_table
     *
     * @mbg.generated
     */
    int insert(Dis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dis_table
     *
     * @mbg.generated
     */
    int insertSelective(Dis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dis_table
     *
     * @mbg.generated
     */
    Dis selectByPrimaryKey(Long disID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dis_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Dis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dis_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Dis record);


    List<DisAll> selectByAppID(Long appID);

    List<DisAll> selectAll();
}