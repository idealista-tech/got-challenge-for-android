package es.npatarino.android.gotchallenge;


import android.app.Application;
import android.content.Context;

import es.npatarino.android.gotchallenge.injection.component.DaggerGoTComponent;
import es.npatarino.android.gotchallenge.injection.component.GoTComponent;
import es.npatarino.android.gotchallenge.injection.module.GoTModule;

public class GoTApplication extends Application{

    private GoTComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        initAppComponent();
    }

    private void initAppComponent() {
        component = DaggerGoTComponent.builder()
                .goTModule(new GoTModule(this))
                .build();
    }

    public GoTComponent getComponent() {
        return component;
    }

    public static GoTApplication get(Context context) {
        return (GoTApplication) context.getApplicationContext();
    }
}
