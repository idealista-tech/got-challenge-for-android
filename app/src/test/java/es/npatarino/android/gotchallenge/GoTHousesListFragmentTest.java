package es.npatarino.android.gotchallenge;

import android.support.v4.app.Fragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import es.npatarino.android.gotchallenge.view.fragment.GoTHousesListFragment;

import static es.npatarino.android.gotchallenge.support.FragmentUtil.startFragment;
import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class GoTHousesListFragmentTest {

    Fragment fragment;

    @Before
    public void setUp() throws Exception {
        fragment = new GoTHousesListFragment();
    }

    @Test
    public void testStartFragmentView() {
        startFragment(fragment);
        assertNotNull(fragment);
        assertNotNull(fragment.getView());
        assertNotNull(fragment.getActivity());
    }

    @Test
    public void testStartFragmentUniqueView() {
        startFragment(fragment);
        assertNotNull(fragment.getView().findViewById(R.id.recyclerView));
        assertNotNull(fragment.getView().findViewById(R.id.progressBar));
    }

    @Test
    public void testProgressVisibleWhenLoad() throws Exception {

    }
}