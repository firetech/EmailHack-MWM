package org.metawatch.manager.emailhack;

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class Main extends PreferenceActivity {
	public static final String TAG = "EmailHack-MWM";
	public static final boolean LOG = true;
	
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
		
		if (!DatabaseService.isRunning()) startService(new Intent(this, DatabaseService.class));
    }
    
}