package runtime;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class LoadConfig {

    private static final String propPath =  Thread.currentThread().getContextClassLoader().
            getResource("properties/" + System.getenv("env") + ".properties").getPath();

    public LoadConfig() {}

    public static String loadEnvProp(String key) {
        return loadPropByFile(propPath, key);
    }

    public static String loadPropByFile(String fileDir, String key) {
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(fileDir);
            prop.load(new InputStreamReader(is, StandardCharsets.UTF_8));
        } catch (Exception e) {
        } finally {
            if (is != null) try {
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return prop.getProperty(key);
    }

    public static void main(String args[]) {
        System.out.println("Testing variables");
        // System.out.println(System.getProperty("user.dir"));
        System.out.println(LoadConfig.loadEnvProp("chrome.driver"));
    }
}
