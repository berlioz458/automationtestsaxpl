package integrationservice.tests;

import helpers.Ref;
import integrationservice.model.Company;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static integrationservice.utils.IntegrationCompanyController.*;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationCompanyApiTest {
    String name="Autotest";
    Ref currency= new Ref("Currency",643);
    String nameChange="Change Autotest";
    Integer id=1003;
    @Description("List company")
    @Test
    void successGetListCompany() {
        Response companyList= getCompanyList();
        assertThat(companyList).isNotNull();
    }
    @Description("Company by id")
    @Test
    void successGetCompanyById() {
        Company company= getCompanyById(id);
        assertThat(company).isNotNull();
        assertThat(company.getId()).isEqualTo(id);

    }
    @Description("Company by name")
    @Test
    void successGetCompanyByName() {
        Response company= getCompany("q","{\"$and\": [{\"name\":\"ИП Романова Наталия Ювеналиевна\"}]}");
        assertThat(company).isNotNull();
    }
    @Description("Get one company")
    @Test
    void successGetCompany() {
        Response company= getCompany("limit","1");
        assertThat(company).isNotNull();
    }
    @Description("Create company")
    @Test
    void successCreateCompany() {
        name=name + java.time.LocalDateTime.now();
        Company company= createCompany(name,currency);
        assertThat(company.getName()).isEqualTo(name);


    }
    @Description("Edit company by name")
    @Test
    void successChangeCompanyName() {
        // create
        name=name + java.time.LocalDateTime.now();
        Company company= createCompany(name,currency);
        //edit by id
        Company companyEdit= changeCompanyName(company.getId(),nameChange);
        assertThat(companyEdit.getName()).isEqualTo(nameChange);
        assertThat(companyEdit.getChangedAt()).isNotNull();
        assertThat(companyEdit.getChangedByUser()).isNotNull();
    }
    @Description("Delete company")
    @Test
    void successDeleteCompany() {
        // create
        name=name + java.time.LocalDateTime.now();
        Company company= createCompany(name,currency);
        //delete by id
        Company companyDelete= deleteCompany(company.getId());
        assertThat(companyDelete).isNotNull();
        assertThat(companyDelete.getId()).isEqualTo(null);
    }
}
