import java.time.LocalDateTime

class ATM(val userData: List<BankAccount>){

    fun start(cardNumber: String) {
        val account = findAccountByCard(cardNumber)
        if(account==null){
            println("Ваша карта не читается.\nПожалуйста, обратитесь в отделение банка.")
            stop()
        }
        else{
            if(!pinCodeValidation(account.pinCode))
                stop()
                choosingAnAction()

        }

    }

    private fun pinCodeValidation(validPin: String):Boolean{
        println("Введите ПИН-КОД")
        var pinCode = readLine()?.substring(0,4)

        if(pinCode==validPin)
            return true

        for(i in 1..3){
            println("Неверный ПИН-КОД\nВведите ПИН-КОД ещё раз")
            pinCode = readLine()?.substring(0,4)
            if(pinCode==validPin)
                return true
        }
        println("Превышено максимальное количество попыток, пожалуйса, попробуйте позже")
        return false
    }



    private fun findAccountByCard(cardNumber: String) = userData.find { it.cardNumber == cardNumber }

    fun mainMenu(){
        val date = LocalDateTime.now().toString().substringBefore('T')
        println("""
             ___________________________________________
            |                 БАНКмини                  |
            |___________________________________________|    
            |                               ${date}  |
            |               ГЛАВНОЕ МЕНЮ                |
            |--------------------+----------------------|
            |1.Запросить баланс  |   2.Получить наличные|
            |3.Внести наличные   |   q.Выход            |
            |___________________________________________|
        """.trimIndent())
    }


    fun choosingAnAction(){
        mainMenu()
        var choose = readLine()?.get(0)
        when(choose){
            '1' -> showTheBalance()
            '2' -> withdrawMoney()
            '3' -> depositMoney()
            'q' -> stop()
            else -> choosingAnAction()
        }
    }


    fun inputCard() {
        TODO("Not yet implemented")
    }

    fun showTheBalance() {
        TODO("Not yet implemented")
    }

    fun depositMoney() {}
    fun withdrawMoney() {}

    fun stop() {
        println("Работа банкомата окончена")
    }

}