
package edu.unicauca.aplimovil.safekids.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val itemsRepository: ItemsRepository
    val teachersRepository: TeachersRepository
    val coursesRepository: CoursesRepository
    val guardiansRepository: GuardiansRepository
    val studentsRepository: StudentsRepository
    val studentGuardiansRepository: StudentGuardiansRepository
    val studentCoursesRepository: StudentCoursesRepository
    val moneyRepository: MoneyRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }

    override val teachersRepository: TeachersRepository by lazy {
        OfflineTeachersRepository(InventoryDatabase.getDatabase(context).teacherDao())
    }

    override val coursesRepository: CoursesRepository by lazy {
        OfflineCoursesRepository(InventoryDatabase.getDatabase(context).courseDao())
    }

    override val guardiansRepository: GuardiansRepository by lazy {
        OfflineGuardiansRepository(InventoryDatabase.getDatabase(context).guardianDao())
    }

    override val studentsRepository: StudentsRepository by lazy {
        OfflineStudentsRepository(InventoryDatabase.getDatabase(context).studentDao())
    }

    override val studentGuardiansRepository: StudentGuardiansRepository by lazy {
        OfflineStudentGuardiansRepository(InventoryDatabase.getDatabase(context).studentGuardianDao())
    }

    override val studentCoursesRepository: StudentCoursesRepository by lazy {
        OfflineStudentCoursesRepository(InventoryDatabase.getDatabase(context).studentCourseDao())
    }

    override val moneyRepository: MoneyRepository by lazy {
        OfflineMoneyRepository(InventoryDatabase.getDatabase(context).moneyDao())
    }
}