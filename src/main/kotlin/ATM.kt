
class ATM(val userData: List<BankAccount>){
    var cardAvailability = false
    var currentUser: BankAccount? = null
    var attemptsToEnterThePincode = 0


    fun scanCart(cardNumber: String):Boolean{
        if(cardAvailability)
            return false
        val account = findAccountByCard(cardNumber)
        if(account==null){
            println("Ваша карта не читается.\nПожалуйста, обратитесь в отделение банка.")
            return false
        }
        currentUser = account
        return true
    }


    fun pinCodeValidation(pinCode: String):Boolean{
        attemptsToEnterThePincode++
        val validPin = currentUser?.pinCode
        if(pinCode==validPin)
            return true
        return false
    }


    private fun findAccountByCard(cardNumber: String) = userData.find { it.cardNumber == cardNumber }



    fun getTheBalance():Double{
        if(currentUser==null)
            return 0.0

        return currentUser!!.cash
    }

    fun depositMoney(money: Double):Boolean {
        if(money<0)
            return false
        currentUser?.cash = currentUser?.cash?.plus(money)!!
        return true
    }


    fun withdrawMoney(money: Double):Boolean {
        if(money> currentUser?.cash!!)
            return false
        currentUser?.cash = currentUser?.cash?.plus(-money)!!
        return true
    }
}