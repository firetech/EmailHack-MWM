package org.metawatch.manager.emailhack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Main extends PreferenceActivity {
	public static final String TAG = "EmailHack-MWM";
	public static boolean log = true;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.layout.settings);

		EditTextPreference intervalPreference = (EditTextPreference)findPreference("alarmInterval");
		intervalPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				DatabaseService.setAlarm(Main.this, (String)newValue);
				return true;
			}
		});

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		log = prefs.getBoolean("log", log);

		CheckBoxPreference logPreference = (CheckBoxPreference)findPreference("log");
		logPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				log = (Boolean)newValue;
				return true;
			}
		});

		if (!DatabaseService.isRunning()) startService(new Intent(this, DatabaseService.class));
	}

}