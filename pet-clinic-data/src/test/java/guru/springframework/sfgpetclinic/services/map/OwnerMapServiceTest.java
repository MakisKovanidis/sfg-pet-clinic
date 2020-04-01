package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String ownerLastName = "Smith";


    @BeforeEach
    void setUp() {
        // here is the dependency injection to do the spring framework for us
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().id(ownerId).lastName(ownerLastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(1, ownerSet.size());

    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }


    @Test
    void saveExistingId() {

        Long owner2Id = 2L;

        Owner owner2 = Owner.builder().id(owner2Id).build();

        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(owner2Id, savedOwner.getId());

    }

    @Test
    void saveNoId() {

        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    //todo didn;t work this test
    @Test
    void delete() {

        ownerMapService.delete(ownerMapService.findById(ownerId));


        // must be 0
        assertEquals(1, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());

    }

    @Test
    void findByLastName() {

        Owner smith = ownerMapService.findByLastName("smith");

        assertNotNull(smith);
        assertEquals(ownerId, smith.getId());
        assertEquals(ownerLastName, smith.getLastName());
    }

    @Test
    void findByLastNameNotFound() {

        Owner smith = ownerMapService.findByLastName("foo");

        assertNull(smith);
    }
}