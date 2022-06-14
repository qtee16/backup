import 'package:flutter/material.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:get/get.dart';
import 'package:todo_task/app/core/utils/extensions.dart';
import 'package:todo_task/app/data/models/task.dart';
import 'package:todo_task/app/modules/home/controller.dart';
import 'package:todo_task/app/widgets/icons.dart';
import 'package:dotted_border/dotted_border.dart';

import '../../../core/values/colors.dart';

class AddCard extends StatelessWidget {
  final homeCtrl = Get.find<HomeController>();

  AddCard({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final icons = getIcons();
    var squareWidth = Get.width - 12.0.wp;
    return Container(
      width: squareWidth / 2,
      height: squareWidth / 2,
      margin: EdgeInsets.all(3.0.wp),
      child: InkWell(
          onTap: () async {
            await showDialog(
                barrierDismissible: true,
                context: context,
                builder: (BuildContext context) {
                  return Center(
                    child: SingleChildScrollView(
                      scrollDirection: Axis.vertical,
                      child: AlertDialog(
                          titlePadding: EdgeInsets.symmetric(vertical: 5.0.wp),
                          title: const Center(
                            child: Text(
                              'Task Type',
                              style: TextStyle(fontWeight: FontWeight.bold),
                            ),
                          ),
                          content: Form(
                            key: homeCtrl.formKey,
                            child: Column(
                              children: [
                                Padding(
                                  padding: EdgeInsets.symmetric(horizontal: 3.0.wp),
                                  child: TextFormField(
                                    controller: homeCtrl.editController,
                                    decoration: const InputDecoration(
                                      border: OutlineInputBorder(),
                                      labelText: 'Title',
                                    ),
                                    validator: (value) {
                                      if (value == null || value.trim().isEmpty) {
                                        return 'Please enter your task title';
                                      }
                                      return null;
                                    },
                                  ),
                                ),
                                Padding(
                                  padding: EdgeInsets.symmetric(vertical: 5.0.wp),
                                  child: Wrap(
                                    spacing: 2.0.wp,
                                    children: icons
                                        .map(
                                          (e) => Obx(() {
                                            final index = icons.indexOf(e);
                                            return ChoiceChip(
                                              label: e,
                                              selected:
                                                  homeCtrl.chipIndex.value ==
                                                      index,
                                              onSelected: (bool selected) {
                                                homeCtrl.chipIndex.value =
                                                    selected ? index : 0;
                                              },
                                              selectedColor: Colors.grey[200],
                                              pressElevation: 0,
                                              backgroundColor: Colors.white,
                                            );
                                          }),
                                        )
                                        .toList(),
                                  ),
                                ),
                                ElevatedButton(
                                  style: ElevatedButton.styleFrom(
                                    primary: blue,
                                    shape: RoundedRectangleBorder(
                                      borderRadius: BorderRadius.circular(20),
                                    ),
                                    minimumSize: const Size(150, 40),
                                  ),
                                  onPressed: () {
                                    if (homeCtrl.formKey.currentState!
                                        .validate()) {
                                      int icon = icons[homeCtrl.chipIndex.value]
                                          .icon!
                                          .codePoint;
                                      String color =
                                          icons[homeCtrl.chipIndex.value]
                                              .color!
                                              .toHex();
                                      var task = Task(
                                        title: homeCtrl.editController.text,
                                        icon: icon,
                                        color: color,
                                      );
                                      Get.back();
                                      homeCtrl.addTask(task)
                                          ? EasyLoading.showSuccess(
                                              'Create success')
                                          : EasyLoading.showError(
                                              'Duplicated Task');
                                    }
                                  },
                                  child: const Text('Confirm'),
                                ),
                              ],
                            ),
                          )),
                    ),
                  );
                });
            homeCtrl.editController.clear();
            homeCtrl.changeChipIndex(0);
            // await Get.defaultDialog(
            //     titlePadding: EdgeInsets.symmetric(vertical: 5.0.wp),
            //     radius: 5,
            //     title: 'Task Type',
            //     content: Form(
            //       key: homeCtrl.formKey,
            //       child: Column(
            //         children: [
            //           Padding(
            //             padding: EdgeInsets.symmetric(horizontal: 3.0.wp),
            //             child: TextFormField(
            //               controller: homeCtrl.editController,
            //               decoration: const InputDecoration(
            //                 border: OutlineInputBorder(),
            //                 labelText: 'Title',
            //               ),
            //               validator: (value) {
            //                 if (value == null || value.trim().isEmpty) {
            //                   return 'Please enter your task title';
            //                 }
            //                 return null;
            //               },
            //             ),
            //           ),
            //           Padding(
            //             padding: EdgeInsets.symmetric(vertical: 5.0.wp),
            //             child: Wrap(
            //               spacing: 2.0.wp,
            //               children: icons
            //                   .map(
            //                     (e) => Obx(() {
            //                       final index = icons.indexOf(e);
            //                       return ChoiceChip(
            //                         label: e,
            //                         selected: homeCtrl.chipIndex.value == index,
            //                         onSelected: (bool selected) {
            //                           homeCtrl.chipIndex.value =
            //                               selected ? index : 0;
            //                         },
            //                         selectedColor: Colors.grey[200],
            //                         pressElevation: 0,
            //                         backgroundColor: Colors.white,
            //                       );
            //                     }),
            //                   )
            //                   .toList(),
            //             ),
            //           ),
            //           ElevatedButton(
            //             style: ElevatedButton.styleFrom(
            //               primary: blue,
            //               shape: RoundedRectangleBorder(
            //                 borderRadius: BorderRadius.circular(20),
            //               ),
            //               minimumSize: const Size(150, 40),
            //             ),
            //             onPressed: () {
            //               if (homeCtrl.formKey.currentState!.validate()) {
            //                 int icon = icons[homeCtrl.chipIndex.value].icon!.codePoint;
            //                 String color = icons[homeCtrl.chipIndex.value].color!.toHex();
            //
            //               }
            //             },
            //             child: const Text('Confirm'),
            //           ),
            //         ],
            //       ),
            //     ));
          },
          child: DottedBorder(
            color: Colors.grey[400]!,
            dashPattern: const [8, 4],
            child: Center(
              child: Icon(
                Icons.add,
                size: 10.0.wp,
                color: Colors.grey,
              ),
            ),
          )),
    );
  }
}
