package canard.intern.post.following.backend.controller.fixture;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.LocalDate;
import java.util.stream.Stream;

public class TraineeJsonProvider {

    private static ObjectNode traineeJsonObjectRequiredFields(){
        var objectMapper = new ObjectMapper();
        var trainee = objectMapper.createObjectNode();
        return trainee.put("lastname", "solo")
                .put("firstname", "han")
                .put("birthdate", "1950-11-05")
                .put("email", "han@solo.fr");
    }
    //{ "lastName": "Solo",
         //   "firstName": "Han",
         //   "gender": "M",
          //  "birthDate": "1950/11/05",
          //  "phoneNumber": "+33707070707",
         //   "email": "bop@billly.gmail.com"
  //  }
    public static String traineeJsonAllFieldsValid(){
//        var objectMapper = new ObjectMapper();
//        var trainee = objectMapper.createObjectNode();
        return traineeJsonObjectRequiredFields()
                .put("gender", "M")
                .put("phoneNumber", "+33707070707")
                .toPrettyString();

    }

    public static Stream <String> traineeJsonMissingNonRequiredField (){
        //var objectMapper = new ObjectMapper();
        return Stream.of(
                traineeJsonObjectRequiredFields().toPrettyString(),
                traineeJsonObjectRequiredFields()
                        .put("gender","M")
                        .toPrettyString(),
                traineeJsonObjectRequiredFields()
                        .put("phoneNumber", "+33707070707")
                        .toPrettyString()
        );

    }

    public static String traineeJasonInvalidBirthdate(){
        var wrongBirthdate = LocalDate.now()
                .minusYears(18L)
                .plusDays(1L);
        var objectMapper = new ObjectMapper();
        var trainee = objectMapper.createObjectNode();
        return trainee.put("lastname", "solo")
                .put("firstname", "han")
                .put("birthdate", wrongBirthdate.toString())
                .put("email", "han@solo.fr")
                .toPrettyString();
    }


}

//package canard.intern.post.following.backend.controller.fixture;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//
//import java.util.stream.Stream;
//
//public class TraineeJsonProvider {
//
//    private static ObjectNode traineeJsonRequiredFields(){
//        var objectMapper = new ObjectMapper();
//        var trainee = objectMapper.createObjectNode();
//        trainee.put("lastName","Solo")
//                .put("firstName","Han")
//                .put("birthDate","1950-11-10")
//                .put("email","tamer@tamer.com");
//    }
//    public static String traineeJsonAllFieldsValid(){
//        return trainee.toPrettyString();
//    }
//
//    public static Stream<String> traineeJsonMissingNonRequiredField(){
//        var objectMapper = new ObjectMapper();
//
//        return Stream.of();
//    }
//
//}
