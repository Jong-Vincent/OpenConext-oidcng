package oidc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

import static oidc.manage.ServiceProviderTranslation.translateServiceProviderEntityId;

@Getter
@Document(collection = "clients")
@NoArgsConstructor
public class OpenIDClient {

    @Id
    private String id;
    private String clientId;
    private String name;
    private String secret;
    private List<String> redirectUrls;
    private List<String> scopes;
    private List<String> grants;
    private boolean resourceServer;

    @SuppressWarnings("unchecked")
    public OpenIDClient(Map<String, Object> root) {
        Map<String, Object> data = (Map<String, Object>) root.get("data");

        this.clientId = translateServiceProviderEntityId(String.class.cast(data.get("entityid")));

        Map<String, Object> metaDataFields = (Map<String, Object>) data.get("metaDataFields");

        this.name = String.class.cast(metaDataFields.get("name:en"));
        this.secret = String.class.cast(metaDataFields.get("secret"));
        this.redirectUrls = List.class.cast(metaDataFields.get("redirectUrls"));
        this.scopes = List.class.cast(metaDataFields.getOrDefault("scopes", "oidc"));
        this.grants = List.class.cast(metaDataFields.getOrDefault("grants", "authorization_code"));
        this.resourceServer = String.class.cast(metaDataFields.getOrDefault("resourceServer", "0")).equals("1");
    }

    public OpenIDClient setId(String id) {
        this.id = id;
        return this;
    }
}
