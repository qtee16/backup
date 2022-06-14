
import 'dart:async';

import 'package:counter_app/events/remote_event.dart';
import 'package:counter_app/state/remote_state.dart';

class RemoteBloc {
  var state = RemoteState(70);

  final eventController = StreamController<RemoteEvent>();
  final stateController = StreamController<RemoteState>();

  RemoteBloc() {
    eventController.stream.listen((event) {
      if (event is IncrementEvent) {
        if (state.volume + event.increment <= 100) {
          state = RemoteState(state.volume + event.increment);
        }
      } else if (event is DecrementEvent) {
        if (state.volume - event.decrement >= 0) {
          state = RemoteState(state.volume - event.decrement);
        }
      } else {
        state = RemoteState(0);
      }
      
      stateController.sink.add(state);
    });
  }

  void dispose() {
    eventController.close();
    stateController.close();
  }
}