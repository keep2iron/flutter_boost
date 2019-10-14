import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'simple_page_widgets.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();

    FlutterBoost.singleton.registerPageBuilders({
      'flutterPage1': (pageName, params, _) => FirstRouteWidget(),
      'flutterPage2': (pageName, params, _) => SecondRouteWidget(),
      'flutterPage3': (pageName, params, _) => TabRouteWidget(),
      'flutterFragment': (pageName, params, _) => FragmentRouteWidget(params),
      ///可以在native层通过 getContainerParams 来传递参数
      'flutterMain': (pageName, params, _) {
        print("flutterPage params:$params");

        return FlutterRouteWidget(params:params);
      },
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Boost example',
        builder: FlutterBoost.init(postPush: _onRoutePushed),
        home: FlutterRouteWidget());
  }

  void _onRoutePushed(
      String pageName, String uniqueId, Map params, Route route, Future _) {
  }
}
