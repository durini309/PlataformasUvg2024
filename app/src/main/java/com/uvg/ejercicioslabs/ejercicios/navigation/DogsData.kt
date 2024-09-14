package com.uvg.ejercicioslabs.ejercicios.navigation

enum class BreedSize {
    MINI,
    SMALL,
    MEDIUM,
    LARGE,
    EXTRA_LARGE
}

data class Dog(
    val id: Int,
    val name: String,
    val breedSize: BreedSize
)

val dogs by lazy {
    val randomDogNames = listOf(
        "Mike", "Max", "Yesi", "Capu", "Juana",
        "Molly", "Lucy", "Cooper", "Bailey", "Sophie",
        "Oliver", "Riley", "Nina", "Jake", "Lily",
        "Sadie", "Abby", "Buster", "Toby", "Zoe",
        "Rocky", "Duke", "Harley", "Maggie", "Teddy"
    )

    val randomDogs = (1..25).map {
        Dog(
            id = it,
            name = randomDogNames.random(),
            breedSize = BreedSize.entries.toTypedArray().random()
        )
    }

    randomDogs
}

fun getDogDataFromId(id: Int): Dog = dogs.first { dog -> dog.id == id }
