import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import './add_place_screen.dart';
import './place_detail_screen.dart';
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
      body: FutureBuilder(
        future: Provider.of<GreatPlacesProvider>(context, listen: false)
            .fetchAndSetPlaces(),
        builder: (ctx, snapshot) => snapshot.connectionState ==
                ConnectionState.waiting
            ? Center(
                child: CircularProgressIndicator(),
              )
            : Consumer<GreatPlacesProvider>(
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
                            subtitle: Text(place.location.address),
                            onTap: () {
                              Navigator.of(context).pushNamed(
                                PlaceDetailScreen.routeName,
                                arguments: greatPlaces.items[i].id,
                              );
                            },
                          );
                        },
                      ),
              ),
      ),
    );
  }
}
