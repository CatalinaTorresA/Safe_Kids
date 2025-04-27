package edu.unicauca.aplimovil.safekids.data

import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    override suspend fun insertItem(item: Item) = itemDao.insert(item)

    override suspend fun deleteItem(item: Item) = itemDao.delete(item)

    override suspend fun updateItem(item: Item) = itemDao.update(item)
}

class OfflineTeachersRepository(private val teacherDao: TeacherDao) : TeachersRepository {
    override fun getAllTeachersStream(): Flow<List<Teacher>> = teacherDao.getAllTeachers()
    override fun getTeacherStream(id: String): Flow<Teacher?> = teacherDao.getTeacher(id)
    override fun getTeacherByName(name: String): Flow<Teacher?> = teacherDao.getTeacherByName(name)
    override suspend fun insertTeacher(teacher: Teacher) = teacherDao.insert(teacher)
    override suspend fun deleteTeacher(teacher: Teacher) = teacherDao.delete(teacher)
    override suspend fun updateTeacher(teacher: Teacher) = teacherDao.update(teacher)
}

class OfflineCoursesRepository(private val courseDao: CourseDao) : CoursesRepository {
    override fun getAllCoursesStream(): Flow<List<Course>> = courseDao.getAllCourses()
    override fun getCourseStream(id: Int): Flow<Course?> = courseDao.getCourse(id)
    override suspend fun insertCourse(course: Course) = courseDao.insert(course)
    override suspend fun deleteCourse(course: Course) = courseDao.delete(course)
    override suspend fun updateCourse(course: Course) = courseDao.update(course)
}

class OfflineGuardiansRepository(private val guardianDao: GuardianDao) : GuardiansRepository {
    override fun getAllGuardiansStream(): Flow<List<Guardian>> = guardianDao.getAllGuardians()
    override fun getGuardianStream(id: String): Flow<Guardian?> = guardianDao.getGuardian(id)
    override fun getGuardianByName(name: String): Flow<Guardian?> = guardianDao.getGuardianByName(name)
    override suspend fun insertGuardian(guardian: Guardian) = guardianDao.insert(guardian)
    override suspend fun deleteGuardian(guardian: Guardian) = guardianDao.delete(guardian)
    override suspend fun updateGuardian(guardian: Guardian) = guardianDao.update(guardian)
}

class OfflineStudentsRepository(private val studentDao: StudentDao) : StudentsRepository {
    override fun getAllStudentsStream(): Flow<List<Student>> = studentDao.getAllStudents()
    override fun getStudentStream(id: String): Flow<Student?> = studentDao.getStudent(id)
    override suspend fun insertStudent(student: Student) = studentDao.insert(student)
    override suspend fun deleteStudent(student: Student) = studentDao.delete(student)
    override suspend fun updateStudent(student: Student) = studentDao.update(student)
}

class OfflineStudentGuardiansRepository(private val studentGuardianDao: StudentGuardianDao) : StudentGuardiansRepository {
    override fun getGuardiansOfStudent(studentId: String): Flow<List<StudentGuardian>> =
        studentGuardianDao.getGuardiansOfStudent(studentId)
    override fun getStudentsOfGuardian(guardianId: String): Flow<List<Student>> =
        studentGuardianDao.getStudentsOfGuardian(guardianId)
    override suspend fun insertStudentGuardian(relation: StudentGuardian) = studentGuardianDao.insert(relation)
    override suspend fun deleteStudentGuardian(relation: StudentGuardian) = studentGuardianDao.delete(relation)
}

class OfflineStudentCoursesRepository(private val studentCourseDao: StudentCourseDao) : StudentCoursesRepository {
    override fun getCoursesOfStudent(studentId: String): Flow<List<StudentCourse>> =
        studentCourseDao.getCoursesOfStudent(studentId)
    override fun getStudentsOfCourse(courseId: Int): Flow<List<StudentCourse>> =
        studentCourseDao.getStudentsOfCourse(courseId)
    override suspend fun insertStudentCourse(relation: StudentCourse) = studentCourseDao.insert(relation)
    override suspend fun deleteStudentCourse(relation: StudentCourse) = studentCourseDao.delete(relation)
    override fun getCourseNamesByTeacher(teacherId: String): Flow<List<String>> =
        studentCourseDao.getCourseNamesByTeacher(teacherId)
    override fun countStudentsByCourseName(courseName: String): Flow<Int> =
        studentCourseDao.countStudentsByCourseName(courseName)
}

class OfflineMoneyRepository(private val moneyDao: MoneyDao) : MoneyRepository {

    // Obtener todas las transacciones de dinero de un estudiante
    override fun getAllMoneyForStudent(studentId: String): Flow<List<Money>> =
        moneyDao.getAllMoneyForStudent(studentId)

    // Obtener el balance total de un estudiante
    override fun getTotalMoneyForStudent(studentId: String): Flow<Double?> =
        moneyDao.getTotalMoneyForStudent(studentId)

    // Insertar dinero a través de un Teacher
    override suspend fun insertMoneyByTeacher(money: Money) =
        moneyDao.insertMoneyByTeacher(money)

    // Insertar dinero a través de un Guardian
    override suspend fun insertMoneyByGuardian(money: Money) =
        moneyDao.insertMoneyByGuardian(money)

    // Bloquear el dinero del estudiante
    override suspend fun lockMoneyForStudent(studentId: String) =
        moneyDao.lockMoneyForStudent(studentId)

    // Desbloquear el dinero del estudiante
    override suspend fun unlockMoneyForStudent(studentId: String) =
        moneyDao.unlockMoneyForStudent(studentId)
}

