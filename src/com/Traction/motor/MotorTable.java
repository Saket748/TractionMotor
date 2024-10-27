package com.Traction.motor;

public class MotorTable {
    private int SerialNo;
    private String MotorNumber;
    private String ProjectName;
    private String SupplyDate;

    public MotorTable(int serialNo, String motorNumber, String projectName, String supplyDate) {
        super();
        SerialNo = serialNo;
        MotorNumber = motorNumber;
        ProjectName = projectName;
        SupplyDate = supplyDate;
    }

    public MotorTable(String motorNumber, String projectName, String supplyDate) {
        super();
        MotorNumber = motorNumber;
        ProjectName = projectName;
        SupplyDate = supplyDate;
    }

    public MotorTable() {
        super();
    }

    public int getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(int serialNo) {
        SerialNo = serialNo;
    }

    public String getMotorNumber() {
        return MotorNumber;
    }

    public void setMotorNumber(String motorNumber) {
        MotorNumber = motorNumber;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getSupplyDate() {
        return SupplyDate;
    }

    public void setSupplyDate(String supplyDate) {
        SupplyDate = supplyDate;
    }

    //Display method

    @Override
    public String toString() {
        return "MotorTable{" +
                "SerialNo=" + SerialNo +
                ", MotorNumber='" + MotorNumber + '\'' +
                ", ProjectName='" + ProjectName + '\'' +
                ", SupplyDate='" + SupplyDate + '\'' +
                '}';
    }
}
