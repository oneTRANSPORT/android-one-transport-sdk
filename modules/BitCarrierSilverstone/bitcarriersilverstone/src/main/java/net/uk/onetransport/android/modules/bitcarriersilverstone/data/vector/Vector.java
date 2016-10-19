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
package net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.Details;

public class Vector {

    @Expose
    @SerializedName("vid")
    private Integer vectorId;
    @Expose
    @SerializedName("time")
    private String timestamp;
    @Expose
    @SerializedName("average")
    private Details averageDetails;
    @Expose
    @SerializedName("last")
    private Details lastDetails;
    @Expose
    @SerializedName("levelofservice")
    private String levelOfService;
    private String cinId;
    private Long creationTime;

    public Integer getVectorId() {
        return vectorId;
    }

    public void setVectorId(Integer vectorId) {
        this.vectorId = vectorId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Details getAverageDetails() {
        return averageDetails;
    }

    public void setAverageDetails(Details averageDetails) {
        this.averageDetails = averageDetails;
    }

    public Details getLastDetails() {
        return lastDetails;
    }

    public void setLastDetails(Details lastDetails) {
        this.lastDetails = lastDetails;
    }

    public String getLevelOfService() {
        return levelOfService;
    }

    public void setLevelOfService(String levelOfService) {
        this.levelOfService = levelOfService;
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
