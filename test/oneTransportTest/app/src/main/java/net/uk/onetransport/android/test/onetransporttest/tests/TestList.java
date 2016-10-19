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
package net.uk.onetransport.android.test.onetransporttest.tests;

import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.Sketch.BcsSketchRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.node.BcsNodeRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsConfigVectorDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsConfigVectorInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsConfigVectorIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsConfigVectorQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsDataVectorDeleteBeforeTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsDataVectorDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsDataVectorInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsDataVectorIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsDataVectorQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsLatestDataVectorQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsLatestTravelSummaryQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsNodeDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsNodeInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsNodeIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsNodeQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSketchDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSketchInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSketchIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSketchQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryDeleteBeforeTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.provider.BcsTravelSummaryQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.travelsummary.BcsTravelSummaryRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.vector.BcsConfigVectorRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bitcarriersilverstone.vector.BcsDataVectorRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.carpark.BucksCarParkRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.event.BucksEventRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksCarParkDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksCarParkInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksCarParkIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksCarParkLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksEventDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksEventInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksEventIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksEventLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksRoadworksDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksRoadworksInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksRoadworksIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksRoadworksLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficFlowLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficQueueDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficQueueInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficQueueIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficQueueLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficScootDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficScootInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficScootIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficScootLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficSpeedDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficSpeedInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficSpeedIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficSpeedLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficTravelTimeDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficTravelTimeInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficTravelTimeIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksTrafficTravelTimeLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVariableMessageSignDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVariableMessageSignInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVariableMessageSignIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.provider.BucksVariableMessageSignLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.roadworks.BucksRoadworksRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.trafficflow.BucksTrafficFlowRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.trafficqueue.BucksTrafficQueueRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.trafficscoot.BucksTrafficScootRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.trafficspeed.BucksTrafficSpeedRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.traffictraveltime.BucksTrafficTravelTimeRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.bucks.vms.BucksVariableMessageSignRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.device.CvsDeviceRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsDeviceDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsDeviceInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsDeviceIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsDeviceQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsLatestTrafficItemQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsTrafficGroupInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsTrafficItemDeleteBeforeTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsTrafficItemDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsTrafficItemIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.provider.CvsTrafficItemQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.clearviewsilverstone.traffic.CvsTrafficGroupRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.carpark.HertsCarParkRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.event.HertsEventRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsCarParkDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsCarParkInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsCarParkIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsCarParkLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsEventDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsEventInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsEventIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsEventLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsRoadworksDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsRoadworksInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsRoadworksIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsRoadworksLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficFlowDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficFlowInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficFlowIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficFlowLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficScootDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficScootInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficScootIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficScootLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficSpeedDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficSpeedInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficSpeedIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficSpeedLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficTravelTimeDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficTravelTimeInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficTravelTimeIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsTrafficTravelTimeLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsVariableMessageSignDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsVariableMessageSignInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsVariableMessageSignIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.provider.HertsVariableMessageSignLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.roadworks.HertsRoadworksRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.trafficflow.HertsTrafficFlowRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.trafficscoot.HertsTrafficScootRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.trafficspeed.HertsTrafficSpeedRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.traffictraveltime.HertsTrafficTravelTimeRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.herts.vms.HertsVariableMessageSignRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.carpark.NorthantsCarParkRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsCarParkDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsCarParkInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsCarParkIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsCarParkLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsRoadworksDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsRoadworksInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsRoadworksIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsRoadworksLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficFlowDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficFlowInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficFlowIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficFlowLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficTravelTimeDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficTravelTimeInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficTravelTimeIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsTrafficTravelTimeLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsVariableMessageSignDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsVariableMessageSignInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsVariableMessageSignIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.provider.NorthantsVariableMessageSignLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.roadworks.NorthantsRoadworksRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.trafficflow.NorthantsTrafficFlowRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.traffictraveltime.NorthantsTrafficTravelTimeRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.northants.vms.NorthantsVariableMessageSignRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.carpark.OxonCarParkRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.event.OxonEventRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonCarParkDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonCarParkInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonCarParkIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonCarParkLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonEventDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonEventInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonEventIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonEventLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonRoadworksDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonRoadworksInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonRoadworksIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonRoadworksLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonSyncAdapterTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficFlowDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficFlowInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficFlowIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficFlowLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficQueueDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficQueueInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficQueueIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficQueueLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficScootDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficScootInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficScootIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficScootLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficSpeedDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficSpeedInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficSpeedIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficSpeedLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficTravelTimeDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficTravelTimeInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficTravelTimeIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonTrafficTravelTimeLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonVariableMessageSignDeleteTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonVariableMessageSignInsertTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonVariableMessageSignIntervalQueryTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.provider.OxonVariableMessageSignLatestTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.roadworks.OxonRoadworksRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.trafficflow.OxonTrafficFlowRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.trafficqueue.OxonTrafficQueueRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.trafficscoot.OxonTrafficScootRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.trafficspeed.OxonTrafficSpeedRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.traffictraveltime.OxonTrafficTravelTimeRetrieveTest;
import net.uk.onetransport.android.test.onetransporttest.tests.oxon.vms.OxonVariableMessageSignRetrieveTest;

