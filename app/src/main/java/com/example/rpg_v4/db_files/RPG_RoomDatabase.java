package com.example.rpg_v4.db_files;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

//entities that belong in the database and set the version number.
// Listing the entities will create tables in the database.

//YOU MUST UPDATE THE VERSION NUMBER IF YOU MODIFY DB SCHEMA, AND CHANGE THE MIGRATION
//https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929

@Database(entities = {User_Values.class,User_Characters.class, User_EQPlayed.class, User_Inventory.class, User_Cards.class, User_Decks.class}, version = 1)
public abstract class RPG_RoomDatabase extends RoomDatabase {

    //abstract getter for each DAO
    public abstract User_Values_Dao UserValuesDao();
    public abstract User_EQPlayed_Dao UserEQPlayedDao();
    public abstract User_Characters_Dao UserCharactersDao();
    public abstract User_Decks_Dao UserDecksDao();
    public abstract User_Inventory_Dao UserInventoryDao();
    public abstract User_Cards_Dao UserCardsDao();
    private static Context forMessaging;

    //the following is to prevent multiple instances of the db from being created
    private static volatile RPG_RoomDatabase INSTANCE;

    static RPG_RoomDatabase getDatabase(final Context context) {
        //updateRequire
        //forMessaging = context;
        //sendMessage("in database",context);
        //INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
        //       RPG_RoomDatabase.class, "rpg_database").addMigrations(MIGRATION_5_1).build();
        //sendMessage("INSTANCE mitigated, now removing",context);
        //context.deleteDatabase("rpg_database");
        //System.out.println("Database deleted");
        //sendMessage("Database deleted",context);

        if (INSTANCE == null) {
            synchronized (RPG_RoomDatabase.class) {
                if (INSTANCE == null) {
                    //normal
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RPG_RoomDatabase.class, "rpg_database").addCallback(sRoomDatabaseCallback).build();
                    //sendMessage("Database created; ver: ",context);
                }
            }
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_5_1 = new Migration(5, 1) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            //sendMessage("mitigationTest ver: "+database.getVersion(),forMessaging);

            // Since we didn't alter the table, there's nothing else to do here.
            //IF YOU DO MODIFY, EX:
            //adding columns: https://medium.com/@manuelvicnt/android-room-upgrading-alpha-versions-needs-a-migration-with-kotlin-or-nonnull-7a2d140f05b9
            /*
            database.execSQL("ALTER TABLE User_Values_Table"
                            + " ADD COLUMN REGION TEXT NOT NULL");
             */
            //create new table

            /*
            database.execSQL("CREATE TABLE IF NOT EXISTS TABLE_NAME_TEMP " +
                    "(`PL` INT NOT NULL, " +
                    "`PHASE` INT NOT NULL, " +
                    "`OKANE` INT NOT NULL, " +
                    "`FRONTCHAR` TEXT NOT NULL, " +
                    "`USERNAME` TEXT NOT NULL, " +
                    "`PASSWORD` TEXT NOT NULL, " +
                    "`REGION` TEXT NOT NULL, " +
                    "PRIMARY KEY(`USERNAME`))");
            //copy the data
            database.execSQL("INSERT INTO TABLE_NAME_TEMP (PL, PHASE, OKANE, FRONTCHAR, USERNAME, PASSWORD, REGION) "
                    + "SELECT PL, PHASE, OKANE, FRONTCHAR, USERNAME, PASSWORD, PASSWORD "
                    + "FROM User_Values_Table");
            //drop table
            database.execSQL("DROP TABLE User_Values_Table");
            //replace table name
            database.execSQL("ALTER TABLE TABLE_NAME_TEMP RENAME TO User_Values_Table");
            */
        }
    };

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {

        }

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            //sendMessage("db Opened: ver: "+db.getVersion(),forMessaging);
            System.out.println("DB HAS BEEN OPENED");
            new PopulateDbAsync(INSTANCE).execute();
            //sendMessage("populated db",forMessaging);
        }
    };

    /*
    public static void sendMessage(String message, Context context){
        Intent intent = new Intent();
        intent.setClassName("com.example.twoactivitycrash", "com.example.twoactivitycrash.MyBroadcastReceiver");
        intent.setAction("com.example.twoactivitycrash.MyBroadcastReceiver");
        intent.putExtra("MESSAGE_A", message);
        context.sendBroadcast(intent);
    }
    */
}
