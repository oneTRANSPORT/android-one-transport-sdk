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
package net.uk.onetransport.android.county.herts.roadworks;

import com.google.gson.annotations.Expose;

public class Roadworks {

    @Expose
    private String id;
    @Expose
    private String effectOnRoadLayout;
    @Expose
    private String roadMaintenanceType;
    @Expose
    private String comment;
    @Expose
    private String impactOnTraffic;
    @Expose
    private Double latitude;
    @Expose
    private Double longitude;
    @Expose
    private String validityStatus;
    @Expose
    private String overallStartTime;
    @Expose
    private String overallEndTime;
    @Expose
    private String startOfPeriod;
    @Expose
    private String endOfPeriod;
    private String cinId;
    private Long creationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEffectOnRoadLayout() {
        return effectOnRoadLayout;
    }

    public void setEffectOnRoadLayout(String effectOnRoadLayout) {
        this.effectOnRoadLayout = effectOnRoadLayout;
    }

    public String getRoadMaintenanceType() {
        return roadMaintenanceType;
    }

    public void setRoadMaintenanceType(String roadMaintenanceType) {
        this.roadMaintenanceType = roadMaintenanceType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImpactOnTraffic() {
        return impactOnTraffic;
    }

    public void setImpactOnTraffic(String impactOnTraffic) {
        this.impactOnTraffic = impactOnTraffic;
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

    public String getValidityStatus() {
        return validityStatus;
    }

    public void setValidityStatus(String validityStatus) {
        this.validityStatus = validityStatus;
    }

    public String getOverallStartTime() {
        return overallStartTime;
    }

    public void setOverallStartTime(String overallStartTime) {
        this.overallStartTime = overallStartTime;
    }

    public String getOverallEndTime() {
        return overallEndTime;
    }

    public void setOverallEndTime(String overallEndTime) {
        this.overallEndTime = overallEndTime;
    }

    public String getStartOfPeriod() {
        return startOfPeriod;
    }

    public void setStartOfPeriod(String startOfPeriod) {
        this.startOfPeriod = startOfPeriod;
    }

    public String getEndOfPeriod() {
        return endOfPeriod;
    }

    public void setEndOfPeriod(String endOfPeriod) {
        this.endOfPeriod = endOfPeriod;
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
