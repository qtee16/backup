import 'package:date/transaction.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class TransactionList extends StatelessWidget {

  late List<Transaction> listTransaction;

  TransactionList({required this.listTransaction});

  ListView _buildListView() {
    return ListView.builder(
      itemCount: listTransaction.length,
        itemBuilder: (context, index) {
          return Card(
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(10)
            ),
            color: (index % 2) == 0 ? Colors.pink : Colors.green,
            elevation: 10,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                const Padding(padding: EdgeInsets.all(10)),
                Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Padding(padding: EdgeInsets.all(4)),
                    Text(
                      listTransaction[index].content,
                      style: const TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                        fontWeight: FontWeight.bold
                      ),
                    ),
                    const Padding(padding: EdgeInsets.all(4)),
                    Text(
                      'Date: ${DateFormat.yMd().format(listTransaction[index].dateTime ?? DateTime.now())}',
                      style: const TextStyle(
                        fontSize: 16,
                        fontStyle: FontStyle.italic,
                        color: Colors.white
                      ),
                    ),
                    const Padding(padding: EdgeInsets.all(4))
                  ],
                ),
                Expanded(
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.end,
                      children: [
                        Container(
                          padding: const EdgeInsets.all(10),
                          child: Text(
                            '${listTransaction[index].money}\$',
                            style: const TextStyle(
                                fontSize: 18,
                                color: Colors.white
                            ),
                          ),
                          decoration: BoxDecoration(
                            border: Border.all(color: Colors.white, width: 2, style: BorderStyle.solid),
                            borderRadius: const BorderRadius.all(Radius.circular(10))
                          ),
                        )
                      ],
                    )
                ),
                const Padding(padding: EdgeInsets.all(10))

              ],
            )
          );
        }
    );
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Container(
      height: 300,
      child: this._buildListView()
    );
  }

}