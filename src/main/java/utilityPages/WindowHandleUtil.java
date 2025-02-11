package utilityPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.util.Iterator;
import java.util.Set;

public class WindowHandleUtil {
       WebDriver driver;

        public WindowHandleUtil(WebDriver driver)
        {
            super();
            this.driver = driver;
        }

        // extract all the window id
        public Set<String> getAllWindowHandles() {
            return driver.getWindowHandles();
        }

        // get the current windowid
        public String getCurrentWindowHandle() {
            return driver.getWindowHandle();
        }

        // open new tab and return windowHandle id
        public String openNewTabAndGetHandle() {
            // Get the parent window handle
            String parentWindowId = getCurrentWindowHandle();

            // Open a new tab
            driver.switchTo().newWindow(WindowType.TAB);

            // Get all window handles
            Set<String> handles = getAllWindowHandles();
            Iterator<String> it = handles.iterator();

            String childWindowId = null;

            // Iterate through handles to find the new window handle
            while (it.hasNext()) {
                String handle = it.next();
                if (!handle.equals(parentWindowId)) {
                    childWindowId = handle;
                    break;
                }
            }

            return childWindowId;
        }


        public void switchToWindow(String handle) {
            driver.switchTo().window(handle);
        }

    // Retrive both child and parent tab ID
    public String[] windowHandle() {
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        String parentWindowId = null;
        String childWindowId = null;

        if (it.hasNext()) {
            parentWindowId = it.next();
        }

        if (it.hasNext()) {
            childWindowId = it.next();
        }

        return new String[]{parentWindowId, childWindowId};
    }

}


