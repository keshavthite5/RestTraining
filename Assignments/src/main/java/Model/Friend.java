package Model;
import lombok.*;
import org.json.JSONObject;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Friend {
    private String firstname;
    private String lastname;
    private String id;
    private int age;
    private JSONObject address;

}

