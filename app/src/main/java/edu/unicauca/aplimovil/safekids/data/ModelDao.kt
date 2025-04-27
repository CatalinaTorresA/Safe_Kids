package edu.unicauca.aplimovil.safekids.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}

// Needed DAOs from here on

@Dao
interface TeacherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(teacher: Teacher)

    @Update
    suspend fun update(teacher: Teacher)

    @Delete
    suspend fun delete(teacher: Teacher)

    @Query("SELECT * FROM Teacher WHERE teacher_id = :id")
    fun getTeacher(id: String): Flow<Teacher?>

    @Query("SELECT * FROM Teacher ORDER BY name ASC")
    fun getAllTeachers(): Flow<List<Teacher>>

    @Query("SELECT * FROM Teacher WHERE name = :name")
    fun getTeacherByName(name: String): Flow<Teacher?>
}

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: Course)

    @Update
    suspend fun update(course: Course)

    @Delete
    suspend fun delete(course: Course)

    @Query("SELECT * FROM Course WHERE course_id = :id")
    fun getCourse(id: Int): Flow<Course>

    @Query("SELECT * FROM Course ORDER BY name ASC")
    fun getAllCourses(): Flow<List<Course>>
}

@Dao
interface GuardianDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(guardian: Guardian)

    @Update
    suspend fun update(guardian: Guardian)

    @Delete
    suspend fun delete(guardian: Guardian)

    @Query("SELECT * FROM Guardian WHERE guardian_id = :id")
    fun getGuardian(id: String): Flow<Guardian?>

    @Query("SELECT * FROM Guardian ORDER BY name ASC")
    fun getAllGuardians(): Flow<List<Guardian>>

    @Query("SELECT * FROM Guardian WHERE name = :name")
    fun getGuardianByName(name: String): Flow<Guardian?>
}

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: Student)

    @Update
    suspend fun update(student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Query("SELECT * FROM Student WHERE student_id = :id")
    fun getStudent(id: String): Flow<Student>

    @Query("SELECT * FROM Student ORDER BY name ASC")
    fun getAllStudents(): Flow<List<Student>>
}

@Dao
interface StudentGuardianDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(studentGuardian: StudentGuardian)

    @Delete
    suspend fun delete(studentGuardian: StudentGuardian)

    @Query("SELECT * FROM Student_Guardian WHERE student_id = :studentId")
    fun getGuardiansOfStudent(studentId: String): Flow<List<StudentGuardian>>

    @Query("""
    SELECT Student.student_id, Student.name 
    FROM Student 
    INNER JOIN Student_Guardian ON Student.student_id = Student_Guardian.student_id 
    WHERE Student_Guardian.guardian_id = :guardianId
""")
    fun getStudentsOfGuardian(guardianId: String): Flow<List<Student>>
}

@Dao
interface StudentCourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(studentCourse: StudentCourse)

    @Delete
    suspend fun delete(studentCourse: StudentCourse)

    @Query("SELECT * FROM Student_Course WHERE student_id = :studentId")
    fun getCoursesOfStudent(studentId: String): Flow<List<StudentCourse>>

    @Query("SELECT * FROM Student_Course WHERE course_id = :courseId")
    fun getStudentsOfCourse(courseId: Int): Flow<List<StudentCourse>>

    @Query("SELECT name FROM Course WHERE teacher_id = :teacherId ORDER BY name ASC")
    fun getCourseNamesByTeacher(teacherId: String): Flow<List<String>>

    @Query("""
    SELECT COUNT(*) 
    FROM Student_Course 
    WHERE course_id = (
        SELECT course_id 
        FROM Course 
        WHERE name = :courseName
    )
""")
    fun countStudentsByCourseName(courseName: String): Flow<Int>
}

@Dao
interface MoneyDao {

    // Insertar dinero a través de un Teacher
    @Insert
    suspend fun insertMoneyByTeacher(money: Money)

    // Insertar dinero a través de un Guardian
    @Insert
    suspend fun insertMoneyByGuardian(money: Money)

    // Obtener todos los registros de transacciones por student_id
    @Query("SELECT * FROM money WHERE student_id = :studentId")
    fun getAllMoneyForStudent(studentId: String): Flow<List<Money>>

    // Obtener el balance total de un estudiante (suma de todas las transacciones)
    @Query("SELECT SUM(amount) FROM money WHERE student_id = :studentId")
    fun getTotalMoneyForStudent(studentId: String): Flow<Double?>

    // Bloquear el dinero del estudiante (cambiar el estado de is_locked)
    @Query("UPDATE money SET is_locked = 1 WHERE student_id = :studentId")
    suspend fun lockMoneyForStudent(studentId: String)

    // Desbloquear el dinero del estudiante (cambiar el estado de is_locked)
    @Query("UPDATE money SET is_locked = 0 WHERE student_id = :studentId")
    suspend fun unlockMoneyForStudent(studentId: String)
}
