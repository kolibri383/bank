class ServiceATMImpl(private val atm: ATM, private val user: User) : ServiceATM {


    override fun inputCard() {
        atm.start(user.card)
    }

    override fun checkTheBalance() {
        TODO("Not yet implemented")
    }

    override fun depositMoney() {
        TODO("Not yet implemented")
    }

    override fun withdrawMoney() {
        TODO("Not yet implemented")
    }

    override fun pickUpTheCard() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }
}