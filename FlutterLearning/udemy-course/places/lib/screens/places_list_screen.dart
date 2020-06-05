import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import './add_place_screen.dart';
import '../providers/great_places_provider.dart';

class PlacesListScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Your Places'),
        actions: <Widget>[
          IconButton(
            icon: Icon(Icons.add),
            onPressed: () {
              Navigator.of(context).pushNamed(AddPlaceScreen.routeName);
            },
          )
        ],
      ),
      body: Consumer<GreatPlacesProvider>(
        child: Center(
          child: const Text("Got no places yet, start adding some!"),
        ),
        builder: (ctx, greatPlaces, ch) => greatPlaces.items.length <= 0
            ? ch
            : ListView.builder(
                itemCount: greatPlaces.items.length,
                itemBuilder: (ctx, i) {
                  final place = greatPlaces.items[i];
                  print(place);
                  return ListTile(
                    leading: CircleAvatar(
                      backgroundImage: FileImage(place.image),
                    ),
                    title: Text(place.title),
                    onTap: () {
                      // todo
                    },
                  );
                },
              ),
      ),
    );
  }
}
