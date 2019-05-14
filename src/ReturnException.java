public class ReturnException extends Exception{
    int days;

    public ReturnException(String message) {
        super(message);
    }

    public ReturnException(int x) {
        days = x;
    }
}
