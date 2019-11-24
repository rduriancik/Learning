import 'package:flutter/material.dart';

class TextController extends StatelessWidget {
  final Function clickHandler;

  TextController(this.clickHandler);

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.all(24),
      child: RaisedButton(
        textColor: Colors.white,
        color: Colors.red,
        child: Text("Click to change text"),
        onPressed: clickHandler,
      ),
    );
  }
}
