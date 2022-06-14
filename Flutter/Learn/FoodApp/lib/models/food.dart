import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';

class Food {
  late int id;
  String name;
  String urlName;
  Duration duration;
  Complexity complexity;
  List<String> ingredients = List<String>.empty(growable: true);
  int categoryId;

  Food(
      this.name,
      this.urlName,
      this.duration,
      this.complexity,
      this.ingredients,
      this.categoryId
      ) {
    id = Random().nextInt(1000);
  }
}

enum Complexity {
  simple,
  medium,
  hard
}