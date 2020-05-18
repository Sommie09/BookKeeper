package com.example.bookkeeper

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Book::class], version = 3)
@TypeConverters(DateTypeConverter::class)
abstract class BookRoomDatabase : RoomDatabase() {

    abstract fun bookDao() : BookDao

    companion object{
        private var bookRoomInstance: BookRoomDatabase? = null

        val MIGRATION_1_2: Migration = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE books " + " ADD COLUMN description TEXT DEFAULT 'Add Description' "+
                " NOT NULL ")
            }
        }

        private val MIGRATION_2_3: Migration = object : Migration(2,3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE books " + " ADD COLUMN last_updated INTEGER DEFAULT NULL")
            }
        }


        fun getDatabase(context:Context): BookRoomDatabase?{
            if(bookRoomInstance == null){
                synchronized(BookRoomDatabase::class.java){
                    if(bookRoomInstance == null){
                        bookRoomInstance = Room.databaseBuilder<BookRoomDatabase>(context.applicationContext,
                            BookRoomDatabase::class.java, "book_database")
                            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                            .build()
                    }
                }
            }
            return bookRoomInstance
        }
    }
}