public class TestList {

    public OneTransportTest[] oneTransportTests = {
//            new ApplicationEntityCreateTest(),
            // Buckinghamshire.
            new BucksCarParkRetrieveTest(),
            new BucksEventRetrieveTest(),
            new BucksRoadworksRetrieveTest(),
            new BucksTrafficFlowRetrieveTest(),
            new BucksTrafficQueueRetrieveTest(),
            new BucksTrafficScootRetrieveTest(),
            new BucksTrafficSpeedRetrieveTest(),
            new BucksTrafficTravelTimeRetrieveTest(),
            new BucksVariableMessageSignRetrieveTest(),

            new BucksCarParkInsertTest(), // Also query test.
            new BucksEventInsertTest(),
            new BucksRoadworksInsertTest(),
            new BucksTrafficFlowInsertTest(),
            new BucksTrafficQueueInsertTest(),
            new BucksTrafficScootInsertTest(),
            new BucksTrafficSpeedInsertTest(),
            new BucksTrafficTravelTimeInsertTest(),
            new BucksVariableMessageSignInsertTest(),

            new BucksCarParkIntervalQueryTest(),
            new BucksEventIntervalQueryTest(),
            new BucksRoadworksIntervalQueryTest(),
            new BucksTrafficFlowIntervalQueryTest(),
            new BucksTrafficQueueIntervalQueryTest(),
            new BucksTrafficScootIntervalQueryTest(),
            new BucksTrafficSpeedIntervalQueryTest(),
            new BucksTrafficTravelTimeIntervalQueryTest(),
            new BucksVariableMessageSignIntervalQueryTest(),

            new BucksCarParkLatestTest(),
            new BucksEventLatestTest(),
            new BucksRoadworksLatestTest(),
            new BucksTrafficFlowLatestTest(),
            new BucksTrafficQueueLatestTest(),
            new BucksTrafficScootLatestTest(),
            new BucksTrafficSpeedLatestTest(),
            new BucksTrafficTravelTimeLatestTest(),
            new BucksVariableMessageSignLatestTest(),

            new BucksCarParkDeleteTest(),
            new BucksEventDeleteTest(),
            new BucksRoadworksDeleteTest(),
            new BucksTrafficFlowDeleteTest(),
            new BucksTrafficQueueDeleteTest(),
            new BucksTrafficScootDeleteTest(),
            new BucksTrafficSpeedDeleteTest(),
            new BucksTrafficTravelTimeDeleteTest(),
            new BucksVariableMessageSignDeleteTest(),

            new BucksSyncAdapterTest(),

            // Hertfordshire.
            new HertsCarParkRetrieveTest(),
            new HertsEventRetrieveTest(),
            new HertsRoadworksRetrieveTest(),
            new HertsTrafficFlowRetrieveTest(),
            new HertsTrafficScootRetrieveTest(),
            new HertsTrafficSpeedRetrieveTest(),
            new HertsTrafficTravelTimeRetrieveTest(),
            new HertsVariableMessageSignRetrieveTest(),

            new HertsCarParkInsertTest(), // Also query test.
            new HertsEventInsertTest(),
            new HertsRoadworksInsertTest(),
            new HertsTrafficFlowInsertTest(),
            new HertsTrafficScootInsertTest(),
            new HertsTrafficSpeedInsertTest(),
            new HertsTrafficTravelTimeInsertTest(),
            new HertsVariableMessageSignInsertTest(),

            new HertsCarParkIntervalQueryTest(),
            new HertsEventIntervalQueryTest(),
            new HertsRoadworksIntervalQueryTest(),
            new HertsTrafficFlowIntervalQueryTest(),
            new HertsTrafficScootIntervalQueryTest(),
            new HertsTrafficSpeedIntervalQueryTest(),
            new HertsTrafficTravelTimeIntervalQueryTest(),
            new HertsVariableMessageSignIntervalQueryTest(),

            new HertsCarParkLatestTest(),
            new HertsEventLatestTest(),
            new HertsRoadworksLatestTest(),
            new HertsTrafficFlowLatestTest(),
            new HertsTrafficScootLatestTest(),
            new HertsTrafficSpeedLatestTest(),
            new HertsTrafficTravelTimeLatestTest(),
            new HertsVariableMessageSignLatestTest(),

            new HertsCarParkDeleteTest(),
            new HertsEventDeleteTest(),
            new HertsRoadworksDeleteTest(),
            new HertsTrafficFlowDeleteTest(),
            new HertsTrafficScootDeleteTest(),
            new HertsTrafficSpeedDeleteTest(),
            new HertsTrafficTravelTimeDeleteTest(),
            new HertsVariableMessageSignDeleteTest(),

            new HertsSyncAdapterTest(),

            // Northamptonshire.
            new NorthantsCarParkRetrieveTest(),
            new NorthantsRoadworksRetrieveTest(),
            new NorthantsTrafficFlowRetrieveTest(),
            new NorthantsTrafficTravelTimeRetrieveTest(),
            new NorthantsVariableMessageSignRetrieveTest(),

            new NorthantsCarParkInsertTest(), // Also query test.
            new NorthantsRoadworksInsertTest(),
            new NorthantsTrafficFlowInsertTest(),
            new NorthantsTrafficTravelTimeInsertTest(),
            new NorthantsVariableMessageSignInsertTest(),

            new NorthantsCarParkIntervalQueryTest(),
            new NorthantsRoadworksIntervalQueryTest(),
            new NorthantsTrafficFlowIntervalQueryTest(),
            new NorthantsTrafficTravelTimeIntervalQueryTest(),
            new NorthantsVariableMessageSignIntervalQueryTest(),

            new NorthantsCarParkLatestTest(),
            new NorthantsRoadworksLatestTest(),
            new NorthantsTrafficFlowLatestTest(),
            new NorthantsTrafficTravelTimeLatestTest(),
            new NorthantsVariableMessageSignLatestTest(),

            new NorthantsCarParkDeleteTest(),
            new NorthantsRoadworksDeleteTest(),
            new NorthantsTrafficFlowDeleteTest(),
            new NorthantsTrafficTravelTimeDeleteTest(),
            new NorthantsVariableMessageSignDeleteTest(),

            new NorthantsSyncAdapterTest(),

            // Oxfordshire.
            new OxonCarParkRetrieveTest(),
            new OxonEventRetrieveTest(),
            new OxonRoadworksRetrieveTest(),
            new OxonTrafficFlowRetrieveTest(),
            new OxonTrafficQueueRetrieveTest(),
            new OxonTrafficScootRetrieveTest(),
            new OxonTrafficSpeedRetrieveTest(),
            new OxonTrafficTravelTimeRetrieveTest(),
            new OxonVariableMessageSignRetrieveTest(),

            new OxonCarParkInsertTest(), // Also query test.
            new OxonEventInsertTest(),
            new OxonRoadworksInsertTest(),
            new OxonTrafficFlowInsertTest(),
            new OxonTrafficQueueInsertTest(),
            new OxonTrafficScootInsertTest(),
            new OxonTrafficSpeedInsertTest(),
            new OxonTrafficTravelTimeInsertTest(),
            new OxonVariableMessageSignInsertTest(),

            new OxonCarParkIntervalQueryTest(),
            new OxonEventIntervalQueryTest(),
            new OxonRoadworksIntervalQueryTest(),
            new OxonTrafficFlowIntervalQueryTest(),
            new OxonTrafficQueueIntervalQueryTest(),
            new OxonTrafficScootIntervalQueryTest(),
            new OxonTrafficSpeedIntervalQueryTest(),
            new OxonTrafficTravelTimeIntervalQueryTest(),
            new OxonVariableMessageSignIntervalQueryTest(),

            new OxonCarParkLatestTest(),
            new OxonEventLatestTest(),
            new OxonRoadworksLatestTest(),
            new OxonTrafficFlowLatestTest(),
            new OxonTrafficQueueLatestTest(),
            new OxonTrafficScootLatestTest(),
            new OxonTrafficSpeedLatestTest(),
            new OxonTrafficTravelTimeLatestTest(),
            new OxonVariableMessageSignLatestTest(),

            new OxonCarParkDeleteTest(),
            new OxonEventDeleteTest(),
            new OxonRoadworksDeleteTest(),
            new OxonTrafficFlowDeleteTest(),
            new OxonTrafficQueueDeleteTest(),
            new OxonTrafficScootDeleteTest(),
            new OxonTrafficSpeedDeleteTest(),
            new OxonTrafficTravelTimeDeleteTest(),
            new OxonVariableMessageSignDeleteTest(),

            new OxonSyncAdapterTest(),

            // Clearview Silverstone.
            new CvsDeviceRetrieveTest(),
            new CvsDeviceDeleteTest(),
            new CvsDeviceInsertTest(),
            new CvsDeviceQueryTest(),
            new CvsDeviceIntervalQueryTest(),
            new CvsTrafficGroupRetrieveTest(),
            new CvsTrafficGroupInsertTest(),
            new CvsTrafficItemDeleteTest(),
            new CvsTrafficGroupInsertTest(),
            new CvsTrafficItemQueryTest(),
            new CvsLatestTrafficItemQueryTest(),
            new CvsTrafficItemIntervalQueryTest(),
            new CvsTrafficItemDeleteBeforeTest(),
            new CvsSyncAdapterTest(),
            // BitCarrier Silverstone
            new BcsNodeRetrieveTest(),
            new BcsNodeDeleteTest(),
            new BcsNodeInsertTest(),
            new BcsNodeQueryTest(),
            new BcsNodeIntervalQueryTest(),
            new BcsDataVectorRetrieveTest(),
            new BcsDataVectorInsertTest(),
            new BcsDataVectorDeleteTest(),
            new BcsDataVectorInsertTest(),
            new BcsDataVectorQueryTest(),
            new BcsLatestDataVectorQueryTest(),
            new BcsDataVectorIntervalQueryTest(),
            new BcsDataVectorDeleteBeforeTest(),
            new BcsConfigVectorRetrieveTest(),
            new BcsConfigVectorDeleteTest(),
            new BcsConfigVectorInsertTest(),
            new BcsConfigVectorQueryTest(),
            new BcsConfigVectorIntervalQueryTest(),
            new BcsTravelSummaryRetrieveTest(),
            new BcsTravelSummaryInsertTest(),
            new BcsTravelSummaryDeleteTest(),
            new BcsTravelSummaryInsertTest(),
            new BcsTravelSummaryQueryTest(),
            new BcsLatestTravelSummaryQueryTest(),
            new BcsTravelSummaryIntervalQueryTest(),
            new BcsTravelSummaryDeleteBeforeTest(),
            new BcsSketchRetrieveTest(),
            new BcsSketchDeleteTest(),
            new BcsSketchInsertTest(),
            new BcsSketchQueryTest(),
            new BcsSketchIntervalQueryTest(),
            new BcsSyncAdapterTest(),

//            new ApplicationEntityDeleteTest()
    };
}
