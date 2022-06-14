import 'package:flutter/material.dart';
import 'package:food_app/foods_page.dart';

import 'models/food.dart';

class DetailFoodPage extends StatelessWidget {
  static const String routeName = "/DetailFoodPage";
  late Food food;
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    final args = ModalRoute.of(context)!.settings.arguments as FoodArguments;
    food = args.food;
    return SafeArea(
        child: Scaffold(
          appBar: AppBar(
            title: Text(food.name),
            centerTitle: true,
          ),
          body: Center(
            child: Column(
              children: [
                Container(
                  padding: const EdgeInsets.all(10),
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
                Container(
                  child: Text("Ingredients", style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),),
                ),
                Expanded(
                    child: ListView.builder(
                        itemCount: this.food.ingredients.length,
                        itemBuilder: (context, index) {
                          String ingredient = this.food.ingredients[index];
                          return ListTile(
                            leading: CircleAvatar(
                              backgroundColor: Colors.lightBlueAccent,
                              child: Text('#${index + 1}', style: TextStyle(color: Colors.white),),
                            ),
                            title: Text(ingredient, style: TextStyle(fontSize: 18),),
                          );
                        }
                    )
                )
              ],
            ),
          ),
        )
    );
  }

}