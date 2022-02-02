class Formatting() {

    fun CheckMailFormat(mail: String) : Boolean {
        if(mail.contains("^\\w+[@]+(\\w+)+[.]+(\\w+)".toRegex())) { //used regex, tested on website https://regexr.com/
            return true
        }
        return false
    }

    fun CheckNumberFormat(number: String) : Boolean {
        if(number.contains("[+]+(46)+[-]+(7)+([0-9]+)".toRegex())) {
            return true
        }
        return false
    }
}