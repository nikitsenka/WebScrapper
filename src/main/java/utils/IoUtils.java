package utils;

import java.net.URISyntaxException;


public final class IoUtils {
    private IoUtils() {
    }

    /**
     * Get full file path using class path
     * @param  path
     * @return full path
     * @throws java.net.URISyntaxException
     */
    public static String getFullPath(String path) throws URISyntaxException {
        return IoUtils.class.getResource(path).toURI().getPath();
    }

}
