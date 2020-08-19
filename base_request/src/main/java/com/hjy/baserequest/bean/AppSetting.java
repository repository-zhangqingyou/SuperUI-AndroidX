package com.hjy.baserequest.bean;

import java.io.Serializable;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class AppSetting implements Serializable {


    private String Desc;
    private DataBean data;
    private String Code;

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public static class DataBean implements Serializable {


        private int id;
        private String sdes = "";
        private String version = "0";
        private String qudaoName;
        private String appSize = "0";
        private Object dtinsert;
        private long dtupdate;
        private String versionCode = "0";
        private String downurl;
        private String qudaoNumber;
        private long newUpdateTime;
        private String downNumber;
        private String theTotalJob;
        private String appMD5;
        private String registeredPercentage;
        private String totalRegistrations;
        private String evaluationJob;
        private String istate;
        private String minVersion = "0";

        public String getMinVersion() {
            return minVersion;
        }

        public void setMinVersion(String minVersion) {
            this.minVersion = minVersion;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSdes() {
            return sdes;
        }

        public void setSdes(String sdes) {
            this.sdes = sdes;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getQudaoName() {
            return qudaoName;
        }

        public void setQudaoName(String qudaoName) {
            this.qudaoName = qudaoName;
        }

        public String getAppSize() {
            return appSize;
        }

        public void setAppSize(String appSize) {
            this.appSize = appSize;
        }

        public Object getDtinsert() {
            return dtinsert;
        }

        public void setDtinsert(Object dtinsert) {
            this.dtinsert = dtinsert;
        }

        public long getDtupdate() {
            return dtupdate;
        }

        public void setDtupdate(long dtupdate) {
            this.dtupdate = dtupdate;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getDownurl() {
            return downurl;
        }

        public void setDownurl(String downurl) {
            this.downurl = downurl;
        }

        public String getQudaoNumber() {
            return qudaoNumber;
        }

        public void setQudaoNumber(String qudaoNumber) {
            this.qudaoNumber = qudaoNumber;
        }

        public long getNewUpdateTime() {
            return newUpdateTime;
        }

        public void setNewUpdateTime(long newUpdateTime) {
            this.newUpdateTime = newUpdateTime;
        }

        public String getDownNumber() {
            return downNumber;
        }

        public void setDownNumber(String downNumber) {
            this.downNumber = downNumber;
        }

        public String getTheTotalJob() {
            return theTotalJob;
        }

        public void setTheTotalJob(String theTotalJob) {
            this.theTotalJob = theTotalJob;
        }

        public Object getAppMD5() {
            return appMD5;
        }

        public void setAppMD5(String appMD5) {
            this.appMD5 = appMD5;
        }

        public String getRegisteredPercentage() {
            return registeredPercentage;
        }

        public void setRegisteredPercentage(String registeredPercentage) {
            this.registeredPercentage = registeredPercentage;
        }

        public String getTotalRegistrations() {
            return totalRegistrations;
        }

        public void setTotalRegistrations(String totalRegistrations) {
            this.totalRegistrations = totalRegistrations;
        }

        public String getEvaluationJob() {
            return evaluationJob;
        }

        public void setEvaluationJob(String evaluationJob) {
            this.evaluationJob = evaluationJob;
        }

        public String getIstate() {
            return istate;
        }

        public void setIstate(String istate) {
            this.istate = istate;
        }
    }
}
