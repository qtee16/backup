import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:food_app/foods_page.dart';
import 'package:food_app/models/category.dart';

class CategoryItem extends StatelessWidget {
  Category category;

  CategoryItem(this.category);

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return InkWell(
      onTap: () {
        Navigator.pushNamed(
            context,
            FoodsPage.routeName,
            arguments: CategoryArguments(category)
        );
      },
      child: Container(
          decoration: BoxDecoration(
              color: this.category.color,
              borderRadius: BorderRadius.circular(10)
          ),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Container(
                padding: const EdgeInsets.all(10),
                child: Text(
                    this.category.content,
                    style: const TextStyle(fontSize:30, fontWeight: FontWeight.bold, color: Colors.white, fontFamily: 'Inspiration'),
                    textAlign: TextAlign.center
                ),
              )
            ],
          )
      ),
    );
  }

}

class CategoryArguments {
  final Category category;
  CategoryArguments(this.category);
}