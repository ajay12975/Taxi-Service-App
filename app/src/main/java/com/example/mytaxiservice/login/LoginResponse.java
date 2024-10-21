package com.example.mytaxiservice.login;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class LoginResponse {

    @SerializedName("data")
    @Expose
    private Data data;

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
        private String emailVerifiedAt;
        @SerializedName("mobile_verified_at")
        @Expose
        private Object mobileVerifiedAt;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("status")
        @Expose
        private Integer status;
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
        private Integer registeredVehicleCount;
        @SerializedName("active_vehicle_count")
        @Expose
        private Integer activeVehicleCount;
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
        private Integer online;
        @SerializedName("previousCancelledRide")
        @Expose
        private List<Object> previousCancelledRide;
        @SerializedName("detail")
        @Expose
        private Detail detail;
        @SerializedName("activeVehicle")
        @Expose
        private Object activeVehicle;

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

        public String getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        public void setEmailVerifiedAt(String emailVerifiedAt) {
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

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
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

        public Integer getRegisteredVehicleCount() {
            return registeredVehicleCount;
        }

        public void setRegisteredVehicleCount(Integer registeredVehicleCount) {
            this.registeredVehicleCount = registeredVehicleCount;
        }

        public Integer getActiveVehicleCount() {
            return activeVehicleCount;
        }

        public void setActiveVehicleCount(Integer activeVehicleCount) {
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

        public Integer getOnline() {
            return online;
        }

        public void setOnline(Integer online) {
            this.online = online;
        }

        public List<Object> getPreviousCancelledRide() {
            return previousCancelledRide;
        }

        public void setPreviousCancelledRide(List<Object> previousCancelledRide) {
            this.previousCancelledRide = previousCancelledRide;
        }

        public Detail getDetail() {
            return detail;
        }

        public void setDetail(Detail detail) {
            this.detail = detail;
        }

        public Object getActiveVehicle() {
            return activeVehicle;
        }

        public void setActiveVehicle(Object activeVehicle) {
            this.activeVehicle = activeVehicle;
        }

    }
    public class Detail {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("birth_date")
        @Expose
        private String birthDate;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("fcm_token")
        @Expose
        private Object fcmToken;
        @SerializedName("user_type")
        @Expose
        private String userType;
        @SerializedName("company_name")
        @Expose
        private Object companyName;
        @SerializedName("google_id")
        @Expose
        private Object googleId;
        @SerializedName("facebook_id")
        @Expose
        private Object facebookId;
        @SerializedName("apple_id")
        @Expose
        private Object appleId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Object getFcmToken() {
            return fcmToken;
        }

        public void setFcmToken(Object fcmToken) {
            this.fcmToken = fcmToken;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public Object getCompanyName() {
            return companyName;
        }

        public void setCompanyName(Object companyName) {
            this.companyName = companyName;
        }

        public Object getGoogleId() {
            return googleId;
        }

        public void setGoogleId(Object googleId) {
            this.googleId = googleId;
        }

        public Object getFacebookId() {
            return facebookId;
        }

        public void setFacebookId(Object facebookId) {
            this.facebookId = facebookId;
        }

        public Object getAppleId() {
            return appleId;
        }

        public void setAppleId(Object appleId) {
            this.appleId = appleId;
        }

    }



}
