package database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.usercollectionapp.entities.UserEntity;

import dao.UserDao;

@database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
