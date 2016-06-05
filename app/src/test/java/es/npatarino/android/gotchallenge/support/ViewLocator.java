package es.npatarino.android.gotchallenge.support;


import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

public class ViewLocator {

    public static TextView getTextView(Activity activity, int viewId ) {
        return (TextView) activity.findViewById( viewId );
    }

    public static EditText getEditText( Activity activity, int viewId ) {
        return (EditText) activity.findViewById( viewId );
    }

    public static TabLayout getTabLayout(Activity activity, int viewId ) {
        return (TabLayout) activity.findViewById( viewId );
    }

    public static Toolbar getToolbar(Activity activity, int viewId ) {
        return (Toolbar) activity.findViewById( viewId );
    }

    public static RecyclerView getRecyclerView(Fragment fragment, int viewId ) {
        return (RecyclerView) fragment.getView().findViewById( viewId );
    }
}
