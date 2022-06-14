import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:plant_app_clone/constants.dart';

import 'image_and_icon.dart';

class Body extends StatelessWidget {
  const Body({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return SingleChildScrollView(
      scrollDirection: Axis.vertical,
      child: Column(
        children: [
          ImageAndIcon(size: size),
          const TitleAndPrice(
            title: 'Angelica',
            country: 'Russia',
            price: 440,
          ),
          const SizedBox(height: kDefaultPadding,),
          Row(
            children: [
              SizedBox(
                height: 60,
                width: size.width / 2,
                child: TextButton(
                  style: TextButton.styleFrom(
                    backgroundColor: kPrimaryColor,
                    primary: Colors.white,
                    shape: const RoundedRectangleBorder(
                        borderRadius: BorderRadius.only(
                          topRight: Radius.circular(10)
                        )
                    ),
                  ),
                  onPressed: () {},
                  child: const Text(
                    'Buy Now',
                  ),
                ),
              ),
              SizedBox(
                height: 60,
                width: size.width / 2,
                child: TextButton(
                  style: TextButton.styleFrom(
                    shape: const RoundedRectangleBorder(
                      borderRadius: BorderRadius.only(
                        topLeft: Radius.circular(10)
                      )
                    )
                  ),
                  onPressed: () {},
                  child: const Text(
                    'Description'
                  ),
                )
              )
            ],
          )
        ],
      ),
    );
  }
}

class TitleAndPrice extends StatelessWidget {
  const TitleAndPrice({
    Key? key, required this.title, required this.country, required this.price,
  }) : super(key: key);

  final String title, country;
  final int price;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: kDefaultPadding),
      child: Row(
        children: [
          RichText(
            text: TextSpan(
              children: [
                TextSpan(
                  text: '$title\n',
                  style: Theme.of(context).textTheme.headline4?.copyWith(
                    color: kTextColor,
                    fontWeight: FontWeight.bold
                  )
                ),
                TextSpan(
                  text: country,
                  style: const TextStyle(
                    fontSize: 20,
                    color: kPrimaryColor,
                    fontWeight: FontWeight.w300
                  )
                )
              ]
            )
          ),
          const Spacer(),
          Text(
            '\$$price',
            style: Theme.of(context).textTheme.headline5?.copyWith(color: kPrimaryColor),
          )
        ],
      ),
    );
  }
}


