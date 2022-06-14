import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:plant_app/constants.dart';
import 'package:plant_app/screens/details/components/title_and_price.dart';

import 'icon_card.dart';
import 'image_and_icons.dart';

class Body extends StatelessWidget {
  const Body({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return SingleChildScrollView(
      child: Column(
        children: [
          ImageAndIcons(size: size),
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
                child: FlatButton(
                  shape: const RoundedRectangleBorder(
                    borderRadius: BorderRadius.only(
                        topRight: Radius.circular(20)
                    )
                  ),
                  color: kPrimaryColor,
                  onPressed: () {},
                  child: const Text(
                      'Buy Now',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 16
                    ),
                  ),

                ),
              ),
              Expanded(
                  child: FlatButton(
                    height: 60,
                    onPressed: () {  },
                    child: const Text('Description'),
                  )
              )
            ],
          ),
          const SizedBox(height: kDefaultPadding,),
        ],
      ),
    );
  }
}






