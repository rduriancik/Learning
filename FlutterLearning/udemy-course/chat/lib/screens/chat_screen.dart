import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

class ChatScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Flutter Chat'),
        actions: <Widget>[
          DropdownButton(
            icon: Icon(
              Icons.more_vert,
              color: Theme.of(context).primaryIconTheme.color,
            ),
            items: [
              DropdownMenuItem(
                value: 'logout',
                child: Container(
                  child: Row(
                    children: <Widget>[
                      const Icon(Icons.exit_to_app),
                      const SizedBox(
                        width: 8,
                      ),
                      const Text('Log out')
                    ],
                  ),
                ),
              )
            ],
            onChanged: (itemId) {
              if (itemId == 'logout') {
                FirebaseAuth.instance.signOut();
              }
            },
          )
        ],
      ),
      body: StreamBuilder(
        stream: Firestore.instance
            .collection('chats/yxnDGVy4lr028196JIaF/messages')
            .snapshots(),
        builder: (ctx, streamSnapshot) {
          if (streamSnapshot.connectionState == ConnectionState.waiting) {
            return Center(
              child: CircularProgressIndicator(),
            );
          }
          final documents = streamSnapshot.data.documents;
          return ListView.builder(
              itemCount: documents.length,
              itemBuilder: (ctx, index) {
                return Container(
                  padding: const EdgeInsets.all(8),
                  child: Text(documents[index]['text']),
                );
              });
        },
      ),
      floatingActionButton: FloatingActionButton(
        child: const Icon(Icons.add),
        onPressed: () {
          Firestore.instance
              .collection('chats/yxnDGVy4lr028196JIaF/messages')
              .add({'text': 'This was added by clicking the button!'});
        },
      ),
    );
  }
}
