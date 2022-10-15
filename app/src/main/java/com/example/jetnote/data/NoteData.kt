package com.example.jetnote.data

import com.example.jetnote.model.Note

class NotesDataSource {
    fun loadNotes(): List<Note>{
        return listOf(
            Note(title = "A good Day", description = "Kotlin Android App Development: Firebase Firestore, Hilt & Dagger, ROOM DB, ViewModel, Navigation & Clean Architecture"),
            Note(title = "A good Day", description = "Kotlin Android App Development: Firebase Firestore, Hilt & Dagger, ROOM DB, ViewModel, Navigation & Clean Architecture"),
            Note(title = "A good Day", description = "Kotlin Android App Development: Firebase Firestore, Hilt & Dagger, ROOM DB, ViewModel, Navigation & Clean Architecture"),
//            Note(title = "A good Day", description = "Kotlin Android App Development: Firebase Firestore, Hilt & Dagger, ROOM DB, ViewModel, Navigation & Clean Architecture"),
//            Note(title = "A good Day", description = "Kotlin Android App Development: Firebase Firestore, Hilt & Dagger, ROOM DB, ViewModel, Navigation & Clean Architecture"),
//            Note(title = "A good Day", description = "Kotlin Android App Development: Firebase Firestore, Hilt & Dagger, ROOM DB, ViewModel, Navigation & Clean Architecture"),
//            Note(title = "A good Day", description = "Kotlin Android App Development: Firebase Firestore, Hilt & Dagger, ROOM DB, ViewModel, Navigation & Clean Architecture"),
        )
    }
}