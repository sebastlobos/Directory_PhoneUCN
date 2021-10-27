package cl.ucn.disc.dsm.slobos;

import android.app.Application;
import android.content.Context;

import org.acra.ACRA;
import org.acra.BuildConfig;
import org.acra.annotation.AcraCore;

/**
 * Main App
 * @author Sebastian Lobos
 */
@AcraCore(buildConfigClass =BuildConfig.class)
public class App extends Application {
    /**
     *
     * @param base context to use.
     */

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // The following line triggers the initialization of ACRA
        ACRA.init(this);
    }
}
