package net.uk.onetransport.android.modules.common.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import net.uk.onetransport.android.modules.common.R;
import net.uk.onetransport.android.modules.common.provider.lastupdated.LastUpdatedProviderModule;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class OneTransportProvider extends ContentProvider {

    // Not final as the authority must be injected by the app.
    public static String AUTHORITY;
    public static Uri AUTHORITY_URI;
    // Sync adapter needs public access.
    public static ArrayList<ProviderModule> providerModules = new ArrayList<>();

    // Content MIME types.
    private static String MIME_DIR_PREFIX;
    private static String MIME_ITEM_PREFIX;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static boolean initialised = false;

    private CommonDbHelper commonDbHelper;

    public OneTransportProvider() {
    }

    public static void initialise(@NonNull Context context) {
        if (!initialised) {
            initialised = true;
            AUTHORITY = context.getString(R.string.provider_authority);
            AUTHORITY_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
                    .authority(AUTHORITY).build();

            MIME_DIR_PREFIX = "vnd.android.cursor.dir/vnd." + AUTHORITY + ".";
            MIME_ITEM_PREFIX = "vnd.android.cursor.item/vnd." + AUTHORITY + ".";
            addModules(context);
        }
    }

    @Override
    public boolean onCreate() { // TODO    Tidy up this method.
        initialise(getContext());
        commonDbHelper = new CommonDbHelper(getContext(), providerModules);
        return true;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        int match = uriMatcher.match(uri);
        return providerModules.get(match).getType(match, MIME_DIR_PREFIX, MIME_ITEM_PREFIX);
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        int match = uriMatcher.match(uri);
        SQLiteDatabase db = commonDbHelper.getWritableDatabase();
        return providerModules.get(match).bulkInsert(match, values, db);
//        return super.bulkInsert(uri, values);
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        int match = uriMatcher.match(uri);
        SQLiteDatabase db = commonDbHelper.getWritableDatabase();
        return providerModules.get(match).insert(match, values, db);
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase db = commonDbHelper.getReadableDatabase();
        int match = uriMatcher.match(uri);
        return providerModules.get(match).query(uri, match, projection, selection, selectionArgs, sortOrder,
                db);
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = commonDbHelper.getReadableDatabase();
        int match = uriMatcher.match(uri);
        return providerModules.get(match).update(uri, match, values, selection, selectionArgs, db);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = commonDbHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        return providerModules.get(match).delete(match, selection, selectionArgs, db);
    }

    private static void addModules(Context context) {
        // Add Bucks if available.
        addModule(context, R.string.bucks_provider_module_class);
        // Add Herts if available.
        addModule(context, R.string.herts_provider_module_class);
        // Add Northants if available.
        addModule(context, R.string.northants_provider_module_class);
        // Add Oxon if available.
        addModule(context, R.string.oxon_provider_module_class);
        // Add Clearview Silverstone if available.
        addModule(context, R.string.clearview_silverstone_provider_module_class);
        // Add BitCarrier Silverstone if available.
        addModule(context, R.string.bitcarrier_silverstone_provider_module_class);
        // Automatically add the last updated module to the end of the list.
        // We want this to sync last.
        LastUpdatedProviderModule lastUpdatedProviderModule =
                new LastUpdatedProviderModule(context);
        lastUpdatedProviderModule.addUris(uriMatcher, providerModules, AUTHORITY);
    }

    private static void addModule(Context context, int moduleClassResource) {
        String moduleClass = context.getString(moduleClassResource);
        if (!moduleClass.equals("none")) {
            try {
                Class<?> clazz = Class.forName(moduleClass);
                Constructor<?> constructor = clazz.getConstructor(Context.class);
                ProviderModule providerModule = (ProviderModule) constructor.newInstance(
                        context);
                providerModule.addUris(uriMatcher, providerModules, AUTHORITY);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
