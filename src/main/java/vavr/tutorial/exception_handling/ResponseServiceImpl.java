package vavr.tutorial.exception_handling;

public class ResponseServiceImpl implements ResponseService
{
    public static final String DEFAULT_RESPONSE = "Default response!!";

    public static final String RESPONSE = "Hello world!";

    public static final String ERROR_MESSAGE = "Something went wrong!";

    @Override
    public Response getResponseWithCheckedException() throws ResponseCheckedException
    {
        throw new ResponseCheckedException(ERROR_MESSAGE);
    }

    @Override
    public Response getResponseWithRuntimeException()
    {
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }

    @Override
    public Response getResponse()
    {
        return new Response(200, RESPONSE);
    }

    @Override
    public Response getDefaultResponse()
    {
        return new Response(200, DEFAULT_RESPONSE);
    }
}
