import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/great_places_provider.dart';
import '../screens/places_list_screen.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider.value(
      value: GreatPlacesProvider(),
      child: MaterialApp(
        title: 'Great Places',
        theme: ThemeData(
          primarySwatch: Colors.green,
          accentColor: Colors.yellow,
          visualDensity: VisualDensity.adaptivePlatformDensity,
        ),
        home: PlacesListScreen(),
      ),
    );
  }
}
