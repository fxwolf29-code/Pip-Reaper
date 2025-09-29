package com.pipreaper.app

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var statusView: TextView
    private lateinit var progressBar: ProgressBar

    private val pickFile = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { startFakeConversion(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusView = findViewById(R.id.status)
        progressBar = findViewById(R.id.progressBar)
        findViewById<Button>(R.id.btnPick).setOnClickListener {
            // pick any file type for demo
            pickFile.launch("*/*")
        }
        findViewById<Button>(R.id.btnBatch).setOnClickListener {
            Toast.makeText(this, "Batch mode demo: pick files in your file manager and share to app (not implemented)", Toast.LENGTH_LONG).show()
        }
        findViewById<Button>(R.id.btnSignOut).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun startFakeConversion(uri: Uri) {
        statusView.text = "Converting: ${'$'}{uri.lastPathSegment ?: uri}")
        progressBar.progress = 0
        // fake progress
        Thread {
            try {
                for (i in 1..10) {
                    Thread.sleep(250)
                    runOnUiThread { progressBar.progress = i*10 }
                }
                runOnUiThread {
                    statusView.text = "Conversion complete: output_saved_to_downloads"
                    Toast.makeText(this, "Conversion finished", Toast.LENGTH_SHORT).show()
                }
            } catch (e: InterruptedException) {}
        }.start()
    }
}
