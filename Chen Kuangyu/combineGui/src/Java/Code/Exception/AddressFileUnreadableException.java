package Code.Exception;

public class AddressFileUnreadableException extends Exception {
    public AddressFileUnreadableException(String address) {
        String msg = "Address '" + address + "' is not exist in this computer or not a image file !";
        System.out.println(msg);
    }
}
