import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;

import './cart.dart';

class OrderItem {
  final String id;
  final double amount;
  final List<CartItem> products;
  final DateTime dateTime;

  OrderItem({
    @required this.id,
    @required this.amount,
    @required this.products,
    @required this.dateTime,
  });
}

class Orders with ChangeNotifier {
  List<OrderItem> _orders = [];

  List<OrderItem> get orders {
    return [..._orders];
  }

  Future<void> fetchAndSetupOrders() async {
    const url = 'https://shop-flutter-ad563.firebaseio.com/orders.json';
    try {
      final response = await http.get(url);
    } catch (error) {
      print(error);
      throw error;
    }
  }

  Future<void> addOrder(List<CartItem> cartProducts, double total) async {
    const url = 'https://shop-flutter-ad563.firebaseio.com/orders.json';
    try {
      final dateTime = DateTime.now();
      final response = await http.post(
        url,
        body: json.encode(
          {
            'amount': total,
            'products': cartProducts
                .map((cartProd) => {
                      'id': cartProd.id,
                      'title': cartProd.title,
                      'quantity': cartProd.quantity,
                      'price': cartProd.price
                    })
                .toList(),
            'dateTime': dateTime.toIso8601String()
          },
        ),
      );
      _orders.insert(
          0,
          OrderItem(
            id: json.decode(response.body)['name'],
            amount: total,
            dateTime: dateTime,
            products: cartProducts,
          ));
      notifyListeners();
    } catch (error) {
      print(error);
      throw error;
    }
  }
}
