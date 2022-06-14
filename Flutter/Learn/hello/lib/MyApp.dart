import 'package:flutter/material.dart';

class MyApp extends StatefulWidget {

  String name;
  int age;

  MyApp(this.name, this.age);

  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return _MyAppState();
  }

}

class _MyAppState extends State<MyApp> with WidgetsBindingObserver{
  String _email = '';
  final emailEditingController = TextEditingController();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    WidgetsBinding.instance?.addObserver(this);
    print("Run initState");
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
    WidgetsBinding.instance?.removeObserver(this);
    emailEditingController.dispose();
    print("Run dispose");
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    // TODO: implement didChangeAppLifecycleState
    super.didChangeAppLifecycleState(state);
    if (state == AppLifecycleState.paused) {
      print("Background");
    } else if (state == AppLifecycleState.resumed) {
      print("Foreground");
    }
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    print("Run build");

    return MaterialApp(
      title: "This is my app",
      home: Scaffold(
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Container(
                padding: EdgeInsets.symmetric(horizontal: 20, vertical: 10),
                child: TextField(
                  controller: emailEditingController,
                  onChanged: (text) {
                    this.setState(() {
                      _email = text;
                    });
                  },
                  decoration: InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: "Enter your email"
                  ),
                ),
              ),
              Text(
                _email,
                style: TextStyle(fontSize: 30, color: Colors.red),
              ),
              Text(
                "This is StatefulWidget",
                style: TextStyle(fontSize: 30, color: Colors.blue),
              )
            ],
          ),
        )
      ),
    );
  }
  
}