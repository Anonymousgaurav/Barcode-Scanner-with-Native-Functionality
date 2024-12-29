package com.gaurav.barcode_scanner_with_native_functionality

import android.content.Intent
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class MainActivity : FlutterActivity() {
    private val CHANNEL = "com.gaurav.barcode/messages"
    private var resultCallback: MethodChannel.Result? = null

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "startBarcodeScanner") {
                resultCallback = result
                startCustomScanner()
            } else {
                result.notImplemented()
            }
        }
    }

    private fun startCustomScanner() {

        val integrator = IntentIntegrator(this)
        integrator.setCaptureActivity(CustomCaptureActivity::class.java)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Scan a barcode")
        integrator.setBeepEnabled(true)
        integrator.setOrientationLocked(false)
        integrator.initiateScan()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result.contents != null) {
            resultCallback?.success(result.contents)
        } else {
            resultCallback?.success("No barcode captured")
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
