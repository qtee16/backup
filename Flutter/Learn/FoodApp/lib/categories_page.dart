import 'package:flutter/material.dart';
import 'package:food_app/category_item.dart';
import 'package:food_app/fake_data.dart';

class CategoriesPage extends StatelessWidget {
  static const String routeName = "/CategoriesPage";

  const CategoriesPage({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return GridView(
      padding: EdgeInsets.all(10),
        children: FAKE_CATEGORIES.map((eachCategory) => CategoryItem(eachCategory)).toList(),
        gridDelegate: const SliverGridDelegateWithMaxCrossAxisExtent(
            maxCrossAxisExtent: 300,
            childAspectRatio: 3/2,
            crossAxisSpacing: 10,
            mainAxisSpacing: 10
        )
    );
  }
  
}