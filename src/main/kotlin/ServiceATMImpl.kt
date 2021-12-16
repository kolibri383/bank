import java.time.LocalDateTime

class ServiceATMImpl(private val atm: ATM, private val user: User) : ServiceATM {

    override fun inputCard() {
        mainScreen()
        if(user.inputCard()){
            if(atm.scanCart(user.card))
                enterPinCode()
        }
    }

    override fun enterPinCode() {
        enterPinCodeScreen()
        var pin = user.enterPinCode()
        while(!atm.pinCodeValidation(pin)&&atm.attemptsToEnterThePincode<=3){
            enterPinCodeScreen()
            pin = user.enterPinCode()
        }
        if(atm.attemptsToEnterThePincode>3){
            errorPinCodeScreen()
            stop()
        }else
            mainMenu()
    }

    override fun checkTheBalance() {
        val balance = atm.getTheBalance()
        balanceScreen(balance)
    }

    override fun depositMoney() {
        depositScreen()
        moneyOperation("deposit")
    }

    override fun withdrawMoney() {
        withdrawScreen()
        moneyOperation("withdraw")

    }

    private fun moneyOperation(screen: String){
        when(user.chooseAction(screen)){
            'w'->{
                val money = user.withdrawMoney()
                if(atm.withdrawMoney(money)){
                    user.cash+=money
                    withdrawOkScreen()
                }
                else withdrawBadScreen()
            }
            'd' -> {
                if(atm.depositMoney(user.depositMoney()))
                    depositOkScreen()
                else
                    depositErrorScreen()
            }

            'b'-> mainMenu()
            else -> stop()
        }
    }


    override fun pickUpTheCard() {
        if(!user.pickUpTheCard())
            enterPinCode()

    }

    override fun stop() {
        println("""
             ___________________________________________
            |                 БАНКмини                  |
            |___________________________________________|    
            |                                           |
            |            СПАСИБО ЗА ВИЗИТ               |
            |                                           |
            |      не забудьте забрать вашу карту       | 
            |___________________________________________|
        """.trimIndent())
        pickUpTheCard()
    }

    private fun withdrawBadScreen(){
        println("""
             ___________________________________________
            |                 БАНКмини                  |
            |___________________________________________|    
            |                                           |
            |            Недостаточно средств           |
            |                                           |
            |    b.Меню                q.Выход          | 
            |___________________________________________|
        """.trimIndent())
        chooseActionMainMenu("withdrawScreen")
    }

    private fun withdrawOkScreen(){
        println("""
             ___________________________________________
            |                 БАНКмини                  |
            |___________________________________________|    
            |                                           |
            |            Заберите деньги                |
            |                                           |
            |    b.Меню                q.Выход          | 
            |___________________________________________|
        """.trimIndent())
        chooseActionMainMenu("withdrawScreen")
    }


    private fun depositErrorScreen(){
        println("""
             ___________________________________________
            |                 БАНКмини                  |
            |___________________________________________|    
            |                                           |
            |            Произошла ошибка.              |
            |                                           |
            |        возврат в главное меню...          | 
            |___________________________________________|
        """.trimIndent())
        Thread.sleep(1000)
        mainMenu()
    }


    private fun depositOkScreen(){
        println("""
             ___________________________________________
            |                 БАНКмини                  |
            |___________________________________________|    
            |                                           |
            |      ВАШИ ДЕНЬГИ ЗАЧИСЛЕНЫ НА СЧЕТ        |
            |                                           |
            |        возврат в главное меню...          | 
            |___________________________________________|
        """.trimIndent())
        Thread.sleep(1000)
        mainMenu()
    }

    private fun withdrawScreen(){
        println("""
             ___________________________________________
            |                 БАНКмини                  |
            |___________________________________________|    
            |             Cнятие наличных               |
            |                                           |            
            |    w.Указать сумму                        |
            |    b.Назад              q.Выход           | 
            |___________________________________________|
        """.trimIndent())
    }



    private fun balanceScreen(balance:Double){
            println("""
             ___________________________________________
            |                 БАНКмини                  |
            |___________________________________________|    
            |                                           |
            |  Ваш баланс:          $balance            |
            |                                           |
            |   b.Назад                 q.Выход         | 
            |___________________________________________|
        """.trimIndent())
        chooseActionMainMenu("balance")
        }


    private fun mainScreen(){
        println("""
             ___________________________________________
            |                 БАНКмини                  |
            |___________________________________________|    
            |   ПОЖАЛУЙСТА,             ___________     |
            |   ВСТАВЬТЕ                |_|     |_|     |
            |   ВАШУ                      |     |       |
            |   КАРТУ                     |_____|       |
            |___________________________________________|
        """.trimIndent())

    }

    private fun enterPinCodeScreen(){
        println("""
             ___________________________________________
            |                 БАНКмини                  |
            |___________________________________________|    
            |       ПОЖАЛУЙСТА, ВВЕДИТЕ ПИН-КОД         |
            |           _____________________           |
            |           |    |    |    |    |           |
            |           |____|____|____|____|           |
            |___________________________________________|
        """.trimIndent())
    }


    private fun errorPinCodeScreen(){
        println("""
             ___________________________________________
            |                 БАНКмини                  |
            |___________________________________________|    
            |     Превышено максимальное количество     |
            |                 попыток                   |
            |           _____________________           |
            |           |    |    |    |    |           |
            |           |____|____|____|____|           |
            |___________________________________________|
        """.trimIndent())
        Thread.sleep(1000)
    }

    private fun depositScreen(){
        println("""
             ___________________________________________
            |                 БАНКмини                  |
            |___________________________________________|    
            |                                           |
            |   Внесите купюры в купюромриёмник         |
            |    d.Внести деньги                        |
            |    b.Назад                   q.Выход      | 
            |___________________________________________|
        """.trimIndent())
    }



    private fun mainMenu(){
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
        chooseActionMainMenu("menu")
    }


    private fun chooseActionMainMenu(screen: String){
        when(user.chooseAction(screen)){
            '1'->checkTheBalance()
            '2'->withdrawMoney()
            '3'->depositMoney()
            'b'-> mainMenu()
            else -> stop()
        }
    }


}