import 'package:flutter/material.dart';
import 'package:plant_app_clone/screens/home/components/recommended_plants.dart';
import 'package:plant_app_clone/screens/home/components/title_with_more_button.dart';

import 'featured_plants.dart';
import 'header_with_search_box.dart';

class Body extends StatelessWidget {
  const Body({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return SingleChildScrollView(
      child: Column(
        children: [
          HeaderWithSearchBox(size: size),
          TitleWithMoreButton(
            title: 'Recommended',
            press: () {},
          ),
          const RecommendedPlants(),
          TitleWithMoreButton(
            title: 'Featured',
            press: () {},
          ),
          const FeaturedPlants()
        ],
      ),
    );
  }
}






