import 'package:flutter/material.dart';

import './text.dart';
import './text_controler.dart';

class App extends StatefulWidget {
  @override
  _AppState createState() => _AppState();
}

class _AppState extends State<App> {
  final _texts = const [
    "First Assignment Not Started",
    "First Assignment Review",
    "First Assignment In Progress",
    "First Assignment Test",
    "First Assignment Done"
  ];
  var _textIndex = 0;

  void updateTextIndex() {
    setState(() {
      _textIndex = (_textIndex + 1) % _texts.length;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("Assignment 1"),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              MyText(_texts[_textIndex]),
              TextController(updateTextIndex),
            ],
          ),
        ),
      ),
    );
  }
}
