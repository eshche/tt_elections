package net.thumbtack.school.elections.model;

import net.thumbtack.school.elections.elValidators.ElectException;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RankTest {
    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void WrongRankTest() throws ElectException {
        Rank testRank = new Rank("Navalny", -4);
        setUpValidator();
        Set<ConstraintViolation<Rank>> constraintViolations = validator.validate(testRank);
        assertEquals("Должно быть не меньше 1", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void WrongRankTest2() throws ElectException {
        Rank testRank = new Rank("Navalny", 6);
        setUpValidator();
        Set<ConstraintViolation<Rank>> constraintViolations = validator.validate(testRank);
        assertEquals("Должно быть не больше 5", constraintViolations.iterator().next().getMessage());
    }
}
