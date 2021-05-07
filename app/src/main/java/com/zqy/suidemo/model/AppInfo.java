package com.zqy.suidemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2021/5/7
 * 描述:
 */
public class AppInfo {
    /**
     * cmd : get_app_info
     * param : {"name":"Android行为测试","package":"zctt.android.testapi","version":"1.0","apk_md5":"5D4AB006182159E171D06BB151853E02","icon_path":"/sdcard/.ppp/zctt.android.testapi.p","apk_path":"/data/app/zctt.android.testapi-1/base.apk","apptype":"1","permissions":["android.permission.READ_PRIVILEGED_PHONE_STATE","android.permission.BLUETOOTH_ADMIN","android.permission.WRITE_SETTINGS","android.permission.CALL_PHONE","android.permission.INTERNET","android.permission.WRITE_EXTERNAL_STORAGE","android.permission.READ_EXTERNAL_STORAGE","android.permission.READ_PHONE_STATE","android.permission.SEND_SMS","android.permission.READ_CONTACTS","android.permission.READ_CALL_LOG","android.permission.READ_SMS","android.permission.RECEIVE_SMS","com.android.browser.permission.READ_HISTORY_BOOKMARKS","android.permission.ACCESS_WIFI_STATE","android.permission.CHANGE_WIFI_STATE","android.permission.ACCESS_COARSE_LOCATION","android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_NETWORK_STATE","android.permission.CHANGE_NETWORK_STATE","android.permission.RECORD_AUDIO","android.permission.CAMERA","android.permission.BLUETOOTH","android.permission.READ_CALENDAR","android.permission.WRITE_CALENDAR","android.permission.REQUEST_INSTALL_PACKAGES","android.permission.ACCESS_LOCATION_EXTRA_COMMANDS","android.permission.PROCESS_OUTGOING_CALLS","android.permission.ACCESS_MOCK_LOCATION","android.permission.WAKE_LOCK"],"percent":33}
     */

    private String cmd;
    private ParamBean param;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public ParamBean getParam() {
        return param;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public class ParamBean {

        /**
         * phone_name : rockchip:KC2
         * brand : Android
         * os_version : Android7.1.2
         * rom_version : rk3399-userdebug 7.1.2 NHG47K eng.robert.20200908.200616 test-keys eng.robert.20200908.200616
         * imei :
         * imsi :
         * resolution : 1280x800
         * mac : b0:02:47:94:24:eb
         * ip : 192.168.1.116
         * cpu :
         * ram : 0
         * root : 1
         * app_count : 3
         * time : 1620271406
         */

        private String phone_name;
        private String brand;
        private String os_version;
        private String rom_version;
        private String imei;
        private String imsi;
        private String resolution;
        private String mac;
        private String ip;
        private String cpu;
        private int ram;
        private int root;
        private int app_count;
        private int time;
        /**
         * name : Android行为测试
         * package : zctt.android.testapi
         * version : 1.0
         * apk_md5 : 5D4AB006182159E171D06BB151853E02
         * icon_path : /sdcard/.ppp/zctt.android.testapi.p
         * apk_path : /data/app/zctt.android.testapi-1/base.apk
         * apptype : 1
         * permissions : ["android.permission.READ_PRIVILEGED_PHONE_STATE","android.permission.BLUETOOTH_ADMIN","android.permission.WRITE_SETTINGS","android.permission.CALL_PHONE","android.permission.INTERNET","android.permission.WRITE_EXTERNAL_STORAGE","android.permission.READ_EXTERNAL_STORAGE","android.permission.READ_PHONE_STATE","android.permission.SEND_SMS","android.permission.READ_CONTACTS","android.permission.READ_CALL_LOG","android.permission.READ_SMS","android.permission.RECEIVE_SMS","com.android.browser.permission.READ_HISTORY_BOOKMARKS","android.permission.ACCESS_WIFI_STATE","android.permission.CHANGE_WIFI_STATE","android.permission.ACCESS_COARSE_LOCATION","android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_NETWORK_STATE","android.permission.CHANGE_NETWORK_STATE","android.permission.RECORD_AUDIO","android.permission.CAMERA","android.permission.BLUETOOTH","android.permission.READ_CALENDAR","android.permission.WRITE_CALENDAR","android.permission.REQUEST_INSTALL_PACKAGES","android.permission.ACCESS_LOCATION_EXTRA_COMMANDS","android.permission.PROCESS_OUTGOING_CALLS","android.permission.ACCESS_MOCK_LOCATION","android.permission.WAKE_LOCK"]
         * percent : 33
         */


        private String name;
        @SerializedName("package")
        private String packageX;
        private String version;
        private String apk_md5;
        private String icon_path;
        private String apk_path;
        private String apptype;
        private int percent;
        private List<String> permissions;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getApk_md5() {
            return apk_md5;
        }

        public void setApk_md5(String apk_md5) {
            this.apk_md5 = apk_md5;
        }

        public String getIcon_path() {
            return icon_path;
        }

        public void setIcon_path(String icon_path) {
            this.icon_path = icon_path;
        }

        public String getApk_path() {
            return apk_path;
        }

        public void setApk_path(String apk_path) {
            this.apk_path = apk_path;
        }

        public String getApptype() {
            return apptype;
        }

        public void setApptype(String apptype) {
            this.apptype = apptype;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public List<String> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<String> permissions) {
            this.permissions = permissions;
        }

        public String getPhone_name() {
            return phone_name;
        }

        public void setPhone_name(String phone_name) {
            this.phone_name = phone_name;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getOs_version() {
            return os_version;
        }

        public void setOs_version(String os_version) {
            this.os_version = os_version;
        }

        public String getRom_version() {
            return rom_version;
        }

        public void setRom_version(String rom_version) {
            this.rom_version = rom_version;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getImsi() {
            return imsi;
        }

        public void setImsi(String imsi) {
            this.imsi = imsi;
        }

        public String getResolution() {
            return resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getCpu() {
            return cpu;
        }

        public void setCpu(String cpu) {
            this.cpu = cpu;
        }

        public int getRam() {
            return ram;
        }

        public void setRam(int ram) {
            this.ram = ram;
        }

        public int getRoot() {
            return root;
        }

        public void setRoot(int root) {
            this.root = root;
        }

        public int getApp_count() {
            return app_count;
        }

        public void setApp_count(int app_count) {
            this.app_count = app_count;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "ParamBean{" +
                    "phone_name='" + phone_name + '\'' +
                    ", brand='" + brand + '\'' +
                    ", os_version='" + os_version + '\'' +
                    ", rom_version='" + rom_version + '\'' +
                    ", imei='" + imei + '\'' +
                    ", imsi='" + imsi + '\'' +
                    ", resolution='" + resolution + '\'' +
                    ", mac='" + mac + '\'' +
                    ", ip='" + ip + '\'' +
                    ", cpu='" + cpu + '\'' +
                    ", ram=" + ram +
                    ", root=" + root +
                    ", app_count=" + app_count +
                    ", time=" + time +
                    ", name='" + name + '\'' +
                    ", packageX='" + packageX + '\'' +
                    ", version='" + version + '\'' +
                    ", apk_md5='" + apk_md5 + '\'' +
                    ", icon_path='" + icon_path + '\'' +
                    ", apk_path='" + apk_path + '\'' +
                    ", apptype='" + apptype + '\'' +
                    ", percent=" + percent +
                    ", permissions=" + permissions +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "cmd='" + cmd + '\'' +
                ", param=" + param.toString() +
                '}';
    }
}
