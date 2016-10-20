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
package net.uk.onetransport.android.modules.bitcarriersilverstone.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.Node;
import net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.Sketch;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.Details;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.Stat;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelSummary;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary.TravelTime;
import net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector.Vector;
import net.uk.onetransport.android.modules.common.provider.CommonBaseColumns;
import net.uk.onetransport.android.modules.common.provider.CommonContentHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneConfigVector;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneDataVector;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneNode;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneSketch;
import static net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContract.BitCarrierSilverstoneTravelSummary;

public class BcsContentHelper extends CommonContentHelper {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            DATA_TYPE_SKETCH,
            DATA_TYPE_NODE,
            DATA_TYPE_TRAVEL_SUMMARY,
            DATA_TYPE_CONFIG_VECTOR,
            DATA_TYPE_DATA_VECTOR
    })
    public @interface DataType {
    }

    public static final int DATA_TYPE_SKETCH = 1;
    public static final int DATA_TYPE_NODE = 2;
    public static final int DATA_TYPE_TRAVEL_SUMMARY = 6;
    public static final int DATA_TYPE_CONFIG_VECTOR = 3;
    public static final int DATA_TYPE_DATA_VECTOR = 4;

    public static void insertSketchesIntoProvider(@NonNull Context context,
                                                  @NonNull ArrayList<Sketch> sketches)
            throws RemoteException, OperationApplicationException {
        if (sketches.size() > 0) {
            ContentValues[] cvs = new ContentValues[sketches.size()];
            for (int i = 0; i < sketches.size(); i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(BitCarrierSilverstoneSketch.COLUMN_SKETCH_ID, sketches.get(i).getSketchId());
                cvs[i].put(BitCarrierSilverstoneSketch.COLUMN_VECTOR_ID, sketches.get(i).getVectorId());
                cvs[i].put(BitCarrierSilverstoneSketch.COLUMN_VISIBLE, sketches.get(i).getVisible());
                cvs[i].put(BitCarrierSilverstoneSketch.COLUMN_COPYRIGHTS, sketches.get(i).getCopyrights());
                cvs[i].put(BitCarrierSilverstoneSketch.COLUMN_COORDINATES, sketches.get(i).getCoordinates());
                cvs[i].put(BitCarrierSilverstoneSketch.COLUMN_CIN_ID, sketches.get(i).getCinId());
                cvs[i].put(BitCarrierSilverstoneSketch.COLUMN_CREATION_TIME, sketches.get(i).getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(BcsProviderModule.SKETCH_URI, cvs);
        }
    }

    public static void insertNodesIntoProvider(@NonNull Context context,
                                               @NonNull ArrayList<Node> nodes)
            throws RemoteException, OperationApplicationException {
        if (nodes.size() > 0) {
            ContentValues[] cvs = new ContentValues[nodes.size()];
            for (int i = 0; i < nodes.size(); i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(BitCarrierSilverstoneNode.COLUMN_NODE_ID, nodes.get(i).getId());
                cvs[i].put(BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID, nodes.get(i).getCustomerId());
                cvs[i].put(BitCarrierSilverstoneNode.COLUMN_CUSTOMER_NAME, nodes.get(i).getCustomerName());
                cvs[i].put(BitCarrierSilverstoneNode.COLUMN_LATITUDE, nodes.get(i).getLatitude());
                cvs[i].put(BitCarrierSilverstoneNode.COLUMN_LONGITUDE, nodes.get(i).getLongitude());
                cvs[i].put(BitCarrierSilverstoneNode.COLUMN_CIN_ID, nodes.get(i).getCinId());
                cvs[i].put(BitCarrierSilverstoneNode.COLUMN_CREATION_TIME, nodes.get(i).getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(BcsProviderModule.NODE_URI, cvs);
        }
    }

    public static void insertConfigVectorsIntoProvider(@NonNull Context context,
                                                       @NonNull ArrayList<net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector> vectors)
            throws RemoteException, OperationApplicationException {
        if (vectors.size() > 0) {
            ContentValues[] cvs = new ContentValues[vectors.size()];
            for (int i = 0; i < vectors.size(); i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(BitCarrierSilverstoneConfigVector.COLUMN_VECTOR_ID, vectors.get(i).getId());
                cvs[i].put(BitCarrierSilverstoneConfigVector.COLUMN_NAME, vectors.get(i).getName());
                cvs[i].put(BitCarrierSilverstoneConfigVector.COLUMN_CUSTOMER_NAME, vectors.get(i).getCustomerName());
                cvs[i].put(BitCarrierSilverstoneConfigVector.COLUMN_FROM, vectors.get(i).getFrom());
                cvs[i].put(BitCarrierSilverstoneConfigVector.COLUMN_TO, vectors.get(i).getTo());
                cvs[i].put(BitCarrierSilverstoneConfigVector.COLUMN_DISTANCE, vectors.get(i).getDistance());
                cvs[i].put(BitCarrierSilverstoneConfigVector.COLUMN_SKETCH_ID, vectors.get(i).getsId());
                cvs[i].put(BitCarrierSilverstoneConfigVector.COLUMN_CIN_ID, vectors.get(i).getCinId());
                cvs[i].put(BitCarrierSilverstoneConfigVector.COLUMN_CREATION_TIME, vectors.get(i).getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(BcsProviderModule.CONFIG_VECTOR_URI, cvs);
        }
    }

    public static void insertDataVectorsIntoProvider(@NonNull Context context,
                                                     @NonNull ArrayList<Vector> vectors)
            throws RemoteException, OperationApplicationException {
        if (vectors.size() > 0) {
            ContentValues[] cvs = new ContentValues[vectors.size()];
            for (int i = 0; i < vectors.size(); i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(BitCarrierSilverstoneDataVector.COLUMN_VECTOR_ID, vectors.get(i).getVectorId());
                Details details = vectors.get(i).getAverageDetails();
                if (details != null) {
                    Stat stat = details.getPublish();
                    if (stat != null) {
                        cvs[i].put(BitCarrierSilverstoneDataVector.COLUMN_ELAPSED, stat.getElapsed());
                        cvs[i].put(BitCarrierSilverstoneDataVector.COLUMN_SPEED, stat.getSpeed());
                    }
                }
                cvs[i].put(BitCarrierSilverstoneDataVector.COLUMN_LEVEL_OF_SERVICE, vectors.get(i).getLevelOfService());
                cvs[i].put(BitCarrierSilverstoneDataVector.COLUMN_TIMESTAMP, vectors.get(i).getTimestamp());
                cvs[i].put(BitCarrierSilverstoneDataVector.COLUMN_CIN_ID, vectors.get(i).getCinId());
                cvs[i].put(BitCarrierSilverstoneDataVector.COLUMN_CREATION_TIME, vectors.get(i).getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(BcsProviderModule.DATA_VECTOR_URI, cvs);
        }
    }

    public static void insertTravelSummariesIntoProvider(@NonNull Context context,
                                                         @NonNull ArrayList<TravelSummary> travelSummaries)
            throws RemoteException, OperationApplicationException {
        if (travelSummaries.size() > 0) {
            ContentValues[] cvs = new ContentValues[travelSummaries.size()];
            for (int i = 0; i < travelSummaries.size(); i++) {
                cvs[i] = new ContentValues();
                cvs[i].put(BitCarrierSilverstoneTravelSummary.COLUMN_CLOCK_TIME,
                        travelSummaries.get(i).getTime());
                if (travelSummaries.get(i).getTravelTimes() != null) {
                    // TODO   We assume the array only has one entry.  Otherwise problem.
                    TravelTime travelTime = travelSummaries.get(i).getTravelTimes()[0];
                    cvs[i].put(BitCarrierSilverstoneTravelSummary.COLUMN_TRAVEL_SUMMARY_ID, travelTime.gettId());
                    cvs[i].put(BitCarrierSilverstoneTravelSummary.COLUMN_FROM_LOCATION, travelTime.getFrom());
                    cvs[i].put(BitCarrierSilverstoneTravelSummary.COLUMN_TO_LOCATION, travelTime.getTo());
                }
                if (travelSummaries.get(i).getDetails() != null) {
                    cvs[i].put(BitCarrierSilverstoneTravelSummary.COLUMN_SCORE,
                            travelSummaries.get(i).getDetails().getScore());
                    if (travelSummaries.get(i).getDetails().getPublish() != null) {
                        Stat stat = travelSummaries.get(i).getDetails().getPublish();
                        cvs[i].put(BitCarrierSilverstoneTravelSummary.COLUMN_SPEED, stat.getSpeed());
                        cvs[i].put(BitCarrierSilverstoneTravelSummary.COLUMN_ELAPSED, stat.getElapsed());
                        cvs[i].put(BitCarrierSilverstoneTravelSummary.COLUMN_TREND, stat.getTrend());
                    }
                }
                cvs[i].put(BitCarrierSilverstoneTravelSummary.COLUMN_CIN_ID, travelSummaries.get(i).getCinId());
                cvs[i].put(BitCarrierSilverstoneTravelSummary.COLUMN_CREATION_TIME, travelSummaries.get(i).getCreationTime());
            }
            ContentResolver contentResolver = context.getContentResolver();
            contentResolver.bulkInsert(BcsProviderModule.TRAVEL_SUMMARY_URI, cvs);
        }
    }

    public static Cursor getNodeCursor(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.NODE_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneNode.COLUMN_CUSTOMER_ID);
    }

    // Need the Silverstone data to test.
    public static Cursor getNodeCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BcsProviderModule.NODE_URI, new String[]{"*"},
                CREATION_INTERVAL_SELECTION,
                new String[]{String.valueOf(oldest), String.valueOf(newest)},
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Node[] getNodes(@NonNull Context context) {
        return nodesFromCursor(getNodeCursor(context));
    }

    public static Node[] getNodes(@NonNull Context context, long oldest, long newest) {
        return nodesFromCursor(getNodeCursor(context, oldest, newest));
    }

    public static Cursor getSketchCursor(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.SKETCH_URI,
                new String[]{"*"}, null, null, BcsContract.BitCarrierSilverstoneSketch.COLUMN_SKETCH_ID);
    }

    public static Cursor getSketchCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BcsProviderModule.SKETCH_URI,
                new String[]{"*"},
                CREATION_INTERVAL_SELECTION,
                interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Sketch[] getSketches(@NonNull Context context) {
        return sketchesFromCursor(getSketchCursor(context));
    }

    public static Sketch[] getSketches(@NonNull Context context, long oldest, long newest) {
        return sketchesFromCursor(getSketchCursor(context, oldest, newest));
    }

    public static Cursor getTravelSummaryCursor(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.TRAVEL_SUMMARY_URI,
                new String[]{"*"}, null, null,
                BitCarrierSilverstoneTravelSummary.COLUMN_TRAVEL_SUMMARY_ID);
    }

    public static Cursor getTravelSummaryCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BcsProviderModule.TRAVEL_SUMMARY_URI,
                new String[]{"*"},
                CREATION_INTERVAL_SELECTION,
                interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestTravelSummaryCursor(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.LATEST_TRAVEL_SUMMARY_URI,
                new String[]{"*"}, null, null,
                BitCarrierSilverstoneTravelSummary.COLUMN_TRAVEL_SUMMARY_ID);
    }

    public static TravelSummary[] getTravelSummaries(@NonNull Context context) {
        return travelSummariesFromCursor(getTravelSummaryCursor(context));
    }

    public static TravelSummary[] getTravelSummaries(@NonNull Context context,
                                                     long oldest, long newest) {
        return travelSummariesFromCursor(getTravelSummaryCursor(context, oldest, newest));
    }

    public static TravelSummary[] getLatestTravelSummaries(@NonNull Context context) {
        return travelSummariesFromCursor(getLatestTravelSummaryCursor(context));
    }

    public static Cursor getConfigVectorCursor(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.CONFIG_VECTOR_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneConfigVector.COLUMN_VECTOR_ID);
    }

    public static Cursor getConfigVectorCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BcsProviderModule.CONFIG_VECTOR_URI,
                new String[]{"*"},
                CREATION_INTERVAL_SELECTION,
                interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector[]
    getConfigVectors(@NonNull Context context) {
        return configVectorsFromCursor(getConfigVectorCursor(context));
    }

    public static net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector[]
    getConfigVectors(@NonNull Context context, long oldest, long newest) {
        return configVectorsFromCursor(getConfigVectorCursor(context, oldest, newest));
    }

    public static Cursor getDataVectorCursor(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.DATA_VECTOR_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneDataVector.COLUMN_VECTOR_ID);
    }

    public static Cursor getDataVectorCursor(@NonNull Context context, long oldest, long newest) {
        return context.getContentResolver().query(BcsProviderModule.DATA_VECTOR_URI,
                new String[]{"*"},
                CREATION_INTERVAL_SELECTION,
                interval(oldest, newest),
                CommonBaseColumns.COLUMN_CREATION_TIME);
    }

    public static Cursor getLatestDataVectorCursor(@NonNull Context context) {
        return context.getContentResolver().query(BcsProviderModule.LATEST_DATA_VECTOR_URI,
                new String[]{"*"}, null, null, BitCarrierSilverstoneDataVector.COLUMN_VECTOR_ID);
    }

    public static Vector[] getDataVectors(@NonNull Context context) {
        return dataVectorsFromCursor(getDataVectorCursor(context));
    }

    public static Vector[] getDataVectors(@NonNull Context context, long oldest, long newest) {
        return dataVectorsFromCursor(getDataVectorCursor(context, oldest, newest));
    }

    public static Vector[] getLatestDataVectors(@NonNull Context context) {
        return dataVectorsFromCursor(getLatestDataVectorCursor(context));
    }

    public static void deleteFromProvider(@NonNull Context context, @DataType int dataType) {
        ContentResolver contentResolver = context.getContentResolver();
        switch (dataType) {
            case DATA_TYPE_SKETCH:
                contentResolver.delete(BcsProviderModule.SKETCH_URI, null, null);
                break;
            case DATA_TYPE_NODE:
                contentResolver.delete(BcsProviderModule.NODE_URI, null, null);
                break;
            case DATA_TYPE_CONFIG_VECTOR:
                contentResolver.delete(BcsProviderModule.CONFIG_VECTOR_URI, null, null);
                break;
            case DATA_TYPE_DATA_VECTOR:
                contentResolver.delete(BcsProviderModule.DATA_VECTOR_URI, null, null);
                break;
            case DATA_TYPE_TRAVEL_SUMMARY:
                contentResolver.delete(BcsProviderModule.TRAVEL_SUMMARY_URI, null, null);
                break;
        }
    }

    public static void deleteFromProviderBeforeTime(@NonNull Context context, @DataType int dataType,
                                                    long creationTime) {
        ContentResolver contentResolver = context.getContentResolver();
        switch (dataType) {
            case DATA_TYPE_SKETCH:
                contentResolver.delete(BcsProviderModule.SKETCH_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_NODE:
                contentResolver.delete(BcsProviderModule.NODE_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_CONFIG_VECTOR:
                contentResolver.delete(BcsProviderModule.CONFIG_VECTOR_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_DATA_VECTOR:
                contentResolver.delete(BcsProviderModule.DATA_VECTOR_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
            case DATA_TYPE_TRAVEL_SUMMARY:
                contentResolver.delete(BcsProviderModule.TRAVEL_SUMMARY_URI, CREATED_BEFORE,
                        new String[]{String.valueOf(creationTime)});
                break;
        }
    }

    public static Node[] nodesFromCursor(Cursor cursor) {
        Node[] nodes = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                nodes = new Node[cursor.getCount()];
                for (int i = 0; i < nodes.length; i++) {
                    nodes[i] = new Node();
                    nodes[i].setId(cursor.getInt(cursor.getColumnIndex(
                            BitCarrierSilverstoneNode.COLUMN_NODE_ID)));
                    nodes[i].setCustomerName(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneNode.COLUMN_CUSTOMER_NAME)));
                    nodes[i].setCustomerIdFromName();
                    nodes[i].setLatitude(cursor.getDouble(cursor.getColumnIndex(
                            BitCarrierSilverstoneNode.COLUMN_LATITUDE)));
                    nodes[i].setLongitude(cursor.getDouble(cursor.getColumnIndex(
                            BitCarrierSilverstoneNode.COLUMN_LONGITUDE)));
                    nodes[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneNode.COLUMN_CIN_ID)));
                    nodes[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            BitCarrierSilverstoneNode.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        if (nodes == null) {
            return new Node[0];
        }
        return nodes;
    }

    public static Sketch[] sketchesFromCursor(Cursor cursor) {
        Sketch[] sketches = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                sketches = new Sketch[cursor.getCount()];
                for (int i = 0; i < sketches.length; i++) {
                    sketches[i] = new Sketch();
                    sketches[i].setSketchId(cursor.getInt(cursor.getColumnIndex(
                            BitCarrierSilverstoneSketch.COLUMN_SKETCH_ID)));
                    sketches[i].setVectorId(cursor.getInt(cursor.getColumnIndex(
                            BitCarrierSilverstoneSketch.COLUMN_VECTOR_ID)));
                    sketches[i].setVisible(cursor.getInt(cursor.getColumnIndex(
                            BitCarrierSilverstoneSketch.COLUMN_VISIBLE)) == 1);
                    sketches[i].setCopyrights(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneSketch.COLUMN_COPYRIGHTS)));
                    sketches[i].setCoordinates(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneSketch.COLUMN_COORDINATES)));
                    sketches[i].setPositionsFromCoordinates();
                    sketches[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneSketch.COLUMN_CIN_ID)));
                    sketches[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            BitCarrierSilverstoneSketch.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        if (sketches == null) {
            return new Sketch[0];
        }
        return sketches;
    }

    public static TravelSummary[] travelSummariesFromCursor(Cursor cursor) {
        TravelSummary[] travelSummaries = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                travelSummaries = new TravelSummary[cursor.getCount()];
                for (int i = 0; i < travelSummaries.length; i++) {
                    travelSummaries[i] = new TravelSummary();
                    travelSummaries[i].setTime(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneTravelSummary.COLUMN_CLOCK_TIME)));
                    TravelTime travelTime = new TravelTime();
                    travelTime.settId(cursor.getInt(cursor.getColumnIndex(
                            BitCarrierSilverstoneTravelSummary.COLUMN_TRAVEL_SUMMARY_ID)));
                    travelTime.setFrom(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneTravelSummary.COLUMN_FROM_LOCATION)));
                    travelTime.setTo(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneTravelSummary.COLUMN_TO_LOCATION)));
                    travelSummaries[i].setTravelTimes(new TravelTime[]{travelTime});
                    Details details = new Details();
                    travelSummaries[i].setDetails(details);
                    details.setScore(cursor.getInt(cursor.getColumnIndex(
                            BitCarrierSilverstoneTravelSummary.COLUMN_SCORE)));
                    Stat stat = new Stat();
                    stat.setSpeed(cursor.getDouble(cursor.getColumnIndex(
                            BitCarrierSilverstoneTravelSummary.COLUMN_SPEED)));
                    stat.setElapsed(cursor.getDouble(cursor.getColumnIndex(
                            BitCarrierSilverstoneTravelSummary.COLUMN_ELAPSED)));
                    stat.setTrend(cursor.getDouble(cursor.getColumnIndex(
                            BitCarrierSilverstoneTravelSummary.COLUMN_TREND)));
                    details.setPublish(stat);
                    travelSummaries[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneTravelSummary.COLUMN_CIN_ID)));
                    travelSummaries[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            BitCarrierSilverstoneTravelSummary.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        if (travelSummaries == null) {
            return new TravelSummary[0];
        }
        return travelSummaries;
    }

    public static net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector[]
    configVectorsFromCursor(Cursor cursor) {
        net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector[] vectors = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                vectors = new net.uk.onetransport.android.modules.bitcarriersilverstone.config
                        .vector.Vector[cursor.getCount()];
                for (int i = 0; i < vectors.length; i++) {
                    vectors[i] = new net.uk.onetransport.android.modules.bitcarriersilverstone
                            .config.vector.Vector();
                    vectors[i].setId(cursor.getInt(cursor.getColumnIndex(
                            BitCarrierSilverstoneConfigVector.COLUMN_VECTOR_ID)));
                    vectors[i].setName(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneConfigVector.COLUMN_NAME)));
                    vectors[i].setCustomerName(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneConfigVector.COLUMN_CUSTOMER_NAME)));
                    vectors[i].setFrom(cursor.getInt(cursor.getColumnIndex(
                            BitCarrierSilverstoneConfigVector.COLUMN_FROM)));
                    vectors[i].setTo(cursor.getInt(cursor.getColumnIndex(
                            BitCarrierSilverstoneConfigVector.COLUMN_TO)));
                    vectors[i].setDistance(cursor.getInt(cursor.getColumnIndex(
                            BitCarrierSilverstoneConfigVector.COLUMN_DISTANCE)));
                    vectors[i].setsId(cursor.getInt(cursor.getColumnIndex(
                            BitCarrierSilverstoneConfigVector.COLUMN_SKETCH_ID)));
                    vectors[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneConfigVector.COLUMN_CIN_ID)));
                    vectors[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            BitCarrierSilverstoneConfigVector.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        if (vectors == null) {
            return new net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector[0];
        }
        return vectors;
    }

    public static Vector[] dataVectorsFromCursor(Cursor cursor) {
        Vector[] vectors = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                vectors = new Vector[cursor.getCount()];
                for (int i = 0; i < vectors.length; i++) {
                    vectors[i] = new Vector();
                    vectors[i].setVectorId(cursor.getInt(cursor.getColumnIndex(
                            BitCarrierSilverstoneDataVector.COLUMN_VECTOR_ID)));
                    Details details = new Details();
                    vectors[i].setAverageDetails(details);
                    Stat stat = new Stat();
                    details.setPublish(stat);
                    stat.setElapsed(cursor.getDouble(cursor.getColumnIndex(
                            BitCarrierSilverstoneDataVector.COLUMN_ELAPSED)));
                    stat.setElapsed(cursor.getDouble(cursor.getColumnIndex(
                            BitCarrierSilverstoneDataVector.COLUMN_SPEED)));
                    vectors[i].setLevelOfService(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneDataVector.COLUMN_LEVEL_OF_SERVICE)));
                    vectors[i].setTimestamp(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneDataVector.COLUMN_TIMESTAMP)));
                    vectors[i].setCinId(cursor.getString(cursor.getColumnIndex(
                            BitCarrierSilverstoneDataVector.COLUMN_CIN_ID)));
                    vectors[i].setCreationTime(cursor.getLong(cursor.getColumnIndex(
                            BitCarrierSilverstoneDataVector.COLUMN_CREATION_TIME)));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        if (vectors == null) {
            return new Vector[0];
        }
        return vectors;
    }
}