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

public class Stat {

    @Expose
    @SerializedName("speed")
    private Double speed;
    @Expose
    @SerializedName("elapsed")
    private Double elapsed;
    @Expose
    @SerializedName("trend")
    private Double trend;
    @Expose
    @SerializedName("readings")
    private Integer readings;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getElapsed() {
        return elapsed;
    }

    public void setElapsed(Double elapsed) {
        this.elapsed = elapsed;
    }

    public Double getTrend() {
        return trend;
    }

    public void setTrend(Double trend) {
        this.trend = trend;
    }

    public Integer getReadings() {
        return readings;
    }

    public void setReadings(Integer readings) {
        this.readings = readings;
    }
}
