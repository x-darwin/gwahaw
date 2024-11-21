package com.gaeio.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gaeio.app.R
import com.gaeio.app.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        binding.saveButton.setOnClickListener {
            val url = binding.urlInput.text.toString()
            if (url.isEmpty()) {
                showError(getString(R.string.error_empty_url))
                return@setOnClickListener
            }
            if (!url.startsWith("rtmp://")) {
                showError(getString(R.string.error_invalid_rtmp))
                return@setOnClickListener
            }
            viewModel.saveStreamUrl(url, binding.audioSwitch.isChecked)
        }

        // Load saved settings
        viewModel.loadSettings()
    }

    private fun observeViewModel() {
        viewModel.streamUrl.observe(this) { url ->
            binding.urlInput.setText(url)
        }

        viewModel.audioEnabled.observe(this) { enabled ->
            binding.audioSwitch.isChecked = enabled
        }

        viewModel.saveSuccess.observe(this) { success ->
            if (success) {
                showSuccess(getString(R.string.save_success))
            }
        }
    }

    private fun showError(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.error)
            .setMessage(message)
            .setPositiveButton(R.string.ok, null)
            .show()
    }

    private fun showSuccess(message: String) {
        MaterialAlertDialogBuilder(this)
            .setMessage(message)
            .setPositiveButton(R.string.ok, null)
            .show()
    }
}