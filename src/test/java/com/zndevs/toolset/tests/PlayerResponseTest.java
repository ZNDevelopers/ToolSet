package com.zndevs.toolset.tests;

import com.zndevs.toolset.tools.CommandResponse;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the CommandResponse class
 */

public class PlayerResponseTest {

    // Default message tests
    @Test
    public void defaultPlayerMessage() {
        CommandResponse commandResponse = new CommandResponse();
        Assert.assertNull("Should be null as it hasn't been set yet.", commandResponse.getPlayerMessage());
        Assert.assertFalse("Should be false because it hasn't been set yet.", commandResponse.usePlayerMessage());
    }

    @Test
    public void defaultConsoleMessage() {
        CommandResponse commandResponse = new CommandResponse();
        Assert.assertNull("Should be null as it hasn't been set yet.", commandResponse.getConsoleMessage());
        Assert.assertFalse("Should be false because it hasn't been set yet.", commandResponse.useConsoleMessage());
    }

    @Test
    public void defaultBroadcastMessage() {
        CommandResponse commandResponse = new CommandResponse();
        Assert.assertNull("Should be null as it hasn't been set yet.", commandResponse.getBroadcastMessage());
        Assert.assertFalse("Should be false because it hasn't been set yet.", commandResponse.useBroadcastMessage());
    }

    // Has-been-set message tests (no colours)
    @Test
    public void setPlayerMessage() {
        CommandResponse commandResponse = new CommandResponse();
        commandResponse.setPlayerMessage("Test message");
        Assert.assertEquals("Should be 'Test message' as it has been set.", "Test message", commandResponse.getPlayerMessage());
        Assert.assertTrue("Should be true because it has been set.", commandResponse.usePlayerMessage());
    }

    @Test
    public void setBroadcastMessage() {
        CommandResponse commandResponse = new CommandResponse();
        commandResponse.setBroadcastMessage("Test message");
        Assert.assertEquals("Should be 'Test message' as it has been set.", "Test message", commandResponse.getBroadcastMessage());
        Assert.assertTrue("Should be true because it has been set.", commandResponse.useBroadcastMessage());
    }

    @Test
    public void setConsoleMessage() {
        CommandResponse commandResponse = new CommandResponse();
        commandResponse.setConsoleMessage("Test message");
        Assert.assertEquals("Should be 'Test message' as it has been set.", "Test message", commandResponse.getConsoleMessage());
        Assert.assertTrue("Should be true because it has been set.", commandResponse.useConsoleMessage());
    }

    // Has-been-set message tests (with colours)
    @Test
    public void setPlayerMessageColour() {
        CommandResponse commandResponse = new CommandResponse();
        commandResponse.setPlayerMessage("Test &3Message");
        Assert.assertEquals(commandResponse.getPlayerMessage(), CommandResponse.parseColours("Test &3Message"));
    }

    @Test
    public void setBroadcastMessageColour() {
        CommandResponse commandResponse = new CommandResponse();
        commandResponse.setBroadcastMessage("Test &3Message");
        Assert.assertEquals(commandResponse.getBroadcastMessage(), CommandResponse.parseColours("Test &3Message"));
    }

    @Test
    public void setConsoleMessageColour() {
        CommandResponse commandResponse = new CommandResponse();
        commandResponse.setConsoleMessage("Test &3Message");
        Assert.assertEquals(commandResponse.getConsoleMessage(), CommandResponse.parseColours("Test &3Message"));
    }

}
