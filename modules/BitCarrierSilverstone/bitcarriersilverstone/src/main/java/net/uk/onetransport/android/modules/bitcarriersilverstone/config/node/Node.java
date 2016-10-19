/* Copyright 2016 InterDigital Communications, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.uk.onetransport.android.modules.bitcarriersilverstone.config.node;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Node {

    @Expose
    @SerializedName("id")
    private Integer id;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("block_time")
    private Integer blockTime;
    @Expose
    @SerializedName("customer_name")
    private String customerName;
    @Expose
    @SerializedName("zone")
    private Integer zone;
    @Expose
    @SerializedName("road")
    private String road;
    @Expose
    @SerializedName("pk")
    private String pk;
    @Expose
    @SerializedName("way")
    private String way;
    @Expose
    @SerializedName("lat")
    private Double latitude;
    @Expose
    @SerializedName("lon")
    private Double longitude;
    @Expose
    @SerializedName("cityid")
    private Integer cityId;
    // This field is generated from the prefix of the customer name.
    private Integer customerId;
    private String cinId;
    private Long creationTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(Integer blockTime) {
        this.blockTime = blockTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerIdFromName() {
        if (!TextUtils.isEmpty(customerName)) {
            customerId = Integer.parseInt(customerName.replaceFirst("-.*", ""));
        }
    }

    public String getCinId() {
        return cinId;
    }

    public void setCinId(String cinId) {
        this.cinId = cinId;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }
}
