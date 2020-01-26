package vavr.tutorial.exception_handling;

public interface ResponseService
{
    Response getResponseWithCheckedException() throws ResponseCheckedException;

    Response getResponseWithRuntimeException();

    Response getResponse();

    Response getDefaultResponse();
}
