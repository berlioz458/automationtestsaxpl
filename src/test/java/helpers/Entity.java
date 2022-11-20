package helpers;

import lombok.Getter;

@Getter
public class Entity {
    private Integer id;
    private String changedAt;
    private String changedByParty;
    private String changedByUser;
    private String createdAt;
    private String createdByParty;
    private String createdByUser;
    private String guid;
    private Integer version;
}
