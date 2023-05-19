package fitness.test.kit.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fitness.test.kit.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }
}