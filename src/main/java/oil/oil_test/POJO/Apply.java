package oil.oil_test.POJO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Apply {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_table.appID
     *
     * @mbg.generated
     */
    private Long appID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_table.appUserID
     *
     * @mbg.generated
     */
    private Long appUserID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_table.wareID
     *
     * @mbg.generated
     */
    private Long wareID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_table.oilID
     *
     * @mbg.generated
     */
    private Long oilID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_table.appNumber
     *
     * @mbg.generated
     */
    private Float appNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_table.appDate
     *
     * @mbg.generated
     */
    private Date appDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_table.appReason
     *
     * @mbg.generated
     */
    private String appReason;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column apply_table.appState
     *
     * @mbg.generated
     */
    private Integer appState;


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_table.appID
     *
     * @return the value of apply_table.appID
     *
     * @mbg.generated
     */
    public Long getAppID() {
        return appID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_table.appID
     *
     * @param appID the value for apply_table.appID
     *
     * @mbg.generated
     */
    public void setAppID(Long appID) {
        this.appID = appID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_table.appUserID
     *
     * @return the value of apply_table.appUserID
     *
     * @mbg.generated
     */
    public Long getAppUserID() {
        return appUserID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_table.appUserID
     *
     * @param appUserID the value for apply_table.appUserID
     *
     * @mbg.generated
     */
    public void setAppUserID(Long appUserID) {
        this.appUserID = appUserID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_table.wareID
     *
     * @return the value of apply_table.wareID
     *
     * @mbg.generated
     */
    public Long getWareID() {
        return wareID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_table.wareID
     *
     * @param wareID the value for apply_table.wareID
     *
     * @mbg.generated
     */
    public void setWareID(Long wareID) {
        this.wareID = wareID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_table.oilID
     *
     * @return the value of apply_table.oilID
     *
     * @mbg.generated
     */
    public Long getOilID() {
        return oilID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_table.oilID
     *
     * @param oilID the value for apply_table.oilID
     *
     * @mbg.generated
     */
    public void setOilID(Long oilID) {
        this.oilID = oilID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_table.appNumber
     *
     * @return the value of apply_table.appNumber
     *
     * @mbg.generated
     */
    public Float getAppNumber() {
        return appNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_table.appNumber
     *
     * @param appNumber the value for apply_table.appNumber
     *
     * @mbg.generated
     */
    public void setAppNumber(Float appNumber) {
        this.appNumber = appNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_table.appDate
     *
     * @return the value of apply_table.appDate
     *
     * @mbg.generated
     */

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getAppDate() {
        return appDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_table.appDate
     *
     * @param appDate the value for apply_table.appDate
     *
     * @mbg.generated
     */
    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_table.appReason
     *
     * @return the value of apply_table.appReason
     *
     * @mbg.generated
     */
    public String getAppReason() {
        return appReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_table.appReason
     *
     * @param appReason the value for apply_table.appReason
     *
     * @mbg.generated
     */
    public void setAppReason(String appReason) {
        this.appReason = appReason == null ? null : appReason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column apply_table.appState
     *
     * @return the value of apply_table.appState
     *
     * @mbg.generated
     */
    public Integer getAppState() {
        return appState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column apply_table.appState
     *
     * @param appState the value for apply_table.appState
     *
     * @mbg.generated
     */
    public void setAppState(Integer appState) {
        this.appState = appState;
    }
}