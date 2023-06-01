package com.example.ccdemo.LocalDBDaos;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Database(entities = {}, version = 1, exportSchema = false)
public abstract class LocalDB extends RoomDatabase {


    private static volatile LocalDB instance;

    public abstract CurrentUserDao currentUserDao();

    public abstract FieldOrganizationDao fieldOrganizationDao();


    @Module
    @InstallIn(SingletonComponent.class)
    public static class Factory {
        @Singleton
        @Provides
        public static LocalDB provideLocalDB(Application application) {
            if (instance == null) {
                instance = Room.databaseBuilder(application.getApplicationContext(),
                                LocalDB.class, "LocalDB")
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return instance;
        }

    }

}