package com.systena.githupdemo.di.module;

import android.content.Context;

import com.systena.githupdemo.data.AppDatabase;
import com.systena.githupdemo.util.common.Define;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class AppDatabaseModule {

    @Singleton
    @Provides
    AppDatabase provideRoomDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, Define.Database.DATABASE_NAME)
                .build();
    }
}

