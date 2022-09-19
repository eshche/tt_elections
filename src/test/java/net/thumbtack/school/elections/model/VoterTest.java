package net.thumbtack.school.elections.model;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VoterTest {
    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testWrongVoter1() {
        Voter voter1 = new Voter("Nav123", "nav123", "navalny", "Alex", null, "Ленина", "5", "64");
        setUpValidator();
        Set<ConstraintViolation<Voter>> constraintViolations = validator.validate(voter1);

        assertEquals(1, constraintViolations.size());
        assertEquals("Пароль - строчные и прописные латинские буквы, цифры, спецсимволы. Минимум 8 символов", constraintViolations.iterator().next().getMessage());

    }

    @Test
    void testWrongVoter2() {
        Voter voter1 = new Voter("Nav123", "navV1.23", "Alexey", null, null, "Ленина", "5", "64");
        setUpValidator();
        Set<ConstraintViolation<Voter>> constraintViolations = validator.validate(voter1);

        assertEquals(1, constraintViolations.size());
        assertEquals("Укажите имя", constraintViolations.iterator().next().getMessage());

    }

  /*  @Test
    void testWrongVoter3() {
        Voter voter1 = new Voter(null, null, null, null, null, null, null, null);
        setUpValidator();
        Set<ConstraintViolation<Voter>> constraintViolations = validator.validate(voter1);

        assertEquals(6, constraintViolations.size());
        assertEquals("Укажите дом", constraintViolations.iterator().next().getMessage());

    }*/


    @Test
    void testVoter() {
        Voter voter = new Voter("navalny", "nav1234", "Навальный", "Алексей", null, "Ленина", "5", "64");
        assertEquals("Навальный", voter.getLastName());
        assertEquals("Алексей", voter.getFirstName());
        assertEquals(null, voter.getPatronymic());
        voter.setLastName("Путин");
        assertEquals("Путин", voter.getLastName());
        voter.setFirstName("Владимир");
        assertEquals("Владимир", voter.getFirstName());
        voter.setPatronymic("Владимирович");
        assertEquals("Владимирович", voter.getPatronymic());
    }

    @Test
    void testEqualsVoter() {
        Voter voter1 = new Voter("navalny", "nav1234", "Навальный", "Алексей", null, "Ленина", "5", "64");
        Voter voter2 = new Voter("navalny", "na234", "Наьный", "Алсей", null, "Ленина", "5", "64");

        assertEquals(voter1, voter2);
    }
}
