package com.edmonbl.cuartitocapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.edmonbl.cuartitocapp.Student
import com.edmonbl.cuartitocapp.students
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StudentViewModel : ViewModel() {

    private val _students = MutableStateFlow(students)
    val studentsUiState: StateFlow<List<Student>> = _students.asStateFlow()

    fun addStudent(student: Student) {
        _students.update { list ->
            list + student
        }
    }

    fun deleteStudent(student: Student) {
        _students.update { list ->
            list - student
        }
    }
}
