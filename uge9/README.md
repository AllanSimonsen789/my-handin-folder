
# --------TUESDAY---------------
## OPGAVE: Exercises JPA Relations

### Collections of basic types

#Task 1 
-Completed
-Can be seen in jpa_relations project folder. Under src -> main -> java -> entities -> customer.java

#Task 2
-Completed
-Can be seen in jpa_relations project folder. Under src -> main -> java -> entities -> customer.java

#Task 3
-Completed
-Can be seen in jpa_relations project folder. Under src -> main -> java -> entities -> tester.java
-What we see before we add the @ElementCollection annotations is a "blob" field in the customer tabler.
After the annotation is put on we see a seperate table with hobbies with FK to customer, much better!

#Task 4 (red)
-Completed
-Can be seen in jpa_relations project folder. Under src -> main -> java -> entities -> customer.java
-We use the @CollectionTable and @Column annotations to set the names in the new hobbies table so that the table have the name "Hobbies" and the column in the hobbies table is "hobby"
@CollectionTable(
            name = "Hobbies",
            joinColumns = @JoinColumn(name = "CustomerID")
    )
    @Column(name = "Hobby")


## Maps of Basic Types

#Task(red)
-Completed
-Can be seen in jpa_relations project folder. Under src -> main -> java -> entities -> customer.java and tester.java
-What we see is a new table called customer_PHONES with a FK column to customer table and a column called "description" and a column called "Phone"
 this is what we expect and want from a java map in the DB


### JPA Entity Mappings

#Task 1
-Completed
-Can be seen in JPA_Relationships project folder. Under src -> main -> java -> entities -> customer.java
-we can see that the simple onetoOne unidirectional creates two tables in the db, one with the customer and one for the addresses. the FK is in a column in the address table.
-It only requires a @OneToOne annotation on the owning class

#Task 2
-Completed
-Can be seen in JPA_Relationships project folder. Under src -> main -> java -> entities -> customer.java and Address.java
-OneToOne BiDirectional requires both classes to have the @OneToOne Annotation however it also requires the mappedBy parameter to define which table should have the FK
-in the DB we see the same as the previous task. two tables one for customer, one for addresses. the address table have the FK in a column to Customer. the reason why both tables dont have a 
fk to eachother is that its not considered good DB design.

#Task 3
-Completed
-Can be seen in JPA_Relationships project folder. Under src -> main -> java -> entities -> customer.java
-OneToMany Unidirectional requires a single @OneToMany annotation on the customer side and creates 3 tables. one table for the customer one for the address and a join table that
maps the IDs from each of the two other tables. both are foreign keys to their respective tables.

#Task 4
-Completed
-Can be seen in JPA_Relationships project folder. Under src -> main -> java -> entities -> customer.java & Address.java
-OneToMany Bidirectional requires the customer class to have the @OneToMany annotation with mappedBy parameter on it, also address need the @ManyToOne annotation. what we see
is two tables. The Customer table and the address table, the address table has a customer id field with FK to the customer it belongs to. We use the mappedBy annotation on customer class to tell that the 
address should have the FK. this is to avoid double relation between the two tables.

#Task 5
#a
-Completed
-Can be seen in JPA_Relationships project folder. Under src -> main -> java -> entities -> customer.java & Address.java & the main class in Tester.java
-@ManyToMany Bidirectional requires the @ManyToMany annotation in the customer class and also the cascade parameter that allows it to persist the addresses.  in my code i also
used som jointable annotations to make the join table look nicer. The Address class also need @ManyToMany annotation and mappedBy parameter to the customer class. both classes have methods to
insert customer and addresses respectively into their lists. in the DB 3 tabels are created. 1 for customer, 1 for addresses, and last is the join tables with 2 FK to each of the
before mentioned tables. The join tables is used to map the many to many relation. In the tester class we can see that customer and addresses are persisted seperately and afterward the addresses are added to 
the customer. The add method in the customer class also adds itself (the object reference to itself) in to the address.This is how we make the manyToMany relation
#B
-Completed
-Can be seen in JPA_Relationships project folder. Under src -> main -> java -> entities -> CustomerFacade.java
-The methods can be seen the class mentioned above. The methods are also tested in the Tester.java class.

# --------Wednesday---------------

## Opgave REST with JAX RS

### The facade and the matching endpoints

#TASK 1
-Completed
-project is in uge9 -> restwithjaxrsthursday

#TASK 2
-Completed
-used startcode databade. the code with automatically make the tables.

#TASK 3
-Completed
-Facade: uge9->restwithjaxrsthursday->src->main->java->interfaces->IPersonFacade
-Implementation: uge9->restwithjaxrsthursday->src->main->java->facade->PersonFacade
-facade test: uge9->restwithjaxrsthursday->src->test->java->facades->PersonFacadeTest

#TASK 4
-Completed
-the rest endpoint can be seen in uge9->restwithjaxrsthursday->src->main->java->rest->PersonRessource.java

#TASK 5
-Completed
-the rest endpoint can be seen in uge9->restwithjaxrsthursday->src->main->java->rest->PersonRessource.java

#TASK 6
-Completed
-the rest endpoint can be seen in uge9->restwithjaxrsthursday->src->main->java->rest->PersonRessource.java

#TASK 7
-Completed
-the rest endpoint can be seen in uge9->restwithjaxrsthursday->src->main->java->rest->PersonRessource.java

### Rest Assured Tests

#TASK(red)
-Completed
-Made test for all endpoints(the class also contains test for exception for a later task)
-The test can be seen here uge9->restwithjaxrsthursday->src->test->java->rest->PersonRessourceTest.java'

### Error Handling with JAX RS and ExceptionMappers

#Task 1
-Completed
-class can be seen in uge9->restwithjaxrsthursday->src->main->java->exceptions->PersonNotFoundException


#Task 2
-Completed
-ExceptionMappers can be seen in uge9->restwithjaxrsthursday->src->main->java->exceptions
-Facade: uge9->restwithjaxrsthursday->src->main->java->interfaces->IPersonFacade
-Implementation: uge9->restwithjaxrsthursday->src->main->java->facade->PersonFacade


#Task 3
-Completed
-Test of the points shows the exception handling is working: uge9->restwithjaxrsthursday->src->test->java->rest->PersonRessourceTest.java'


### Additional Error Responses

#TASK
-ExceptionMappers can be seen in uge9->restwithjaxrsthursday->src->main->java->exceptions
-Test of the points shows the exception handling is working: uge9->restwithjaxrsthursday->src->test->java->rest->PersonRessourceTest.java'


### Entity Classes with relations
-NOTICE NEW PROJECT PATH

#Task 1
-completed
-The address and person entity can be seen here: uge9->entityclasseswithrelations->src->main->java->entities
-The personDTO class can be seen here: uge9->entityclasseswithrelations->src->main->java->dto->PersonDTO.java
They both implement @OneToOne relation with person being the owning side with the FK in the DB


#Task 2
-Completed
-we can see the cascading working by running the testDeletePerson method in the test class:
->uge9->entityclasseswithrelations->src->test->java->rest
also we can see all the test running green

# --------thursday---------------
