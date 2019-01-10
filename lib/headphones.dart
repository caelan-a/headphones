import 'dart:async';

import 'package:flutter/services.dart';

class Headphones {
  static const MethodChannel _channel =
      const MethodChannel('github.caelana.headphones');

  static Future<String> get isUsingHeadphones async {
    final String version = await _channel.invokeMethod('isUsingHeadphones');
    return version;
  }
}
