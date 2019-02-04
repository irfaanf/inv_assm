public class InvalidAddressException extends RuntimeException {
    public InvalidAddressException() {
    }

    public InvalidAddressException(String message) {
        super(message);
    }
}
