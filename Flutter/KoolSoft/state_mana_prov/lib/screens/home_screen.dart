import 'package:flutter/material.dart';
import 'package:state_mana_prov/models/counter.dart';
import 'package:provider/provider.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final counter = Provider.of<Counter>(context);

    return Scaffold(
      appBar: AppBar(
        title: const Text("Counter"),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
          children: [
            Text('Value is: ${counter.counter}'),
            ElevatedButton(
              onPressed: () {
                counter.increment();
              },
              child: const Text("Increment"),
            ),
            ElevatedButton(
              onPressed: () {
                counter.decrement();
              },
              child: const Text("Decrement"),
            ),
          ],
        ),
      ),
    );
  }
}
