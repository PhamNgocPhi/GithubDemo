package com.systena.githupdemo.data;

import com.systena.githupdemo.data.dao.RepoDao;
import com.systena.githupdemo.data.dao.UserDao;
import com.systena.githupdemo.data.model.Repo;
import com.systena.githupdemo.data.model.User;
import com.systena.githupdemo.util.common.Define;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Repo.class, User.class}, version = Define.Database.DATABASE_VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RepoDao repoDao();

    public abstract UserDao userDao();

}
