package android.sgz.com.bean;

/**
 * Created by WD on 2018/6/22.
 */

public class DefaultProjectOrderBean {


    /**
     * success : true
     * resultCode : 1
     * resultMsg : SUCCESS
     * data : {"date":"2018-09-19","project":{"id":25,"userid":1,"name":"魏","categoryid":1,"regulatoryagency":null,"headman":"伟哥","mobile":"1333333333","address":"安徽省合肥市蜀山区","clockrange":3000,"lng":117.223434,"lat":31.859878,"createtime":"2018-07-04 21:27","starttime":"2018-07-04","startworktime":"21:27","endworktime":"21:27","status":1,"worknum":null,"merchantid":2,"ifend":0,"endtime":null},"workRecord":{"id":null,"userid":null,"projectid":null,"startrecordtime":null,"endrecordtime":null,"startrecordaddress":null,"endrecordaddress":null,"startlat":null,"startlng":null,"endlat":null,"endlng":null,"daysalary":0,"allowance":0,"createtime":"2018-09-19","startstatus":4,"endstatus":4,"remark":null,"operaremark":null}}
     * exception : false
     */

    private boolean success;
    private String resultCode;
    private String resultMsg;
    private DataBean data;
    private boolean exception;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isException() {
        return exception;
    }

    public void setException(boolean exception) {
        this.exception = exception;
    }

    public static class DataBean {
        /**
         * date : 2018-09-19
         * project : {"id":25,"userid":1,"name":"魏","categoryid":1,"regulatoryagency":null,"headman":"伟哥","mobile":"1333333333","address":"安徽省合肥市蜀山区","clockrange":3000,"lng":117.223434,"lat":31.859878,"createtime":"2018-07-04 21:27","starttime":"2018-07-04","startworktime":"21:27","endworktime":"21:27","status":1,"worknum":null,"merchantid":2,"ifend":0,"endtime":null}
         * workRecord : {"id":null,"userid":null,"projectid":null,"startrecordtime":null,"endrecordtime":null,"startrecordaddress":null,"endrecordaddress":null,"startlat":null,"startlng":null,"endlat":null,"endlng":null,"daysalary":0,"allowance":0,"createtime":"2018-09-19","startstatus":4,"endstatus":4,"remark":null,"operaremark":null}
         */

        private String date;
        private ProjectBean project;
        private WorkRecordBean workRecord;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public ProjectBean getProject() {
            return project;
        }

        public void setProject(ProjectBean project) {
            this.project = project;
        }

        public WorkRecordBean getWorkRecord() {
            return workRecord;
        }

        public void setWorkRecord(WorkRecordBean workRecord) {
            this.workRecord = workRecord;
        }

        public static class ProjectBean {
            /**
             * id : 25
             * userid : 1
             * name : 魏
             * categoryid : 1
             * regulatoryagency : null
             * headman : 伟哥
             * mobile : 1333333333
             * address : 安徽省合肥市蜀山区
             * clockrange : 3000
             * lng : 117.223434
             * lat : 31.859878
             * createtime : 2018-07-04 21:27
             * starttime : 2018-07-04
             * startworktime : 21:27
             * endworktime : 21:27
             * status : 1
             * worknum : null
             * merchantid : 2
             * ifend : 0
             * endtime : null
             */

            private int id;
            private int userid;
            private String name;
            private int categoryid;
            private String regulatoryagency;
            private String headman;
            private String mobile;
            private String address;
            private int clockrange;
            private double lng;
            private double lat;
            private String createtime;
            private String starttime;
            private String startworktime;
            private String endworktime;
            private int status;
            private String worknum;
            private int merchantid;
            private int ifend;
            private String endtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(int categoryid) {
                this.categoryid = categoryid;
            }

            public String getRegulatoryagency() {
                return regulatoryagency;
            }

            public void setRegulatoryagency(String regulatoryagency) {
                this.regulatoryagency = regulatoryagency;
            }

