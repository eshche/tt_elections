package net.thumbtack.school.elections.model;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProposalTest {
    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void testProposal(){
        Proposal proposal = new Proposal("Navalny", "Kill Putin");
        setUpValidator();
        Set<ConstraintViolation<Proposal>> constraintViolations = validator.validate(proposal);
        assertEquals("Navalny",proposal.getAuthorId());
        assertEquals("Kill Putin", proposal.getText());
        proposal.setAuthorId("Varlamov");
        proposal.setText("Make beautiful city");
        assertEquals("Varlamov", proposal.getAuthorId());
        assertEquals("Make beautiful city", proposal.getText());
    }

    @Test
    void testWrongProposal1() {
       Proposal proposal1 = new Proposal(null, null);
        setUpValidator();
        Set<ConstraintViolation<Proposal>> constraintViolations = validator.validate(proposal1);

        assertEquals(2, constraintViolations.size());
        assertEquals("Не должно быть пустым", constraintViolations.iterator().next().getMessage());

    }

}
