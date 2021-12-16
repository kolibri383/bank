class User(val name: String,
           var cash: Double,
           val card: String,
           val pinCode: String) {

    fun inputCard():Boolean = askYN("Вы хотите вставить карту в банкомат? (y/n)")

    private fun askYN(message: String):Boolean{
        println(message)
        var choose = readLine()?.get(0)
        while (choose!='y' && choose!='n'){
            println(message)
            choose = readLine()?.get(0)
        }
        return when(choose){
            'y' -> true
            else -> false
        }
    }


     fun enterPinCode():String {
         println("Введите ПИН-КОД")
         var pin = readLine()
         while (pin?.length!=4||pin.all{ it !in '0'..'9' }){
             println("ПИН-КОД состоит их четырёх цифр")
             pin = readLine()
         }
         return pin.substring(0,4)
    }

    fun chooseAction(screen: String):Char{
        print("Ваше дейстивие: ")
        var choose: Char? = ' '
        var condition = true

        while (condition){
            print("Ваше дейстивие: ")
            choose = readLine()?.get(0)
            condition = when(screen){
                "menu" -> choose!='1'&&choose!='2'&&choose!='3'&&choose!='q'
                "balance","withdrawScreen" -> choose!='b'&&choose!='q'
                "deposit" -> choose!='b'&&choose!='d'&&choose!='q'
                "withdraw" -> choose!='b'&&choose!='w'&&choose!='q'
                else -> choose != 'q'
            }
        }
        return choose!!
    }


     fun depositMoney():Double {
         print("Введите сумму ")
         var money = readLine()?.toDouble()?:0.0
         while(cash<money){
                println("Недостаточно наличных\nУ вас $cash")
                print("Введите меньшую сумму ")
             money = readLine()?.toDouble()?:0.0
         }
         cash-=money
         return money
    }

     fun withdrawMoney():Double {
         print("Введите сумму ")
         var money = readLine()?.toDouble()?:0.0
         while(money<=0){
             print("Введите корректную сумму ")
             money = readLine()?.toDouble()?:0.0
         }
         return money
    }

     fun pickUpTheCard(): Boolean = askYN("Забрать карту? (y/n)")

}