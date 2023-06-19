package com.example.mycamera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mycamera.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    val REQUEST_PREVIEW = 1
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result?.resultCode == Activity.RESULT_OK) {
                    result.data?.let {
                        val imageBitmap = it.extras?.get("data") as Bitmap
                        binding.imageView.setImageBitmap(imageBitmap)
                    }
                }

            }

        binding.radioGroup.setOnCheckedChangeListener { group, checkId ->
            when (checkId) {
                R.id.preview ->
                    binding.cameraButton.text = binding.preview.text
                R.id.takePicture ->
                    binding.cameraButton.text = binding.takePicture.text
            }
        }

        binding.cameraButton.setOnClickListener {
            when (binding.radioGroup.checkedRadioButtonId) {
                R.id.preview -> {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    resultLauncher.launch(intent)
                }
                R.id.takePicture -> takePicture()
            }
        }




    }

    private fun takePicture() { }

}