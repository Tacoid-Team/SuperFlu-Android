package com.tacoid.superflu;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;

public class SuperFluAndroid extends AndroidApplication
{	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(SuperFlu.getInstance(), false);
    }
}
