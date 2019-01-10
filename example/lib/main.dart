import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:headphones/headphones.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String headphonesFlag = "Unknown";

  @override
  void initState() {
    super.initState();
  }

  void usingHeadphones() async {
    headphonesFlag = await Headphones.isUsingHeadphones;
    setState(() {
          
        });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(children: <Widget>[
            Text('Running on: $headphonesFlag\n'),
            FloatingActionButton(onPressed: usingHeadphones,)
          ]),
        ),
      ),
    );
  }
}
