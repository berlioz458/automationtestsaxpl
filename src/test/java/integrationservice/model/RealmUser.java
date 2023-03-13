package integrationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Ref;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class RealmUser {
    private Ref agent;
    private List<String> roles;
    private List<Ref> groups;

    RealmUser(Ref agent, ArrayList<String> roles, List<Ref> groups) {
        this.agent = agent;
        this.roles = roles;
        this.groups = groups;
    }
}
