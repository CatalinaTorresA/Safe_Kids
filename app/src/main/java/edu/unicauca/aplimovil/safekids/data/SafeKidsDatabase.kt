package edu.unicauca.aplimovil.safekids.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [
        Item::class,
        Teacher::class,
        Course::class,
        Guardian::class,
        Student::class,
        StudentGuardian::class,
        StudentCourse::class,
        Money::class
    ],
    version = 3,
    exportSchema = false
)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    abstract fun teacherDao(): TeacherDao
    abstract fun courseDao(): CourseDao
    abstract fun guardianDao(): GuardianDao
    abstract fun studentDao(): StudentDao
    abstract fun studentGuardianDao(): StudentGuardianDao
    abstract fun studentCourseDao(): StudentCourseDao
    abstract fun moneyDao(): MoneyDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}