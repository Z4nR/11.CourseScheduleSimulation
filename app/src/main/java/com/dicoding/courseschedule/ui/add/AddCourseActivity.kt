package com.dicoding.courseschedule.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.util.TimePickerFragment
import java.util.*

class AddCourseActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    private var startTimeCourse: Long = System.currentTimeMillis()
    private var endTimeCourse: Long = System.currentTimeMillis()

    private lateinit var addCourseViewModel: AddCourseViewModel

    private lateinit var courseName : EditText
    private lateinit var courseLecture : EditText
    private lateinit var courseNote : EditText
    private lateinit var addDaySpinner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        supportActionBar?.title = getString(R.string.add_course)

        courseName = findViewById(R.id.add_course)
        courseLecture = findViewById(R.id.add_lecturer)
        courseNote = findViewById(R.id.add_note)
        addDaySpinner = findViewById(R.id.spinner_day)

        val factory = AddCourseViewModelFactory.createFactory(this)
        addCourseViewModel = ViewModelProvider(this, factory)[AddCourseViewModel::class.java]
        addCourseViewModel.saved

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_insert -> {
                val addCourseName = courseName.text.toString()
                val addLecture = courseLecture.text.toString()
                val addNote = courseNote.text.toString()
                val addDayText = addDaySpinner.selectedItemPosition
                addCourseViewModel.insertCourse(addCourseName, addDayText, startTimeCourse.toString(), endTimeCourse.toString(), addLecture, addNote)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showTimePickerStart(view: View) {
        val dialogFragment = TimePickerFragment()
        dialogFragment.show(supportFragmentManager, "timeStartPicker")
    }

    fun showTimePickerEnd(view: View) {
        val dialogFragment = TimePickerFragment()
        dialogFragment.show(supportFragmentManager, "timeEndPicker")
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        val timeFormat = "$hour:$minute"

        when (tag) {
            "timeStartPicker" -> {
                findViewById<TextView>(R.id.textTimeStart).text = timeFormat
                startTimeCourse = calendar.timeInMillis
            }
            else -> {
                findViewById<TextView>(R.id.textTimeEnd).text = timeFormat
                endTimeCourse = calendar.timeInMillis
            }
        }
    }
}