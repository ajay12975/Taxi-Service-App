package com.example.mytaxiservice.categories;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CategoriesResponse {

    @SerializedName("data")
    @Expose
    private List<Datum> data;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
    public static class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("fare_per_km")
        @Expose
        private String farePerKm;
        @SerializedName("extra_percent")
        @Expose
        private String extraPercent;
        @SerializedName("fare_minimum")
        @Expose
        private String fareMinimum;
        @SerializedName("fare_base")
        @Expose
        private String fareBase;
        @SerializedName("fare_per_minute")
        @Expose
        private String farePerMinute;
        @SerializedName("cancel_charge")
        @Expose
        private Integer cancelCharge;
        @SerializedName("cancel_time_threshold")
        @Expose
        private Integer cancelTimeThreshold;
        @SerializedName("waiting_fare")
        @Expose
        private Float waitingFare;
        @SerializedName("for_disabled")
        @Expose
        private Integer forDisabled;
        @SerializedName("city_id")
        @Expose
        private Integer cityId;
        @SerializedName("vehicle_type")
        @Expose
        private String vehicleType;
        @SerializedName("ride_type")
        @Expose
        private String rideType;
        @SerializedName("reservation_percent")
        @Expose
        private String reservationPercent;
        @SerializedName("commission")
        @Expose
        private Integer commission;
        @SerializedName("tax")
        @Expose
        private Double tax;
        @SerializedName("dynamicPricings")
        @Expose
        private List<Object> dynamicPricings;
        @SerializedName("seating_capacity")
        @Expose
        private Integer seatingCapacity;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFarePerKm() {
            return farePerKm;
        }

        public void setFarePerKm(String farePerKm) {
            this.farePerKm = farePerKm;
        }

        public String getExtraPercent() {
            return extraPercent;
        }

        public void setExtraPercent(String extraPercent) {
            this.extraPercent = extraPercent;
        }

        public String getFareMinimum() {
            return fareMinimum;
        }

        public void setFareMinimum(String fareMinimum) {
            this.fareMinimum = fareMinimum;
        }

        public String getFareBase() {
            return fareBase;
        }

        public void setFareBase(String fareBase) {
            this.fareBase = fareBase;
        }

        public String getFarePerMinute() {
            return farePerMinute;
        }

        public void setFarePerMinute(String farePerMinute) {
            this.farePerMinute = farePerMinute;
        }

        public Integer getCancelCharge() {
            return cancelCharge;
        }

        public void setCancelCharge(Integer cancelCharge) {
            this.cancelCharge = cancelCharge;
        }

        public Integer getCancelTimeThreshold() {
            return cancelTimeThreshold;
        }

        public void setCancelTimeThreshold(Integer cancelTimeThreshold) {
            this.cancelTimeThreshold = cancelTimeThreshold;
        }

        public Float getWaitingFare() {
            return waitingFare;
        }

        public void setWaitingFare(Float waitingFare) {
            this.waitingFare = waitingFare;
        }

        public Integer getForDisabled() {
            return forDisabled;
        }

        public void setForDisabled(Integer forDisabled) {
            this.forDisabled = forDisabled;
        }

        public Integer getCityId() {
            return cityId;
        }

        public void setCityId(Integer cityId) {
            this.cityId = cityId;
        }

        public String getVehicleType() {
            return vehicleType;
        }

        public void setVehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
        }

        public String getRideType() {
            return rideType;
        }

        public void setRideType(String rideType) {
            this.rideType = rideType;
        }

        public String getReservationPercent() {
            return reservationPercent;
        }

        public void setReservationPercent(String reservationPercent) {
            this.reservationPercent = reservationPercent;
        }

        public Integer getCommission() {
            return commission;
        }

        public void setCommission(Integer commission) {
            this.commission = commission;
        }

        public Double getTax() {
            return tax;
        }

        public void setTax(Double tax) {
            this.tax = tax;
        }

        public List<Object> getDynamicPricings() {
            return dynamicPricings;
        }

        public void setDynamicPricings(List<Object> dynamicPricings) {
            this.dynamicPricings = dynamicPricings;
        }

        public Integer getSeatingCapacity() {
            return seatingCapacity;
        }

        public void setSeatingCapacity(Integer seatingCapacity) {
            this.seatingCapacity = seatingCapacity;
        }

    }

}

