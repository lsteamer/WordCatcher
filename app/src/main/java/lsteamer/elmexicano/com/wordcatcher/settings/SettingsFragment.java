package lsteamer.elmexicano.com.wordcatcher.settings;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import lsteamer.elmexicano.com.wordcatcher.R;

public class SettingsFragment extends PreferenceFragmentCompat{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.pref_wordcatcher);

    }
}
