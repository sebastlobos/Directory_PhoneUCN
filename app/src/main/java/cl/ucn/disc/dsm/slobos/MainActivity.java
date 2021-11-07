package cl.ucn.disc.dsm.slobos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
/**
 * @author Sebastian Lobos Aravena
 */
public class MainActivity extends AppCompatActivity {
    /**
     *
     * @param savedInstanceState context to use.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        throw new IllegalArgumentException("Error en la aplicacion!!");
    }


}