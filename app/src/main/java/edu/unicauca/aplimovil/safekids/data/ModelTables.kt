package edu.unicauca.aplimovil.safekids.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "items")
class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)

//Needed entities from here on

@Entity(tableName = "Teacher")
data class Teacher(
    @PrimaryKey val teacher_id: String,
    val name: String,
    val password: String
)

@Entity(
    tableName = "Course",
    foreignKeys = [ForeignKey(
        entity = Teacher::class,
        parentColumns = ["teacher_id"],
        childColumns = ["teacher_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Course(
    @PrimaryKey(autoGenerate = true) val course_id: Int = 0,
    val name: String,
    val teacher_id: String
)

@Entity(tableName = "Guardian")
data class Guardian(
    @PrimaryKey val guardian_id: String,
    val name: String,
    val password: String,
    val balance: Double = 0.0
)

@Entity(tableName = "Student")
data class Student(
    @PrimaryKey val student_id: String,
    val name: String
)

@Entity(
    tableName = "Student_Guardian",
    primaryKeys = ["student_id", "guardian_id"],
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["student_id"],
            childColumns = ["student_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Guardian::class,
            parentColumns = ["guardian_id"],
            childColumns = ["guardian_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StudentGuardian(
    val student_id: String,
    val guardian_id: String
)

@Entity(
    tableName = "Student_Course",
    primaryKeys = ["student_id", "course_id"],
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["student_id"],
            childColumns = ["student_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Course::class,
            parentColumns = ["course_id"],
            childColumns = ["course_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StudentCourse(
    val student_id: String,
    val course_id: Int
)

@Entity(
    tableName = "money",
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["student_id"],
            childColumns = ["student_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Teacher::class,
            parentColumns = ["teacher_id"],
            childColumns = ["teacher_id"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Guardian::class,
            parentColumns = ["guardian_id"],
            childColumns = ["guardian_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class Money(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id")
    val transactionId: Long = 0,

    @ColumnInfo(name = "student_id")
    val studentId: String,

    @ColumnInfo(name = "teacher_id")
    val teacherId: String? = null,

    @ColumnInfo(name = "guardian_id")
    val guardianId: String? = null,

    @ColumnInfo(name = "amount")
    val amount: Double,

    @ColumnInfo(name = "transaction_date")
    val transactionDate: String, // Almacenamos la fecha como una cadena

    @ColumnInfo(name = "is_locked")
    val isLocked: Int = 0 // 0 = desbloqueado, 1 = bloqueado
) {
    companion object {
        private fun getCurrentTimestamp(): String {
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            return format.format(Date())
        }
    }
}