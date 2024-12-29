package com.gaurav.barcode_scanner_with_native_functionality

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class CustomCaptureActivity : CaptureActivity() {

    private var barcodeView: DecoratedBarcodeView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_scanner_overlay)


        try {
            barcodeView = findViewById(R.id.zxing_barcode_scanner)
            if (barcodeView == null) {
                Toast.makeText(this, "Failed to find barcode view", Toast.LENGTH_LONG).show()
            } else {
                Handler(Looper.getMainLooper()).postDelayed({
                    barcodeView?.setStatusText("")
                    barcodeView?.resume()
                }, 1000)
            }
        } catch (e: Exception) {
            Log.e("CustomCaptureActivity", "Error initializing barcode scanner: ${e.message}", e)
        }
    }

    override fun onResume() {
        super.onResume()
        barcodeView?.resume()
        Log.d("CustomCaptureActivity", "onResume: barcodeView resumed")
    }

    override fun onPause() {
        super.onPause()
        barcodeView?.pause()
        Log.d("CustomCaptureActivity", "onPause: barcodeView paused")
    }
}
