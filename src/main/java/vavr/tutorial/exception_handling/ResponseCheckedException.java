package vavr.tutorial.exception_handling;

public class ResponseCheckedException extends Exception
{
    public ResponseCheckedException()
    {
        super();
    }

    public ResponseCheckedException(final String message)
    {
        super(message);
    }

    public ResponseCheckedException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    public ResponseCheckedException(final Throwable cause)
    {
        super(cause);
    }

    protected ResponseCheckedException(final String message, final Throwable cause, final boolean enableSuppression,
        final boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
