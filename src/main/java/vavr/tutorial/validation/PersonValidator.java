package vavr.tutorial.validation;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;

public class PersonValidator
{
    public static final String ERROR_NAME_LENGTH = "Invalid name length";

    public static final String ERROR_PHONE_LENGTH = "Invalid phone length";

    public static final String ERROR_AGE = "Invalid age, must be bigger than 0";

    public Validation<Seq<String>, PersonEntity> validate(final PersonData personData)
    {
        return Validation
            .combine(
                validateAge(personData.getAge()),
                validateName(personData.getName()),
                validatePhone(personData.getPhone()))
            .ap(PersonEntity::new);
    }

    public Validation<String, String> validateName(final String name)
    {
        return (name != null && name.trim().length() > 3)
            ? Validation.valid(name)
            : Validation.invalid(ERROR_AGE);
    }

    public Validation<String, String> validatePhone(final String phone)
    {
        return (phone != null && phone.trim().length() > 3 && phone.trim().length() <= 10)
            ? Validation.valid(phone)
            : Validation.invalid(ERROR_PHONE_LENGTH);
    }

    public Validation<String, Integer> validateAge(final int age)
    {
        return age > 0
            ? Validation.valid(age)
            : Validation.invalid(ERROR_NAME_LENGTH);
    }
}
