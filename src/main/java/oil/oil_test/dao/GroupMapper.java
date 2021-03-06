package oil.oil_test.dao;

import oil.oil_test.POJO.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface GroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_table
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long groupID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_table
     *
     * @mbg.generated
     */
    int insert(Group record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_table
     *
     * @mbg.generated
     */
    int insertSelective(Group record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_table
     *
     * @mbg.generated
     */
    Group selectByPrimaryKey(Long groupID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Group record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Group record);

    List<Group> selectByNature(Group group); //userService会用到

    List<Group> selectByGroupName(@Param("zqName") String zqName, @Param("jtName")String jtName, @Param("ltName") String ltName,@Param("level") Integer level);

    List<Group> selectByUpName(String upName);

    List<Group> findJTJ(String zqName);

    List<Group> findLT(String zqName);

    List<Group> selectByLevel(int level);
}