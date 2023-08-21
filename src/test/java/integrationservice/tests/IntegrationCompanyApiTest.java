package integrationservice.tests;

import helpers.ListInfo;
import helpers.Ref;
import integrationservice.model.Company;
import io.qameta.allure.Description;
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
        ListInfo<Company> companyList= getCompanyList();
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
        ListInfo<Company> company= getCompany("q","{\"$and\": [{\"name\":\"ИП Романова Наталия Ювеналиевна\"}]}");
        assertThat(company).isNotNull();
    }
    @Description("Get one company")
    @Test
    void successGetCompany() {
        ListInfo<Company> company= getCompany("limit","1");
        assertThat(company).isNotNull();
    }
    @Description("Create company")
    @Test
    void successCreateCompany() {
        name=name + java.time.LocalDateTime.now();
        Company company= createCompany(name,currency);
        assertThat(company.getName()).isEqualTo(name);
        assertThat(company.getBaseCurrency().getId()).isEqualTo(currency.getId());
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
    @Description("Edit company by currency")
    @Test
    void successChangeCompanyCurrency() {
        // create
        name=name + java.time.LocalDateTime.now();
        Company company= createCompany(name,currency);
        //edit by id
        Ref currencyChange= new Ref("Currency",840);
        Company companyEdit= changeCompanyCurrency(company.getId(),currencyChange);
        assertThat(companyEdit.getName()).isEqualTo(nameChange);
        assertThat(companyEdit.getChangedAt()).isNotNull();
        assertThat(companyEdit.getChangedByUser()).isNotNull();
        assertThat(company.getBaseCurrency().getId()).isEqualTo(currencyChange.getId());
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
