import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:signup/screens/signin/signin_screen.dart';
import 'package:signup/screens/verify_screen.dart';
import '../models/user_detail.dart';
import '../reusable/reusable_func.dart';
import '../screens/home_screen.dart';

class FirebaseAuthMethods {
  final FirebaseAuth _auth;
  final GoogleSignIn _googleSignIn = GoogleSignIn();
  UserDetail? userDetail;

  FirebaseAuthMethods(this._auth);

  // FOR EVERY FUNCTION HERE
  // POP THE ROUTE USING: Navigator.of(context).pushNamedAndRemoveUntil('/', (Route<dynamic> route) => false);

  // GET USER DATA
  // using null check operator since this method should be called only
  // when the user is logged in
  User get user => _auth.currentUser!;

  // STATE PERSISTENCE STREAM
  Stream<User?> get authState => FirebaseAuth.instance.authStateChanges();

  // OTHER WAYS (depends on use case):
  // Stream get authState => FirebaseAuth.instance.userChanges();
  // Stream get authState => FirebaseAuth.instance.idTokenChanges();
  // KNOW MORE ABOUT THEM HERE: https://firebase.flutter.dev/docs/auth/start#auth-state

  // EMAIL SIGN IN
  void signInWithEmail(
      {required String email,
      required String password,
      required BuildContext context}) {
    showMyDialog(context);
    _auth
        .signInWithEmailAndPassword(email: email, password: password)
        .then((value) {
      Navigator.pop(context);
      // var currUser = _auth.currentUser;
      userDetail = UserDetail(
        username: user.displayName ?? "",
        email: user.email ?? "",
        photoURL: user.photoURL ?? "",
      );
      if (user.emailVerified) {
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => const HomeScreen(),
          ),
        );
        showMySnackBar(context, "Sign in successfully!");
      } else {
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => const VerifyScreen(),
          ),
        );
      }

    }).onError((error, stackTrace) {
      Navigator.pop(context);
      showMySnackBar(context, "Error: ${error.toString()}");
    });
  }

  // EMAIL SIGN UP
  void signUpWithEmail(
      {required String username,
      required String email,
      required String password,
      required BuildContext context}) {
    showMyDialog(context);
    _auth
        .createUserWithEmailAndPassword(email: email, password: password)
        .then((value) async {
          Navigator.pop(context);
      // var currUser = _auth.currentUser;
      await user.updateDisplayName(username);
      await user.updatePhotoURL(
          "https://cdn-icons-png.flaticon.com/512/149/149071.png");
      userDetail = UserDetail(
        username: user.displayName ?? "",
        email: user.email ?? "",
        photoURL: user.photoURL ?? "",
      );
      showMySnackBar(
          context, "Sign up successfully!");
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => const VerifyScreen(),
        ),
      );
    }).onError((error, stackTrace) {
      Navigator.pop(context);
      showMySnackBar(context, "Error: ${error.toString()}");
    });
  }

  // EMAIL VERIFICATION
  Future<void> sendEmailVerification(BuildContext context) async {
    try {
      _auth.currentUser!.sendEmailVerification();
      showMySnackBar(context, 'Email verification sent!');
    } on FirebaseAuthException catch (e) {
      showMySnackBar(context, e.message!); // Display error message
    }
  }

  // GOOGLE SIGN IN
  Future<void> signInWithGoogle(BuildContext context) async {
    try {
      final GoogleSignInAccount? googleUser = await _googleSignIn.signIn();

      final GoogleSignInAuthentication? googleAuth =
          await googleUser?.authentication;

      userDetail = UserDetail(
        username: googleUser?.displayName ?? "",
        email: googleUser?.email ?? "",
        photoURL: googleUser?.photoUrl ?? "",
      );

      if (googleAuth?.accessToken != null && googleAuth?.idToken != null) {
        // Create a new credential
        final credential = GoogleAuthProvider.credential(
          accessToken: googleAuth?.accessToken,
          idToken: googleAuth?.idToken,
        );
        UserCredential userCredential =
            await _auth.signInWithCredential(credential);

        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => const HomeScreen(),
          ),
        );

        // if you want to do specific task like storing information in firestore
        // only for new users using google sign in (since there are no two options
        // for google sign in and google sign up, only one as of now),
        // do the following:

        // if (userCredential.user != null) {
        //   if (userCredential.additionalUserInfo!.isNewUser) {}
        // }
      }
    } on FirebaseAuthException catch (e) {
      showMySnackBar(context, "Error: ${e.message}");
    }
  }

// // FACEBOOK SIGN IN
// Future<void> signInWithFacebook(BuildContext context) async {
//   try {
//     final LoginResult loginResult = await FacebookAuth.instance.login();
//
//     final OAuthCredential facebookAuthCredential =
//     FacebookAuthProvider.credential(loginResult.accessToken!.token);
//
//     await _auth.signInWithCredential(facebookAuthCredential);
//   } on FirebaseAuthException catch (e) {
//     showSnackBar(context, e.message!); // Displaying the error message
//   }
// }
//
  // SIGN OUT
  Future<void> signOut(BuildContext context) async {
    try {
      await _googleSignIn.signOut();
      await _auth.signOut();
      userDetail = null;
      showMySnackBar(context, "Signed out!");
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => const SignInScreen(),
        ),
      );
    } on FirebaseAuthException catch (e) {
      showMySnackBar(
          context, "Error: ${e.message}"); // Displaying the error message
    }
  }
}
