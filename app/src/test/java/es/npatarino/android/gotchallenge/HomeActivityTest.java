package es.npatarino.android.gotchallenge;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import es.npatarino.android.gotchallenge.view.activity.HomeActivity;

import static es.npatarino.android.gotchallenge.support.ResourceLocator.getString;
import static es.npatarino.android.gotchallenge.support.ViewLocator.getTabLayout;
import static es.npatarino.android.gotchallenge.support.ViewLocator.getToolbar;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class HomeActivityTest {
    HomeActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(HomeActivity.class);
    }

    @Test
    public void testNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void testTitleIsVisible() throws Exception {

        Toolbar toolbar = getToolbar(activity, R.id.toolbar);
        assertNotNull(toolbar);
        assertThat(toolbar.getTitle().toString(),
                equalTo(getString(R.string.title_activity_home)));
    }

    @Test
    public void testHaveTwoTabs() throws Exception {
        TabLayout tabLayout = getTabLayout(activity, R.id.tabs);
        assertNotNull(tabLayout);
        assertEquals(tabLayout.getTabCount(), 2);
        assertThat(tabLayout.getTabAt(0).getText(),
                equalTo(getString(R.string.characters)));
        assertThat(tabLayout.getTabAt(1).getText(),
                equalTo(getString(R.string.houses)));
    }

    @Test
    public void testTabCharactersVisibleByDefault() throws Exception {
        TabLayout tabLayout = getTabLayout(activity, R.id.tabs);
        assertNotNull(tabLayout);
        assertThat(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getText(),
                equalTo(getString(R.string.characters)));
    }


}