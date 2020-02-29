package rest;

import dto.PersonDTO;
import entities.Address;
import entities.Person;
import exceptions.MissingInputException;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonRessourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Person r1, r2, r3;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.CREATE);

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() throws MissingInputException {
        EntityManager em = emf.createEntityManager();
        try {
            r1 = new Person("Allan", "Simonsen", "34567890", new Date(), new Date(), new Address("Søborg", "2860", "kagsåkollegiet 35"));
            r2 = new Person("Niels", "Simonsen", "45678", new Date(), new Date(), new Address("Nexo", "3730", "Stormgade 11"));
            r3 = new Person("Pia", "Simonsen", "0987654", new Date(), new Date(), new Address("Nexo", "3730", "Sotrmgade 11b"));
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.persist(r1);
            em.persist(r2);
            em.persist(r3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/persons").then().statusCode(200);
    }

    @Test
    public void testGetPerson() throws Exception {
        given()
                .contentType("application/json")
                .get("/persons/" + r1.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("fName", equalTo("Allan"))
                .body("lName", equalTo("Simonsen"))
                .body("phone", equalTo("34567890"));
    }

    @Test
    public void testGetPersonFail() throws Exception {
        given()
                .contentType("application/json")
                .get("/persons/" + 0).then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode())
                .body("code", equalTo(404))
                .body("message", equalTo("Person does not exist"));
    }

    @Test
    public void testGetPersons() throws Exception {
        given()
                .contentType("application/json")
                .get("/persons/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("all", hasSize(3));
    }

    @Test
    public void testAddPerson() throws Exception {
        r1.setfName("TOBIAS");
        r1.setlName("MONSTER");
        r1.setPhone("NIGgaA");
        given().contentType(ContentType.JSON)
                .body(new PersonDTO(r1))
                .when()
                .post("/persons/add")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("fName", equalTo(r1.getfName()))
                .body("lName", equalTo(r1.getlName()))
                .body("phone", equalTo(r1.getPhone()));
    }

    
    public void testAddPersonFail() throws Exception {
        r1.setfName(null);
        r1.setlName("MONSTER");
        r1.setPhone("NIGgaA");
        given().contentType(ContentType.JSON)
                .body(new PersonDTO(r1))
                .when()
                .post("/persons/add")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode())
                .body("code", equalTo(404))
                .body("message", equalTo("Missing input"));
    }

    @Test
    public void testDeletePerson() throws Exception {
        given().contentType(ContentType.JSON)
                .when()
                .delete("/persons/delete/" + r1.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("fName", equalTo(r1.getfName()))
                .body("lName", equalTo(r1.getlName()))
                .body("phone", equalTo(r1.getPhone()));
    }

    @Test
    public void testDeletePersonFail() throws Exception {
        given().contentType(ContentType.JSON)
                .when()
                .delete("/persons/delete/" + 0)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode())
                .body("code", equalTo(404))
                .body("message", equalTo("Person does not exist"));
    }

    @Test
    public void testEditPerson() throws Exception {
        r1.setfName("TOBIAS");
        r1.setlName("MONSTER");
        r1.setPhone("NIGgaA");
        given().contentType(ContentType.JSON)
                .body(new PersonDTO(r1))
                .when()
                .put("/persons/edit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("fName", equalTo(r1.getfName()))
                .body("lName", equalTo(r1.getlName()))
                .body("phone", equalTo(r1.getPhone()));
    }

    public void testEditPersonFail() throws Exception {
        r1.setfName("TOBIAS");
        r1.setlName("MONSTER");
        r1.setPhone("NIGgaA");
        r1.setId(0L);
        given().contentType(ContentType.JSON)
                .body(new PersonDTO(r1))
                .when()
                .put("/persons/edit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode())
                .body("code", equalTo(404))
                .body("message", equalTo("Person does not exist"));
    }

    @Test
    public void testEditPersonFail2() throws Exception {
        r1.setfName(null);
        r1.setlName("MONSTER");
        r1.setPhone("NIGgaA");
        given().contentType(ContentType.JSON)
                .body(new PersonDTO(r1))
                .when()
                .put("/persons/edit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode())
                .body("code", equalTo(404))
                .body("message", equalTo("Missing input"));
    }
}
