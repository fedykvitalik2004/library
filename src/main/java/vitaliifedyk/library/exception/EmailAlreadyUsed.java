package vitaliifedyk.library.exception;

public class EmailAlreadyUsed extends RuntimeException {
    public EmailAlreadyUsed(String message) {
        super(message);
    }
}
