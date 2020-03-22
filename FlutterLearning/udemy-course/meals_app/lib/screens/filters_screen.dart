import 'package:flutter/material.dart';

import '../widgets/main_drawer.dart';

class FiltersScreen extends StatefulWidget {
  static const routeName = '/filters';

  final Function saveFilters;
  final Map<String, bool> currentFilters;

  FiltersScreen(this.currentFilters, this.saveFilters);

  @override
  _FiltersScreenState createState() => _FiltersScreenState();
}

class _FiltersScreenState extends State<FiltersScreen> {
  bool _isGlutenFree = false;
  bool _isVegetarian = false;
  bool _isVegan = false;
  bool _isLactoseFree = false;

  @override
  initState() {
    super.initState();
    _isGlutenFree = widget.currentFilters['gluten'];
    _isLactoseFree = widget.currentFilters['lactose'];
    _isVegetarian = widget.currentFilters['vegetarian'];
    _isVegan = widget.currentFilters['vegan'];
  }

  Widget _buildSwitchListTile(String title, String description,
      bool currentValue, Function updateValue) {
    return SwitchListTile(
      title: Text(title),
      subtitle: Text(description),
      value: currentValue,
      onChanged: updateValue,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Your filters'),
        actions: <Widget>[
          IconButton(
            icon: Icon(Icons.save),
            onPressed: () {
              Map<String, bool> selectedFilters = {
                'gluten': _isGlutenFree,
                'lactose': _isLactoseFree,
                'vegan': _isVegan,
                'vegetarian': _isVegetarian,
              };
              widget.saveFilters(selectedFilters);
            },
          )
        ],
      ),
      drawer: MainDrawer(),
      body: Column(
        children: <Widget>[
          Container(
            padding: const EdgeInsets.all(20),
            child: Text(
              "Adjust your meal seelction.",
              style: Theme.of(context).textTheme.title,
            ),
          ),
          Expanded(
            child: ListView(
              children: <Widget>[
                _buildSwitchListTile(
                  "Gluten-free",
                  "Only include gluten-free meals.",
                  _isGlutenFree,
                  (newValue) {
                    setState(() {
                      _isGlutenFree = newValue;
                    });
                  },
                ),
                _buildSwitchListTile(
                  "Lactose-free",
                  "Only include lactose-free meals.",
                  _isLactoseFree,
                  (newValue) {
                    setState(() {
                      _isLactoseFree = newValue;
                    });
                  },
                ),
                _buildSwitchListTile(
                  "Vegetarian",
                  "Only include vegetarian meals.",
                  _isVegetarian,
                  (newValue) {
                    setState(() {
                      _isVegetarian = newValue;
                    });
                  },
                ),
                _buildSwitchListTile(
                  "Vegan",
                  "Only include vegan meals.",
                  _isVegan,
                  (newValue) {
                    setState(() {
                      _isVegan = newValue;
                    });
                  },
                )
              ],
            ),
          )
        ],
      ),
    );
  }
}
