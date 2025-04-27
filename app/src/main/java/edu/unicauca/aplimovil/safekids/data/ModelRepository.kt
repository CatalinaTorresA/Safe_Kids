package edu.unicauca.aplimovil.safekids.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface ItemsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: Item)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: Item)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: Item)
}

// Needed repositories from here on

interface TeachersRepository {
    fun getAllTeachersStream(): Flow<List<Teacher>>
    fun getTeacherStream(id: String): Flow<Teacher?>
    fun getTeacherByName(name: String): Flow<Teacher?>
    suspend fun insertTeacher(teacher: Teacher)
    suspend fun deleteTeacher(teacher: Teacher)
    suspend fun updateTeacher(teacher: Teacher)
}

interface CoursesRepository {
    fun getAllCoursesStream(): Flow<List<Course>>
    fun getCourseStream(id: Int): Flow<Course?>
    suspend fun insertCourse(course: Course)
    suspend fun deleteCourse(course: Course)
    suspend fun updateCourse(course: Course)
}

interface GuardiansRepository {
    fun getAllGuardiansStream(): Flow<List<Guardian>>
    fun getGuardianStream(id: String): Flow<Guardian?>
    fun getGuardianByName(name: String): Flow<Guardian?>
    suspend fun insertGuardian(guardian: Guardian)
    suspend fun deleteGuardian(guardian: Guardian)
    suspend fun updateGuardian(guardian: Guardian)
}

interface StudentsRepository {
    fun getAllStudentsStream(): Flow<List<Student>>
    fun getStudentStream(id: String): Flow<Student?>
    suspend fun insertStudent(student: Student)
    suspend fun deleteStudent(student: Student)
    suspend fun updateStudent(student: Student)
}

interface StudentGuardiansRepository {
    fun getGuardiansOfStudent(studentId: String): Flow<List<StudentGuardian>>
    fun getStudentsOfGuardian(guardianId: String): Flow<List<Student>>
    suspend fun insertStudentGuardian(relation: StudentGuardian)
    suspend fun deleteStudentGuardian(relation: StudentGuardian)
}

interface StudentCoursesRepository {
    fun getCoursesOfStudent(studentId: String): Flow<List<StudentCourse>>
    fun getStudentsOfCourse(courseId: Int): Flow<List<StudentCourse>>
    suspend fun insertStudentCourse(relation: StudentCourse)
    suspend fun deleteStudentCourse(relation: StudentCourse)
    fun getCourseNamesByTeacher(teacherId: String): Flow<List<String>>
    fun countStudentsByCourseName(courseName: String): Flow<Int>
}

interface MoneyRepository {
    fun getAllMoneyForStudent(studentId: String): Flow<List<Money>>
    fun getTotalMoneyForStudent(studentId: String): Flow<Double?>
    suspend fun insertMoneyByTeacher(money: Money)
    suspend fun insertMoneyByGuardian(money: Money)
    suspend fun lockMoneyForStudent(studentId: String)
    suspend fun unlockMoneyForStudent(studentId: String)
}


