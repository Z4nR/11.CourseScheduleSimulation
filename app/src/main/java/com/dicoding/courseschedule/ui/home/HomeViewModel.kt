package com.dicoding.courseschedule.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.courseschedule.data.Course
import com.dicoding.courseschedule.data.DataRepository
import com.dicoding.courseschedule.util.QueryType

class HomeViewModel(private val repository: DataRepository): ViewModel() {

    private val _queryType = MutableLiveData<QueryType>()

    private lateinit var nearestCourse : LiveData<Course?>

    init {
        _queryType.value = QueryType.CURRENT_DAY
    }

    fun setQueryType(queryType: QueryType) {
        _queryType.value = queryType
    }

    fun setNearestCourse(queryType: QueryType){
        nearestCourse = repository.getNearestSchedule(queryType)
    }

    fun getNearetsCourse(): LiveData<Course?>{
        return nearestCourse
    }
}
