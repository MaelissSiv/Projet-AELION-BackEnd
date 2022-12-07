package canard.intern.post.following.backend.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenderTest {

    @Test
    void testGender(){
        var gender = Gender.FEMALE;
        var o = gender.ordinal();
        var s = gender.toString();
        System.out.println("Ordinal: " + o);
        System.out.println("Text : " + s);
        System.out.println("Name : " + gender.name());
    }
}
