import 'package:flutter/material.dart';

class Transaction {
  String content;
  double money;
  DateTime? dateTime;

  Transaction({required this.content, required this.money, this.dateTime});

  @override
  String toString() {
    // TODO: implement toString
    return "content: $content, money: $money";
  }
}