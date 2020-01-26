package vavr.tutorial.exception_handling;

import java.util.List;

import io.vavr.control.Try;
import org.junit.Test;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static vavr.tutorial.exception_handling.ResponseServiceImpl.DEFAULT_RESPONSE;
import static vavr.tutorial.exception_handling.ResponseServiceImpl.RESPONSE;

public class ResponseServiceImplTest
{
    private final ResponseService responseService = new ResponseServiceImpl();

    /**
     * Use Try.of or Try.ofCallable when method throws a checked exception
     * and returns a result.
     *
     * Use .andThen to execute a method in case Try is Successful.
     *
     * Use .andFinally to do something after call has been done disregarding if
     * it was a success or a failure
     */
    @Test
    public void shouldReturnDefaultValueWhenCheckedExceptionWasThrown()
    {
        final var mockMethod = mock(List.class);
        final var response = Try.of(() -> responseService.getResponseWithCheckedException())
            .andThen(() -> mockMethod.size()) // this won't be called since Try is Failure
            .andFinally(() -> printLog("After calling service with checked exception"));

        final var message =
            response
                .map(Response::getMessage)
                .getOrElse(responseService.getDefaultResponse().getMessage());

        assertEquals(message, DEFAULT_RESPONSE);
        assertTrue(response.isFailure());
        verifyZeroInteractions(mockMethod);
    }

    /**
     * Try.of will catch any kind of exceptions (Checked or Unchecked)
     */
    @Test
    public void shouldReturnDefaultValueWhenRuntimeExceptionWasThrown()
    {
        final var response = Try.of(() -> responseService.getResponseWithCheckedException());
        final var message =
            response
                .map(Response::getMessage)
                .getOrElse(responseService.getDefaultResponse().getMessage());

        assertEquals(message, DEFAULT_RESPONSE);
        assertTrue(response.isFailure());
    }

    /**
     * Return a default value when Try was Failure
     */
    @Test
    public void shouldReturnDefaultWhenResponseIsFailure()
    {
        final var response = Try.of(() -> responseService.getResponseWithCheckedException());
        final var message =
            response
                .getOrElseGet(throwable -> responseService.getDefaultResponse())
                .getMessage();

        assertEquals(message, DEFAULT_RESPONSE);
        assertTrue(response.isFailure());
    }

    /**
     * Recover from given exceptions returning a default value
     */
    @Test
    public void shouldRecoverForExceptions()
    {
        final var response = Try.of(() -> responseService.getResponseWithCheckedException())
            .andThen(() -> printLog("After calling service with checked exception"))
            .recover(exception -> Match(exception).of(
                Case($(instanceOf(ResponseCheckedException.class)), t -> {
                    printLog("Recovering after exception thrown: " + t.getMessage());
                    return responseService.getDefaultResponse();
                })));

        assertEquals(response.get().getMessage(), DEFAULT_RESPONSE);
        assertTrue(response.isSuccess());
    }

    // Utility methods
    private void printLog(final String message)
    {
        System.out.println("LOG: " + message);
    }
}
