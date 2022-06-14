import 'dart:io';

import 'package:flutter/material.dart';
import 'package:food_app/categories_page.dart';
import 'package:food_app/detail_food_page.dart';
import 'package:food_app/foods_page.dart';

void main() {
  HttpOverrides.global = MyHttpOverrides();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Food App with navigation",
      initialRoute: '/',
      routes: {
        DetailFoodPage.routeName: (context) => DetailFoodPage(),
        FoodsPage.routeName: (context) => FoodsPage(),
        CategoriesPage.routeName: (context) => CategoriesPage()
      },
      theme: ThemeData(
        primarySwatch: Colors.blue
      ),
      home: SafeArea(
        child: Scaffold(
          appBar: AppBar(
            title: Text("Food App"),
            centerTitle: true,
          ),
          body: Center(
              child: CategoriesPage()
          ),
        ),
      )
    );
  }
  
}

class MyHttpOverrides extends HttpOverrides{
  @override
  HttpClient createHttpClient(SecurityContext? context){
    return super.createHttpClient(context)
      ..badCertificateCallback = (X509Certificate cert, String host, int port)=> true;
  }
}