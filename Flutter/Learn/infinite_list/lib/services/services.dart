import 'dart:convert';

import 'package:infinite_list/models/comment.dart';
import 'package:http/http.dart' as http;

Future<List<Comment>> getCommentsFromApi(int start, int limit) async {
  final urlStr = "https://jsonplaceholder.typicode.com/comments?_start=$start&_limit=$limit";
  Uri url = Uri.parse(urlStr);
  final http.Client httpClient = http.Client();
  try{
    final response = await httpClient.get(url);

    if(response.statusCode == 200) {
      final responseData = json.decode(response.body) as List;
      //convert responseData to List of comment ?
      final List<Comment> comments = responseData.map((comment) {
        return Comment(
            id: comment['id'],
            name: comment['name'],
            email: comment['email'],
            body: comment['body']
        );
      }).toList();
      print('start = $start, limit = $limit');
      return comments;
    } else {
      return List<Comment>.empty(growable: true);
    }
  } catch(exception) {
    print('Exception sending api : '+exception.toString());
    return List<Comment>.empty(growable: true);
  }

}