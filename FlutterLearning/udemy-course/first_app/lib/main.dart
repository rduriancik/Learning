import 'package:flutter/material.dart';
import './quiz.dart';
import './result.dart';

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
      'answers': [
        {'text': "Black", 'score': 10},
        {'text': "Red", 'score': 6},
        {'text': "Green", 'score': 3},
        {'text': "White", 'score': 1}
      ],
    },
    {
      'questionText': "What's your favorite animal?",
      'answers': [
        {'text': "Rabbit", 'score': 2},
        {'text': "Snake", 'score': 9},
        {'text': "Elephant", 'score': 4},
        {'text': "Lion", 'score': 6}
      ],
    },
    {
      'questionText': "Who's your favorite instructor?",
      'answers': [
        {'text': "Robert", 'score': 1},
        {'text': "Max", 'score': 4},
        {'text': "Steven", 'score': 6},
        {'text': "Josh", 'score': 5}
      ],
    }
  ];
  var _questionIndex = 0;
  var _totalScore = 0;

  void _resetQuiz() {
    setState(() {
      _questionIndex = 0;
      _totalScore = 0;
    });
  }

  void _answerQuestion(int score) {
    if (_questionIndex < _questions.length) {
      _totalScore += score;
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
        body: _questionIndex < _questions.length
            ? Quiz(
                questions: _questions,
                questionIndex: _questionIndex,
                answerQuestion: _answerQuestion,
              )
            : Result(_totalScore, _resetQuiz),
      ),
    );
  }
}
