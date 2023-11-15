import com.kt_lr1.controller.route.Router
import com.kt_lr1.controller.route.initializeRoutes

fun main(args: Array<String>) {
    var number: Int = Int.MAX_VALUE

    val router = Router()

    initializeRoutes(router)
    println("Welcome to the Health Care System!")

    while (number != -1){
        println("Choose a command:")
        println("1. Calculate Sick Leave")
        println("2. Check sick leave payment")
        println("3. Add Patient")
        println("4. Show Patients")
        println("5. Calculate treatment cost")
        println("0. Exit")
       try {
           print("Your answer: ")
           number = readln().toInt()
           router.route(number)
       } catch (e: NumberFormatException){
           println("Error: ${e.message}. Please enter a valid integer.")
       }
    }
}