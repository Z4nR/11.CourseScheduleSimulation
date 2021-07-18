package com.dicoding.courseschedule.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.dicoding.courseschedule.MyApplication
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.util.NightMode

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        //TODO 10 : Update theme based on value in ListPreference
        val setTheme = findPreference<ListPreference>(getString(R.string.pref_key_dark))
        setTheme?.setOnPreferenceChangeListener { preferences, newValue ->
            when (newValue){
                R.string.pref_dark_auto -> updateTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                R.string.pref_dark_on -> updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
                R.string.pref_dark_off -> updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
            }
            true
        }

        //TODO 11 : Schedule and cancel notification in DailyReminder based on SwitchPreference
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}