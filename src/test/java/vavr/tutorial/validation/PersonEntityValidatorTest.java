package vavr.tutorial.validation;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static vavr.tutorial.validation.PersonValidator.ERROR_AGE;
import static vavr.tutorial.validation.PersonValidator.ERROR_NAME_LENGTH;
import static vavr.tutorial.validation.PersonValidator.ERROR_PHONE_LENGTH;

public class PersonEntityValidatorTest
{
    private final PersonValidator validator = new PersonValidator();

    @Test
    public void ShouldReturnErrors()
    {
        // given:
        final var person = new PersonData(0, "Hi", "545");

        // when:
        final var validation = validator.validate(person);

        // then:
        assertTrue(validation.isInvalid());
        assertTrue(3 == validation.getError().size());

    }

    @Test
    public void ShouldValidateSuccessfully()
    {

        // given:
        final var person = new PersonData(30, "Marie", "5519555225");

        // when:
        final var validation = validator.validate(person);

        // then:
        assertTrue(validation.isValid());
    }


    @Test
    public void ShouldMapErrorsToList()
    {
        // given:
        final var person = new PersonData(0, "Hi", "545");
        final var errorsList = List.of(ERROR_NAME_LENGTH, ERROR_AGE, ERROR_PHONE_LENGTH);

        // when:
        final var validation = validator.validate(person);
        final var errorsToList = validation.getError().toJavaList();

        // then:
        assertTrue(validation.isInvalid());
        assertTrue(3 == validation.getError().size());
        assertEquals(errorsList, errorsToList);

    }
}
