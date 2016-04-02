package org.jdc.template.model.database;

import android.app.Application;
import android.util.Log;

import org.dbtools.android.domain.AndroidDatabase;
import org.dbtools.android.domain.database.AndroidDatabaseWrapper;
import org.dbtools.android.domain.database.DatabaseWrapper;

import java.util.Collections;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class DatabaseManager extends DatabaseBaseManager {

    public static final int MAIN_VERSION = 2;
    public static final int MAIN_VIEWS_VERSION = 1;

    public static final int OTHER_VERSION = 1;
    public static final int OTHER_VIEWS_VERSION = 1;

    private Application application;

    @Inject
    public DatabaseManager(Application application) {
        this.application = application;
    }

    public void identifyDatabases() {
        addDatabase(application, DatabaseManagerConst.MAIN_DATABASE_NAME, MAIN_VERSION, MAIN_VIEWS_VERSION);
        addDatabase(application, DatabaseManagerConst.OTHER_DATABASE_NAME, OTHER_VERSION, OTHER_VIEWS_VERSION);

        addAttachedDatabase(DatabaseManagerConst.ATTACHED_DATABASE_NAME, DatabaseManagerConst.MAIN_DATABASE_NAME, Collections.singletonList(DatabaseManagerConst.OTHER_DATABASE_NAME));
    }

    @Override
    public DatabaseWrapper createNewDatabaseWrapper(AndroidDatabase androidDatabase) {
        return new AndroidDatabaseWrapper(androidDatabase.getPath()); // default built in Android
//        return new SQLiteDatabaseWrapper(androidDatabase.getPath()); // built version from sqlite.org
    }

    public void onUpgrade(AndroidDatabase androidDatabase, int oldVersion, int newVersion) {
        String databaseName = androidDatabase.getName();
        Log.i(TAG, "Upgrading database [" + databaseName + "] from version " + oldVersion + " to " + newVersion);
        if (oldVersion < newVersion) {
            // todo implement database migration??
            deleteDatabase(androidDatabase);
            onCleanDatabase(androidDatabase);
        }
    }

    public void setContext(Application app) {
        this.application = app;
    }

    public void initDatabaseConnection() {
        Log.i(TAG, "Initializing Database connection: ");
        try {
            getWritableDatabase(DatabaseManagerConst.MAIN_DATABASE_NAME);
        } catch (Exception e) {
            Log.e(TAG, "Failed to open database... attempting to recreate database", e);
            cleanAllDatabases();
            getWritableDatabase(DatabaseManagerConst.MAIN_DATABASE_NAME);
        }
    }
}