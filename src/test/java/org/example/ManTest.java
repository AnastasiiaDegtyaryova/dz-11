package org.example;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManTest {

    private Man man;
    private Woman woman;

    @BeforeMethod
    public void setUp() {
        man = new Man("John", "Doe", 70, 100000);
        woman = new Woman("Jane", "Doe", 65, 80000);
    }

    @Test
    public void testIsRetired() {
        Assert.assertTrue(man.isRetired(), "Expected man to be retired");
    }

    @Test
    public void testRegisterPartnership() {
        man.registerPartnership(woman);
        Assert.assertEquals(man.getPartner(), woman, "Partner should be set correctly");
        Assert.assertEquals(woman.getLastName(), "Doe", "Last name should be set to man's last name");
        Assert.assertEquals(woman.getPartner(), man, "Partner reference in woman should point to man");
    }

    @Test
    public void testDeregisterPartnership() {
        man.registerPartnership(woman);
        man.deregisterPartnership(true); // Revert last name
        Assert.assertNull(man.getPartner(), "Partner should be null after deregistering partnership");
        Assert.assertEquals(woman.getLastName(), "Doe", "Last name should be reverted back to maiden name");
    }

    @Test
    public void testDividePossessions() {
        man.registerPartnership(woman);
        man.dividePossessions();
        Assert.assertEquals(man.getPossessions(), 90000.0, "Man's possessions should be divided correctly");
        Assert.assertEquals(woman.getPossessions(), 90000.0, "Woman's possessions should be divided correctly");
    }
}

