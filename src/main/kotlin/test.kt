
fun main(args: Array<String>) {


    val users = listOf(
        User("Alex",1000.0, "1234567812345678", "1234"),
        User("Nick",1000.0, "123423344545678", "1111"),
        User("Alice",1000.0, "1212343488554433", "0000")
    )

    val userData = listOf(
        BankAccount("Alex","1234567812345678","1234",10000.00),
        BankAccount("Nick","123423344545678", "1111",1000.00),
        BankAccount("Alice","1212343488554433", "0000", 5000.00),
    )


    val atm  = ATM(userData)
    val service =  ServiceATMImpl(atm,users[0])
    service.inputCard()

    println("Имя ${users[0].name}\nНаличные ${users[0].cash}\nСредства в банке ${userData[0].cash}")

}



