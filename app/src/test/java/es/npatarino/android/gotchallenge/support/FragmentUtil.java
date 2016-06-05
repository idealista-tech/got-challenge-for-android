package es.npatarino.android.gotchallenge.support;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import org.robolectric.Robolectric;

public class FragmentUtil {

    public static void startFragment(Fragment fragment) {
        AppCompatActivity activity = createActivity();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(fragment, null)
                .commit();
    }

    private static AppCompatActivity createActivity() {
        return Robolectric.buildActivity(AppCompatActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }

}
