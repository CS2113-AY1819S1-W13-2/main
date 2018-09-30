package seedu.divelog.ui;

import static guitests.guihandles.WebViewUtil.waitUntilBrowserLoaded;
import static org.junit.Assert.assertEquals;
import static seedu.divelog.testutil.EventsUtil.postNow;
import static seedu.divelog.testutil.TypicalPersons.ALICE;
import static seedu.divelog.ui.BrowserPanel.DEFAULT_PAGE;
import static seedu.divelog.ui.UiPart.FXML_FILE_FOLDER;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.BrowserPanelHandle;
import seedu.divelog.MainApp;
import seedu.divelog.commons.events.ui.DivePanelSelectionChangedEvent;

public class BrowserPanelTest extends GuiUnitTest {
    private DivePanelSelectionChangedEvent selectionChangedEventStub;

    private BrowserPanel browserPanel;
    private BrowserPanelHandle browserPanelHandle;

    @Before
    public void setUp() {
        selectionChangedEventStub = new DivePanelSelectionChangedEvent(ALICE);

        guiRobot.interact(() -> browserPanel = new BrowserPanel());
        uiPartRule.setUiPart(browserPanel);

        browserPanelHandle = new BrowserPanelHandle(browserPanel.getRoot());
    }

    @Test
    public void display() throws Exception {
        // default web page
        URL expectedDefaultPageUrl = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        assertEquals(expectedDefaultPageUrl, browserPanelHandle.getLoadedUrl());

        // associated web page of a person
        postNow(selectionChangedEventStub);
        URL expectedPersonUrl = new URL(BrowserPanel.SEARCH_PAGE_URL + ALICE.getName().fullName.replaceAll(" ", "%20"));

        waitUntilBrowserLoaded(browserPanelHandle);
        assertEquals(expectedPersonUrl, browserPanelHandle.getLoadedUrl());
    }
}
