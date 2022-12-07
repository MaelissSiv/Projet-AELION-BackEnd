package canard.intern.post.following.backend.validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateLessThanValidator.class)

public @interface DateLessThan{
    String message() default "date is not before 18 years ago";

    Class<?>[] groups() default {};

    Class<?extends Payload> [] payload() default {};

}

class DateLessThanValidator implements ConstraintValidator <DateLessThan,LocalDate> {

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext){
        LocalDate today = LocalDate.now();
        LocalDate date18yearsAgo = today.minusYears(18L);
        //localDate <= .compareTo(date18yearsAgo)<=0;
        return localDate.compareTo(date18yearsAgo) <= 0;
    }
}
