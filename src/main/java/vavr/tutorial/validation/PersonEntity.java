package vavr.tutorial.validation;

public class PersonEntity
{
    private int age;

    private String name;

    private String phone;

    public PersonEntity(final int age, final String name, final String phone)
    {
        this.age = age;
        this.name = name;
        this.phone = phone;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(final int age)
    {
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public void setPhone(final String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
}
