import 'package:flutter/material.dart';
import './question.dart';
import './answer.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _MyAppState();
  }
}

class _MyAppState extends State<MyApp> {
  final _questions = const [
    {
      'questionText': "What's your favorite color?",
      'answers': ["Black", "Red", "Green", "White"],
    },
    {
      'questionText': "What's your favorite animal?",
      'answers': ["Rabbit", "Snake", "Elephant", "Lion"],
    },
    {
      'questionText': "Who's your favorite instructor?",
      'answers': ["Robert", "Max", "Steven", "Josh"],
    }
  ];
  var _questionIndex = 0;

  void _answerQuestion() {
    if (_questionIndex < _questions.length) {
      setState(() {
        _questionIndex += 1;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("My first App"),
        ),
        body: _questionIndex < _questions.length ? Column(
          children: [
            Question(_questions[_questionIndex]['questionText']),
            ...(_questions[_questionIndex]['answers'] as List<String>)
                .map((answer) => Answer(_answerQuestion, answer))
                .toList()
          ],
        ) : Center(child: Text('You did it!'),),
      ),
    );
  }
}
