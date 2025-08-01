package dangelodavide.U2_W3_D5_Friday.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