            public String getHeadman() {
                return headman;
            }

            public void setHeadman(String headman) {
                this.headman = headman;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getClockrange() {
                return clockrange;
            }

            public void setClockrange(int clockrange) {
                this.clockrange = clockrange;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getStartworktime() {
                return startworktime;
            }

            public void setStartworktime(String startworktime) {
                this.startworktime = startworktime;
            }

            public String getEndworktime() {
                return endworktime;
            }

            public void setEndworktime(String endworktime) {
                this.endworktime = endworktime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getWorknum() {
                return worknum;
            }

            public void setWorknum(String worknum) {
                this.worknum = worknum;
            }

            public int getMerchantid() {
                return merchantid;
            }

            public void setMerchantid(int merchantid) {
                this.merchantid = merchantid;
            }

            public int getIfend() {
                return ifend;
            }

            public void setIfend(int ifend) {
                this.ifend = ifend;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }
        }

        public static class WorkRecordBean {
            /**
             * id : null
             * userid : null
             * projectid : null
             * startrecordtime : null
             * endrecordtime : null
             * startrecordaddress : null
             * endrecordaddress : null
             * startlat : null
             * startlng : null
             * endlat : null
             * endlng : null
             * daysalary : 0
             * allowance : 0
             * createtime : 2018-09-19
             * startstatus : 4
             * endstatus : 4
             * remark : null
             * operaremark : null
             */

            private String id;
            private String userid;
            private String projectid;
            private String startrecordtime;
            private String endrecordtime;
            private String startrecordaddress;
            private String endrecordaddress;
            private String startlat;
            private String startlng;
            private String endlat;
            private String endlng;
            private int daysalary;
            private int allowance;
            private String createtime;
            private int startstatus;
            private int endstatus;
            private String remark;
            private String operaremark;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getProjectid() {
                return projectid;
            }

            public void setProjectid(String projectid) {
                this.projectid = projectid;
            }

            public String getStartrecordtime() {
                return startrecordtime;
            }

            public void setStartrecordtime(String startrecordtime) {
                this.startrecordtime = startrecordtime;
            }

            public String getEndrecordtime() {
                return endrecordtime;
            }

            public void setEndrecordtime(String endrecordtime) {
                this.endrecordtime = endrecordtime;
            }

            public String getStartrecordaddress() {
                return startrecordaddress;
            }

            public void setStartrecordaddress(String startrecordaddress) {
                this.startrecordaddress = startrecordaddress;
            }

            public String getEndrecordaddress() {
                return endrecordaddress;
            }

            public void setEndrecordaddress(String endrecordaddress) {
                this.endrecordaddress = endrecordaddress;
            }

            public String getStartlat() {
                return startlat;
            }

            public void setStartlat(String startlat) {
                this.startlat = startlat;
            }

            public String getStartlng() {
                return startlng;
            }

            public void setStartlng(String startlng) {
                this.startlng = startlng;
            }

            public String getEndlat() {
                return endlat;
            }

            public void setEndlat(String endlat) {
                this.endlat = endlat;
            }

            public String getEndlng() {
                return endlng;
            }

            public void setEndlng(String endlng) {
                this.endlng = endlng;
            }

            public int getDaysalary() {
                return daysalary;
            }

            public void setDaysalary(int daysalary) {
                this.daysalary = daysalary;
            }

            public int getAllowance() {
                return allowance;
            }

            public void setAllowance(int allowance) {
                this.allowance = allowance;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public int getStartstatus() {
                return startstatus;
            }

            public void setStartstatus(int startstatus) {
                this.startstatus = startstatus;
            }

            public int getEndstatus() {
                return endstatus;
            }

            public void setEndstatus(int endstatus) {
                this.endstatus = endstatus;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getOperaremark() {
                return operaremark;
            }

            public void setOperaremark(String operaremark) {
                this.operaremark = operaremark;
            }
        }
    }
}
