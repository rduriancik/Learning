import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;

class Product with ChangeNotifier {
  final String id;
  final String title;
  final String description;
  final double price;
  final String imageUrl;
  bool isFavorite;

  Product({
    @required this.id,
    @required this.title,
    @required this.description,
    @required this.price,
    @required this.imageUrl,
    this.isFavorite = false,
  });

  void _setFavoriteVal(bool newValue) {
    isFavorite = newValue;
    notifyListeners();
  }

  Future<void> toggleFavoriteStatus(String authToken, String userId) async {
    _setFavoriteVal(!isFavorite);
    final url =
        'https://shop-flutter-ad563.firebaseio.com/users/$userId/favorites/$id.json?auth=$authToken';
    try {
      final response = await http.put(
        url,
        body: json.encode(isFavorite),
      );
      if (response.statusCode >= 400) {
        _setFavoriteVal(!isFavorite);
        // throw HttpException('Updating of isFavorite wasn\'t successful.');
      }
    } catch (error) {
      _setFavoriteVal(!isFavorite);
      print(error);
      // throw error;
    }
  }
}
