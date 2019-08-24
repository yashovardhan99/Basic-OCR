package com.yashovardhan99.basicocr

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.firebase.FirebaseApp
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata

/**
 * Created by Yashovardhan99 on 24/8/19 as a part of Basic OCR.
 */
class MyImageAnalyzer(private val activity: MainActivity) : ImageAnalysis.Analyzer {
    init {
        FirebaseApp.initializeApp(activity)
    }

    private fun degreesToFirebaseRotation(degrees: Int) = when (degrees) {
        0 -> FirebaseVisionImageMetadata.ROTATION_0
        90 -> FirebaseVisionImageMetadata.ROTATION_90
        180 -> FirebaseVisionImageMetadata.ROTATION_180
        270 -> FirebaseVisionImageMetadata.ROTATION_270
        else -> throw Exception("Rotation must be 0, 90, 180 or 270.")
    }

    override fun analyze(imageProxy: ImageProxy?, rotationDegrees: Int) {
        val mediaImage = imageProxy?.image
        val imageRotation = degreesToFirebaseRotation(rotationDegrees)
        if (mediaImage != null) {
            val image = FirebaseVisionImage.fromMediaImage(mediaImage, imageRotation)
            activity.recognizeText(image)
        }
    }
}