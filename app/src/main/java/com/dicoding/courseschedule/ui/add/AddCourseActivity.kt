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
import java.text.SimpleDateFormat
import java.util.*

class AddCourseActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    private var startTimeCourse: Long = System.currentTimeMillis()
    private var endTimeCourse: Long = System.currentTimeMillis()

    private lateinit var addCourseViewModel: AddCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        supportActionBar?.title = getString(R.string.add_course)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_insert -> {
                val addCourseName = findViewById<EditText>(R.id.add_course).text.toString()
                val addLecture = findViewById<EditText>(R.id.add_lecturer).text.toString()
                val addNote = findViewById<EditText>(R.id.add_note).text.toString()

                val addDaySpinner = findViewById<Spinner>(R.id.spinner_day)
                val addDayText = addDaySpinner.selectedItemPosition

                val factory = AddCourseViewModelFactory.createFactory(this)
                addCourseViewModel = ViewModelProvider(this, factory)[AddCourseViewModel::class.java]
                addCourseViewModel.insertCourse(addCourseName, addDayText, startTimeCourse.toString(), endTimeCourse.toString(), addLecture, addNote)
                addCourseViewModel.saved
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showTimePicker(view: View) {
        val dialogFragment = TimePickerFragment()
        dialogFragment.show(supportFragmentManager, "timePicker")
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(hour, minute)
        val startTime = SimpleDateFormat("HH:mm", Locale.getDefault())
        findViewById<TextView>(R.id.textTimeStart).text = startTime.format(calendar.time)
        startTimeCourse = calendar.timeInMillis

        val endTime = SimpleDateFormat("HH:mm", Locale.getDefault())
        findViewById<TextView>(R.id.textTimeEnd).text = endTime.format(calendar.time)
        endTimeCourse = calendar.timeInMillis

    }
}