package com.edmonbl.cuartitocapp

data class Student(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String
)

val students = listOf(
    Student(1, "Eugenio Derbez", "El mismo", "https://variety.com/wp-content/uploads/2016/03/eugenio-derbez.jpg"),
    Student(2, "Ludovico P. Luche", "El papá de la Familia P. Luche", "https://cdn2.mediotiempo.com/uploads/media/2022/08/21/ludovico-peluche-aficionado-cruz-azul.jpg"),
    Student(3, "El Lonje Moco", "Personaje de Derbez en Cuando", "https://st1.uvnimg.com/44/63/c29ef36c1b32fbf6c3be00f27a8d/lonje-moco.jpg"),
    Student(4, "Armando Hoyos", "El filósofo de la televisión", "https://i.ytimg.com/vi/IoFScxzq5s0/maxresdefault.jpg"),
    Student(5, "Burro", "El burro de Shrek (doblaje)", "https://static-live.nmas.com.mx/nmas-news/styles/corte_16_9/cloud-storage/2025-01/shrek-muere-perry-burro-que-inspiro-personaje.jpg?h=920929c4&itok=zqiyPIF8"),
    Student(6, "Maximo", "How to Be a Latin Lover", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmJ9-N0sSzkz3d1IvYBu6dMIW1hDH39cHxOQ&s"),
    Student(7, "Valentin", "Instructions Not Included", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSuJV9fLP-AL9gHDHDbVnq2WO36wujCRVDKbg&s"),
    Student(8, "Bernardo Villalobos", "CODA", "https://www.hola.com/us/horizon/landscape/ccb808535276-eugenio-derbez-in-coda.jpg?im=Resize=(640),type=downsize"),
    Student(9, "Felipe", "Jack and Jill", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8thNi6ZUolmuwYJOHNdyE4O7HYDR7YmM-LQ&s"),
    Student(10, "Dr. Nurko", "Miracles from Heaven", "https://pbs.twimg.com/media/CbYZ9HqVAAEWVCi.jpg")
)
