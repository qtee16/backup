import 'package:flutter/material.dart';
import 'package:food_app/category_item.dart';
import 'package:food_app/detail_food_page.dart';
import 'package:food_app/fake_data.dart';
import 'package:food_app/models/category.dart';
import 'package:food_app/models/food.dart';

class FoodsPage extends StatelessWidget {
  static const String routeName = "/FoodsPage";

  late Category category;

  FoodsPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    final args = ModalRoute.of(context)!.settings.arguments as CategoryArguments;
    category = args.category;

    List<Food> foods = FAKE_FOODS.where((food) => food.categoryId == category.id).toList();
    return SafeArea(
        child: Scaffold(
          appBar: AppBar(
            title: Text("Foods from ${category.content}", style: TextStyle(fontSize: 16),),
          ),
          body: Center(
            child: Center(
              child: Container(
                width: 300,
                child: ListView.builder(
                    itemCount: foods.length,
                    itemBuilder: (context, index) {
                      Food food = foods[index];
                      return InkWell(
                        onTap: () {
                          Navigator.pushNamed(
                              context,
                              DetailFoodPage.routeName,
                              arguments: FoodArguments(food)
                          );
                        },
                        child: Stack(
                          children: [
                            Container(
                              padding: EdgeInsets.all(10),
                              child: ClipRRect(
                                  borderRadius: BorderRadius.circular(10),
                                  clipBehavior: Clip.hardEdge,
                                  child: FadeInImage.assetNetwork(
                                    placeholder: 'assets/images/ATB3o.gif',
                                    image: food.urlName,
                                    width: 300,
                                    height: 200,
                                    fit: BoxFit.cover,
                                  )
                              ),
                            ),
                            Positioned(
                              top: 20, left: 20,
                              child: Container(
                                padding: EdgeInsets.all(5),
                                decoration: BoxDecoration(
                                    color: Colors.black54,
                                    borderRadius: BorderRadius.circular(10),
                                    border: Border.all(color: Colors.white, width: 2)
                                ),
                                child: Row(
                                  children: [
                                    const Icon(Icons.timer, color: Colors.white, size: 20,),
                                    Text('${food.duration.inMinutes} minutes', style: const TextStyle(fontSize: 18, color: Colors.white),)
                                  ],
                                ),
                              ),
                            ),
                            Positioned(
                              top: 20, right: 20,
                              child: Container(
                                padding: EdgeInsets.all(5),
                                decoration: BoxDecoration(
                                  color: Colors.lightGreen,
                                  borderRadius: BorderRadius.circular(10),
                                ),
                                child: Text(food.complexity.toString().split('.').last, style: const TextStyle(fontSize: 18, color: Colors.white)),
                              ),
                            ),
                            Positioned(
                              bottom: 20, right: 20,
                              child: Container(
                                padding: EdgeInsets.all(10),
                                decoration: BoxDecoration(
                                  color: Colors.black54,
                                  borderRadius: BorderRadius.circular(10),
                                ),
                                child: Text(food.name, style: const TextStyle(fontSize: 18, color: Colors.white)),
                              ),
                            ),
                          ],
                        ),
                      );
                    }
                ),
              ),
            )
          ),
        )
    );
  }

}

class FoodArguments {
  final Food food;
  FoodArguments(this.food);
}