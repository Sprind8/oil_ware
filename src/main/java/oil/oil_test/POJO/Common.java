package oil.oil_test.POJO;

public class Common {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_table.commonID
     *
     * @mbg.generated
     */
    private Long commonID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_table.groupID
     *
     * @mbg.generated
     */
    private Long groupID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column common_table.commonName
     *
     * @mbg.generated
     */
    private String commonName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_table.commonID
     *
     * @return the value of common_table.commonID
     *
     * @mbg.generated
     */
    public Long getCommonID() {
        return commonID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_table.commonID
     *
     * @param commonID the value for common_table.commonID
     *
     * @mbg.generated
     */
    public void setCommonID(Long commonID) {
        this.commonID = commonID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_table.groupID
     *
     * @return the value of common_table.groupID
     *
     * @mbg.generated
     */
    public Long getGroupID() {
        return groupID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_table.groupID
     *
     * @param groupID the value for common_table.groupID
     *
     * @mbg.generated
     */
    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column common_table.commonName
     *
     * @return the value of common_table.commonName
     *
     * @mbg.generated
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column common_table.commonName
     *
     * @param commonName the value for common_table.commonName
     *
     * @mbg.generated
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName == null ? null : commonName.trim();
    }
}