import 'package:flutter/material.dart';
import 'package:plant_app_clone/constants.dart';
import 'package:plant_app_clone/screens/home/home_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(
        appBarTheme: const AppBarTheme(
          backgroundColor: kPrimaryColor
        ),
        textTheme: Theme.of(context).textTheme.apply(bodyColor: kTextColor)
      ),
      home: const HomeScreen(),
    );
  }
}


