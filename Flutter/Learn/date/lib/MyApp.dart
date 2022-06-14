import 'package:date/TransactionList.dart';
import 'package:date/transaction.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class MyApp extends StatefulWidget {

  @override
  State<StatefulWidget> createState() {
    return _MyAppState();
  }

}

class _MyAppState extends State<MyApp> {
  final GlobalKey<ScaffoldState> _scaffoldKey = new GlobalKey<ScaffoldState>();
  final _contentController = TextEditingController();
  final _moneyController = TextEditingController();

  // state
  Transaction _transaction = Transaction(content: "", money: 0.0);
  List<Transaction> _listTransaction = List<Transaction>.empty(growable: true);

  void insert() {
    if (_transaction.content.isEmpty || _transaction.money.isNaN || _transaction.money == 0.0) {
      return;
    }
    _listTransaction.add(_transaction);
    _transaction = Transaction(content: "", money: 0.0);
    _contentController.text = "";
    _moneyController.text = "";
  }

  void _onButtonShowModalSheet() {
    showModalBottomSheet(
        context: context,
        builder: (context) {
          return Container(
            padding: const EdgeInsets.all(10),
            child: Column(
              children: [
                TextField(
                  decoration: const InputDecoration(labelText: "Content"),
                  controller: _contentController,
                  onChanged: (text) {
                    setState(() {
                      _transaction.content = text;
                    });
                  },
                ),
                TextField(
                  decoration: const InputDecoration(labelText: "Money"),
                  controller: _moneyController,
                  onChanged: (text) {
                    setState(() {
                      _transaction.money = double.tryParse(text) ?? 0;
                    });
                  },
                ),
                const Padding(padding: EdgeInsets.all(10)),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  children: [
                    Expanded(
                        child: SizedBox(child: RaisedButton(
                            color: Colors.green,
                            child: const Text("Save", style: TextStyle(color: Colors.white),),
                            onPressed: () {
                              setState(() {
                                this.insert();
                              });
                              Navigator.of(context).pop();
                            }
                        ), height: 40)
                    ),
                    Padding(padding: EdgeInsets.all(10)),
                    Expanded(
                        child: SizedBox(child: RaisedButton(
                            color: Colors.red,
                            child: const Text("Cancel", style: TextStyle(color: Colors.white),),
                            onPressed: () {
                              Navigator.of(context).pop();
                            }
                        ), height: 40)
                    )
                  ],
                )
              ],
            ),
          );
        }
    );
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build

    DateTime now = DateTime.now();
    return Scaffold(
        floatingActionButton: FloatingActionButton(
          tooltip: "Add transaction",
          child: Icon(Icons.add),
          onPressed: () {
            setState(() {
              insert();
            });
          },
        ),
        appBar: AppBar(
          title: Text("My App"),
          actions: [
            IconButton(
                onPressed: () {
                  setState(() {
                    insert();
                  });
                },
                icon: Icon(Icons.add)
            )
          ],
        ),
        key: _scaffoldKey,
        body: SafeArea(
          minimum: EdgeInsets.only(left: 20, right: 20),
          child: SingleChildScrollView(
            child: Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  Container(
                    width: 300,
                    decoration: BoxDecoration(
                      color: Colors.red
                    ),
                    child: Text("Hello"),
                  ),
                  FlatButton(
                    onPressed: () {
                      _onButtonShowModalSheet();
                      setState(() {
                        insert();
                      });

                      // _scaffoldKey.currentState?.showSnackBar(
                      //     SnackBar(
                      //       content: Text(_listTransaction.toString()),
                      //       duration: const Duration(seconds: 3),
                      //     )
                      // );
                    },
                    child: const Text("Insert"),
                    color: Colors.green,
                    textColor: Colors.white,

                  ),
                  TransactionList(listTransaction: _listTransaction)
                ],
              ),
            ),
          ),
        )
    );
  }

}