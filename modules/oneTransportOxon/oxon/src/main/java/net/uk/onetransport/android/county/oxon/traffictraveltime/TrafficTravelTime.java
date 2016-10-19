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
package net.uk.onetransport.android.county.oxon.traffictraveltime;

import com.google.gson.annotations.Expose;

public class TrafficTravelTime {

    @Expose
    private String id;
    @Expose
    private String tpegDirection;
    @Expose
    private String fromType;
    @Expose
    private String fromDescriptor;
    @Expose
    private Double fromLatitude;
    @Expose
    private Double fromLongitude;
    @Expose
    private String toType;
    @Expose
    private String toDescriptor;
    @Expose
    private Double toLongitude;
    @Expose
    private Double toLatitude;
    @Expose
    private String time;
    @Expose
    private Double travelTime;
    @Expose
    private Double freeFlowTravelTime;
    @Expose
    private Double freeFlowSpeed;
    private String cinId;
    private Long creationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTpegDirection() {
        return tpegDirection;
    }

    public void setTpegDirection(String tpegDirection) {
        this.tpegDirection = tpegDirection;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public String getFromDescriptor() {
        return fromDescriptor;
    }

    public void setFromDescriptor(String fromDescriptor) {
        this.fromDescriptor = fromDescriptor;
    }

    public Double getFromLatitude() {
        return fromLatitude;
    }

    public void setFromLatitude(Double fromLatitude) {
        this.fromLatitude = fromLatitude;
    }

    public Double getFromLongitude() {
        return fromLongitude;
    }

    public void setFromLongitude(Double fromLongitude) {
        this.fromLongitude = fromLongitude;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public String getToDescriptor() {
        return toDescriptor;
    }

    public void setToDescriptor(String toDescriptor) {
        this.toDescriptor = toDescriptor;
    }

    public Double getToLongitude() {
        return toLongitude;
    }

    public void setToLongitude(Double toLongitude) {
        this.toLongitude = toLongitude;
    }

    public Double getToLatitude() {
        return toLatitude;
    }

    public void setToLatitude(Double toLatitude) {
        this.toLatitude = toLatitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Double travelTime) {
        this.travelTime = travelTime;
    }

    public Double getFreeFlowTravelTime() {
        return freeFlowTravelTime;
    }

    public void setFreeFlowTravelTime(Double freeFlowTravelTime) {
        this.freeFlowTravelTime = freeFlowTravelTime;
    }

    public Double getFreeFlowSpeed() {
        return freeFlowSpeed;
    }

    public void setFreeFlowSpeed(Double freeFlowSpeed) {
        this.freeFlowSpeed = freeFlowSpeed;
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
