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
package net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelSummary {

    @Expose
    @SerializedName("rid")
    private Integer rId;
    @Expose
    @SerializedName("time")
    private String time;
    @Expose
    @SerializedName("traveltimes")
    private TravelTime[] travelTimes;
    @Expose
    @SerializedName("average")
    private Details details;
    @Expose
    @SerializedName("last")
    private Details last;
    @Expose
    @SerializedName("levelofservice")
    private String levelOfService;
    private String cinId;
    private Long creationTime;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TravelTime[] getTravelTimes() {
        return travelTimes;
    }

    public void setTravelTimes(TravelTime[] travelTimes) {
        this.travelTimes = travelTimes;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Details getLast() {
        return last;
    }

    public void setLast(Details last) {
        this.last = last;
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
