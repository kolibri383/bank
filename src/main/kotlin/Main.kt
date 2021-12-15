import java.time.LocalDateTime

fun main(args: Array<String>) {
    val user = User("Alex",1000.0, "1234567812345678", "1234")
    val a = BankAccount("Alex","1234567812345678","1234")
    val userData = listOf(a)
    val atm  = ATM(userData)
    val service =  ServiceATMImpl(atm,user)
    Process(service).start()

}





class Process(val service: ServiceATM){
    var authorized = false

    fun start(){
        mainScreen()
        println("Вы вставили карту")
        service.inputCard()
    }

    fun mainScreen(){
        println("""
             ___________________________________________
            |                 БАНКмини                  |
            |___________________________________________|    
            |   ПОЖАЛУЙСТА              ___________     |
            |   ВСТАВЬТЕ                |_|     |_| 
            |   ВАШУ                      |     |       |
            |   КАРТУ                     |_____|       |
            |___________________________________________|
        """.trimIndent())
    }

    fun mainMenu(){
        val date = LocalDateTime.now().toString().substringBefore('T')
        println("""
             ___________________________________________
            |                 БАНКмини                  |
            |___________________________________________|    
            |                               ${date}  |
            |               ГЛАВНОЕ МЕНЮ                |
            |-------------------------------------------|
            |1.Запросить баланс  |   2.Получить наличные|
            |3.Внести наличные   |   q.Выход            |
            |____________________|______________________|
        """.trimIndent())
    }

    fun chooseAction(){
        if(authorized==true){
            authorizedAction()
        }
    }

    fun authorizedAction(){
        mainMenu()
        var choose = readLine()?.get(0)
        when(choose){
            '1' -> service.checkTheBalance()
            '2' -> service.withdrawMoney()
            '3' -> service.depositMoney()
            'q' -> service.stop()
            else -> authorizedAction()
        }
    }


}