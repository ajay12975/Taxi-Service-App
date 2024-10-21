package com.example.mytaxiservice.Register;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class StoreResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public class Data {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("profile_img")
        @Expose
        private String profileImg;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("email_verified_at")
        @Expose
        private Object emailVerifiedAt;
        @SerializedName("mobile_verified_at")
        @Expose
        private Object mobileVerifiedAt;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("verified")
        @Expose
        private Boolean verified;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("registered_vehicle_count")
        @Expose
        private Object registeredVehicleCount;
        @SerializedName("active_vehicle_count")
        @Expose
        private Object activeVehicleCount;
        @SerializedName("active_vehicle_doc_count")
        @Expose
        private Object activeVehicleDocCount;
        @SerializedName("country_flag")
        @Expose
        private String countryFlag;
        @SerializedName("country_code")
        @Expose
        private String countryCode;
        @SerializedName("ride_completed")
        @Expose
        private Integer rideCompleted;
        @SerializedName("language")
        @Expose
        private Object language;
        @SerializedName("online")
        @Expose
        private Object online;
        @SerializedName("previousCancelledRide")
        @Expose
        private List<Object> previousCancelledRide;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getProfileImg() {
            return profileImg;
        }

        public void setProfileImg(String profileImg) {
            this.profileImg = profileImg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        public void setEmailVerifiedAt(Object emailVerifiedAt) {
            this.emailVerifiedAt = emailVerifiedAt;
        }

        public Object getMobileVerifiedAt() {
            return mobileVerifiedAt;
        }

        public void setMobileVerifiedAt(Object mobileVerifiedAt) {
            this.mobileVerifiedAt = mobileVerifiedAt;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public Boolean getVerified() {
            return verified;
        }

        public void setVerified(Boolean verified) {
            this.verified = verified;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Object getRegisteredVehicleCount() {
            return registeredVehicleCount;
        }

        public void setRegisteredVehicleCount(Object registeredVehicleCount) {
            this.registeredVehicleCount = registeredVehicleCount;
        }

        public Object getActiveVehicleCount() {
            return activeVehicleCount;
        }

        public void setActiveVehicleCount(Object activeVehicleCount) {
            this.activeVehicleCount = activeVehicleCount;
        }

        public Object getActiveVehicleDocCount() {
            return activeVehicleDocCount;
        }

        public void setActiveVehicleDocCount(Object activeVehicleDocCount) {
            this.activeVehicleDocCount = activeVehicleDocCount;
        }

        public String getCountryFlag() {
            return countryFlag;
        }

        public void setCountryFlag(String countryFlag) {
            this.countryFlag = countryFlag;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public Integer getRideCompleted() {
            return rideCompleted;
        }

        public void setRideCompleted(Integer rideCompleted) {
            this.rideCompleted = rideCompleted;
        }

        public Object getLanguage() {
            return language;
        }

        public void setLanguage(Object language) {
            this.language = language;
        }

        public Object getOnline() {
            return online;
        }

        public void setOnline(Object online) {
            this.online = online;
        }

        public List<Object> getPreviousCancelledRide() {
            return previousCancelledRide;
        }

        public void setPreviousCancelledRide(List<Object> previousCancelledRide) {
            this.previousCancelledRide = previousCancelledRide;
        }

    }

}