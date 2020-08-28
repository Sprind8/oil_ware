package oil.oil_test.dao;

import oil.oil_test.POJO.Manage;
import oil.oil_test.POJO.data.ManageAll;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ManageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_table
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long manageID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_table
     *
     * @mbg.generated
     */
    int insert(Manage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_table
     *
     * @mbg.generated
     */
    int insertSelective(Manage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_table
     *
     * @mbg.generated
     */
    Manage selectByPrimaryKey(Long manageID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Manage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manage_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Manage record);

    List<ManageAll> selectByAppID(Long appID);

    List<ManageAll> selectAll();

    List<ManageAll> selectByState();
}