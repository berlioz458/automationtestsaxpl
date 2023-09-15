package integrationservice.spec;

import config.CredentialsConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import static io.restassured.RestAssured.with;

public class IntegrationBusEventNotificationApiSpecs {
    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    static String baseUri = config.uri();
    static String username = config.usr();
    static String password = config.pswrd();
    public static RequestSpecification success_request(Integer postBox){
        Pair<String,String> accessData = ChooseOfAccessesData(postBox);
        RequestSpecification sr= with()
            .baseUri("http://api.integration."+baseUri)
            .auth().preemptive().basic(accessData.getLeft(), accessData.getRight())
            .basePath("/v2")
            .log().all()
            .contentType(ContentType.JSON);
    return sr;
    }

    public static ResponseSpecification success_responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();
    private static Pair <String,String> ChooseOfAccessesData(Integer postBox){
        Pair<String,String> accessData =new MutablePair<>();
        switch(postBox){
            case (10002):{
                accessData.of("SHOP","123456");
                break;}
            case (10128):{
                accessData.of("ERP-auto3n","2");
                break;}
            case (10005):{
                accessData.of("uiserver","123456");
                break;}
            case (10062):{
                accessData.of("notificationservice","2");
                break;}
            case (10139):{
                accessData.of("usearch-publisher","2");
                break;}
            case (10151):{
                accessData.of("bpmonline2","2");
                break;}
            default:{
                accessData.of(username,password);;
                break;}
        }
        return accessData;
    }

    public static RequestSpecification error_request = with()
            .baseUri("http://api.integration."+baseUri)
            .auth().preemptive().basic(username, password)
            .basePath("/v2")
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification error_responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .expectContentType(ContentType.JSON)
            .build();
}
