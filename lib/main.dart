import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: BarcodeScannerScreen(),
    );
  }
}

class BarcodeScannerScreen extends StatefulWidget {
  const BarcodeScannerScreen({super.key});

  @override
  _BarcodeScannerScreenState createState() => _BarcodeScannerScreenState();
}

class _BarcodeScannerScreenState extends State<BarcodeScannerScreen> {
  static const platform = MethodChannel('com.gaurav.barcode/messages');
  String _barcodeResult = "No barcode scanned";

  Future<void> startBarcodeScanner() async {
    try {
      final result = await platform.invokeMethod('startBarcodeScanner');
      setState(() {
        _barcodeResult = result ?? "No barcode scanned";
      });
    } on PlatformException catch (e) {
      setState(() {
        _barcodeResult = "Failed to scan barcode: ${e.message}";
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Barcode Scanner')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(_barcodeResult, textAlign: TextAlign.center, style: TextStyle(fontSize: 18)),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: startBarcodeScanner,
              child: const Text('Start Barcode Scanner'),
            ),
          ],
        ),
      ),
    );
  }
}
