import 'package:flutter/material.dart';

class ChatScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView.builder(
          itemCount: 10,
          itemBuilder: (ctx, index) {
            return Container(
              padding: const EdgeInsets.all(8),
              child: const Text('This works!'),
            );
          }),
    );
  }
}
