package com.edmonbl.cuartitocapp

data class Student(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String
)

val students = listOf(
    Student(1, "Eugenio Derbez", "Actor chido", "https://variety.com/wp-content/uploads/2016/03/eugenio-derbez.jpg"),
    Student(2, "Ludovico Peluche", "Papá que le va al Cruz Azul", "https://example.com/image2.jpg"),
    Student(3, "Alice Johnson", "Loves mathematics", "https://example.com/image3.jpg"),
    Student(4, "Bob Brown", "Enjoys physics", "https://example.com/image4.jpg"),
    Student(5, "Charlie Davis", "A creative thinker", "https://example.com/image5.jpg"),
    Student(6, "Diana Evans", "Passionate about art", "https://example.com/image6.jpg"),
    Student(7, "Ethan Foster", "A sports enthusiast", "https://example.com/image7.jpg"),
    Student(8, "Fiona Green", "Loves programming", "https://example.com/image8.jpg"),
    Student(9, "George Harris", "Enjoys history", "https://example.com/image9.jpg"),
    Student(10, "Hannah White", "A science lover", "https://example.com/image10.jpg"),
    Student(11, "Ian King", "A music enthusiast", "https://example.com/image11.jpg"),
    Student(12, "Julia Lee", "Enjoys literature", "https://example.com/image12.jpg"),
    Student(13, "Kevin Moore", "A tech geek", "https://example.com/image13.jpg"),
    Student(14, "Laura Scott", "Loves biology", "https://example.com/image14.jpg"),
    Student(15, "Michael Taylor", "A natural leader", "https://example.com/image15.jpg")
)
