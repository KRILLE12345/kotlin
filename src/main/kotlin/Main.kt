import com.beust.klaxon.Klaxon
import org.jetbrains.annotations.Contract
import java.io.File
import java.io.InputStream


fun main(args: Array<String>) {
    val contactHandler = ContactHandler()
    val inputStream: InputStream = File("contacts.txt").inputStream() //relative text file to build?
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
    lineList.forEach{
        val result = Klaxon().parse<Contact>(it) //Klaxon is used for JSON
        contactHandler.contacts.add(result!!) //After conversion to Contact object it can be added in contacthandler list for contacts
    }

    MainMenu(contactHandler)
}

fun MainMenu(contactHandler: ContactHandler) {
    println("HUVUDMENY")
    println("Skriv in alternativ: ")
    println("(1) lägg till kontakt")
    println("(2) ta bort kontakt")
    println("(3) redigera kontakt")
    println("(4) visa alla kontakter")
    println("(5) spara kontakter till fil")
    println("(6) sortera lista")

    var chooser = readLine()

    if(chooser == "1") {
        contactHandler.AddContact()
        MainMenu(contactHandler)
    }else if(chooser == "2") {
        contactHandler.RemoveContact()
        MainMenu(contactHandler)
    } else if(chooser == "3") {
        contactHandler.EditContact()
        MainMenu(contactHandler)
    } else if(chooser == "4") {
        contactHandler.PrintContacts()
        MainMenu(contactHandler)
    } else if(chooser == "5") {
        File("contacts.txt").printWriter().use { out ->
            for(contact in contactHandler.contacts) {
                out.println(Klaxon().toJsonString(contact))
            }
        }
    } else if(chooser == "6") {
        contactHandler.contacts = ArrayList<Contact>(contactHandler.contacts.sortedWith(compareBy(Contact::firstname, Contact::surname))) //order firstname then surname must be converted to arraylist
        MainMenu(contactHandler)
    }
}


