package vavr.tutorial;

import io.vavr.Function2;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Test;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TutorialTests
{
    /**
     * Similar to java Optional, {@link Option} wraps either
     * nullable and non-nullable Objects to avoid null checks.
     */
    @Test
    public void shouldNotThrowNPE_when_comparingNullableObjects()
    {
        Option<Object> noneOption = Option.of(null);
        Option<Object> someOption = Option.of("val");

        assertEquals("None", noneOption.toString());
        assertEquals("Some(val)", someOption.toString());
    }

    /**
     * {@link Tuple} objects wrap other objects and can be accessed in
     * a type-safe way.
     */
    @Test
    public void shouldCompareTupleValuesInATypeSafeWay()
    {
        Tuple3<String, Integer, Double> java8 = Tuple.of("Java", 8, 5.6);
        String element1 = java8._1();
        int element2 = java8._2();
        double element3 = java8._3();

        assertEquals("Java", element1);
        assertEquals(8, element2);
        assertEquals(5.6, element3, 0);
    }

    /**
     * This is the common way in which we call methods and expect them to fail,
     * leading us to use try-catch blocks to avoid our application from crashing.
     */
    @Test(expected = ArithmeticException.class)
    public void shouldThrowException_when_InvalidFunctionCalled()
    {
        arithmeticFailingFunction();
    }

    /**
     * Now, when using vavr {@link io.vavr.control.Try}, the computation is
     * wrapped so that we don't have to explicitly declare try-catch blocks
     */
    @Test
    public void whenUsingTry_then_noTryCatchDeclared_callFunction_then_EvaluateFailure()
    {
        final var result = Try.of(() -> arithmeticFailingFunction());

        assertTrue(result.isFailure());
    }

    /**
     * Using default when exception is thrown inside function
     */
    @Test
    public void whenNullObjectReturned_then_FallbackToDefault()
    {
        final var defaultString = "default";
        final var defaultValue = Try.of(() -> getSomeDBValue()).getOrElse(defaultString);

        assertEquals(defaultValue, defaultString);
    }

    /**
     * Create vavr function from method reference
     */
    @Test
    public void shouldCreateFunctionFromMethodReference()
    {
        final var function = Function2.of(this::sum);
        final int result = function.apply(1, 2);

        assertEquals(result, 3);
    }

    /**
     * Intersting approach for reducing switch verbosity
     */
    @Test
    public void shouldUsePatternMatching()
    {
        int input = 2;
        String output = Match(input).of(
            Case($(1), "one"),
            Case($(2), "two"),
            Case($(3), "three"),
            Case($(), "?"));

        assertEquals("two", output);
    }

    /**
     * Expect exception
     */
    private double arithmeticFailingFunction()
    {
        return 1 / 0;
    }

    /**
     * Some DB call that in which an exception was thrown
     */
    private String getSomeDBValue()
    {
        throw new RuntimeException("Some DB exception");
    }

    private int sum(final int a, final int b)
    {
        return a + b;
    }
}
