///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.tienda;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.firestore.Firestore;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.cloud.FirestoreClient;
//import java.io.FileInputStream;
//import java.io.IOException;
//import javax.annotation.PostConstruct;
//
///**
// *
// * @author XPC
// */
//public class FirebaseConfig {
//     @PostConstruct
//    private  void  Firestore() throws IOException {
//     FileInputStream serviceAccount =
//new FileInputStream("path/to/serviceAccountKey.json");
//
//FirebaseOptions options = new FirebaseOptions.Builder()
//  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//  .build();
//
//FirebaseApp.initializeApp(options);
//
//}
//     public Firestore getFirestore(){
//        return FirestoreClient.getFirestore();
//    }
//}