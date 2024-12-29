import UIKit
import Flutter
import AVFoundation

@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate {
    private let channelName = "com.gaurav.barcode/messages"

    override func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        let controller = window?.rootViewController as! FlutterViewController
        let methodChannel = FlutterMethodChannel(name: channelName, binaryMessenger: controller.binaryMessenger)

        methodChannel.setMethodCallHandler { (call, result) in
            if call.method == "startBarcodeScanner" {
                self.startBarcodeScanner(result: result)
            } else {
                result(FlutterMethodNotImplemented)
            }
        }

        return super.application(application, didFinishLaunchingWithOptions: launchOptions)
    }

    private func startBarcodeScanner(result: @escaping FlutterResult) {
        // Integrate AVFoundation or any barcode scanning library for iOS here.
        // For brevity, just send a dummy barcode:
        result("Dummy barcode: 1234567890")
    }
}
