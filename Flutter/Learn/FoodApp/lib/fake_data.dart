import 'package:flutter/material.dart';
import 'package:food_app/models/category.dart';
import 'package:food_app/models/food.dart';

 List<Category> FAKE_CATEGORIES = [
  Category(id: 1, content: "Bún chả Hà Nội", color: Colors.deepOrange),
  Category(id: 2, content: "Bún đậu mắm tôm", color: Colors.green),
  Category(id: 3, content: "Phở Hà Nội", color: Colors.blue),
];

var FAKE_FOODS = [
 Food(
     "Bún chả Hà Nội",
     "https://top10tphcm.com/wp-content/uploads/2021/01/Quan-bun-cha-ha-noi-o-TPHCM.jpg",
     Duration(minutes: 15),
     Complexity.medium,
     ["Bún", "Thịt nướng", "Chả nướng"],
     1
 ),
 Food(
     "Phở Hà Nội",
     "https://images.foody.vn/res/g69/684724/prof/s576x330/foody-mobile-hmb-jpg-151-636395999663051288.jpg",
     Duration(minutes: 15),
     Complexity.medium,
     ["Phở", "Thịt bò", "Hành mùi"],
     3
 ),
 Food(
     "Bún chả Hà Nội",
     "https://poliva.vn/wp-content/uploads/2019/07/bun-cha-ha-noi.jpg",
     Duration(minutes: 15),
     Complexity.medium,
     ["Bún", "Chả nướng"],
     1
 ),
 Food(
     "Bún đậu mắm tôm",
     "https://vcdn-giadinh.vnecdn.net/2021/01/15/bun-1-5687-1610696220.jpg",
     Duration(minutes: 15),
     Complexity.medium,
     ["Bún", "Đậu rán", "Nem rán", "Thịt luộc"],
     2
 ),
];