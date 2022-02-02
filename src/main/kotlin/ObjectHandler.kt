class ContactHandler() {
    var contacts = ArrayList<Contact>()
    val formatCheck = Formatting()

    fun AddContact() {
        println("Skriv in förnamn: ")
        val firstname : String? = readLine()
        println("Skriv in efternamn: ")
        val surname : String? = readLine()
        println("Skriv in telefonnummer: ")
        val phoneNumber : String = readLine().toString()
        println("Skriv in mail: ")
        val mail : String = readLine().toString()

        if(formatCheck.CheckMailFormat(mail) && formatCheck.CheckNumberFormat(phoneNumber)) {
            println("Lade till: ${firstname.toString()} ${surname.toString()}")
            contacts.add(Contact(firstname.toString(), surname.toString(), phoneNumber, mail))
        } else {1
            println("Ditt format är felaktigt")
        }
    }

    fun RemoveContact() {
        println("Skriv in förnamn och efternamn på personen du vill ta bort")
        println("Förnamn: ")
        val firstname: String? = readLine()
        println("Efternamn: ")
        val surname: String? = readLine()
        val contact = FindContactFromName(firstname.toString(), surname.toString())
        contacts.remove(contact)
    }

    fun FindContactFromName(firstname : String, surname: String) : Contact? {
        for (contact in contacts) {
            if(contact.firstname == firstname && contact.surname == surname) {
                return contact
            }
        }
        return null //possible cause return type is accepted when using ?
    }

    fun PrintContactFromObject(contact: Contact) {
        println(contact.firstname)
        println(contact.surname)
        println(contact.phoneNumber)
        println(contact.mail)
    }

    fun PrintContactFromId(id : Int) {
        println(contacts[id].firstname)
        println(contacts[id].surname)
        println(contacts[id].phoneNumber)
        println(contacts[id].mail)
    }

    fun PrintContacts() {
        var i = 0
        for(contact in contacts) { //probably better to use a for i loop instead
            println("Kontakt nummer $i :")
            i++
            println(contact.firstname)
            println(contact.surname)
            println("-----------------------")
        }
    }

    fun EditData(contact : Contact) {
        println("Vad vill du ändra, förnamn(1), efternamn(2), telefonnummer(3) eller mail(4)?")
        val chooser: String? = readLine()

        if(chooser == "1") {
            contact.firstname = readLine().toString()
        } else if(chooser == "2") {
            contact.surname = readLine().toString()
        } else if(chooser == "3") {
            contact.phoneNumber = readLine().toString()
        } else if(chooser == "4") {
            contact.mail = readLine().toString()
        }
        println("Vill du fortsätta redigera? (1) eller huvudmeny (valfri återstående knapp)")

        val chooser2: String? = readLine()
        if(chooser2 == "1") {
            EditData(contact)
        }
    }

    fun EditContact() {
        println("[Ta bort] Välj mellan att skriva in förnamn och efternamn(1) eller id:n på personen(2)")
        val chooser: String? = readLine()
        val contact : Contact

        if(chooser.toString() == "1") {
            println("Förnamn: ")
            val firstname: String? = readLine()
            println("Efternamn: ")
            val surname: String? = readLine()
            contact = FindContactFromName(firstname.toString(), surname.toString()) ?: return //if the retuned value is null then returns
            PrintContactFromObject(contact)
            EditData(contact)
        } else if(chooser.toString() == "2") {
            println("ID på personen: ")
            val id : Int = readLine()!!.toInt()
            PrintContactFromId(id)
            contact = contacts[id]
            EditData(contact)
        }
    }
}