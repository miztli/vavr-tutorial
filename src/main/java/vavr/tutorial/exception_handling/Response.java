package vavr.tutorial.exception_handling;

public class Response
{
    public Response(final int code, final String message)
    {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;

    public int getCode()
    {
        return code;
    }

    public void setCode(final int code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(final String message)
    {
        this.message = message;
    }
}
