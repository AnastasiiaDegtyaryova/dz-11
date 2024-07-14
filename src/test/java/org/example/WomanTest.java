package org.example;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WomanTest {

    private Woman woman;
    private Man man;

    @BeforeMethod
    public void setUp() {
        woman = new Woman("Jane", "Smith", 65, 80000);
        man = new Man("John", "Doe", 70, 100000);
    }

    @Test
    public void testIsRetired() {
        Assert.assertTrue(woman.isRetired(), "Expected woman to be retired");
    }

    @Test
    public void testRegisterPartnership() {
        woman.registerPartnership(man);
        Assert.assertEquals(woman.getPartner(), man, "Partner should be set correctly");
        Assert.assertEquals(woman.getLastName(), "Doe", "Last name should be set to man's last name");
        Assert.assertEquals(man.getPartner(), woman, "Partner reference in man should point to woman");
    }

    @Test
    public void testDeregisterPartnership() {
        woman.registerPartnership(man);
        woman.deregisterPartnership(true); // Revert last name
        Assert.assertNull(woman.getPartner(), "Partner should be null after deregistering partnership");
        Assert.assertEquals(woman.getLastName(), "Smith", "Last name should be reverted back to maiden name");
    }

    @Test
    public void testDividePossessions() {
        woman.registerPartnership(man);
        woman.dividePossessions();
        Assert.assertEquals(woman.getPossessions(), 90000.0, "Woman's possessions should be divided correctly");
        Assert.assertEquals(man.getPossessions(), 90000.0, "Man's possessions should be divided correctly");
    }
}

